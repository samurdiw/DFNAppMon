package com.isi.appmon.agent;

import com.isi.appmon.agent.beans.AgentConfigs;
import com.isi.appmon.bean.AgentBean;

import java.io.File;
import java.lang.management.ManagementFactory;

import com.isi.appmon.bean.DiskUsageBean;
import com.sun.management.OperatingSystemMXBean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samurdiw on 1/10/2017.
 */
public class NetMonAgentMsgDefaultBuilder implements NetMonAgentMsgBuilderI {
    private AgentBean agentBean = new AgentBean();
    private AgentConfigs agentConfigs;
    private Runtime runtime = Runtime.getRuntime();
    InetAddress inetAddr = null;
    private String localIP = "127.0.0.1";
    private String osName = null;
    private String osVersion = null;
    private int noOfCores;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    private OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private List<DiskUsageBean> diskUsageBeans = new ArrayList();
    final String userDir = System.getProperty("user.dir");

    public NetMonAgentMsgDefaultBuilder() {
        try {
            inetAddr = InetAddress.getLocalHost();
            localIP = inetAddr.getHostAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        osName = System.getProperty("os.name");
        osVersion = System.getProperty("os.version");
        noOfCores = Runtime.getRuntime().availableProcessors();
    }

    private List<DiskUsageBean> getDiskUsage() {
        DiskUsageBean diskUsageBean;
        diskUsageBeans.clear();
        try {
            File file = new File(userDir);
            long totalSpace = file.getTotalSpace(); //total disk space in bytes.
            long usableSpace = file.getUsableSpace(); ///unallocated / free disk space in bytes.
            long freeSpace = file.getFreeSpace(); //unallocated / free disk space in bytes.
            diskUsageBean = new DiskUsageBean();
            diskUsageBean.setDriveName(file.getParent());
            diskUsageBean.setTotalSpace(totalSpace);
            diskUsageBean.setUsableSpace(usableSpace);
            diskUsageBean.setFreeSpace(freeSpace);
            diskUsageBeans.add(diskUsageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diskUsageBeans;
    }


    public AgentBean buildAgentMsg() {
        agentBean.setID(agentConfigs.getAgentID());
        agentBean.setComponentDownConfirmInMillis(agentConfigs.getComponentDownConfirmInMillis());
        agentBean.setComponentDownWarningInMillis(agentConfigs.getComponentDownWarningInMillis());
        agentBean.setFreeMemoryCriticalPercentage(agentConfigs.getFreeMemoryCriticalPercentage());
        agentBean.setFreeMemoryWarningPercentage(agentConfigs.getFreeMemoryWarningPercentage());
        agentBean.setPublishTimeInMills(System.currentTimeMillis());
        agentBean.setTotalMemory(runtime.totalMemory());
        agentBean.setFreeMemory(runtime.freeMemory());
        agentBean.setAllocatedMemory(runtime.maxMemory());
        agentBean.setAgentIP(localIP);
        agentBean.setOsName(osName);
        agentBean.setOsVersion(osVersion);
        agentBean.setProcessLoadAverage(operatingSystemMXBean.getProcessCpuLoad());
        agentBean.setSysLoadAVG(operatingSystemMXBean.getSystemLoadAverage());
        agentBean.setDiskUsageBeans(getDiskUsage());
        agentBean.setNoOfCores(noOfCores);
        return agentBean;
    }

    public AgentConfigs getAgentConfigs() {
        return agentConfigs;
    }

    public void setAgentConfigs(AgentConfigs agentConfigs) {
        this.agentConfigs = agentConfigs;
    }


}
