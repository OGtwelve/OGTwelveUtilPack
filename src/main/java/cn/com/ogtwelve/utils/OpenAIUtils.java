package cn.com.ogtwelve.utils;

import cn.com.ogtwelve.entity.Billing;
import cn.com.ogtwelve.entity.Subscription;
import cn.com.ogtwelve.enums.ImageSizeEnum;
import cn.com.ogtwelve.enums.ModelEnum;
import cn.com.ogtwelve.enums.RoleEnum;
import cn.com.ogtwelve.service.OpenAIService;
import com.google.common.cache.Cache;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.image.CreateImageRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * @Author OGtwelve
 * @Date 2023/7/12 12:03
 * @Description: openai工具类
 */
public class OpenAIUtils {
    private static final Log LOG = LogFactory.getLog(OpenAIUtils.class);
    private static OpenAIService openAIService;

    public OpenAIUtils(OpenAIService openAIService) {
        OpenAIUtils.openAIService = openAIService;
    }

    public static void createStreamChatCompletion(String content) {
        createStreamChatCompletion(content, "DEFAULT USER", System.out);
    }

    public static void createStreamChatCompletion(String content, OutputStream os) {
        createStreamChatCompletion(content, "DEFAULT USER", os);
    }

    public static void createStreamChatCompletion(String content, String user, OutputStream os) {
        openAIService.createStreamChatCompletion(content, user, os);
    }

    public static void createStreamChatCompletion(String content, String user, String model, OutputStream os) {
        createStreamChatCompletion(RoleEnum.USER.getRoleName(), content, user, model, 1.0, 1.0, os);
    }

    public static void createStreamChatCompletion(String role, String content, String user, String model, Double temperature, Double topP, OutputStream os) {
        createStreamChatCompletion(ChatCompletionRequest.builder().model(model).messages(Collections.singletonList(new ChatMessage(role, content))).user(user).temperature(temperature).topP(topP).stream(true).build(), os);
    }

    public static void createStreamChatCompletion(ChatCompletionRequest chatCompletionRequest, OutputStream os) {
        openAIService.createStreamChatCompletion(chatCompletionRequest, os);
    }

    public static List<String> createChatCompletion(String content) {
        return createChatCompletion(content, "DEFAULT USER");
    }

    public static List<String> createChatCompletion(String content, String user) {
        return openAIService.chatCompletion(content, user);
    }

    public static List<String> createChatCompletion(String content, String user, String model) {
        return createChatCompletion(RoleEnum.USER.getRoleName(), content, user, model, 1.0, 1.0);
    }

    public static List<String> createChatCompletion(String role, String content, String user, String model, Double temperature, Double topP) {
        return createChatCompletion(ChatCompletionRequest.builder().model(model).messages(Collections.singletonList(new ChatMessage(role, content))).user(user).temperature(temperature).topP(topP).build());
    }

    public static List<String> createChatCompletion(ChatCompletionRequest chatCompletionRequest) {
        return openAIService.chatCompletion(chatCompletionRequest);
    }

    public static List<String> createImage(String prompt) {
        return createImage(prompt, "DEFAULT USER");
    }

    public static List<String> createImage(String prompt, String user) {
        return createImage(CreateImageRequest.builder().prompt(prompt).user(user).build());
    }

    public static List<String> createImage(CreateImageRequest createImageRequest) {
        return openAIService.createImages(createImageRequest);
    }

    public static void downloadImage(String prompt, HttpServletResponse response) {
        downloadImage(prompt, ImageSizeEnum.S1024x1024.getSize(), response);
    }

    public static void downloadImage(String prompt, Integer n, HttpServletResponse response) {
        downloadImage(prompt, n, ImageSizeEnum.S1024x1024.getSize(), response);
    }

    public static void downloadImage(String prompt, String size, HttpServletResponse response) {
        downloadImage(prompt, 1, size, response);
    }

    public static void downloadImage(String prompt, Integer n, String size, HttpServletResponse response) {
        downloadImage(CreateImageRequest.builder().prompt(prompt).n(n).size(size).user("DEFAULT USER").build(), response);
    }

    public static void downloadImage(CreateImageRequest createImageRequest, HttpServletResponse response) {
        openAIService.downloadImage(createImageRequest, response);
    }

    public static String billingUsage(String... startDate) {
        return openAIService.billingUsage(startDate);
    }

    public static String billingUsage(String startDate, String endDate) {
        return openAIService.billingUsage(startDate, endDate);
    }

    public static Billing billing(String... startDate) {
        return openAIService.billing(startDate);
    }

    public static Subscription subscription() {
        return openAIService.subscription();
    }

    public static void forceClearCache(String cacheName) {
        openAIService.forceClearCache(cacheName);
    }

    public static Cache<String, LinkedList<ChatMessage>> retrieveCache() {
        return openAIService.retrieveCache();
    }

    public static LinkedList<ChatMessage> retrieveChatMessage(String key) {
        return openAIService.retrieveChatMessage(key);
    }

    public static void setCache(Cache<String, LinkedList<ChatMessage>> cache) {
        openAIService.setCache(cache);
    }

    public static void addCache(String key, LinkedList<ChatMessage> chatMessages) {
        openAIService.addCache(key, chatMessages);
    }
}

