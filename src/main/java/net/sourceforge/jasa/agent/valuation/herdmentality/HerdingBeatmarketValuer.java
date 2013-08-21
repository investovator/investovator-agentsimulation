package net.sourceforge.jasa.agent.valuation.herdmentality;

import net.sourceforge.jasa.agent.herdmentality.HerdingAgent;
import net.sourceforge.jasa.market.Market;
import net.sourceforge.jasa.report.AbstractOrderBookReport;

public class HerdingBeatmarketValuer extends HerdingValuer {
	
	protected HerdingAgent agent;
	
	public HerdingBeatmarketValuer(HerdingAgent agent, AbstractOrderBookReport orderBookReport) {
		super(orderBookReport);
		this.agent = agent;
	}

    public HerdingBeatmarketValuer (){
        //TODO
    }

    public double determineValue(Market auction){
		double value;
		boolean marketIsBid = agent.alignment();
		if(marketIsBid){
			value = agent.cohesion() + agent.separation();
		} else {
			value = agent.cohesion() - agent.separation();
		}
		return value;
	}
}
