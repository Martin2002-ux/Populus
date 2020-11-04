package edu.umn.ecology.populus.plot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the LiveGraph class enables a graph that implements the Stepper interface to
 * be a "live" graph, meaning that the plotting of the points is staggered, and so
 * the user can watch the points being plotted one by one.
 * <p>Title: Populus</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002, 2015</p>
 * <p>Company: University of Minnesota</p>
 *
 * @author Amos Anderson
 * @version 5.2
 */
public class LiveGraph implements ActionListener {
    javax.swing.Timer t;
    Stepper graph;

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!graph.stepGraph()) {
            t.stop();
            System.gc();
        }
    }

    public LiveGraph(Stepper st, int increment, int sleepMillis) {
        graph = st;
        t = new javax.swing.Timer(sleepMillis, this);
        graph.setUpLive(increment);
        run();
    }

    public void run() {
        t.start();
    }

    public void setPauseTime(int newTime) {
        t.setDelay(newTime);
    }
}

