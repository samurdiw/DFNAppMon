package com.isi.appmon.server.camel.processor;

import com.isi.appmon.bean.AgentBean;
import com.isi.appmon.server.AgentRegistry;
import com.isi.appmon.parser.XMLParserUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by samurdiw on 1/9/2017.
 */
public class NettyTCPDefaultINProcessor implements Processor {
    private AgentRegistry agentRegistry=new AgentRegistry();
    public void process(Exchange exchange) throws Exception {
        String msg=exchange.getIn().getBody(String.class);
        System.out.println(msg);
        AgentBean agentBean= XMLParserUtils.getAgentBeanFromXML(msg);
        agentRegistry.updateRegistry(agentBean);

    }

}
