package net.sourceforge.jasa.market;

import com.investovator.ats.Exchange;
import net.sourceforge.jabm.spring.BeanFactorySingleton;
import org.apache.log4j.Logger;
import org.investovator.jasa.mockGui.HumanInterface;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: amila
 * Date: 7/7/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class MarketRegulator {

    static Logger logger = Logger.getLogger(MarketFacade.class);
    public static Exchange exchange = new Exchange();

    public static void main(String[] args) {
        Runnable market =
                (Runnable) BeanFactorySingleton.getBean("market");

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
//


        logger.info("Starting...");
        market.run();
        logger.info("all done.");
    }
}
