package abstractCTRW;

import histogram.Histogram;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author tadaki
 */
public class AbstractMain {

    protected static void startThread(AbstractCTRW ctrw, Histogram histogram,
            String filename,String historyFilename,int sleep) {
        // Defining a new thread
        Thread thread = new Thread(new Runnable() {
            private volatile boolean running = true;

            @Override
            public void run() {
                while (running) {
                    if (ctrw.isFinished()) {//confirm ctrw is finished
                        List<AbstractWalker> walkers = ctrw.getWalkers();
                        walkers.forEach(w -> {
                            w.getHistory().forEach(p -> histogram.put(p.y));
                        });
                        try {
                            //output histgram
                            outputData(histogram, filename);
                            //output motion history of a walker
                            drawHistory(walkers.get(0),historyFilename);
                        } catch (IOException e) {
                        }
                        running = false;
                    }
                    // continue running
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * Output histogram to file
     *
     * @param histogram
     * @param filename
     * @throws IOException
     */
    protected static void outputData(Histogram histogram, String filename)
            throws IOException {
        List<Point2D.Double> plist = histogram.calculateFrequency();
        try (PrintStream out = new PrintStream(filename)) {
            plist.forEach(p -> out.println(p.x + " " + p.y));
        }
    }
    
    /**
     * Output history of one walker to file
     * 
     * @param w
     * @param filename
     * @throws IOException 
     */
    protected static void drawHistory(AbstractWalker w,String filename)
            throws IOException{
        List<Point2D.Double> history = w.getHistory();
        try(PrintStream out = new PrintStream(filename)){
            history.stream().forEachOrdered(h->out.println(h.x+" "+h.y));
        }
    }
}
