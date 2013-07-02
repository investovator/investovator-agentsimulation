package net.sourceforge.jasa.report;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sourceforge.jabm.event.SimEvent;
import net.sourceforge.jabm.report.XYReportVariables;
import net.sourceforge.jasa.event.TransactionExecutedEvent;

public class TransactionPriceReportVariables implements XYReportVariables {

	protected double lastTransactionPrice;
	
	protected int time;
	
	public static final String NAME = "transaction";
	
	public static final String TRANSACTIONPRICE_VAR = "price";
	
	@Override
	public Map<Object, Number> getVariableBindings() {
		LinkedHashMap<Object, Number> result = 
			new LinkedHashMap<Object, Number>();
		result.put(getName() + ".t", time);
		result.put(getName() + "." + TRANSACTIONPRICE_VAR, lastTransactionPrice);
		return result;
	}

	@Override
	public void compute(SimEvent ev) {
		
	}

	@Override
	public void dispose(SimEvent event) {
		// Do nothing
	}

	@Override
	public void initialise(SimEvent event) {
		// Do nothing

	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public Number getX(int seriesIndex) {
		return this.time;
	}

	@Override
	public Number getY(int seriesIndex) {
		return this.lastTransactionPrice;
	}
	
	public int getNumberOfSeries() {
		return 1;
	}

	@Override
	public void eventOccurred(SimEvent ev) {
		if (ev instanceof TransactionExecutedEvent) {
			TransactionExecutedEvent event = (TransactionExecutedEvent) ev;
			this.lastTransactionPrice = event.getPrice();
			this.time = event.getTime();
		}
	}

	@Override
	public List<Object> getyVariableNames() {
		LinkedList<Object> result = new LinkedList<Object>();
		result.add(getName() + "." + TRANSACTIONPRICE_VAR);
		return result;
	}

	@Override
	public String getxVariableName() {
		return getName() + ".t";
	}

}
