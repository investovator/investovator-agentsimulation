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

package net.sourceforge.jasa.report.timeseries;

import net.sourceforge.jabm.event.RoundFinishedEvent;
import net.sourceforge.jasa.event.TransactionExecutedEvent;
import net.sourceforge.jasa.market.MarketSimulation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CurrentPriceReportTimeseriesVariables extends MarketPriceReportTimeseriesVariables {

    public static final String NAME = "time.current";

    public Map<Object, ArrayList<Number>> getTimeseriesVariableBindings() {
        LinkedHashMap<Object,  ArrayList<Number>> result =
                new LinkedHashMap<Object,  ArrayList<Number>>();
        result.put(getName() + ".t", time);
        result.put(getName() + "." + PRICE_VAR, price);
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getPrice(RoundFinishedEvent event) {
        return ((MarketSimulation) event.getSimulation()).getCurrentPrice();
    }

    @Override
    public double getPrice(TransactionExecutedEvent event) {
        return (((TransactionExecutedEvent) event).getAuction().getCurrentPrice());
    }


}
