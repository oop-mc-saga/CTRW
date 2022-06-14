package simplest;

import java.util.List;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintStream;
import histogram.Histogram;
import randomNumberGenerators.Uniform;

/**
 *
 * @author tadaki
 */
public class Main {

    public static void main(String args[]) throws IOException {
        long seed = 48L;//seed of random number generator
        int maxT = 100;//maximum iteration
        int n = 10000; // number of walkers
        double p = 0.5; // probability for moving right
        int numBin = 100;//number of bins for histogram

        Uniform uniform = new Uniform(0., 1., seed);
        Histogram histogram = new Histogram(-maxT, maxT, numBin);

        RandomWalk rw = new RandomWalk(n, p, uniform);
        for (int t = 0; t < maxT; t++) {//update system
            rw.update();
        }
        //get positions of all walkers
        rw.getWalkers().forEach(
                w -> histogram.put(w.getX())
        );

        List<Point2D.Double> plist = histogram.calculateFrequency();
        //output histogram to file
        try (PrintStream out = new PrintStream("simplestRW.txt")) {
            //Header part
            out.println("# simplest random walk");
            out.println("# p = " + p);
            out.println("# n = " + n);
            out.println("#");
            //output data
            plist.forEach(
                    v -> out.println(v.x + " " + v.y)
            );
        }
    }
}
