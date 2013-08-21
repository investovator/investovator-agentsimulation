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

package net.sourceforge.jasa.agent.valuation.herdmentality;

import net.sourceforge.jabm.EventScheduler;
import net.sourceforge.jabm.event.SimEvent;
import net.sourceforge.jasa.agent.TradingAgent;
import net.sourceforge.jasa.agent.herdmentality.HerdingAgent;
import net.sourceforge.jasa.agent.herdmentality.HerdingObject;
import net.sourceforge.jasa.agent.valuation.AbstractValuationPolicy;
import net.sourceforge.jasa.market.Market;
import net.sourceforge.jasa.report.AbstractOrderBookReport;
import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;

/**
 * A HerdingValuation that calculate its value from the OrderBookReport given to it. 
 * 
 * @author Wilhelm Eklund
 */

public class HerdingValuer extends AbstractValuationPolicy implements Serializable, HerdingObject {
	
	//protected HerdingAgent agent;
    private AbstractOrderBookReport orderBookReport;
	
	public HerdingValuer(AbstractOrderBookReport orderBookReport) {
		this.setOrderBookReport(orderBookReport);
	}

    public HerdingValuer(){
        //TODO
    }
	
	public double determineValue(Market auction) {
		return orderBookReport.getValue();
	}
	
	public void consumeUnit(Market auction) {
		// Do nothing
	}
	
	public void eventOccurred(SimEvent event) {
		// Do nothing
	}
	
	public void reset() {
	}
	
	public void initialise() {
	    orderBookReport.reset();
	}
	
	public void setAgent(TradingAgent agent) {
		this.setOrderBookReport(((HerdingAgent)agent).getOrderBookReport());
	}
	
	public String toString() {
		return "(" + getClass() + " value:" + determineValue(null) + ")";
	}
	
	@Override
	public void subscribeToEvents(EventScheduler scheduler) {
		// Do nothing
	}
	
	public AbstractOrderBookReport getOrderBookReport(){
		return this.orderBookReport;
	}
	
	public boolean isHerdTop(){
		return orderBookReport.isHerdTop();
	}

    @Required
    public void setOrderBookReport(AbstractOrderBookReport orderBookReport) {
        this.orderBookReport = orderBookReport;
    }
}