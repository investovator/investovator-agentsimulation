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



    public synchronized void add(Order shout) throws DuplicateShoutException {

        String secId = shout.getSecurityID();
        if(!orderBooks.containsKey(secId)) orderBooks.put(secId, new FourHeapOrderBook());
        FourHeapOrderBook orderBook = orderBooks.get(secId);
        orderBook.add(shout);
    }


    public synchronized void remove(Order shout) {

        String secId = shout.getSecurityID();
        FourHeapOrderBook orderBook = orderBooks.get(secId);
        if(orderBook == null) return;
        orderBook.remove(shout);

    }

    public synchronized void add(Order shout, Market market) throws DuplicateShoutException {

        String secId = market.getSecurityID();
        shout.setSecurityID(secId);
        if(!orderBooks.containsKey(secId)) orderBooks.put(secId, new FourHeapOrderBook());
        FourHeapOrderBook orderBook = orderBooks.get(secId);
        orderBook.add(shout);
    }


    public synchronized void remove(Order shout, Market market) {

        String secId = market.getSecurityID();
        FourHeapOrderBook orderBook = orderBooks.get(secId);
        if(orderBook == null) return;
        orderBook.remove(shout);

    }

    public synchronized OrderBook getOrderBook(Market market){
        if(!orderBooks.containsKey(market.getSecurityID())){
            setOrderBook(new FourHeapOrderBook(), market);
        }
        OrderBook result =  orderBooks.get(market.getSecurityID());

        return result;
    }

    public synchronized void setOrderBook(OrderBook orderBook, Market market){
        if(!orderBooks.containsKey(market.getSecurityID()))
            orderBooks.put(market.getSecurityID(), (FourHeapOrderBook)orderBook);
    }

    public synchronized void printState(Market market) {
        getOrderBook(market).printState();
    }


    public synchronized List<Order> matchOrders(Market market) {
        return getOrderBook(market).matchOrders();
    }


    public synchronized Order getHighestUnmatchedBid(Market market) {
        return getOrderBook(market).getHighestUnmatchedBid();
    }


    public synchronized Order getLowestMatchedBid(Market market) {
        return getOrderBook(market).getLowestMatchedBid();
    }


    public synchronized Order getLowestUnmatchedAsk(Market market) {
        return getOrderBook(market).getLowestUnmatchedAsk();
    }


    public synchronized Order getHighestMatchedAsk(Market market) {
        return getOrderBook(market).getHighestMatchedAsk();
    }


    public synchronized Iterator<Order> askIterator(Market market) {
        return getOrderBook(market).askIterator();
    }


    public synchronized Iterator<Order> bidIterator(Market market) {
        return getOrderBook(market).bidIterator();
    }


    public synchronized boolean isEmpty(Market market) {
        return getOrderBook(market).isEmpty();
    }


    public synchronized void reset(Market market) {
        getOrderBook(market).reset();
    }


}
