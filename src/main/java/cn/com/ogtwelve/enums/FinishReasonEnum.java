package cn.com.ogtwelve.enums;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 11:59
 */
public enum FinishReasonEnum {
    LENGTH("length"),
    STOP("stop");

    private final String message;

    private FinishReasonEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}