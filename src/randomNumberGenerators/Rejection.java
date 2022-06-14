package randomNumberGenerators;

import java.util.Random;
import java.util.function.DoubleFunction;

/**
 * Random number generator by rejection methods.
 *
 * @author tadaki
 */
public class Rejection extends AbstractRandom {

    private final DoubleFunction<Double> probDensity;//PDF
    private final double min;//lower limit
    private final double max;//
    private final double maxOfFunction;//the maximimum of PDF

    /**
     * Constructor
     *
     * @param probDensity PDF
     * @param min lower limit
     * @param max upper limit
     * @param maxOfFunction /the maximimum of PDF
     * @param seed provided for java.util.Random
     */
    public Rejection(DoubleFunction<Double> probDensity,
            double min, double max, double maxOfFunction, long seed) {
        super(seed);
        this.probDensity = probDensity;
        this.min = min;
        this.max = max;
        this.maxOfFunction = maxOfFunction;
    }

    /**
     * Constructor
     *
     * @param probDensity PDF
     * @param min lower limit
     * @param max upper limit
     * @param maxOfFunction the maximimum of PDF
     * @param random java.util.Random instance
     */
    public Rejection(DoubleFunction<Double> probDensity,
            double min, double max, double maxOfFunction, Random random) {
        super(random);
        this.probDensity = probDensity;
        this.min = min;
        this.max = max;
        this.maxOfFunction = maxOfFunction;
    }

    @Override
    public double getNext() {
        boolean done = false;
        double nextRandom = 0.;
        while (!done) {
            //generate two random numbers
            double x = random.nextDouble();
            double y = random.nextDouble();
            nextRandom = (max - min) * x + min;
            done = (y < probDensity.apply(nextRandom) / maxOfFunction);
        }
        return nextRandom;
    }

}
