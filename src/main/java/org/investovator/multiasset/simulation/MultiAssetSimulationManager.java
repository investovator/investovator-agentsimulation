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

package org.investovator.multiasset.simulation;

import net.infonode.docking.View;
import net.infonode.docking.util.ViewMap;
import net.sourceforge.jabm.DesktopSimulationManager;
import net.sourceforge.jabm.SpringSimulationController;
import net.sourceforge.jabm.Version;
import net.sourceforge.jabm.report.Report;
import net.sourceforge.jabm.report.ReportWithGUI;
import net.sourceforge.jabm.spring.BeanFactorySingleton;
import net.sourceforge.jabm.view.PropertiesEditor;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rajith
 * @version $Revision$
 */
public class MultiAssetSimulationManager extends DesktopSimulationManager {

    public static final String SIMULATION_MANAGER_BEAN = "simulationManager";

    static Logger logger = Logger.getLogger(MultiAssetSimulationManager.class);

    private MultiAssetSimulationManager multiAssetSimulationManager;

    private ArrayList<SpringSimulationController> simulationControllers;

    private static final int ICON_SIZE = 8;

    private static final Icon VIEW_ICON = new Icon() {
        public int getIconHeight() {
            return ICON_SIZE;
        }

        public int getIconWidth() {
            return ICON_SIZE;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Color oldColor = g.getColor();

            g.setColor(new Color(70, 70, 70));
            g.fillRect(x, y, ICON_SIZE, ICON_SIZE);

            g.setColor(new Color(100, 230, 100));
            g.fillRect(x + 1, y + 1, ICON_SIZE - 2, ICON_SIZE - 2);

            g.setColor(oldColor);
        }
    };

    @Override
    public void initialise(){
        this.simulationControllers = new ArrayList<SpringSimulationController>();
        this.multiAssetSimulationManager = getMultiAssetSimulationManager();
        loadSimulationProperties();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initialiseGUI();
            }
        });
    }

    @Override
    public JToolBar createToolBar() {

        JToolBar toolBar = new JToolBar();

        ImageIcon runIcon = createImageIcon("/net/sourceforge/jabm/icons/Play24.gif",
                "run");
        runButton = new JButton(runIcon);
        runButton.setToolTipText("Launch (a batch of) simulation(s)");
        this.simulationThread = new Thread(this);
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runButton.setEnabled(false);
                simulationThread.start();
            }
        });
        toolBar.add(runButton);

        ImageIcon stopIcon = createImageIcon("/net/sourceforge/jabm/icons/Stop24.gif",
                "stop");
        terminateButton = new JButton(stopIcon);
        terminateButton.setToolTipText("Terminate all simulations");
        terminateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                terminate();
            }
        });
        toolBar.add(terminateButton);

        ImageIcon pauseIcon = createImageIcon("/net/sourceforge/jabm/icons/Pause24.gif",
                "pause");
        pauseButton = new JToggleButton(pauseIcon);
        pauseButton.setToolTipText("Pause the current simulation");
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pauseButton.isSelected()) {
                    pause();
                } else {
                    resume();
                }
            }
        });
        toolBar.add(pauseButton);

        toolBar.addSeparator();

        JSlider speedSlider = new JSlider(0, 1000, 0);
        speedSlider.setToolTipText("Simulation step sleep interval in ms");
        speedSlider.setMajorTickSpacing(250);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                multiAssetSimulationManager.slow(((JSlider) e
                        .getSource()).getValue());
            }

        });
        toolBar.add(speedSlider);

        return toolBar;
    }

    @Override
    public void initialiseViews() {
        viewMap = new ViewMap();

        reportViews = new LinkedList<View>();
        int viewNumber = 0;
        for (Report report : getReports()) {
            if (report instanceof ReportWithGUI) {
                String name = report.getName();
                if (name == null || "".equals(name)) {
                    name = "Report " + viewNumber;
                }
                View view = new View(name, VIEW_ICON,
                        ((ReportWithGUI) report).getComponent());
                viewMap.addView(viewNumber, view);
                reportViews.add(view);
                viewNumber++;
            }
        }

        builtinViews  = new LinkedList<View>();

        outputView =
                new View("Output Console", VIEW_ICON, createOutputFrame());
        builtinViews.add(outputView);
        viewMap.addView(viewNumber, outputView);
        viewNumber++;

        if (this.simulationProperties != null) {
            propertiesEditor = new PropertiesEditor(simulationProperties);
            propertiesView = new View("Simulation Properties", VIEW_ICON,
                    new JScrollPane(propertiesEditor));
            builtinViews.add(propertiesView);
            viewMap.addView(viewNumber, propertiesView);
            this.propertiesViewId = viewNumber;
            viewNumber++;
        }
    }

    @Override
    public void terminate() {
        for (SpringSimulationController controller: simulationControllers) {
            controller.terminate();
        }
    }

    @Override
    public void pause() {
        for (SpringSimulationController controller: simulationControllers) {
            controller.getSimulation().pause();
        }
    }

    @Override
    public void resume(){
        for (SpringSimulationController controller: simulationControllers) {
            controller.getSimulation().resume();
        }
    }

    @Override
    public void run() {
        outputView.restoreFocus();
        this.pauseButton.setEnabled(true);
        this.terminateButton.setEnabled(true);
        runSingleExperiment();
        this.runButton.setEnabled(true);
        this.terminateButton.setEnabled(false);
        this.pauseButton.setEnabled(false);
    }

    @Override
    public void runSingleExperiment() {
        launchSimulations();
    }

    public void launchSimulations() {
        logger.info("Starting...");
        long start = System.currentTimeMillis();
        for (SpringSimulationController controller: simulationControllers){
           controller.run();
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

    public MultiAssetSimulationManager getMultiAssetSimulationManager(){
        return  (MultiAssetSimulationManager) BeanFactorySingleton
                .getBean(SIMULATION_MANAGER_BEAN);
    }

    @Override
    public List<Report> getReports() {
        List<Report> reports = new ArrayList<Report>();
        for (SpringSimulationController controller: simulationControllers) {
            reports.addAll(controller.getReports());
        }
        return reports;
    }

    @Override
    protected void helpDialog() {
        String modelDescription = getMultiAssetSimulationManager()
                .simulationControllers.get(0).getModelDescription();  //TODO
        String message = Version.getVerboseVersion() + "\n"
                + Version.getCopyright();
        if (modelDescription != null) {
            message += "\n\n" + "Model: " + modelDescription;
        }
        JOptionPane.showMessageDialog(this.desktopFrame,
                message, "About JABM",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void setSimulations(ArrayList<SpringSimulationController> securities) {
        this.simulationControllers = securities;
    }

    public static void main(String[] args) {
        MultiAssetSimulationManager manager = new MultiAssetSimulationManager();
        manager.initialise();
    }

}
