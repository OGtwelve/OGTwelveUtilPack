package cn.com.ogtwelve.utils;

/**
 * 客户端注册对象
 */
public class ClientRegisterBean {

    /**
     * 客户端Ip地址
     */
    private String ip;

    /**
     * 客户端端口
     */
    private String port;

    /**
     * 客户端名称
     */
    private String name;

    /**
     * 客户端描述
     */
    private String describe;

    /**
     * 所属项目分类标识
     */
    private String project;

    /**
     * 系统基本信息
     */
    private OSInfo osInfo;

    public ClientRegisterBean(String ip, String port, String name, String describe, String project, OSInfo osInfo) {
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.describe = describe;
        this.project = project;
        this.osInfo = osInfo;
    }

    /**
     * 获取Ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置Ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取端口
     */
    public String getPort() {
        return port;
    }

    /**
     * 设置端口
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * 获取客户端名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置客户端名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取项目
     */
    public String getProject() {
        return project;
    }

    /**
     * 设置项目
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * 获取系统信息
     */
    public OSInfo getOsInfo() {
        return osInfo;
    }

    /**
     * 设置系统信息对象
     */
    public void setOsInfo(OSInfo osInfo) {
        this.osInfo = osInfo;
    }
}
