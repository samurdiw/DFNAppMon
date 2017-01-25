package com.isi.appmon.agent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by samurdiw on 1/10/2017.
 */
public class DFNAppMonAgentManager {
    private static final String agentCamelContextPath = "META-INF/DFNAppMonAgentSpringContext.xml";
    private static final Logger logger = LogManager.getLogger(DFNAppMonAgentManager.class);
    private static AbstractApplicationContext context;
    public void init(){
        if (context == null) {
            synchronized (DFNAppMonAgentManager.class){
               if(context==null){
                   logger.info("\n Initializing app mon agent camel context \n");
                   context = new ClassPathXmlApplicationContext(agentCamelContextPath);
                   context.start();
                   logger.info("\n Initializing app mon agent camel context started \n");
//                   Runtime r = Runtime.getRuntime();
//                   r.addShutdownHook(new Thread() {
//                       public void run() {
//                           context.stop();
//                       }
//                   });
                   context.registerShutdownHook();
               }
            }
        }
        logger.info("\n Camel context initiated and waiting for signalling \n");

    }

    public static void main(String[] args) {
        DFNAppMonAgentManager dfnAppMonAgentManager=new DFNAppMonAgentManager();
        dfnAppMonAgentManager.init();
    }
}
