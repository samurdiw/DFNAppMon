package com.isi.appmon.parser;

import com.isi.appmon.bean.AgentBean;
import com.isi.appmon.bean.DiskUsageBean;
import com.isi.appmon.utils.AppMonConfig;
import com.thoughtworks.xstream.XStream;


/**
 * Created by samurdiw on 1/13/2017.
 */
public class XMLParserUtils {
    private static final XStream xstream = new XStream();

    public static String getAgentBeanXML(AgentBean agentBean) {
        xstream.alias("AgentBean", AgentBean.class);
        xstream.alias("DiskUsageBean", DiskUsageBean.class);
        xstream.addImplicitCollection(AgentBean.class, "diskUsageBeans");
        return xstream.toXML(agentBean);
    }

    public static AgentBean getAgentBeanFromXML(String xml) {
        xstream.alias("AgentBean", AgentBean.class);
        xstream.alias("DiskUsageBean", DiskUsageBean.class);
        xstream.addImplicitCollection(AgentBean.class, "diskUsageBeans");
        return (AgentBean) xstream.fromXML(xml);
    }

    public static AgentBean getAgentBeanFromXMLFile(String filePath) {
        xstream.alias("AgentBean", AgentBean.class);
        xstream.alias("DiskUsageBean", DiskUsageBean.class);
        xstream.addImplicitCollection(AgentBean.class, "diskUsageBeans");
        return (AgentBean)xstream.fromXML(XMLParserUtils.class.getResource(filePath));
    }

    public static AppMonConfig getAppMonServerConfig(String filePath){
        xstream.alias("Agent", AgentBean.class);
        xstream.alias("AppMonConfig", AppMonConfig.class);
        return (AppMonConfig)xstream.fromXML(XMLParserUtils.class.getResource(filePath));
    }
}
