/*
 * investovator, Stock Market Gaming framework
 * Copyright (C) 2013  investovator
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

package org.investovator.agentsimulation;

import net.sourceforge.jabm.spring.BeanFactorySingleton;
import org.investovator.agentsimulation.exchange.Exchange;
import org.investovator.agentsimulation.multiasset.simulation.HeadlessMultiAssetSimulationManager;
import org.investovator.agentsimulation.ui.HumanInterface;

import javax.swing.*;

/**
 * @author Amila Surendra
 * @author rajith
 * @version $Revision
 */
public class Main {

    //private Exchange exchange;


    public static void main(String[] args) {
        /*MultiAssetSimulationManager manager = new MultiAssetSimulationManager();
        manager.initialise();

        HumanInterfaceRunner hir=new HumanInterfaceRunner(Main.getExchange());
        SwingUtilities.invokeLater(hir);*/

        HeadlessMultiAssetSimulationManager manager=new HeadlessMultiAssetSimulationManager();
        new Thread(manager).start();
    }


    public static Exchange getExchange(){
        return (Exchange) BeanFactorySingleton.getBean("exchange");
    }

}


class HumanInterfaceRunner implements Runnable{
            private Exchange market;

                    HumanInterfaceRunner(Exchange market) {
                    this.market = market;
                }


                    @Override
            public void run() {
                    JFrame frame = new JFrame("HumanInterface");
                    frame.setContentPane(new HumanInterface(Main.getExchange()).panel1);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
        }



