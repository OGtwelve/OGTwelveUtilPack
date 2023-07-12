package cn.com.ogtwelve.service;

import cn.com.ogtwelve.entity.Billing;
import cn.com.ogtwelve.entity.OpenAI;
import cn.com.ogtwelve.entity.Subscription;
import cn.com.ogtwelve.enums.*;
import cn.com.ogtwelve.exceptions.ChatGPTException;
import cn.com.ogtwelve.utils.GsonUtil;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import retrofit2.Retrofit;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 11:39
 * @Description: TODO
 */
public class OpenAIService extends OpenAiService {
    private static final Log LOG = LogFactory.getLog(OpenAIService.class);
    private static final String BASE_URL = "https://api.openai.com";
    private static final Random RANDOM = new Random();
    private final OpenAI openAI;
    private final OkHttpClient client;
    private Cache<String, LinkedList<ChatMessage>> cache;

    public OpenAIService(OpenAI openAI, Duration timeout) {
        super(buildApi(openAI.getToken(), timeout, openAI.getProxyHost(), openAI.getProxyPort()), defaultClient(openAI.getToken(), timeout, openAI.getProxyHost(), openAI.getProxyPort()).dispatcher().executorService());
        this.openAI = openAI;
        this.cache = openAI.getSessionExpirationTime() == null ? CacheBuilder.newBuilder().build() : CacheBuilder.newBuilder().expireAfterAccess((long)openAI.getSessionExpirationTime(), TimeUnit.MINUTES).build();
        this.client = defaultClient(openAI.getToken(), timeout, openAI.getProxyHost(), openAI.getProxyPort());
    }

    public OpenAIService(OpenAI openAI) {
        this(openAI, Duration.ZERO);
    }

    public static OpenAiApi buildApi(String token, Duration timeout, String proxyHost, int proxyPort) {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(token, timeout, proxyHost, proxyPort);
        Retrofit retrofit = defaultRetrofit(client, mapper);
        return (OpenAiApi)retrofit.create(OpenAiApi.class);
    }

    public static OkHttpClient defaultClient(String token, Duration timeout, String proxyHost, int proxyPort) {
        if (Strings.isNullOrEmpty(proxyHost)) {
            return OpenAiService.defaultClient(token, timeout);
        } else {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            return OpenAiService.defaultClient(token, timeout).newBuilder().proxy(proxy).build();
        }
    }

    public void createStreamChatCompletion(String content) {
        this.createStreamChatCompletion(content, "DEFAULT USER", System.out);
    }

    public void createStreamChatCompletion(String content, OutputStream os) {
        this.createStreamChatCompletion(content, "DEFAULT USER", os);
    }

    public void createStreamChatCompletion(String content, String user, OutputStream os) {
        this.createStreamChatCompletion(content, user, this.openAI.getChatModel(), os);
    }

    public void createStreamChatCompletion(String content, String user, String model, OutputStream os) {
        this.createStreamChatCompletion(RoleEnum.USER.getRoleName(), content, user, model, 1.0, 1.0, os);
    }

    public void createStreamChatCompletion(String role, String content, String user, String model, Double temperature, Double topP, OutputStream os) {
        this.createStreamChatCompletion(ChatCompletionRequest.builder().model(model).messages(Collections.singletonList(new ChatMessage(role, content))).user(user).temperature(temperature).topP(topP).stream(true).build(), os);
    }

