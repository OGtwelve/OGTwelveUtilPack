package cn.com.ogtwelve.utils;

/**
 * 网卡信息包装类
 *
 * @author liuyang
 */
public class NetworkInfo {

    /**
     * ipv4地址
     */
    private String ipv4Address;

    /**
     * ipv6地址
     */
    private String ipv6Address;

    /**
     * mac地址
     */
    private String macAddress;

    /**
     * 网卡名称
     */
    private String networkName;

    /**
     * 发送数据量
     */
    private long send;

    /**
     * 接受数据量
     */
    private long accept;

    /**
     * 网卡时间戳
     */
    private long timeStamp;

    /**
     * 获取ipv4地址
     */
    public String getIpv4Address() {
        return ipv4Address;
    }

    /**
     * 设置ipv4地址
     */
    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    /**
     * 获取ipv6地址
     */
    public String getIpv6Address() {
        return ipv6Address;
    }

    /**
     * 设置ipv6地址
     */
    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    /**
     * 获取mac地址
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * 设置mac地址
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * 获取网卡名称
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * 设置网卡名称
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    /**
     * 获取发送数据量
     */
    public long getSend() {
        return send;
    }

    /**
     * 设置发送数据量
     */
    public void setSend(long send) {
        this.send = send;
    }

    /**
     * 获取接受数据量
     */
    public long getAccept() {
        return accept;
    }

    /**
     * 设置接受数据量
     */
    public void setAccept(long accept) {
        this.accept = accept;
    }

    /**
     * 获取网卡时间戳
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设置网卡时间戳
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
