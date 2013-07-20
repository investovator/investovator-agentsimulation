package org.investovator.jasa.mockGui;

import net.sourceforge.jabm.agent.Agent;
import net.sourceforge.jasa.agent.TokenTradingAgent;
import net.sourceforge.jasa.market.DuplicateShoutException;
import net.sourceforge.jasa.market.MarketRegulator;
import net.sourceforge.jasa.market.Order;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: ishan
 * Date: 7/6/13
 * Time: 6:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class HumanInterface {
    public JPanel panel1;
    private JTextField stockName;
    private JRadioButton buyRadioButton;
    private JRadioButton sellRadioButton;
    private JTextField quantityField;
    private JRadioButton limitRadioButton;
    private JRadioButton marketRadioButton;
    private JTextField priceField;
    private JButton sendButton;

    public HumanInterface() {
        sendButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
                //get the stock
                String stockID=stockName.getText();
                //get the side
                boolean isBuy=false;
                if (buyRadioButton.isSelected()){
                    isBuy=true;
                }
                double price=10;
                //get the order type
                boolean isMarketOrder=false;
                if(marketRadioButton.isSelected()){
                    isMarketOrder=true;
                }
                else{
                    price=Double.parseDouble(priceField.getText());
                }
                //get the quantityField
                int quantity=Integer.parseInt(quantityField.getText());


                //send the order
                MarketRegulator.addOrder(new Order(new TokenTradingAgent(),quantity,price,isBuy));
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HumanInterface");
        frame.setContentPane(new HumanInterface().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
