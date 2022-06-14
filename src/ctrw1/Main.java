package ctrw1;

import abstractCTRW.*;
import histogram.Histogram;
import java.io.IOException;
import java.util.Random;
import java.util.function.DoubleFunction;
import randomNumberGenerators.*;

/**
 *
 * @author tadaki
 */
public class Main extends AbstractMain {

    public static void main(String args[]) throws IOException {
        double lambda = 1.;//average for exponential function
        double p = 0.5;//probability for moving right
        int numWalkers = 10000;
        double maxT = 500;
        int sleep = 1000;
        long seed = 48L;

        //Exponential distribution
        DoubleFunction<Double> invProDist = x -> {
            return -lambda * Math.log(1. - x / lambda);
        };
        Random random = new Random(seed);
        AbstractRandom waitingTimeRandom = new Transform(invProDist, random);
        AbstractRandom motionRandom = new Uniform(0., 1., random);
        CTRW ctrw = new CTRW(waitingTimeRandom, motionRandom,
                numWalkers, p, maxT);
        ctrw.start();

        Histogram histogram = new Histogram(-100, 100, 50);
        startThread(ctrw, histogram, "ctrw1.txt", "ctw1History.txt",sleep);
    }
}
