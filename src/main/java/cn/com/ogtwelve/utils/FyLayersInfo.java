package cn.com.ogtwelve.utils;

/**
 * FY图层信息对象
 */
public class FyLayersInfo {

    /**
     * 卫星
     */
    private String satellite;

    /**
     * 区域
     */
    private String area;

    /**
     * 产品标识
     */
    private String productMark;

    public FyLayersInfo(String satellite, String area, String productMark) {
        this.satellite = satellite;
        this.area = area;
        this.productMark = productMark;
    }

    /**
     * 获取卫星
     */
    public String getSatellite() {
        return satellite;
    }

    /**
     * 设置卫星
     */
    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    /**
     * 获取区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区域
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取产品标识
     */
    public String getProductMark() {
        return productMark;
    }

    /**
     * 设置产品标识
     */
    public void setProductMark(String productMark) {
        this.productMark = productMark;
    }
}
