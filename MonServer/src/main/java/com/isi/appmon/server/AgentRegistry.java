package com.isi.appmon.server;

import com.isi.appmon.bean.AgentBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samurdiw on 1/5/2017.
 */
public class AgentRegistry {
    private static Map<String, AgentBean> agentRegistry = new HashMap();
    private StringBuilder agentView = new StringBuilder();

    public void updateRegistry(AgentBean agentBean) {
        AgentBean agentBeanCurrent = agentRegistry.get(agentBean.getID());
        long lastUpdated = System.currentTimeMillis();
        if (agentBeanCurrent == null) {
            agentBeanCurrent = agentBean;
            agentBeanCurrent.setRegisterTimeInMills(lastUpdated);
            agentBeanCurrent.setNoOfCores(agentBean.getNoOfCores());
            agentBeanCurrent.setOsVersion(agentBean.getOsVersion());
            agentBeanCurrent.setOsName(agentBean.getOsName());
            agentBeanCurrent.setAgentIP(agentBean.getAgentIP());
        }
        agentBeanCurrent.setLastUpdatedTimeInMillis(lastUpdated);
        agentBeanCurrent.setTotalMemory(agentBean.getTotalMemory());
        agentBeanCurrent.setFreeMemory(agentBean.getFreeMemory());
        agentBeanCurrent.setCustomMsg(agentBean.getCustomMsg());
        agentBeanCurrent.setPublishTimeInMills(agentBean.getPublishTimeInMills());
        agentBeanCurrent.setDiskUsageBeans(agentBean.getDiskUsageBeans());
        agentBeanCurrent.setProcessLoadAverage(agentBean.getProcessLoadAverage());
        agentBeanCurrent.setSysLoadAVG(agentBean.getSysLoadAVG());
        agentRegistry.put(agentBean.getID(), agentBeanCurrent);
    }

    public AgentBean lookupRegistry(String ID) {
        return agentRegistry.get(ID);
    }

    public String listRegistry() {
        agentView.setLength(0);
        for (AgentBean agentBean : agentRegistry.values()) {
            agentView.append(agentBean);
        }
        return agentView.toString();
    }

    public Map<String, AgentBean> getAgentMap() {
        return agentRegistry;
    }
}
