package histogram;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Histogram class
 *
 * @author tadaki
 */
public class Histogram {

    private final double min;//lower limit
    private final double max;//upper limit
    private final double binWidth;//bin width
    private final int hist[];//histogram

    /**
     * Constructor
     *
     * @param min lower limit
     * @param max upper limit
     * @param numBin number of bins
     */
    public Histogram(double min, double max, int numBin) {
        this.min = min;
        this.max = max;
        binWidth = (max - min) / numBin;
        hist = new int[numBin];
    }

    /**
     * Constructor
     *
     * @param min lower limit
     * @param max upper limit
     * @param binWidth bin width
     */
    public Histogram(double min, double max, double binWidth) {
        this.min = min;
        this.binWidth = binWidth;
        int numBin = (int) ((max - min) / binWidth);
        if (min + numBin * binWidth < max) {
            numBin++;
        }
        this.max = min + numBin * binWidth;
        hist = new int[numBin];
    }

    /**
     * register one value
     *
     * @param x
     * @return
     */
    public int put(double x) {
        if (x < min || x >= max) {//out of range
            return -1;
        }
        //find the index containing x
        int binIndex = (int) ((x - min) / binWidth);
        //increase bin count
        hist[binIndex]++;
        return binIndex;
    }

    public double getLowerBound() {
        return min;
    }

    public double getUpperBound() {
        return max;
    }

    public int[] getHist() {
        return hist;
    }

    public int getCount(int index){
        if(index<0||index>=hist.length)return -1;
        return hist[index];
    }
    
    /**
     * Return the result as a list
     *
     * Values are normalized
     *
     * @return
     */
    public List<Point2D.Double> calculateFrequency() {
        List<Point2D.Double> pointList = 
                Collections.synchronizedList(new ArrayList<>());
        //total number of counts
        int sum = 0;
        for (int i = 0; i < hist.length; i++) {
            sum += hist[i];
        }

        for (int i = 0; i < hist.length; i++) {
            double x = min + i * binWidth + binWidth / 2.;//center of bin
            double y = (double) hist[i] / sum / binWidth;//ratio of counts
            pointList.add(new Point2D.Double(x, y));
        }
        return pointList;
    }

    /**
     * Confirm plist is normalized
     *
     * @param plist
     * @return
     */
    public double checkNormalization(List<Point2D.Double> plist) {
        double frequency = 0.;
        for (Point2D.Double p : plist) {
            frequency += p.y * binWidth;
        }
        return frequency;
    }
}
