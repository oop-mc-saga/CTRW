package ctrw1;

import abstractCTRW.*;
import java.util.Collections;
import java.util.HashSet;
import randomNumberGenerators.AbstractRandom;

/**
 *
 * @author tadaki
 */
public class CTRW extends AbstractCTRW {

    public CTRW(AbstractRandom waitingTimeRandom, AbstractRandom motionRandom,
            int n, double p, double maxT) {
        super(maxT);
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(waitingTimeRandom, motionRandom, p, i));
        }
    }

    @Override
    public void start() {
        //stoppedWalkers are used in walker instances running on threads
        //Need protection 
        stoppedWalkers = Collections.synchronizedSet(new HashSet<>());
        walkers.forEach(w -> ((Walker) w).setStoppedWalkers(stoppedWalkers));
        super.start_sub();
    }

}
