package simplest;

import randomNumberGenerators.AbstractRandom;

/**
 * Random Walker (discrete time and position)
 *
 * @author tadaki
 */
public class Walker {

    protected final AbstractRandom motionRandom;
    protected final double p;//probability for moving right (+)
    protected int x; //position
    protected int t; //time step

    /**
     * Constructor
     *
     * @param motionRandom random number generator for motion
     * @param p probability for moving right
     */
    public Walker(AbstractRandom motionRandom, double p) {
        this.motionRandom = motionRandom;
        this.p = p;
    }

    /**
     * Initialize position and time
     */
    public void start() {
        x = 0;
        t = 0;
    }

    /**
     * Move one step
     *
     * @return new position
     */
    public int move() {
        double r = motionRandom.getNext();
        if (r < p) {
            x++; //move right
        } else {
            x--; // move left
        }
        t++;
        return x;
    }

    public double getX() {
        return x;
    }

}
