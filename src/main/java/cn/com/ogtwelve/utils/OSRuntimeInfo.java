package cn.com.ogtwelve.utils;

import java.util.List;

/**
 * 系统运行时信息
 *
 * @author liuyang
 */
public class OSRuntimeInfo {

    /**
     * cpu使用率
     */
    private double cpuUsage;

    /**
     * 总内存
     */
    private long totalMemory;

    /**
     * 使用内存
     */
    private long usedMemory;

    /**
     * 磁盘信息
     */
    private List<DisksInfo> disksList;

    /**
     * 网卡信息
     */
    private List<NetworkInfo> networkList;

    /**
     * 获取 cpu使用率
     */
    public double getCpuUsage() {
        return cpuUsage;
    }

    /**
     * 设置 cpu使用率
     */
    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    /**
     * 获取 总内存
     */
    public long getTotalMemory() {
        return totalMemory;
    }

    /**
     * 设置 总内存
     */
    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    /**
     * 获取 使用内存
     */
    public long getUsedMemory() {
        return usedMemory;
    }

    /**
     * 设置 使用内存
     */
    public void setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
    }

    /**
     * 获取 磁盘信息
     */
    public List<DisksInfo> getDisksList() {
        return disksList;
    }

    /**
     * 设置 磁盘信息
     */
    public void setDisksList(List<DisksInfo> disksList) {
        this.disksList = disksList;
    }

    /**
     * 获取 网卡信息
     */
    public List<NetworkInfo> getNetworkList() {
        return networkList;
    }

    /**
     * 设置 网卡信息
     */
    public void setNetworkList(List<NetworkInfo> networkList) {
        this.networkList = networkList;
    }
}
