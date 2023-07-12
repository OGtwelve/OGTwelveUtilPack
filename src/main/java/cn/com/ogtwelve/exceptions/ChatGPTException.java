package cn.com.ogtwelve.exceptions;

import cn.com.ogtwelve.enums.ChatGPTErrorEnum;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 12:00
 * @Description: gpt异常
 */
public class ChatGPTException extends RuntimeException {
    private String errorCode = "-1";
    private String errorMessage = "";

    public ChatGPTException() {
    }

    public ChatGPTException(ChatGPTErrorEnum chatGPTErrorEnum) {
        super(chatGPTErrorEnum.getErrorMessage());
        this.errorCode = chatGPTErrorEnum.getErrorCode();
        this.errorMessage = chatGPTErrorEnum.getErrorMessage();
    }

    public ChatGPTException(ChatGPTErrorEnum chatGPTErrorEnum, Object... messages) {
        super(String.format(chatGPTErrorEnum.getErrorMessage(), messages));
        this.errorCode = chatGPTErrorEnum.getErrorCode();
        this.errorMessage = String.format(chatGPTErrorEnum.getErrorMessage(), messages);
    }

    public ChatGPTException(String errorCode, String errorMessage) {
        super(errorMessage);
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
