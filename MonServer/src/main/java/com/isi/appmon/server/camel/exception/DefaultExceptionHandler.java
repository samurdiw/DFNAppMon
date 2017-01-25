package com.isi.appmon.server.camel.exception;

import org.apache.camel.Exchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by samurdiw on 1/10/2017.
 */
public class DefaultExceptionHandler {
    private static final Logger logger = LogManager.getLogger(DefaultExceptionHandler.class);
    public void processException(Exchange exchange){
        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        logger.info("\nException handler: Msg failed:"+exchange.getIn().getBody()+", due to:"+cause.getMessage(),cause);
        cause.printStackTrace();
    }
}
