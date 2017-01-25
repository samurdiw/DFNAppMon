package com.isi.appmon.bean;

//import com.isi.appmon.parser.XMLParserUtils;

import java.util.List;

/**
 * Created by samurdiw on 1/5/2017.
 */
public class AgentBean {
    private String ID;
    private transient String URL;//protocol:ip:port?options
    private long publishTimeInMills;
    private long registerTimeInMills;
    private long lastUpdatedTimeInMillis;
    private double allocatedMemory;
    private double totalMemory;
    private double freeMemory;
    private String customMsg;
    private long componentDownWarningInMillis;
    private long componentDownConfirmInMillis;
    private long freeMemoryWarningPercentage;
    private long freeMemoryCriticalPercentage;
    private String agentIP;
    private String cpu;
    private int noOfCores;
    private double sysLoadAVG=0;
    private double processLoadAverage;
    private String osName;
    private String osVersion;
    private List<DiskUsageBean> diskUsageBeans;
    private transient StringBuilder stringBuilder=new StringBuilder();
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public long getPublishTimeInMills() {
        return publishTimeInMills;
    }

    public void setPublishTimeInMills(long publishTimeInMills) {
        this.publishTimeInMills = publishTimeInMills;
    }

    public double getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(double totalMemory) {
        this.totalMemory = totalMemory;
    }

    public double getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(double freeMemory) {
        this.freeMemory = freeMemory;
    }

//    public String toString(){
//       return XMLParserUtils.getAgentBeanXML(this);
//    }

    public long getRegisterTimeInMills() {
        return registerTimeInMills;
    }

    public void setRegisterTimeInMills(long registerTimeInMills) {
        this.registerTimeInMills = registerTimeInMills;
    }

    public long getLastUpdatedTimeInMillis() {
        return lastUpdatedTimeInMillis;
    }

    public void setLastUpdatedTimeInMillis(long lastUpdatedTimeInMillis) {
        this.lastUpdatedTimeInMillis = lastUpdatedTimeInMillis;
    }

    public String getCustomMsg() {
        return customMsg;
    }

    public void setCustomMsg(String customMsg) {
        this.customMsg = customMsg;
    }

    public long getComponentDownWarningInMillis() {
        return componentDownWarningInMillis;
    }

    public void setComponentDownWarningInMillis(long componentDownWarningInMillis) {
        this.componentDownWarningInMillis = componentDownWarningInMillis;
    }

    public long getComponentDownConfirmInMillis() {
        return componentDownConfirmInMillis;
    }

    public void setComponentDownConfirmInMillis(long componentDownConfirmInMillis) {
        this.componentDownConfirmInMillis = componentDownConfirmInMillis;
    }

    public long getFreeMemoryWarningPercentage() {
        return freeMemoryWarningPercentage;
    }

    public void setFreeMemoryWarningPercentage(long freeMemoryWarningPercentage) {
        this.freeMemoryWarningPercentage = freeMemoryWarningPercentage;
    }

    public long getFreeMemoryCriticalPercentage() {
        return freeMemoryCriticalPercentage;
    }

    public void setFreeMemoryCriticalPercentage(long freeMemoryCriticalPercentage) {
        this.freeMemoryCriticalPercentage = freeMemoryCriticalPercentage;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public double getAllocatedMemory() {
        return allocatedMemory;
    }

    public void setAllocatedMemory(long allocatedMemory) {
        this.allocatedMemory = allocatedMemory;
    }

    public String getAgentIP() {
        return agentIP;
    }

    public void setAgentIP(String agentIP) {
        this.agentIP = agentIP;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public double getProcessLoadAverage() {
        return processLoadAverage;
    }

    public void setProcessLoadAverage(double processLoadAverage) {
        this.processLoadAverage = processLoadAverage;
    }


    public double getSysLoadAVG() {
        return sysLoadAVG;
    }

    public void setSysLoadAVG(double sysLoadAVG) {
        this.sysLoadAVG = sysLoadAVG;
    }

    public int getNoOfCores() {
        return noOfCores;
    }

    public void setNoOfCores(int noOfCores) {
        this.noOfCores = noOfCores;
    }

//    public double getSysLoadAVG() {
//        return sysLoadAVG;
//    }
//
//    public void setSysLoadAVG(double sysLoadAVG) {
//        this.sysLoadAVG = sysLoadAVG;
//    }

    public List<DiskUsageBean> getDiskUsageBeans() {
        return diskUsageBeans;
    }

    public void setDiskUsageBeans(List<DiskUsageBean> diskUsageBeans) {
        this.diskUsageBeans = diskUsageBeans;
    }



    public void setMaxMemory(double maxMemory) {
        this.allocatedMemory = maxMemory;
    }
}
