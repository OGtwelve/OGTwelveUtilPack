package cn.com.ogtwelve.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统信息获取工具类
 * 1.系统cpu使用信息
 * 2.系统内存信息
 * 3.系统卡流量信息
 * 4.磁盘使用量信息
 *
 * @author liuyang
 */
public class SystemInfoUtil {

    private static SystemInfo systemInfo;

    private static HardwareAbstractionLayer abstractionLayer;

    private static OperatingSystem operatingSystem;

    private static CentralProcessor centralProcessor;

    private static GlobalMemory globalMemory;

    private static long[] oldTicks;

    private static Map<String, Long[]> networkInfoMap;

    private static List<NetworkIF> networkIFList;

    private static DecimalFormat df = new DecimalFormat("0.0000");

    private SystemInfoUtil() {
    }

    static {
        try {
            systemInfo = new SystemInfo();
            abstractionLayer = systemInfo.getHardware();
            operatingSystem = systemInfo.getOperatingSystem();
            centralProcessor = abstractionLayer.getProcessor();
            globalMemory = abstractionLayer.getMemory();
            oldTicks = new long[CentralProcessor.TickType.values().length];
            networkInfoMap = new ConcurrentHashMap<>();
            networkIFList = getNetwork();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    enum NetworkType {
        /**
         * 当前网卡时间戳
         */
        TIME_STAMP(0),
        /**
         * 网卡总发送量
         */
        SEND(1),
        /**
         * 网卡总接收量
         */
        ACCEPT(2);
        private int index;

        NetworkType(int value) {
            this.index = value;
        }

        public int getIndex() {
            return index;
        }
    }

    private static double getCUPUsage() {
        double d = centralProcessor.getSystemCpuLoadBetweenTicks(oldTicks);
        oldTicks = centralProcessor.getSystemCpuLoadTicks();
        return Double.parseDouble(df.format(d));
    }


    public static List<DisksInfo> getDisksList() {
        FileSystem fileSystem = operatingSystem.getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores();
        List<DisksInfo> list = new ArrayList<>();
        for (int i = 0; i < fileStores.size(); i++) {
            OSFileStore osFileStore = fileStores.get(i);
            DisksInfo disksInfo = new DisksInfo();
            disksInfo.setDirName(osFileStore.getMount());
            disksInfo.setSysTypeName(osFileStore.getName());
            disksInfo.setTypeName(osFileStore.getType());
            long total = osFileStore.getTotalSpace();
            long free = osFileStore.getFreeSpace();
            long used = total - free;
            disksInfo.setTotal(total);
            disksInfo.setFree(free);
            disksInfo.setUsed(used);
            if (total != 0) {
                disksInfo.setUsage(Double.parseDouble(df.format((double) used / total)));
            }
            list.add(disksInfo);
        }
        return list;
    }

    private static List<NetworkIF> getNetwork() {
        List<NetworkIF> list = new ArrayList<>();
        List<NetworkIF> networkIFs = abstractionLayer.getNetworkIFs();
        for (int i = 0; i < networkIFs.size(); i++) {
            NetworkIF networkIF = networkIFs.get(i);
            if (!networkIF.isKnownVmMacAddr()) {
                if (networkIF.getMacaddr() != null && networkIF.getIPv4addr().length > 0
                        && networkIF.getIPv6addr().length > 0) {
                    if (!networkInfoMap.containsKey(networkIF.getMacaddr())) {
                        networkIF.updateAttributes();
                        Long[] data = new Long[]{networkIF.getTimeStamp(), networkIF.getBytesSent(), networkIF.getBytesRecv()};
                        networkInfoMap.put(networkIF.getMacaddr(), data);
                    }
                    list.add(networkIF);
                }
            }
        }
        return list;
    }

    /**
     * 网卡实际的传输速率受多方面影响，具体如下：
     * 1、硬盘的读写（I/O）速度达不到。
     * 2、网卡本身性能差。
     * 3、交换机/（HUB）性能差。
     * 4、通讯线路条件质量差。
     * 5、网络性能差。
     * <p>
     * 一般情况下：
     * 网络条件比较好的网络利用率100Mbits的一般为：1/8(此方法默认)
     * 网络及各方面条件差的利用率一般为：1/12
     */
    public static List<NetworkInfo> getNetworkInfo() {
        networkIFList = getNetwork();
        List<NetworkInfo> list = new ArrayList<>();
        for (int i = 0; i < networkIFList.size(); i++) {
            NetworkIF networkIF = networkIFList.get(i);
            if (networkIF.updateAttributes()) {
                if (networkIF.getIPv4addr().length > 0 && networkIF.getIPv6addr().length > 0) {
                    NetworkInfo networkInfo = new NetworkInfo();
                    networkInfo.setIpv4Address(networkIF.getIPv4addr()[0]);
                    networkInfo.setIpv6Address(networkIF.getIPv6addr()[0]);
                    networkInfo.setMacAddress(networkIF.getMacaddr());
                    networkInfo.setNetworkName(networkIF.getName());

                    //计算
                    Long[] oldData = networkInfoMap.get(networkIF.getMacaddr());
                    long time = oldData[NetworkType.TIME_STAMP.getIndex()] - networkIF.getTimeStamp();
                    long send = (oldData[NetworkType.SEND.getIndex()] - networkIF.getBytesSent()) * 8 / time * 1000;
                    long accept = (oldData[NetworkType.ACCEPT.getIndex()] - networkIF.getBytesRecv()) * 8 / time * 1000;
                    Long[] newData = new Long[]{networkIF.getTimeStamp(), networkIF.getBytesSent(), networkIF.getBytesRecv()};
                    networkInfoMap.put(networkInfo.getMacAddress(), newData);

                    //对象赋值
                    networkInfo.setTimeStamp(networkIF.getTimeStamp());
                    networkInfo.setSend(send);
                    networkInfo.setAccept(accept);
                    list.add(networkInfo);
                }
            }

        }
        return list;
    }

    public static OSRuntimeInfo getOSRuntimeInfo() {
        OSRuntimeInfo osRuntimeInfo = new OSRuntimeInfo();
        //cpu使用率
        osRuntimeInfo.setCpuUsage(getCUPUsage());
        //系统内存总量
        osRuntimeInfo.setTotalMemory(globalMemory.getTotal());
        //系统使用量
        osRuntimeInfo.setUsedMemory(globalMemory.getTotal() - globalMemory.getAvailable());
        //磁盘信息
        osRuntimeInfo.setDisksList(getDisksList());
        //网卡信息
        osRuntimeInfo.setNetworkList(getNetworkInfo());
        return osRuntimeInfo;
    }

    public static OSInfo getSystemInfo() {
        Properties props = System.getProperties();
        OSInfo osInfo = new OSInfo();
        //操作系统
        osInfo.setOs(props.getProperty("os.name"));
        //系统架构
        osInfo.setOsArch(props.getProperty("os.arch"));
        //java版本
        osInfo.setJavaVersion(props.getProperty("java.version"));
        //工作目录
        osInfo.setUserDir(props.getProperty("user.dir"));
        //CPU核数
        osInfo.setCpuCount(centralProcessor.getLogicalProcessorCount());
        return osInfo;
    }

}
