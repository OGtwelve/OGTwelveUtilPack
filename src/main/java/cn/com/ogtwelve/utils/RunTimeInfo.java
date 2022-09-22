package cn.com.ogtwelve.utils;

import java.util.Date;

/**
 * 运行数据集合
 *
 * @author liuyang
 */
public class RunTimeInfo {

    /**
     * 时间记录
     */
    private Date date;

    /**
     * Ip地址
     */
    private String ip;

    /**
     * 端口号
     */
    private String port;

    /**
     * JVM运行时信息
     */
    private JvmRuntimeInfo jvmRuntimeInfo;

    /**
     * 系统运行时信息
     */
    private OSRuntimeInfo osRuntimeInfo;

    /**
     * 获取时间记录
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置时间记录
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取Ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置Ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取端口号
     */
    public String getPort() {
        return port;
    }

    /**
     * 设置端口号
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * 获取JVM运行时信息
     */
    public JvmRuntimeInfo getJvmRuntimeInfo() {
        return jvmRuntimeInfo;
    }

    /**
     * 设置JVM运行时信息
     */
    public void setJvmRuntimeInfo(JvmRuntimeInfo jvmRuntimeInfo) {
        this.jvmRuntimeInfo = jvmRuntimeInfo;
    }

    /**
     * 获取系统运行时信息
     */
    public OSRuntimeInfo getOsRuntimeInfo() {
        return osRuntimeInfo;
    }

    /**
     * 设置系统运行时信息
     */
    public void setOsRuntimeInfo(OSRuntimeInfo osRuntimeInfo) {
        this.osRuntimeInfo = osRuntimeInfo;
    }
}
