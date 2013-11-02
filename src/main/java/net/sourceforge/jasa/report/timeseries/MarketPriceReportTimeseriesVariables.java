package net.sourceforge.jasa.report.timeseries;

import java.io.Serializable;
import java.util.*;

import net.sourceforge.jabm.event.RoundFinishedEvent;
import net.sourceforge.jabm.event.SimEvent;
import net.sourceforge.jabm.report.XYReportVariables;

public abstract class MarketPriceReportTimeseriesVariables implements Serializable,
        XYReportVariables {

    protected ArrayList<Number> price = new ArrayList<Number>();

    protected ArrayList<Number> time = new ArrayList<Number>();

    public static final String PRICE_VAR = "price";

    public abstract Map<Object, ArrayList<Number>> getTimeseriesVariableBindings();

    @Override
    public Map<Object, Number> getVariableBindings() {
        LinkedHashMap<Object, Number> result =
                new LinkedHashMap<Object, Number>();
        result.put(getName() + ".t", time.get(time.size()-1));
        result.put(getName() + "." + PRICE_VAR, price.get(price.size()-1));
        return result;
    }

    @Override
    public void compute(SimEvent ev) {
        eventOccurred(ev);
    }

    @Override
    public void dispose(SimEvent event) {
        // Do nothing
    }

    @Override
    public void initialise(SimEvent event) {
        this.price = new ArrayList<Number>();
        this.time = new ArrayList<Number>();
    }

    @Override
    public Number getX(int seriesIndex) {
        return this.time.get(seriesIndex);
    }

    @Override
    public Number getY(int seriesIndex) {
        return this.price.get(seriesIndex);
    }

    public int getNumberOfSeries() {
        return 1;
    }

    @Override
    public void eventOccurred(SimEvent ev) {
        if (ev instanceof RoundFinishedEvent) {
            onRoundFinished((RoundFinishedEvent) ev);
        }
    }

    public void onRoundFinished(RoundFinishedEvent event) {
        this.price.add(getPrice(event));
        this.time.add( (int) event.getSimulation().getSimulationTime().getTicks());
    }

    @Override
    public List<Object> getyVariableNames() {
        LinkedList<Object> result = new LinkedList<Object>();
        result.add(getName() + "." + PRICE_VAR);
        return result;
    }

    @Override
    public String getxVariableName() {
        return getName() + ".t";
    }

    @Override
    public abstract String getName();

    public abstract double getPrice(RoundFinishedEvent event);

}
