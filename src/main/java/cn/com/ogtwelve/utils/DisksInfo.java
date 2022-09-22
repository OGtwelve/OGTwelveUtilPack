package cn.com.ogtwelve.utils;

/**
 * 磁盘信息封装类
 *
 * @author liuyang
 */
public class DisksInfo {

    /**
     * 文件系统的挂载点
     */
    private String dirName;

    /**
     * 文件系统名称
     */
    private String sysTypeName;

    /**
     * 文件系统的类型(FAT，NTFS，etx2，ext4等)
     */
    private String typeName;

    /**
     * 总大小
     */
    private long total;

    /**
     * 剩余大小
     */
    private long free;

    /**
     * 已经使用量
     */
    private long used;

    /**
     * 资源的使用率
     */
    private double usage = 100;

    /**
     * 获取文件系统的挂载点
     */
    public String getDirName() {
        return dirName;
    }

    /**
     * 设置文件系统的挂载点
     */
    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    /**
     * 获取文件系统名称
     */
    public String getSysTypeName() {
        return sysTypeName;
    }

    /**
     * 设置文件系统名称
     */
    public void setSysTypeName(String sysTypeName) {
        this.sysTypeName = sysTypeName;
    }

    /**
     * 获取文件系统的类型
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置文件系统的类型
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取总大小
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置总大小
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * 获取剩余大小
     */
    public long getFree() {
        return free;
    }

    /**
     * 设置剩余大小
     */
    public void setFree(long free) {
        this.free = free;
    }

    /**
     * 获取使用大小
     */
    public long getUsed() {
        return used;
    }

    /**
     * 设置使用大小
     */
    public void setUsed(long used) {
        this.used = used;
    }

    /**
     * 获取资源的使用率
     */
    public double getUsage() {
        return usage;
    }

    /**
     * 设置资源的使用率
     */
    public void setUsage(double usage) {
        this.usage = usage;
    }

}
