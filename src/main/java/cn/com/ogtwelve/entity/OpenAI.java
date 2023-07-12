package cn.com.ogtwelve.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 11:39
 * @Description: openai配置
 */
@ConfigurationProperties(
        prefix = "openai"
)
public class OpenAI {
    private String token;
    private String model;
    private String chatModel;
    private int retries;
    private String proxyHost;
    private int proxyPort;
    private Integer sessionExpirationTime;

    private static String $default$model() {
        return "text-davinci-003";
    }

    private static String $default$chatModel() {
        return "gpt-3.5-turbo";
    }

    private static int $default$retries() {
        return 5;
    }

    public static OpenAIBuilder builder() {
        return new OpenAIBuilder();
    }

    public String getToken() {
        return this.token;
    }

    public String getModel() {
        return this.model;
    }

    public String getChatModel() {
        return this.chatModel;
    }

    public int getRetries() {
        return this.retries;
    }

    public String getProxyHost() {
        return this.proxyHost;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public Integer getSessionExpirationTime() {
        return this.sessionExpirationTime;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setChatModel(String chatModel) {
        this.chatModel = chatModel;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setSessionExpirationTime(Integer sessionExpirationTime) {
        this.sessionExpirationTime = sessionExpirationTime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OpenAI)) {
            return false;
        } else {
            OpenAI other = (OpenAI)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getRetries() != other.getRetries()) {
                return false;
            } else if (this.getProxyPort() != other.getProxyPort()) {
                return false;
            } else {
                label76: {
                    Object this$sessionExpirationTime = this.getSessionExpirationTime();
                    Object other$sessionExpirationTime = other.getSessionExpirationTime();
                    if (this$sessionExpirationTime == null) {
                        if (other$sessionExpirationTime == null) {
                            break label76;
                        }
                    } else if (this$sessionExpirationTime.equals(other$sessionExpirationTime)) {
                        break label76;
                    }

                    return false;
                }

                Object this$token = this.getToken();
                Object other$token = other.getToken();
                if (this$token == null) {
                    if (other$token != null) {
                        return false;
                    }
                } else if (!this$token.equals(other$token)) {
                    return false;
                }

                label62: {
                    Object this$model = this.getModel();
                    Object other$model = other.getModel();
                    if (this$model == null) {
                        if (other$model == null) {
                            break label62;
                        }
                    } else if (this$model.equals(other$model)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$chatModel = this.getChatModel();
                    Object other$chatModel = other.getChatModel();
                    if (this$chatModel == null) {
                        if (other$chatModel == null) {
                            break label55;
                        }
                    } else if (this$chatModel.equals(other$chatModel)) {
                        break label55;
                    }

                    return false;
                }

                Object this$proxyHost = this.getProxyHost();
                Object other$proxyHost = other.getProxyHost();
                if (this$proxyHost == null) {
                    if (other$proxyHost != null) {
                        return false;
                    }
                } else if (!this$proxyHost.equals(other$proxyHost)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof OpenAI;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getRetries();
        result = result * 59 + this.getProxyPort();
        Object $sessionExpirationTime = this.getSessionExpirationTime();
        result = result * 59 + ($sessionExpirationTime == null ? 43 : $sessionExpirationTime.hashCode());
        Object $token = this.getToken();
        result = result * 59 + ($token == null ? 43 : $token.hashCode());
        Object $model = this.getModel();
        result = result * 59 + ($model == null ? 43 : $model.hashCode());
        Object $chatModel = this.getChatModel();
        result = result * 59 + ($chatModel == null ? 43 : $chatModel.hashCode());
        Object $proxyHost = this.getProxyHost();
        result = result * 59 + ($proxyHost == null ? 43 : $proxyHost.hashCode());
        return result;
    }

    public String toString() {
        return "OpenAI(token=" + this.getToken() + ", model=" + this.getModel() + ", chatModel=" + this.getChatModel() + ", retries=" + this.getRetries() + ", proxyHost=" + this.getProxyHost() + ", proxyPort=" + this.getProxyPort() + ", sessionExpirationTime=" + this.getSessionExpirationTime() + ")";
    }

    public OpenAI() {
        this.model = $default$model();
        this.chatModel = $default$chatModel();
        this.retries = $default$retries();
    }

    public OpenAI(String token, String model, String chatModel, int retries, String proxyHost, int proxyPort, Integer sessionExpirationTime) {
        this.token = token;
        this.model = model;
        this.chatModel = chatModel;
        this.retries = retries;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.sessionExpirationTime = sessionExpirationTime;
    }

    public static class OpenAIBuilder {
        private String token;
        private boolean model$set;
        private String model$value;
        private boolean chatModel$set;
        private String chatModel$value;
        private boolean retries$set;
        private int retries$value;
        private String proxyHost;
        private int proxyPort;
        private Integer sessionExpirationTime;

        OpenAIBuilder() {
        }

        public OpenAIBuilder token(String token) {
            this.token = token;
            return this;
        }

        public OpenAIBuilder model(String model) {
            this.model$value = model;
            this.model$set = true;
            return this;
        }

        public OpenAIBuilder chatModel(String chatModel) {
            this.chatModel$value = chatModel;
            this.chatModel$set = true;
            return this;
        }

        public OpenAIBuilder retries(int retries) {
            this.retries$value = retries;
            this.retries$set = true;
            return this;
        }

        public OpenAIBuilder proxyHost(String proxyHost) {
            this.proxyHost = proxyHost;
            return this;
        }

        public OpenAIBuilder proxyPort(int proxyPort) {
            this.proxyPort = proxyPort;
            return this;
        }

        public OpenAIBuilder sessionExpirationTime(Integer sessionExpirationTime) {
            this.sessionExpirationTime = sessionExpirationTime;
            return this;
        }

        public OpenAI build() {
            String model$value = this.model$value;
            if (!this.model$set) {
                model$value = OpenAI.$default$model();
            }

            String chatModel$value = this.chatModel$value;
            if (!this.chatModel$set) {
                chatModel$value = OpenAI.$default$chatModel();
            }

            int retries$value = this.retries$value;
            if (!this.retries$set) {
                retries$value = OpenAI.$default$retries();
            }

            return new OpenAI(this.token, model$value, chatModel$value, retries$value, this.proxyHost, this.proxyPort, this.sessionExpirationTime);
        }

        public String toString() {
            return "OpenAI.OpenAIBuilder(token=" + this.token + ", model$value=" + this.model$value + ", chatModel$value=" + this.chatModel$value + ", retries$value=" + this.retries$value + ", proxyHost=" + this.proxyHost + ", proxyPort=" + this.proxyPort + ", sessionExpirationTime=" + this.sessionExpirationTime + ")";
        }
    }
}