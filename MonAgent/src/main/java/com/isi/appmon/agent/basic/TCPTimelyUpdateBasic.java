package com.isi.appmon.agent.basic;

import com.isi.appmon.agent.NetMonAgentMsgDefaultBuilder;
import com.isi.appmon.agent.Settings;
import com.isi.appmon.agent.basic.sender.TCPSenderBasic;
import com.isi.appmon.agent.beans.AgentConfigs;
import com.isi.appmon.parser.XMLParserUtils;

/**
 * Created by samurdiw on 1/24/2017.
 */
public class TCPTimelyUpdateBasic {
    private static AgentConfigs agentConfigs;
    private static NetMonAgentMsgDefaultBuilder agentMsgBuilderI;
    private TCPSenderBasic tcpSenderBasic;
    private static TCPTimelyUpdateBasic tcpTimelyUpdateBasic;

    public static TCPTimelyUpdateBasic getInstance(){
        if(tcpTimelyUpdateBasic==null){
            synchronized (TCPTimelyUpdateBasic.class){
                if(tcpTimelyUpdateBasic==null){
                    tcpTimelyUpdateBasic=new TCPTimelyUpdateBasic();
                    agentMsgBuilderI=new NetMonAgentMsgDefaultBuilder();
                    initAgentConfig();
                    agentMsgBuilderI.setAgentConfigs(agentConfigs);
                }
            }
        }
        return tcpTimelyUpdateBasic;
    }

    private static void initAgentConfig(){
        agentConfigs=new AgentConfigs();
        agentConfigs.setAgentID(Settings.getProperty("agentID"));
        agentConfigs.setComponentDownWarningInMillis(Long.parseLong(Settings.getProperty("componentDownWarningInMillis")));
        agentConfigs.setComponentDownConfirmInMillis(Long.parseLong(Settings.getProperty("componentDownConfirmInMillis")));
        agentConfigs.setFreeMemoryWarningPercentage(Long.parseLong(Settings.getProperty("freeMemoryWarningPercentage")));
        agentConfigs.setFreeMemoryCriticalPercentage(Long.parseLong(Settings.getProperty("freeMemoryCriticalPercentage")));
    }

    private TCPTimelyUpdateBasic(){
        tcpSenderBasic=new TCPSenderBasic(Settings.getProperty("MONSERVER_IP"),
                Integer.parseInt(Settings.getProperty("MONSERVER_PORT")));
    }

    public void sendPeriodicUpdateMessage(){
        String messageOut= XMLParserUtils.getAgentBeanXML(agentMsgBuilderI.buildAgentMsg()).replaceAll("\n","");
        System.out.println("\n Sending periodic update:"+messageOut);
        tcpSenderBasic.send(messageOut);
    }
}
