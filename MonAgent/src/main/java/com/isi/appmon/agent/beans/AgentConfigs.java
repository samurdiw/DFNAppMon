package com.isi.appmon.agent.beans;

/**
 * Created by samurdiw on 1/10/2017.
 */
public class AgentConfigs {
    private String agentID;
    private long componentDownWarningInMillis;
    private long componentDownConfirmInMillis;
    private long freeMemoryWarningPercentage;
    private long freeMemoryCriticalPercentage;

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
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
}
