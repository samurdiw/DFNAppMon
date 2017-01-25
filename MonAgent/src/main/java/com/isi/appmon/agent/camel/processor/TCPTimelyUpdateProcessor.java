package com.isi.appmon.agent.camel.processor;


import com.isi.appmon.agent.NetMonAgentMsgBuilderI;
import com.isi.appmon.parser.XMLParserUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by samurdiw on 1/10/2017.
 */
public class TCPTimelyUpdateProcessor implements Processor {
//    private AgentBean agentBean=new AgentBean();//use wisely,not thread safe
//    private Runtime runtime = Runtime.getRuntime();
    String messageOut="";
    private NetMonAgentMsgBuilderI agentMsgBuilderI;
    private static final Logger logger = LogManager.getLogger(TCPTimelyUpdateProcessor.class);
    public void process(Exchange exchange) throws Exception {
//        agentBean.setID("TEST-ID");
//        agentBean.setPublishTimeInMills(System.currentTimeMillis());
//        agentBean.setTotalMemory(runtime.totalMemory());
//        agentBean.setFreeMemory(runtime.freeMemory());
        messageOut= XMLParserUtils.getAgentBeanXML(agentMsgBuilderI.buildAgentMsg()).replaceAll("\n","");
        logger.info("\n TCP timely update processor:"+messageOut);
        exchange.getIn().setBody(messageOut);
//        exchange.getIn().setBody("<com.isi.appmon.bean.AgentBean><ID>TRS-Dummy-Agent-1</ID>  <publishTimeInMills>1484135295066</publishTimeInMills>  <registerTimeInMills>0</registerTimeInMills>  <lastUpdatedTimeInMillis>0</lastUpdatedTimeInMillis>  <maxMemory>1404043264</maxMemory>  <totalMemory>157286400</totalMemory>  <freeMemory>61169864</freeMemory>  <componentDownWarningInMillis>15000</componentDownWarningInMillis>  <componentDownConfirmInMillis>30000</componentDownConfirmInMillis>  <freeMemoryWarningPercentage>30</freeMemoryWarningPercentage>  <freeMemoryCriticalPercentage>10</freeMemoryCriticalPercentage>  <agentIP>192.168.16.166</agentIP>  <noOfCores>4</noOfCores>  <loadAverage>0.12998783454987833</loadAverage>  <osName>Windows 7</osName>  <osVersion>6.1</osVersion><diskUsageBeans><com.isi.appmon.bean.DiskUsageBean><driveName>test</driveName><totalSpace>2.14853218304E11</totalSpace>\n" +
//                "<usableSpace>5.7110286336E10</usableSpace></com.isi.appmon.bean.DiskUsageBean></diskUsageBeans></com.isi.appmon.bean.AgentBean>");
    }

    public NetMonAgentMsgBuilderI getAgentMsgBuilderI() {
        return agentMsgBuilderI;
    }

    public void setAgentMsgBuilderI(NetMonAgentMsgBuilderI agentMsgBuilderI) {
        this.agentMsgBuilderI = agentMsgBuilderI;
    }
}
