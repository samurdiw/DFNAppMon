package com.isi.appmon.utils;

import com.isi.appmon.bean.AgentBean;

import java.util.List;

/**
 * Created by samurdiw on 1/5/2017.
 */
public class AppMonConfig {
    private List<AgentBean> agents;

    public List<AgentBean> getAgents() {
        return agents;
    }

    public void setAgents(List<AgentBean> agents) {
        this.agents = agents;
    }
}
