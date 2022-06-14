package ctrw2;

import abstractCTRW.*;
import randomNumberGenerators.AbstractRandom;

/**
 *
 * @author tadaki
 */
public class Walker extends AbstractWalker {

    private WalkerEventListener listener;

    public Walker(AbstractRandom waitingTimeRandom, AbstractRandom motionRandom,
            double p, int label) {
        super(waitingTimeRandom, motionRandom, p, label);
    }

    @Override
    protected void stopMotion() {
        super.stopMotion();
        fireActionListener();
    }

    public void setListener(WalkerEventListener listener) {
        this.listener = listener;
    }

    private void fireActionListener() {
        listener.stateChanged(
                new WalkerEvent(this, WalkerEvent.EventType.FINISH));
    }
}
