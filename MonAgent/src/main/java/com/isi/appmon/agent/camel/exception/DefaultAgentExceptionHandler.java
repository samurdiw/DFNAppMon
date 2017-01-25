package com.isi.appmon.agent.camel.exception;

import org.apache.camel.Exchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by samurdiw on 1/10/2017.
 */
public class DefaultAgentExceptionHandler {
    private static final Logger logger = LogManager.getLogger(DefaultAgentExceptionHandler.class);
    public void processException(Exchange exchange){
        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        logger.info("\nException handler: Msg failed:"+exchange.getIn().getBody()+", due to:"+cause.getMessage(),cause);
        cause.printStackTrace();
    }
}
