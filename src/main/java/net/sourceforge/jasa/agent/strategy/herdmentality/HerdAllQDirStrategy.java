/*
 * JASA Java Auction Simulator API
 * Copyright (C) 2001-2009 Steve Phelps
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package net.sourceforge.jasa.agent.strategy.herdmentality;

import net.sourceforge.jasa.agent.AbstractTradingAgent;
import net.sourceforge.jasa.market.Market;
import net.sourceforge.jasa.report.AbstractOrderBookReport;

public class HerdAllQDirStrategy extends AbstractHerdingStrategy {
	
	public HerdAllQDirStrategy(AbstractTradingAgent agent, AbstractOrderBookReport orderBookReport) {
		super(agent, orderBookReport);
	}

    //TODO testing
    public HerdAllQDirStrategy(){
       super.initialise();
    }

	@Override
	public int determineQuantity(Market auction) {
		return this.orderBookReport.getQuantity();
	}

	@Override
	public boolean isBid() {
		return this.orderBookReport.isBid();
	}

}
