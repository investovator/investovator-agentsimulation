package com.investovator.ats;

import net.sourceforge.jasa.market.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: amila
 * Date: 7/7/13
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Exchange {

    HashMap<String, FourHeapOrderBook> orderBooks = new HashMap<String, FourHeapOrderBook>();



    public void add(Order shout) throws DuplicateShoutException {

        String secId = shout.getSecurityID();
        if(!orderBooks.containsKey(secId)) orderBooks.put(secId, new FourHeapOrderBook());
        FourHeapOrderBook orderBook = orderBooks.get(secId);
        orderBook.add(shout);
    }


    public void remove(Order shout) {

        String secId = shout.getSecurityID();
        FourHeapOrderBook orderBook = orderBooks.get(secId);
        if(orderBook == null) return;
        orderBook.remove(shout);

    }

    private FourHeapOrderBook getOrderBook(Market market){
        return orderBooks.get(market.getSecurityID());
    }

    public void printState(Market market) {
        getOrderBook(market).printState();
    }


    public List<Order> matchOrders(Market market) {
        return getOrderBook(market).matchOrders();
    }


    public Order getHighestUnmatchedBid(Market market) {
        return getOrderBook(market).getHighestUnmatchedBid();
    }


    public Order getLowestMatchedBid(Market market) {
        return getOrderBook(market).getLowestMatchedBid();
    }


    public Order getLowestUnmatchedAsk(Market market) {
        return getOrderBook(market).getLowestUnmatchedAsk();
    }


    public Order getHighestMatchedAsk(Market market) {
        return getOrderBook(market).getHighestMatchedAsk();
    }


    public Iterator<Order> askIterator(Market market) {
        return getOrderBook(market).askIterator();
    }


    public Iterator<Order> bidIterator(Market market) {
        return getOrderBook(market).bidIterator();
    }


    public boolean isEmpty(Market market) {
        return getOrderBook(market).isEmpty();
    }


    public void reset(Market market) {
        getOrderBook(market).reset();
    }

}
