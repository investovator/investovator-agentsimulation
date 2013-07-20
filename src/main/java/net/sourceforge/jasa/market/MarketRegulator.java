package net.sourceforge.jasa.market;

import net.sourceforge.jabm.SimulationTime;
import org.investovator.ats.Exchange;
import net.sourceforge.jabm.spring.BeanFactorySingleton;
import org.apache.log4j.Logger;
import org.investovator.jasa.mockGui.HumanInterface;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author amila
 * @author rajith
 *
 * $Revision$
 */
public class MarketRegulator {

    static Logger logger = Logger.getLogger(MarketFacade.class);
    public static Exchange exchange = new Exchange();

    private static ArrayList<MarketFacade> securities = new ArrayList<MarketFacade>();

    public void setSecurities(ArrayList<MarketFacade> securitiesList) {
        securities.addAll(securitiesList);
    }

    public ArrayList<MarketFacade> getSecurities(){
        return securities;
    }

    public static void main(String[] args) {


        //run the GUI for the human player
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("HumanInterface");
                frame.setContentPane(new HumanInterface().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

/*
        logger.info("Starting...");
        market.run();
        logger.info("all done.");*/

        MarketRegulator marketRegulator = (MarketRegulator) BeanFactorySingleton.getBean("marketRegulator");

        logger.info("Market starting");

        for(MarketFacade security: marketRegulator.getSecurities()){
            Thread marketSecurity = new Thread(security);
            marketSecurity.start();
        }
    }

    public static void addOrder(Order order){
        //try {
                                //get the timestamp from the market
                                        SimulationTime time=null;
                                MarketFacade mark=null;
                                for(MarketFacade market:securities){
                                        if(market.getSecurityID().equalsIgnoreCase(order.getSecurityID())){
                                                time=market.getSimulationTime();
                                                mark=market;
                                           }
                                    }
                                if(time==null){
                                        try {
                                                throw new Exception("Simulation time not set");
                                            } catch (Exception e1) {
                                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                                return;
                                            }
                                    }
    }

}
