package org.investovator.multiasset.simulation;

import net.sourceforge.jabm.SimulationManager;
import net.sourceforge.jabm.SpringSimulationController;
import net.sourceforge.jabm.report.Report;
import net.sourceforge.jabm.spring.BeanFactorySingleton;
import net.sourceforge.jabm.util.SystemProperties;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ishan
 * @version: ${Revision}
 */
public class HeadlessMultiAssetSimulationManager extends SimulationManager {

    public static final String SIMULATION_MANAGER_BEAN = "simulationManager";

    private static ArrayList<SpringSimulationController> simulationControllers;

    static Logger logger = Logger.getLogger(HeadlessMultiAssetSimulationManager.class);

    protected Thread simulationThread;

    public HeadlessMultiAssetSimulationManager() {
        SystemProperties systemProperties = SystemProperties
                .jabsConfiguration();
        propFile = systemProperties
                .getProperty(SystemProperties.PROPERTY_PROPFILE);
        varFile = systemProperties.getProperty(
                SystemProperties.PROPERTY_VARFILE);
        baseDirName = systemProperties.getProperty(
                SystemProperties.PROPERTY_BASE_DIR_NAME, "data");
        configOnly = Boolean.parseBoolean(systemProperties.getProperty(
                SystemProperties.PROPERTY_CONFIG_ONLY, "false"));
        generateSeeds = Boolean.parseBoolean(systemProperties.getProperty(
                SystemProperties.PROPERTY_SEEDS, "false"));
        seedMask = Integer.parseInt(systemProperties.getProperty(
                SystemProperties.PROPERTY_SEED_MASK, "-1"));
    }

    public HeadlessMultiAssetSimulationManager getHeadlessMultiAssetSimulationManager(){
        return  (HeadlessMultiAssetSimulationManager) BeanFactorySingleton
                .getBean(SIMULATION_MANAGER_BEAN);
    }

    @Override
    public void run() {
        if (propFile != null) {
            runSingleExperiment(propFile);
        } else {
            if (varFile != null) {
                setup(varFile, baseDirName, generateSeeds,
                        seedMask);
            } else {
                if (configOnly) {
                    setup(baseDirName, generateSeeds,
                            seedMask);
                } else {
                    runSingleExperiment();
                }
            }
        }
    }

    public void runSingleExperiment() {
        launchSimulations();
        //TODO - what does this line do? (from MultiAssetSimManager)
        this.simulationThread = new Thread(this);
    }

    public void launchSimulations() {
        logger.info("Starting...");
        long start = System.currentTimeMillis();

        ArrayList<Thread> simulationThreads = new ArrayList<Thread>();
        for (SpringSimulationController controller : simulationControllers){
            Thread controllerThread = new Thread(controller);
            simulationThreads.add(controllerThread);
        }

        for (Thread simulationThread : simulationThreads){
            simulationThread.start();
        }

        for (Thread simulationThread : simulationThreads){
            try {
                simulationThread.join();
            } catch (InterruptedException ignored) {
                //TODO
            }
        }

        long finish = System.currentTimeMillis();
        long duration = finish - start;
        logger.info("all done.");
        logger.info("completed simulation(s) in " + duration + "ms.");
    }

    public void slow(int slowSleepInterval) {
        for (SpringSimulationController controller: simulationControllers) {
            controller.slow(slowSleepInterval);
        }
    }

    public void setSimulations(ArrayList<SpringSimulationController> securities) {
        System.out.println("called");
        simulationControllers = new ArrayList<SpringSimulationController>(securities);
    }

    public void terminate() {
        for (SpringSimulationController controller: simulationControllers) {
            controller.terminate();
        }
    }

    public void pause() {
        for (SpringSimulationController controller: simulationControllers) {
            controller.getSimulation().pause();
        }
    }

    public void resume(){
        for (SpringSimulationController controller: simulationControllers) {
            controller.getSimulation().resume();
        }
    }

    public List<Report> getReports() {
        List<Report> reports = new ArrayList<Report>();
        for (SpringSimulationController controller: simulationControllers) {
            reports.addAll(controller.getReports());
        }
        return reports;
    }

    public static void main(String[] args) {
        HeadlessMultiAssetSimulationManager manager = new HeadlessMultiAssetSimulationManager();
        manager.run();
    }




}
