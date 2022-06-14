package randomNumberGenerators;

import java.util.Random;

/**
 * random number generator for uniform distribution
 *
 * @author tadaki
 */
public class Uniform extends AbstractRandom {

    private final double min;
    private final double max;

    /**
     * Constructor
     *
     * @param min lower limit
     * @param max upper limit
     * @param random java.util.Random instance
     */
    public Uniform(double min, double max, Random random) {
        super(random);
        this.min = min;
        this.max = max;
    }
    /**
     * Constructor
     *
     * @param min lower limit
     * @param max upper limit
     * @param seed provided for java.util.Random
     */
    public Uniform(double min, double max, long seed) {
        super(seed);
        this.min = min;
        this.max = max;
    }

    @Override
    public double getNext() {
        return (max - min) * random.nextDouble() + min;
    }

}
