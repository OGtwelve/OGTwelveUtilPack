package cn.com.ogtwelve.enums;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 11:59
 * @Description: 图片大小枚举
 */
public enum ImageSizeEnum {
    S256x256("256x256"),
    S512x512("512x512"),
    S1024x1024("1024x1024");

    private final String size;

    private ImageSizeEnum(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }
}