    public void createStreamChatCompletion(ChatCompletionRequest chatCompletionRequest, OutputStream os) {
        chatCompletionRequest.setStream(true);
        chatCompletionRequest.setN(1);
        String user = chatCompletionRequest.getUser();
        LinkedList<ChatMessage> contextInfo = new LinkedList();

        try {
            contextInfo = (LinkedList)this.cache.get(user, LinkedList::new);
        } catch (ExecutionException var13) {
            var13.printStackTrace();
        }

        contextInfo.addAll(chatCompletionRequest.getMessages());
        chatCompletionRequest.setMessages(contextInfo);
        List<ChatCompletionChunk> chunks = new ArrayList();
        int i = 0;

        while(i < this.openAI.getRetries()) {
            try {
                if (i > 0) {
                    this.randomSleep();
                }

                super.streamChatCompletion(chatCompletionRequest).doOnError(Throwable::printStackTrace).blockingForEach((chunk) -> {
                    chunk.getChoices().stream().map((choice) -> {
                        return choice.getMessage().getContent();
                    }).filter(Objects::nonNull).findFirst().ifPresent((o) -> {
                        try {
                            os.write(o.getBytes(Charset.defaultCharset()));
                            os.flush();
                        } catch (Exception var3) {
                            throw new RuntimeException(var3);
                        }
                    });
                    chunks.add(chunk);
                });
                os.close();
                break;
            } catch (Exception var14) {
                String message = var14.getMessage();
                boolean overload = checkTokenUsage(message);
                if (overload) {
                    int size = ((LinkedList)Objects.requireNonNull(this.cache.getIfPresent(user))).size();

                    for(int j = 0; j < size / 2; ++j) {
                        ((LinkedList)Objects.requireNonNull(this.cache.getIfPresent(user))).removeFirst();
                    }

                    chatCompletionRequest.setMessages((List)this.cache.getIfPresent(user));
                }

                LOG.error("answer failed " + (i + 1) + " times, the error message is: " + message);
                if (i == this.openAI.getRetries() - 1) {
                    var14.printStackTrace();
                    throw new ChatGPTException(ChatGPTErrorEnum.FAILED_TO_GENERATE_ANSWER, new Object[]{message});
                }

                ++i;
            }
        }

        LinkedList<ChatMessage> chatMessages = new LinkedList();

        try {
            chatMessages = (LinkedList)this.cache.get(user, LinkedList::new);
        } catch (ExecutionException var12) {
            var12.printStackTrace();
        }

        chatMessages.add(new ChatMessage(RoleEnum.ASSISTANT.getRoleName(), (String)chunks.stream().flatMap((chunk) -> {
            return chunk.getChoices().stream();
        }).map(ChatCompletionChoice::getMessage).map(ChatMessage::getContent).filter(Objects::nonNull).collect(Collectors.joining())));
    }

    public List<String> chatCompletion(String content) {
        return this.chatCompletion(content, "DEFAULT USER");
    }

    public List<String> chatCompletion(String content, String user) {
        return this.chatCompletion(content, user, this.openAI.getChatModel());
    }

    public List<String> chatCompletion(String content, String user, String model) {
        return this.chatCompletion(RoleEnum.USER.getRoleName(), content, user, model, 1.0, 1.0);
    }

    public List<String> chatCompletion(String role, String content, String user, String model, Double temperature, Double topP) {
        return this.chatCompletion(ChatCompletionRequest.builder().model(model).messages(Collections.singletonList(new ChatMessage(role, content))).user(user).temperature(temperature).topP(topP).build());
    }

