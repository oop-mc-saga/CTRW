package ctrw2;
import abstractCTRW.*;
import java.util.Collections;
import java.util.HashSet;
import randomNumberGenerators.AbstractRandom;
/**
 *
 * @author tadaki
 */
public class CTRW extends AbstractCTRW implements WalkerEventListener{

    public CTRW(AbstractRandom waitingTimeRandom, AbstractRandom motionRandom,
            int n, double p, double maxT) {
        super(maxT);
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(waitingTimeRandom, motionRandom, p, i));
        }
    }

    @Override
    public void start() {
        stoppedWalkers = Collections.synchronizedSet(new HashSet<>());
        walkers.forEach(w->((Walker)w).setListener(this));
        start_sub();
    }

    @Override
    public synchronized void stateChanged(WalkerEvent e) {
        stoppedWalkers.add((Walker)e.getSource());
    }
    
}
