package cn.com.ogtwelve.enums;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 11:58
 * @Description: 错误枚举
 */
public enum ChatGPTErrorEnum {
    FAILED_TO_GENERATE_ANSWER("10001", "generate answer error, reason is %s."),
    MODEL_SELECTION_ERROR("10002", "there is no such model!"),
    FAILED_TO_GENERATE_IMAGE("10003", "generate image error, reason is %s."),
    ERROR_RESPONSE_FORMAT("10004", "please check if the response_format is b64_json!"),
    DOWNLOAD_IMAGE_ERROR("10005", "failed to download image!"),
    QUERY_BILLING_USAGE_ERROR("10006", "query billing usage error, reason is %s.");

    private final String errorCode;
    private final String errorMessage;

    private ChatGPTErrorEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}