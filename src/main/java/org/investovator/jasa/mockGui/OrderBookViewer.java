/*
 * investovator, Stock Market Gaming framework
 *     Copyright (C) 2013  investovator
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.investovator.jasa.mockGui;

import net.sourceforge.jasa.market.FourHeapOrderBook;
import net.sourceforge.jasa.market.Order;

import javax.swing.*;
import java.util.Iterator;

/**
 * @author ishan
 * @version $Revision: 1.3 $
 */
public class OrderBookViewer extends JFrame{
    public JPanel rootPane;
    private JTextArea buyOrderArea;
    private JTextArea sellOrderArea;

    public void update(final FourHeapOrderBook market){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //iterate through the buy orders
                Iterator itr=market.bidIterator();
                String bids="";
                while (itr.hasNext()){

                    Order tmp = (Order)itr.next();
                    bids+=  tmp.getPrice() +" -> "+ tmp.getQuantity() +"\n";

                }
                buyOrderArea.setText(null);
                buyOrderArea.setText(bids);
                bids="";

                //iterate through the sell orders
                Iterator sellItr=market.askIterator();
                String sells="";

                while (sellItr.hasNext()){
                    Order tmpSell = (Order)sellItr.next();
                    sells+= tmpSell.getPrice() + " -> " + tmpSell.getQuantity() +"\n";
                }
                sellOrderArea.setText(null);
                sellOrderArea.setText(sells);
                sells="";
            }
        });

    }
}
