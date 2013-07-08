package org.investovator.jasa.mockGui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: amila
 * Date: 7/7/13
 * Time: 8:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class OrderViewer extends JFrame {

    public JPanel rootPane;
    private JTextArea textArea1;


    public void printLine(final String line){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textArea1.append(line);
            }
        });

    }




}
