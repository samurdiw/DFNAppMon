package com.isi.appmon.server;

import com.isi.appmon.bean.AgentBean;
import com.isi.appmon.utils.AppMonConfig;
import com.isi.appmon.parser.XMLParserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samurdiw on 1/5/2017.
 */
public class DFNAppMonManager {
    private static final Logger logger = LogManager.getLogger(DFNAppMonManager.class);
    private AppMonConfig appMonConfig;
    private static final String serverCamelContextPath = "META-INF/DFNAppMonSpringContext.xml";
    private static AbstractApplicationContext context;

    private void init() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("################### |DFN APP MON| #########################");
        System.out.println("-----------------------------------------------------------");

        loadConfig();
        initCamelContext();
        agentRegister();
    }

    public static void main(String[] args) {
        DFNAppMonManager dfnAppMonManager = new DFNAppMonManager();
        dfnAppMonManager.init();
    }

    private void loadConfig() {
        logger.info("\n Loading configuration \n");
        Map<String, Class> aliasClassMap = new HashMap<String, Class>();
        aliasClassMap.put("Agent", AgentBean.class);
        aliasClassMap.put("AppMonConfig", AppMonConfig.class);
        appMonConfig = XMLParserUtils.getAppMonServerConfig("/META-INF/DFNAppMonConfig.xml");
    }

    private void agentRegister() {
        List<AgentBean> agentList = appMonConfig.getAgents();
        logger.info("\n Initializing Agents:" + agentList.size());
        for (AgentBean agentBean : agentList) {
            logger.info("Initializing agent:" + agentBean.getID());
        }
    }

    private void initCamelContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext(serverCamelContextPath);
            context.start();
//            Runtime r = Runtime.getRuntime();
//            r.addShutdownHook(new Thread() {
//                public void run() {
//                    logger.info("\n Camel context shutting down initiated \n");
//                    context.stop();
//                }
//            });
            context.registerShutdownHook();
        }
        System.out.println("\n Camel context initiated and waiting \n");

    }


}
