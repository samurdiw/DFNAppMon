package com.isi.appmon.server.camel.processor;

import com.isi.appmon.server.AgentRegistry;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by samurdiw on 1/10/2017.
 */
public class AppMonViewUpdateProcessor implements Processor {
    private AgentRegistry agentRegistry=new AgentRegistry();
    private static final Logger logger = LogManager.getLogger(AppMonViewUpdateProcessor.class);
    public void process(Exchange exchange) throws Exception {
        logger.debug("\nUpdating Net Mon View");
//        System.out.println("*****>"+agentRegistry.listRegistry());  //todo
//        exchange.getOut().setBody("<html><body>"+agentRegistry.listRegistry()+"</body></html>");
    }
}