    public List<String> chatCompletion(ChatCompletionRequest chatCompletionRequest) {
        String user = chatCompletionRequest.getUser();
        LinkedList<ChatMessage> contextInfo = new LinkedList();

        try {
            contextInfo = (LinkedList)this.cache.get(user, LinkedList::new);
        } catch (ExecutionException var12) {
            var12.printStackTrace();
        }

        contextInfo.addAll(chatCompletionRequest.getMessages());
        chatCompletionRequest.setMessages(contextInfo);
        List<ChatCompletionChoice> choices = new ArrayList();
        int i = 0;

        while(i < this.openAI.getRetries()) {
            try {
                if (i > 0) {
                    this.randomSleep();
                }

                choices = super.createChatCompletion(chatCompletionRequest).getChoices();
                break;
            } catch (Exception var13) {
                String message = var13.getMessage();
                boolean overload = checkTokenUsage(message);
                if (overload) {
                    int size = ((LinkedList)Objects.requireNonNull(this.cache.getIfPresent(user))).size();

                    for(int j = 0; j < size / 2; ++j) {
                        ((LinkedList)Objects.requireNonNull(this.cache.getIfPresent(user))).removeFirst();
                    }

                    chatCompletionRequest.setMessages((List)this.cache.getIfPresent(user));
                }

                LOG.error("answer failed " + (i + 1) + " times, the error message is: " + message);
                if (i == this.openAI.getRetries() - 1) {
                    var13.printStackTrace();
                    throw new ChatGPTException(ChatGPTErrorEnum.FAILED_TO_GENERATE_ANSWER, new Object[]{message});
                }

                ++i;
            }
        }

        List<String> results = new ArrayList();
        LinkedList<ChatMessage> chatMessages = new LinkedList();

        try {
            chatMessages = (LinkedList)this.cache.get(user, LinkedList::new);
        } catch (ExecutionException var11) {
            var11.printStackTrace();
        }

        ChatCompletionChoice choice;
        for(Iterator var15 = ((List)choices).iterator(); var15.hasNext(); chatMessages.add(choice.getMessage())) {
            choice = (ChatCompletionChoice)var15.next();
            String text = choice.getMessage().getContent();
            results.add(text);
            if (FinishReasonEnum.LENGTH.getMessage().equals(choice.getFinishReason())) {
                results.add("答案过长，请输入继续~");
            }
        }

        return results;
    }

    public List<String> createImages(String prompt) {
        return this.createImages(prompt, "DEFAULT USER");
    }

    public List<String> createImages(String prompt, String user) {
        return this.createImages(CreateImageRequest.builder().prompt(prompt).user(user).build());
    }

    public List<String> createImages(CreateImageRequest createImageRequest) {
        List<Image> imageList = new ArrayList<>();
        int i = 0;

        while(i < this.openAI.getRetries()) {
            try {
                if (i > 0) {
                    this.randomSleep();
                }

                imageList = super.createImage(createImageRequest).getData();
                break;
            } catch (Exception var5) {
                LOG.error("image generate failed " + (i + 1) + " times, the error message is: " + var5.getMessage());
                if (i == this.openAI.getRetries() - 1) {
                    var5.printStackTrace();
                    throw new ChatGPTException(ChatGPTErrorEnum.FAILED_TO_GENERATE_IMAGE, var5.getMessage());
                }

                ++i;
            }
        }

        String responseFormat = createImageRequest.getResponseFormat();
        return ((List<Image>)imageList).stream().map((Image image) -> {
            return responseFormat != null && !ImageResponseFormatEnum.URL.getResponseFormat().equals(responseFormat) ? image.getB64Json() : image.getUrl();
        }).collect(Collectors.toList());
    }

    public void downloadImage(String prompt, HttpServletResponse response) {
        this.downloadImage(prompt, ImageSizeEnum.S1024x1024.getSize(), response);
    }

    public void downloadImage(String prompt, Integer n, HttpServletResponse response) {
        this.downloadImage(prompt, n, ImageSizeEnum.S1024x1024.getSize(), response);
    }

    public void downloadImage(String prompt, String size, HttpServletResponse response) {
        this.downloadImage(prompt, 1, size, response);
    }

    public void downloadImage(String prompt, Integer n, String size, HttpServletResponse response) {
        this.downloadImage(CreateImageRequest.builder().prompt(prompt).n(n).size(size).user("DEFAULT USER").build(), response);
    }

