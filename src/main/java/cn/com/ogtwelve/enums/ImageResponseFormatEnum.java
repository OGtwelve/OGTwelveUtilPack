package cn.com.ogtwelve.enums;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 11:59
 * @Description: 图片返回枚举
 */
public enum ImageResponseFormatEnum {
    URL("url"),
    B64_JSON("b64_json");

    private final String responseFormat;

    private ImageResponseFormatEnum(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public String getResponseFormat() {
        return this.responseFormat;
    }
}