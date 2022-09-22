package cn.com.ogtwelve.utils;

/**
 * 系统信息
 *
 * @author liuyang
 */
public class OSInfo {

    /**
     * 系统
     */
    private String os;

    /**
     * 系统架构
     */
    private String osArch;

    /**
     * java版本
     */
    private String javaVersion;

    /**
     * 工作目录
     */
    private String userDir;

    /**
     * cpu核心数
     */
    private int cpuCount;

    /**
     * 获取系统名称
     */
    public String getOs() {
        return os;
    }

    /**
     * 设置系统名称
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 获取系统架构
     */
    public String getOsArch() {
        return osArch;
    }

    /**
     * 设置系统架构
     */
    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    /**
     * 获取 java版本
     */
    public String getJavaVersion() {
        return javaVersion;
    }

    /**
     * 设置 java版本
     */
    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    /**
     * 获取 工作目录
     */
    public String getUserDir() {
        return userDir;
    }

    /**
     * 设置 工作目录
     */
    public void setUserDir(String userDir) {
        this.userDir = userDir;
    }

    /**
     * 获取 cpu核心数
     */
    public int getCpuCount() {
        return cpuCount;
    }

    /**
     * 设置 cpu核心数
     */
    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }
}