    public void downloadImage(CreateImageRequest createImageRequest, HttpServletResponse response) {
        createImageRequest.setResponseFormat(ImageResponseFormatEnum.B64_JSON.getResponseFormat());
        if (!ImageResponseFormatEnum.B64_JSON.getResponseFormat().equals(createImageRequest.getResponseFormat())) {
            throw new ChatGPTException(ChatGPTErrorEnum.ERROR_RESPONSE_FORMAT);
        } else {
            List<String> imageList = this.createImages(createImageRequest);

            try {
                OutputStream os = response.getOutputStream();
                Throwable var5 = null;

                try {
                    if (imageList.size() == 1) {
                        response.setContentType("image/png");
                        response.setHeader("Content-Disposition", "attachment; filename=generated.png");
                        BufferedImage bufferedImage = this.getImageFromBase64((String)imageList.get(0));
                        ImageIO.write(bufferedImage, "png", os);
                    } else {
                        response.setContentType("application/zip");
                        response.setHeader("Content-Disposition", "attachment; filename=images.zip");
                        ZipOutputStream zipOut = new ZipOutputStream(os);
                        Throwable var7 = null;

                        try {
                            for(int i = 0; i < imageList.size(); ++i) {
                                BufferedImage bufferedImage = this.getImageFromBase64((String)imageList.get(i));
                                ZipEntry zipEntry = new ZipEntry("image" + (i + 1) + ".png");
                                zipOut.putNextEntry(zipEntry);
                                ImageIO.write(bufferedImage, "png", zipOut);
                                zipOut.closeEntry();
                            }
                        } catch (Throwable var34) {
                            var7 = var34;
                            throw var34;
                        } finally {
                            if (zipOut != null) {
                                if (var7 != null) {
                                    try {
                                        zipOut.close();
                                    } catch (Throwable var33) {
                                        var7.addSuppressed(var33);
                                    }
                                } else {
                                    zipOut.close();
                                }
                            }

                        }
                    }
                } catch (Throwable var36) {
                    var5 = var36;
                    throw var36;
                } finally {
                    if (os != null) {
                        if (var5 != null) {
                            try {
                                os.close();
                            } catch (Throwable var32) {
                                var5.addSuppressed(var32);
                            }
                        } else {
                            os.close();
                        }
                    }

                }

            } catch (Exception var38) {
                throw new ChatGPTException(ChatGPTErrorEnum.DOWNLOAD_IMAGE_ERROR);
            }
        }
    }

    public String billingUsage(String... startDate) {
        String start = startDate.length == 0 ? "2023-01-01" : startDate[0];
        BigDecimal totalUsage = BigDecimal.ZERO;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate endDate = LocalDate.now();
            Period threeMonth = Period.ofMonths(3);

            String left;
            String right;
            for(LocalDate nextDate = LocalDate.parse(start, formatter); nextDate.isBefore(endDate); totalUsage = totalUsage.add(new BigDecimal(this.billingUsage(left, right)))) {
                left = nextDate.format(formatter);
                nextDate = nextDate.plus(threeMonth);
                right = nextDate.format(formatter);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return totalUsage.toPlainString();
    }

    public String billingUsage(String startDate, String endDate) {
        HttpUrl.Builder urlBuildr = HttpUrl.parse("https://api.openai.com/v1/dashboard/billing/usage").newBuilder();
        urlBuildr.addQueryParameter("start_date", startDate);
        urlBuildr.addQueryParameter("end_date", endDate);
        String url = urlBuildr.build().toString();
        Request request = (new Request.Builder()).url(url).build();
        String billingUsage = "0";
        int i = 0;

        while(i < this.openAI.getRetries()) {
            try {
                Response response = this.client.newCall(request).execute();
                Throwable var9 = null;

                try {
                    if (i > 0) {
                        this.randomSleep();
                    }

                    String resStr = response.body().string();
                    JSONObject resJson = JSONObject.parseObject(resStr);
                    String cents = resJson.get("total_usage").toString();
                    billingUsage = (new BigDecimal(cents)).divide(new BigDecimal("100")).toPlainString();
                    break;
                } catch (Throwable var22) {
                    var9 = var22;
                    throw var22;
                } finally {
                    if (response != null) {
                        if (var9 != null) {
                            try {
                                response.close();
                            } catch (Throwable var21) {
                                var9.addSuppressed(var21);
                            }
                        } else {
                            response.close();
                        }
                    }

                }
            } catch (Exception var24) {
                LOG.error("query billingUsage failed " + (i + 1) + " times, the error message is: " + var24.getMessage());
                if (i == this.openAI.getRetries() - 1) {
                    var24.printStackTrace();
                    throw new ChatGPTException(ChatGPTErrorEnum.QUERY_BILLING_USAGE_ERROR, var24.getMessage());
                }

                ++i;
            }
        }

        return billingUsage;
    }

    public Billing billing(String... startDate) {
        String start = startDate.length == 0 ? "2023-01-01" : startDate[0];
        Subscription subscription = this.subscription();
        String usage = this.billingUsage(start);
        String dueDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(subscription.getAccessUntil() * 1000L));
        String total = subscription.getSystemHardLimitUsd();
        Billing billing = new Billing();
        billing.setDueDate(dueDate);
        billing.setTotal(total);
        billing.setUsage(usage);
        billing.setBalance((new BigDecimal(total)).subtract(new BigDecimal(usage)).toPlainString());
        return billing;
    }

