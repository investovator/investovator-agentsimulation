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

package net.sourceforge.jasa.agent.valuation;

import net.sourceforge.jabm.EventScheduler;
import net.sourceforge.jabm.event.SimEvent;
import net.sourceforge.jasa.agent.TradingAgent;
import net.sourceforge.jasa.event.TransactionExecutedEvent;
import net.sourceforge.jasa.market.Market;

/**
 * @author rajith
 * @version $Revision$
 */
public class CurrentMarketEvaluator implements ValuationPolicy{

    protected TradingAgent agent;
    private double value;

    @Override
    public double determineValue(Market auction) {
        return value = auction.getLastTransactionPrice();
    }

    @Override
    public void consumeUnit(Market auction) {
        value = auction.getLastTransactionPrice();
    }

    @Override
    public void setAgent(TradingAgent agent) {
        this.agent = agent;
    }

    @Override
    public TradingAgent getAgent() {
        return agent;
    }

    @Override
    public void initialise() {
        //Do nothing
    }

    @Override
    public void eventOccurred(SimEvent event) {
        if (event instanceof TransactionExecutedEvent) {
            TransactionExecutedEvent trEvent = (TransactionExecutedEvent) event;
            this.value = trEvent.getPrice();
        }
    }

    @Override
    public void subscribeToEvents(EventScheduler scheduler) {
        scheduler.addListener(TransactionExecutedEvent.class, this);
    }

    @Override
    public void reset() {
        initialise();
    }

}
