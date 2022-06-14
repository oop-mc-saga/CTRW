package randomNumberGenerators;

import java.util.Random;
import java.util.function.DoubleFunction;

/**
 * Random number generator by transform methods.
 *
 * @author tadaki
 */
public class Transform extends AbstractRandom {

    private final DoubleFunction<Double> invProDist;//inverse of PDF

    /**
     * Constructor
     *
     * @param invProDist inverse of PDF
     * @param seed provided for java.util.Random
     */
    public Transform(DoubleFunction<Double> invProDist, long seed) {
        super(seed);
        this.invProDist = invProDist;
    }

    /**
     * Constructor
     *
     * @param invProDist inverse of PDF
     * @param random java.util.Random instance
     */    
    public Transform(DoubleFunction<Double> invProDist, Random random) {
        super(random);
        this.invProDist = invProDist;
    }

    @Override
    public double getNext() {
        double x = random.nextDouble();
        return invProDist.apply(x);
    }

}
