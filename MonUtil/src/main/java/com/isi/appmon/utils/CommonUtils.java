package com.isi.appmon.utils;

import com.isi.appmon.bean.AgentBean;
import com.isi.appmon.parser.XMLParserUtils;
import com.thoughtworks.xstream.XStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by samurdiw on 1/5/2017.
 */
public class CommonUtils {




    public static String getXML(Object bean,Map<String,Class> aliasClassMap){
        XStream xstream = new XStream();
        for(String key:aliasClassMap.keySet()){
            xstream.alias(key, aliasClassMap.get(key));
        }
        return xstream.toXML(bean);
    }
//
//    public static Object geObjectFromXml(String xml,Class... clzes){
//        XStream xstream = new XStream();
//        for(Class clz:clzes){
//            xstream.alias(clz.getSimpleName(), clz);
//        }
////        xstream.alias( "DiskUsageBean", DiskUsageBean.class );
////        xstream.alias( "AgentBean", AgentBean.class );
//        xstream.addImplicitCollection( AgentBean.class, "diskUsageBeans" );
////        xstream.alias( "com.isi.appmon.bean.AgentBean.DiskUsageBean", DiskUsageBean.class );
//        return xstream.fromXML(xml);
//    }

//    public static Object geObjectFromXmlFile(String fileLocation,Map<String,Class> aliasClassMap){
//        XStream xstream = new XStream();
//        for(String key:aliasClassMap.keySet()){
//            xstream.alias(key, aliasClassMap.get(key));
//        }
//        return xstream.fromXML(CommonUtils.class.getResource(fileLocation));
//    }

    public static void main(String[] args) {
        List agents=new ArrayList();
        Map<String,Class> aliasClassMap=new HashMap<String, Class>();
        AppMonConfig appMonConfig=new AppMonConfig();
        AgentBean agentBean=new AgentBean();
        agentBean.setID("TRS-01");
        agentBean.setURL("tcp:127.0.0.1:1414");
        agents.add(agentBean);
        appMonConfig.setAgents(agents);
        aliasClassMap.put("Agent",AgentBean.class);
        aliasClassMap.put("AppMonConfig",AppMonConfig.class);
        String xml=getXML(appMonConfig,aliasClassMap);
        System.out.println(xml);
        Object object= XMLParserUtils.getAgentBeanFromXMLFile("D:\\Projects\\MubasherProjects\\NewOMS_Core_Custom\\DFNAppMon\\MonServer\\src\\main\\resources\\DFNAppMonConfig.xml");
        System.out.println(object);
    }

    public static String getDateFromMillis(long millis,String format){
        Date date = new Date(millis);
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
