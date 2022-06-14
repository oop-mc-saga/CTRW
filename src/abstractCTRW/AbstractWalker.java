package abstractCTRW;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import randomNumberGenerators.AbstractRandom;

/**
 * Abstract Walker
 *
 * @author tadaki
 */
abstract public class AbstractWalker implements Runnable {

    protected final AbstractRandom waitingTimeRandom;
    protected final AbstractRandom motionRandom;
    protected int x = 0; // position
    protected double t = 0.; // time
    protected double maxT; //walker moves until maxT
    protected final int label;
    protected List<Point2D.Double> history;
    protected volatile boolean running = false;
    protected boolean debug = false;
    protected final double p;
    protected Set<AbstractWalker> stoppedWalkers;

    /**
     * Constructor
     *
     * @param waitingTimeRandom
     * @param motionRandom
     * @param p
     * @param label label of walker
     */
    public AbstractWalker(AbstractRandom waitingTimeRandom,
            AbstractRandom motionRandom, double p, int label) {
        this.waitingTimeRandom = waitingTimeRandom;
        this.motionRandom = motionRandom;
        this.label = label;
        this.p = p;
    }

    public void start(double maxT) {
        this.maxT = maxT;
        x = 0;
        t = 0.;
        history = new ArrayList<>();
        running = true;
    }

    /**
     * one move
     */
    protected void move() {
        double r = motionRandom.getNext();
        if (r < p) {
            x++;
        } else {
            x--;
        }
    }

    protected void stopMotion() {
        running = false;
        if (debug) {
            System.out.println("walker " + label + " stops");
        }
    }

    protected void continueMotion() {
        move();
        history.add(new Point2D.Double(t, x));
        if (debug) {
            System.out.println("walker " + label + " running");
        }
    }

    @Override
    public void run() {
        while (running) {
            double dt = waitingTimeRandom.getNext();
            if (dt + t > maxT) {
                stopMotion();
            } else {
                t += dt;
                continueMotion();
            }
        }
    }

    public List<Point2D.Double> getHistory() {
        return history;
    }

}
