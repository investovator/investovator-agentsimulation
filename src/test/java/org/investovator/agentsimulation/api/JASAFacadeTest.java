package org.investovator.agentsimulation.api;

import org.investovator.core.commons.simulationengine.MarketOrder;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Amila Surendra
 * @version $Revision
 */
public class JASAFacadeTest {

    JASAFacade facade;

    String stockID = "GOOG";

    String mainXML = "examples" + File.separator +"multiasset" + File.separator +"main.xml";

    @Before
    public void setUp() throws Exception {

        System.setProperty("jabm.config", mainXML);
        facade = JASAFacade.getMarketFacade();

    }

    @Test
    public void testGetUserUnmatchedOrders() throws Exception {

        facade.startSimulation();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("starting ....");

                for (int i = 0; i < 6; i++) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
        t.join();

        String userName =  "testUser1";

        facade.AddUserAgent(userName,100000);

        facade.putLimitOrder(userName, stockID, 1, 10, true);

        HashMap<String, ArrayList<MarketOrder>> orders = facade.getUserUnmatchedOrders("testUser1");

        System.out.println();

    }
}
