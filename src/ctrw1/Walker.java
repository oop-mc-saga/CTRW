package ctrw1;

import abstractCTRW.*;
import java.util.Set;
import randomNumberGenerators.AbstractRandom;

/**
 *
 * @author tadaki
 */
public class Walker extends AbstractWalker {

    protected Set<AbstractWalker> stoppedWalkers;

    public Walker(AbstractRandom waitingTimeRandom, AbstractRandom motionRandom,
            double p, int label) {
        super(waitingTimeRandom, motionRandom, p, label);
    }

    @Override
    protected void stopMotion() {
        super.stopMotion();
        stoppedWalkers.add(this);
    }

    public void setStoppedWalkers(Set<AbstractWalker> stoppedWalkers) {
        this.stoppedWalkers = stoppedWalkers;
    }

}