    public Subscription subscription() {
        Request request = (new Request.Builder()).url("https://api.openai.com/v1/dashboard/billing/subscription").build();
        Subscription subscription = null;
        int i = 0;

        while(i < this.openAI.getRetries()) {
            try {
                Response response = this.client.newCall(request).execute();
                Throwable var5 = null;

                try {
                    if (i > 0) {
                        this.randomSleep();
                    }

                    assert response.body() != null;
                    String resStr = response.body().string();
                    subscription = (Subscription) JSONObject.parseObject(resStr, Subscription.class);
                    break;
                } catch (Throwable var16) {
                    var5 = var16;
                    throw var16;
                } finally {
                    if (response != null) {
                        if (var5 != null) {
                            try {
                                response.close();
                            } catch (Throwable var15) {
                                var5.addSuppressed(var15);
                            }
                        } else {
                            response.close();
                        }
                    }

                }
            } catch (Exception var18) {
                LOG.error("query billingUsage failed " + (i + 1) + " times, the error message is: " + var18.getMessage());
                if (i == this.openAI.getRetries() - 1) {
                    var18.printStackTrace();
                    throw new ChatGPTException(ChatGPTErrorEnum.QUERY_BILLING_USAGE_ERROR, var18.getMessage());
                }

                ++i;
            }
        }

        return subscription;
    }

    public void forceClearCache(String cacheName) {
        this.cache.invalidate(cacheName);
    }

    public Cache<String, LinkedList<ChatMessage>> retrieveCache() {
        return this.cache;
    }

    public LinkedList<ChatMessage> retrieveChatMessage(String key) {
        return (LinkedList)this.cache.getIfPresent(key);
    }

    public void setCache(Cache<String, LinkedList<ChatMessage>> cache) {
        this.cache = cache;
    }

    public void addCache(String key, LinkedList<ChatMessage> chatMessages) {
        this.cache.put(key, chatMessages);
    }

    private void randomSleep() throws InterruptedException {
        Thread.sleep((long)(500 + RANDOM.nextInt(200)));
    }

    private static boolean checkTokenUsage(String message) {
        return message != null && message.contains("This model's maximum context length is");
    }

    private BufferedImage getImageFromBase64(String base64) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64.getBytes());
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        Throwable var4 = null;

        BufferedImage var5;
        try {
            var5 = ImageIO.read(bis);
        } catch (Throwable var14) {
            var4 = var14;
            throw var14;
        } finally {
            if (bis != null) {
                if (var4 != null) {
                    try {
                        bis.close();
                    } catch (Throwable var13) {
                        var4.addSuppressed(var13);
                    }
                } else {
                    bis.close();
                }
            }

        }

        return var5;
    }
}