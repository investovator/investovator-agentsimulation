/*
 * JASA Java Auction Simulator API
 * Copyright (C) 2013 Steve Phelps
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

package net.sourceforge.jasa.agent;

import net.sourceforge.jabm.agent.Agent;
import net.sourceforge.jabm.agent.utility.UtilityFunction;
import net.sourceforge.jasa.event.MarketEventListener;
import net.sourceforge.jasa.market.Account;
import net.sourceforge.jasa.market.Market;
import net.sourceforge.jasa.market.Order;

/**
 * <p>
 * Classes implementing this interface can trade in round-robin auctions, as
 * implemented by the RoundRobinAuction class.
 * </p>
 * 
 * @author Steve Phelps
 * @version $Revision: 1.12 $
 */

public interface TradingAgent extends Agent, MarketEventListener {
	
	public boolean register(Market market);

	public Account getAccount();

	public Inventory getCommodityHolding();

	public void orderFilled(Market auction, Order shout, double price,
	    int quantity);
	
	public double getValuation(Market auction);
	
	public void setUtilityFunction(UtilityFunction utilityFunction);
	
	public UtilityFunction getUtilityFunction();
	
	public double calculateProfit(Market auction, int quantity, double price);

}