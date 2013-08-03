/*
 * investovator, Stock Market Gaming Framework
 *     Copyright (C) 2013  investovator
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sourceforge.jasa.market;

import net.sourceforge.jabm.Population;
import net.sourceforge.jabm.Simulation;
import net.sourceforge.jabm.SimulationController;
import net.sourceforge.jabm.SimulationTime;
import net.sourceforge.jabm.agent.Agent;

import java.util.ArrayList;

/**
 * @author ishan
 * @author rajith
 * @version $Revision$
 */
public class MarketController implements Simulation {

    private ArrayList<MarketSimulation> securities = new ArrayList<MarketSimulation>();


    /**
     * Fetch the simulation controller for this simulation.
     */
    @Override
    public SimulationController getSimulationController() {
        return securities.get(0).getSimulationController();
    }

    /**
     * Query the current simulation time.
     */
    @Override
    public SimulationTime getSimulationTime() {
        return securities.get(0).getSimulationTime();
    }

    /**
     * Fetch the Population of agents for this simulation.
     */
    @Override
    public Population getPopulation() {
        Population population = new Population();
        for (MarketSimulation security: securities){
            for (Agent agent: security.getPopulation().getAgents()){
                population.add(agent);
            }
        }
        return population;
    }

    /**
     * Pause the simulation.
     */
    @Override
    public void pause() {
        for (MarketSimulation simulation: securities){
           simulation.pause();
        }
    }

    /**
     * Resume the simulation after pausing.
     */
    @Override
    public void resume() {
        for (MarketSimulation simulation: securities){
            simulation.resume();
        }
    }

    /**
     * Terminate the simulation.
     */
    @Override
    public void terminate() {
        for (MarketSimulation simulation: securities){
            simulation.terminate();
        }
    }

    /**
     * Slow down the simulation.
     *
     * @param slowSleepInterval The number of ms to sleep between ticks.
     */
    @Override
    public void slow(int slowSleepInterval) {
        for (MarketSimulation simulation: securities){
            simulation.slow(slowSleepInterval);
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        ArrayList<Thread> marketThreads = new ArrayList<Thread>();
        for (MarketSimulation market: securities){
            marketThreads.add(new Thread(market));
        }

        for (Thread marketThread: marketThreads){
            marketThread.start();
        }

        for (Thread marketThread: marketThreads){
            try {
                marketThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<MarketSimulation> getSecurities() {
        return securities;
    }

    public void setSecurities(ArrayList<MarketSimulation> securities) {
        this.securities = securities;
    }
}
