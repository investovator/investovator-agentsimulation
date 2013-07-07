package com.investovator.ats;

import net.sourceforge.jasa.market.DuplicateShoutException;
import net.sourceforge.jasa.market.Order;
import net.sourceforge.jasa.market.OrderBook;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: amila
 * Date: 7/7/13
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Exchange implements OrderBook {

    @Override
    public void add(Order shout) throws DuplicateShoutException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void remove(Order shout) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void printState() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Order> matchOrders() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Order getHighestUnmatchedBid() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Order getLowestMatchedBid() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Order getLowestUnmatchedAsk() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Order getHighestMatchedAsk() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator<Order> askIterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator<Order> bidIterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEmpty() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void reset() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
