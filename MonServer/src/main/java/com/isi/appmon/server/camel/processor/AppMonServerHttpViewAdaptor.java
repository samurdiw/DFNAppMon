package com.isi.appmon.server.camel.processor;

import com.isi.appmon.bean.AgentBean;
import com.isi.appmon.bean.DiskUsageBean;
import com.isi.appmon.server.AgentRegistry;
import com.isi.appmon.utils.CommonUtils;
import com.isi.appmon.utils.Const;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by samurdiw on 1/10/2017.
 */
public class AppMonServerHttpViewAdaptor implements Processor {
    private AgentRegistry agentRegistry = new AgentRegistry();
    private Map<String, AgentBean> registeredAgents;
    private StringBuilder httpViewBld = new StringBuilder();
    private DecimalFormat df = new DecimalFormat("#.000");
    public void process(Exchange exchange) throws Exception {
        httpViewBld.setLength(0);
        httpViewBld.append("<html><h3>AGENT LIST</h3><hr></hr><body>");
        registeredAgents = agentRegistry.getAgentMap();

        for (AgentBean agent : registeredAgents.values()) {
            httpViewBld.append(getAgentViewAsHTML(agent));
        }
        httpViewBld.append("</body></html>");
        exchange.getOut().setBody(httpViewBld.toString());
    }

    private String getAgentViewAsHTML(AgentBean agentBean) {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("<ul>");
        stringBuilder.append("<li><b>AGENT&nbsp;&nbsp;:").append(agentBean.getID()).append("</b></li>");
        stringBuilder.append("<li>Agent IP&nbsp;&nbsp;:").append(agentBean.getAgentIP()).append("</b></li>");
        stringBuilder.append("<li>Registered At&nbsp;&nbsp;:").append(CommonUtils.getDateFromMillis(agentBean.getRegisterTimeInMills(), Const.TIME_FORMAT_HH_MM_ss_SSS)).append("</li>");
        stringBuilder.append("<li>Updated At&nbsp;&nbsp;:").append(CommonUtils.getDateFromMillis(agentBean.getLastUpdatedTimeInMillis(), Const.TIME_FORMAT_HH_MM_ss_SSS)).append("</li>");
        stringBuilder.append("<li>Published At&nbsp;&nbsp;:").append(CommonUtils.getDateFromMillis(agentBean.getPublishTimeInMills(), Const.TIME_FORMAT_HH_MM_ss_SSS)).append("</li>");
        stringBuilder.append("<li>Heap Max Limit(MB)&nbsp;&nbsp;:").append(agentBean.getAllocatedMemory() / 1048576).append("</li>");
        stringBuilder.append("<li>Used Memory(MB)&nbsp;&nbsp;:").append(agentBean.getTotalMemory() / 1048576).append("</li>");
        stringBuilder.append("<li>Free Memory(MB)&nbsp;&nbsp;:").append(agentBean.getFreeMemory() / 1048576).append("</li>");
        stringBuilder.append("<li>Disk Usage&nbsp;&nbsp;:").append(getDiskUsage(agentBean.getDiskUsageBeans())).append("</li>");
        stringBuilder.append("<li>OS&nbsp;&nbsp;:").append(agentBean.getOsName()).append("</li>");
        stringBuilder.append("<li>OS Version&nbsp;&nbsp;:").append(agentBean.getOsVersion()).append("</li>");
        stringBuilder.append("<li>Process Load Average&nbsp;&nbsp;:").append(agentBean.getProcessLoadAverage()).append("</li>");
        stringBuilder.append("<li>System Load Average&nbsp;&nbsp;:").append(agentBean.getSysLoadAVG()).append("</li>");
        stringBuilder.append("<li>Number of Cores&nbsp;&nbsp;:").append(agentBean.getNoOfCores()).append("</li>");
        stringBuilder.append("<li>Custom Message&nbsp;&nbsp;:").append(agentBean.getCustomMsg()).append("</li>");
        long lastUpdatedGap=System.currentTimeMillis()-agentBean.getLastUpdatedTimeInMillis();
//        double totalFreeMemory=agentBean.getAllocatedMemory()-agentBean.getTotalMemory()+agentBean.getFreeMemory();
        double freeMemoryPercentage=((agentBean.getAllocatedMemory()-agentBean.getTotalMemory()+agentBean.getFreeMemory())/agentBean.getAllocatedMemory())*100;
        if(lastUpdatedGap>=agentBean.getComponentDownConfirmInMillis()){
            stringBuilder.append("<li>Component Status&nbsp;&nbsp;:").append("<font color=\"red\">DOWN</font>").append("</li>");
        }else if(lastUpdatedGap>=agentBean.getComponentDownWarningInMillis()){
            stringBuilder.append("<li>Component Status&nbsp;&nbsp;:").append("\"<font color=\"yellow\">UP WITH WARNING</font>").append("</li>");
        }else{
            stringBuilder.append("<li>Component Status&nbsp;&nbsp;:").append("<font color=\"green\">UP</font>").append("</li>");
        }
        if(freeMemoryPercentage<=agentBean.getFreeMemoryCriticalPercentage()){
            stringBuilder.append("<li>Heap Memory Usage(%)&nbsp;&nbsp;:").append("<font color=\"red\">CRITICAL #").append(df.format(freeMemoryPercentage)).append("%</font></li>");
        }else if(freeMemoryPercentage<=agentBean.getFreeMemoryWarningPercentage()){
            stringBuilder.append("<li>Heap Memory Usage(%)&nbsp;&nbsp;:").append("\"<font color=\"yellow\">WARNING # ").append(df.format(freeMemoryPercentage)).append("%</font></li>");
        }else{
            stringBuilder.append("<li>Heap Memory Usage(%)&nbsp;&nbsp;:").append("<font color=\"green\">OK # ").append(df.format(freeMemoryPercentage)).append("%</font></li>");
        }
        stringBuilder.append("</ul>");
        return stringBuilder.toString();
    }

    private String getDiskUsage(List<DiskUsageBean> diskUsageBeans){
        if(diskUsageBeans==null || diskUsageBeans.size()==0){
            return "";
        }
        StringBuilder diskUsageBuilder=new StringBuilder("<ul>");
        for (DiskUsageBean diskUsage:diskUsageBeans) {
            diskUsageBuilder.append("<li>").append("Drive Name:").append(diskUsage.getDriveName()).append("</li>");
            diskUsageBuilder.append("<li>").append("Total Space:").append(df.format(diskUsage.getTotalSpace()/(1073741824))).append("&nbsp;(GB)</li>");
            diskUsageBuilder.append("<li>").append("Usable Space:").append(df.format(diskUsage.getUsableSpace()/1073741824)).append("&nbsp;(GB)</li>");
            diskUsageBuilder.append("<li>").append("Free Space:").append(df.format(diskUsage.getFreeSpace()/1073741824)).append("&nbsp;(GB)</li>");
        }
        diskUsageBuilder.append("</ul>");
        return diskUsageBuilder.toString();
    }
}
