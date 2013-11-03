package net.sourceforge.jasa.report.timeseries;

import net.sourceforge.jabm.event.RoundFinishedEvent;
import net.sourceforge.jabm.event.SimEvent;
import net.sourceforge.jasa.event.OrderPlacedEvent;
import net.sourceforge.jasa.market.MarketQuote;
import net.sourceforge.jasa.market.MarketSimulation;
import net.sourceforge.jasa.market.Order;
import net.sourceforge.jasa.market.auctioneer.ContinuousDoubleAuctioneer;

/**
 * @author Amila Surendra
 * @version $Revision
 */
public class SpreadReportTimeseriesVariables extends PriceReportTimeseriesVariables {

    @Override
    public String getName() {
        return "market spread";
    }

    public double getPrice(OrderPlacedEvent event) {

        ContinuousDoubleAuctioneer auctioneer = (ContinuousDoubleAuctioneer) event.getAuction().getAuctioneer();
        Order bid = auctioneer.getOrderBook().getHighestUnmatchedBid();
        Order ask = auctioneer.getOrderBook().getLowestUnmatchedAsk();

        if(bid!= null && ask!= null){
            double spread = ask.getPrice() - bid.getPrice();

            if (Double.isInfinite(spread)) {
                spread = Double.NaN;
            }
            return spread;

        } else {
            return  0;
        }
    }

    @Override
    public void eventOccurred(SimEvent event) {
        if(event instanceof OrderPlacedEvent){
            this.price.add(getPrice((OrderPlacedEvent)event));
            this.time.add( ((OrderPlacedEvent) event).getTime());
        }
    }
}
