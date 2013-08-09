/*
 * investovator, Stock Market Gaming Framework
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

package net.sourceforge.jasa.agent.strategy;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;
import net.sourceforge.jasa.agent.TradingAgent;
import net.sourceforge.jasa.market.Market;
import net.sourceforge.jasa.market.Order;
import net.sourceforge.jasa.report.EquilibriumReportVariables;

import java.util.ArrayList;

/**
 * @author rajith
 * @version $Revision$
 */
public class TwoSimpleMovingAverageStrategy
        extends AbstractTradingStrategy implements TradeDirectionPolicy {

    public static final int QUICK_PERIODS_AVERAGE = 20;
    public static final int SLOW_PERIODS_AVERAGE = 50;

    private EquilibriumReportVariables equilibriumReportVariables;


    @Override
    public int determineQuantity(Market auction) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean modifyShout(Order shout) {
        shout.setAgent(getAgent());
        shout.setIsBid(isBuy(this.auction, getAgent()));
        return true;
    }

    @Override
    public boolean isBuy(Market market, TradingAgent agent) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private boolean isGraphsCrossed(){
        ArrayList<Order> matchedShouts =
                new ArrayList<Order>(equilibriumReportVariables.getMatchedShouts());
        double[] closePrice = new double[matchedShouts.size()];
        double[] quickSMAOut = new double[matchedShouts.size()];
        double[] slowSMAOut = new double[matchedShouts.size()];

        MInteger quickSMABegin = new MInteger();
        MInteger quickSMALength = new MInteger();

        MInteger slowSMABegin = new MInteger();
        MInteger slowSMALength = new MInteger();

        for (int i = 0; i < matchedShouts.size(); i++) {
            closePrice[i] = matchedShouts.get(i).getPrice();
        }

        Core core = new Core();
        core.sma(0, closePrice.length - 1, closePrice,
                QUICK_PERIODS_AVERAGE, quickSMABegin, quickSMALength, quickSMAOut);
        core.sma(0, closePrice.length - 1, closePrice,
                SLOW_PERIODS_AVERAGE, slowSMABegin, slowSMALength, slowSMAOut);

        if(){

        }

    }

    public void setEquilibriumReportVariables(EquilibriumReportVariables variables){
        this.equilibriumReportVariables = variables;
    }




}
