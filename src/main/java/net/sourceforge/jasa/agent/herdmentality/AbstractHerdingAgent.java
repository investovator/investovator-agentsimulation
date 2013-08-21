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

package net.sourceforge.jasa.agent.herdmentality;

import net.sourceforge.jabm.EventScheduler;
import net.sourceforge.jasa.agent.AbstractTradingAgent;
import net.sourceforge.jasa.market.Market;
import net.sourceforge.jasa.report.AbstractOrderBookReport;

/**
 * <p>
 * @author Wilhelm Eklund
 * W. Eklund, "Agent-based modeling of herd mentality in the stock market,"
 * 2011.
 * Agents of this type have a fixed volume, and they trade units equal to their
 * volume in each round of the market.
 * </p>
 *
 */

public abstract class AbstractHerdingAgent extends AbstractTradingAgent implements HerdingAgent {
	
	protected AbstractOrderBookReport orderBookReport;
	
	public AbstractHerdingAgent(EventScheduler scheduler, boolean top) {
		super(scheduler);
		if(top){
			this.orderBookReport = ((Market)scheduler).getHerdTopReport();
		} else {
			this.orderBookReport = ((Market)scheduler).getHerdAllReport();
		}
	}

    public AbstractHerdingAgent (){
        super(null);
    }

	public AbstractOrderBookReport getOrderBookReport(){
		return this.orderBookReport;
	}
	
	/*
	 * The following three methods are required for herding agents but not used in the real implementation
	 */
	@Override
	public boolean alignment() {
		return this.orderBookReport.isBid();
	}

	@Override
	public double cohesion() {
		return this.orderBookReport.getValue();
	}

	@Override
	public double separation() {
		return this.orderBookReport.getPricestat().getStdDev();
	}


	@Override
	public boolean isHerdTop() {
		return this.orderBookReport.isHerdTop();
	}
}
