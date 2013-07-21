package net.sourceforge.jasa.market.rules;

import net.sourceforge.jasa.market.MarketQuote;
import net.sourceforge.jasa.market.Order;

/**
 * @author ishan
 * @version $Revision: 1.3 $
 */
public class StockMarketTimePriorityPricingPolicy extends DiscriminatoryPricingPolicy{

    @Override
    public double determineClearingPrice(Order bid, Order ask, MarketQuote clearingQuote) {
        return ask.getPrice();
    }
}
