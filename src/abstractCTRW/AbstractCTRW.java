package abstractCTRW;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Abstract class of CTRW
 * 
 * @author tadaki
 */
public abstract class AbstractCTRW {

    protected final List<AbstractWalker> walkers;
    protected Set<AbstractWalker> stoppedWalkers;
    protected final double maxT;

    /**
     * Constructor
     *
     * @param maxT
     */
    public AbstractCTRW(double maxT) {
        this.maxT = maxT;
        walkers = new ArrayList<>();
    }

    abstract public void start();
    
    protected void start_sub() {
        walkers.forEach(w -> {
            w.start(maxT);
            new Thread(w).start();
        });
    }

    public List<AbstractWalker> getWalkers() {
        return walkers;
    }

    /**
     * Confirm that all walkers stop
     *
     * @return
     */
    public boolean isFinished() {
        return (stoppedWalkers.size() == walkers.size());
    }
}
