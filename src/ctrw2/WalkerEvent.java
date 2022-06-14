package ctrw2;

import java.util.EventObject;
import abstractCTRW.AbstractWalker;
/**
 *
 * @author tadaki
 */
public class WalkerEvent extends EventObject {
    //Event types
    public static enum EventType{
        INITIALIZE,FINISH;
    }
    private final EventType eventType;
    
    public WalkerEvent(AbstractWalker source,EventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
    
}
