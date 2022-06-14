package simplest;

import java.util.ArrayList;
import java.util.List;
import randomNumberGenerators.AbstractRandom;

/**
 * Main class for random walk simulation
 *
 * @author tadaki
 */
public class RandomWalk {

    protected final List<Walker> walkers;

    /**
     * Constructor
     *
     * @param n number of walkers
     * @param p probalibity for moving right
     * @param random random number generator for motion
     */
    public RandomWalk(int n, double p, AbstractRandom random) {
        walkers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(random, p));
        }
    }

    /**
     * Start simulation
     */
    public void start() {
        walkers.forEach(w -> w.start());
    }

    /**
     * one time step
     */
    public void update() {
        walkers.forEach(w -> w.move());
    }

    public List<Walker> getWalkers() {
        return walkers;
    }

}
