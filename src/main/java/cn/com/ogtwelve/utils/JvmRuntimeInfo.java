package cn.com.ogtwelve.utils;

/**
 * JVM运行时数据存储对象
 */
public class JvmRuntimeInfo {

    /**
     * jvm cpu使用率
     */
    private double cpuUsage;

    /**
     * 程序运行时长
     */
    private long runningTime;

    //线程
    /**
     * 活动线程总数
     */
    private int threadCount;

    /**
     * 峰值
     */
    private int peakThreadCount;

    /**
     * 线程总数
     */
    private long totalStartedThreadCount;

    /**
     * 守护线程总数
     */
    private int daemonThreadCount;

    //加载类信息
    /**
     * 已加载当前类
     */
    private int loadedClassCount;

    /**
     * 已加载类总数
     */
    private long totalLoadedClassCount;

    /**
     * 已卸载类总数
     */
    private long unloadedClassCount;

    //堆内存
    /**
     * 最大堆使用量
     */
    private long heapMaxMemory;

    /**
     * 最小堆使用量
     */
    private long heapMinMemory;

    /**
     * 堆使用量
     */
    private long heapUsedMemory;

    /**
     * 堆提交的内存量
     */
    private long heapCommittedMemory;

    /**
     * 获取jvm cpu 使用率
     */
    public double getCpuUsage() {
        return cpuUsage;
    }

    /**
     * 设置jvm cpu使用率
     */
    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    /**
     * 获取程序运行时长
     */
    public long getRunningTime() {
        return runningTime;
    }

    /**
     * 设置程序运行时长
     */
    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }

    /**
     * 获取活动线程总数
     */
    public int getThreadCount() {
        return threadCount;
    }

    /**
     * 设置活动线程总数
     */
    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * 获取峰值
     */
    public int getPeakThreadCount() {
        return peakThreadCount;
    }

    /**
     * 设置峰值
     */
    public void setPeakThreadCount(int peakThreadCount) {
        this.peakThreadCount = peakThreadCount;
    }

    /**
     * 获取线程总数
     */
    public long getTotalStartedThreadCount() {
        return totalStartedThreadCount;
    }

    /**
     * 设置线程总数
     */
    public void setTotalStartedThreadCount(long totalStartedThreadCount) {
        this.totalStartedThreadCount = totalStartedThreadCount;
    }

    /**
     * 获取守护线程总数
     */
    public int getDaemonThreadCount() {
        return daemonThreadCount;
    }

    /**
     * 设置守护线程总数
     */
    public void setDaemonThreadCount(int daemonThreadCount) {
        this.daemonThreadCount = daemonThreadCount;
    }

    /**
     * 获取已加载当前类数量
     */
    public int getLoadedClassCount() {
        return loadedClassCount;
    }

    /**
     * 设置已加载当前类数量
     */
    public void setLoadedClassCount(int loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    /**
     * 获取已加载类总数
     */
    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    /**
     * 设置已加载类总数
     */
    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    /**
     * 获取已卸载类总数
     */
    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    /**
     * 设置已卸载类总数
     */
    public void setUnloadedClassCount(long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }

    /**
     * 获取最大堆使用量
     */
    public double getHeapMaxMemory() {
        return heapMaxMemory;
    }

    /**
     * 设置最大堆使用量
     */
    public void setHeapMaxMemory(long heapMaxMemory) {
        this.heapMaxMemory = heapMaxMemory;
    }

    /**
     * 获取最小堆使用量
     */
    public long getHeapMinMemory() {
        return heapMinMemory;
    }

    /**
     * 设置最小堆使用量
     */
    public void setHeapMinMemory(long heapMinMemory) {
        this.heapMinMemory = heapMinMemory;
    }

    /**
     * 获取堆使用量
     */
    public long getHeapUsedMemory() {
        return heapUsedMemory;
    }

    /**
     * 设置堆使用量
     */
    public void setHeapUsedMemory(long heapUsedMemory) {
        this.heapUsedMemory = heapUsedMemory;
    }

    /**
     * 获取堆提交的内存量
     */
    public long getHeapCommittedMemory() {
        return heapCommittedMemory;
    }

    /**
     * 设置堆提交的内存量
     */
    public void setHeapCommittedMemory(long heapCommittedMemory) {
        this.heapCommittedMemory = heapCommittedMemory;
    }

}
