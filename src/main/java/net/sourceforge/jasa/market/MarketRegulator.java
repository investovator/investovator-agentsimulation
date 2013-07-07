package net.sourceforge.jasa.market;

import net.sourceforge.jabm.spring.BeanFactorySingleton;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: amila
 * Date: 7/7/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class MarketRegulator {

    static Logger logger = Logger.getLogger(MarketFacade.class);

    public static void main(String[] args) {
        Runnable market =
                (Runnable) BeanFactorySingleton.getBean("market");
        logger.info("Starting...");
        market.run();
        logger.info("all done.");
    }
}
