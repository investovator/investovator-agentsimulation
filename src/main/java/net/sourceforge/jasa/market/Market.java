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

package net.sourceforge.jasa.market;

import java.util.Iterator;

import net.sourceforge.jabm.Population;
import net.sourceforge.jabm.agent.Agent;
import net.sourceforge.jabm.util.Resetable;
import net.sourceforge.jasa.agent.AbstractTradingAgent;
import net.sourceforge.jasa.market.auctioneer.Auctioneer;

/**
 * An order-driven market exchange.
 * 
 * @author Steve Phelps
 * @version $Revision: 1.11 $
 */
public interface Market extends QuoteProvider, Resetable {


    /**
     * Returns stock id
     * @return
     */
    public String getStockID();

    /**
     * Set Stock ID
     * @param stockID
     */
    public void setStockID(String stockID);



	/**
	 * Returns true if the market is closed.
	 */
	public boolean closed();

	/**
	 * Close the market.
	 */
	public void close();

	/**
	 * Place a new order in the market.
	 */
	public void placeOrder(Order shout) throws AuctionException;

	/**
	 * Remove an order from the market.
	 */
	public void removeOrder(Order shout);

	/**
	 * Return the most recent order placed in the market.
	 */
	public Order getLastOrder() throws ShoutsNotVisibleException;

	/**
	 * Return the current auctioneer for this market.
	 */
	public Auctioneer getAuctioneer();

	/**
	 * Report the state of the market.
	 */
	public void printState();

	/**
	 * Handle a single clearing operation between two traders
	 *  for a single unit.
	 */
	public void clear(Order ask, Order bid, double price);

	/**
	 * Handle a single clearing operation between two traders
	 *  specifying the prices paid by each party and the volume of 
	 *  the trade.
	 */
	public void clear(Order ask, Order bid, double buyerCharge,
	    double sellerPayment, int quantity);

	/**
	 * Get the age of the market in unspecified units
	 */
	public int getRound();

	public int getDay();

	public int getAge();

	/**
	 * Get the remaining time in the current trading day (period).
	 */
	public int getRemainingTime();

	/**
	 * Get the number of traders known to be trading in the market.
	 */
	public int getNumberOfTraders();

	/**
	 * Find out whether the given shout has resulted in a transaction in the
	 * current round of trading.
	 */
	public boolean orderAccepted(Order shout) throws ShoutsNotVisibleException;

	/**
	 * Determine whether or not any transactions have occurred in the current round
	 * of trading.
	 */
	public boolean transactionsOccurred() throws ShoutsNotVisibleException;

	/**
	 * Return the price of the most transaction that occurred in the market.
	 * @return
	 */
	public double getLastTransactionPrice();
	
	/**
	 * Return the current price.  This is typically a mid-price or
	 * a recent transaction price.
	 */
	public double getCurrentPrice();

	public Iterator<Agent> getTraderIterator();

	public void remove(AbstractTradingAgent abstractTradingAgent);

	public Population getPopulation();

}