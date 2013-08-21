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

import net.sourceforge.jasa.agent.herdmentality.HerdingAgent;
import net.sourceforge.jasa.market.Market;
import net.sourceforge.jasa.report.AbstractOrderBookReport;

public class HerdingBeatmarketStrategy extends AbstractHerdingStrategy {

	public HerdingBeatmarketStrategy() {
		// TODO Auto-generated constructor stub
	}

	public HerdingBeatmarketStrategy(AbstractTradingAgent agent, AbstractOrderBookReport orderBookReport) {
		super(agent, orderBookReport);
	}

	@Override
	public int determineQuantity(Market auction) {
		return 1;
	}

	@Override
	public boolean isBid() {
		// if market wants to buy, it is time to SELL to them!
		return !(((HerdingAgent)agent).alignment());
	}

}
