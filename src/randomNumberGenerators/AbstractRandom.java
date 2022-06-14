package randomNumberGenerators;

import java.util.Random;

/**
 * Abstract Random Generator
 *
 * @author tadaki
 */
abstract public class AbstractRandom {

    protected final Random random;

    /**
     * Constructor by specifying seed of java.util.Random
     *
     * @param seed
     */
    public AbstractRandom(long seed) {
        random = new Random();
        if (seed > 0) {
            random.setSeed(seed);
        }
    }

    /**
     * Constructor by specifying java.util.Random instance
     *
     * @param random
     */
    public AbstractRandom(Random random) {
        this.random = random;
    }

    /**
     * generating next random number
     *
     * @return
     */
    abstract public double getNext();
}
