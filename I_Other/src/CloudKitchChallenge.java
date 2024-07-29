import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


public class CloudKitchChallenge {
	
	
		

	class Action {
	    private long timestamp;
	    private String id;
	    private ActionType actionType;

	    public Action(long timestamp, String id, ActionType actionType) {
	        this.timestamp = timestamp;
	        this.id = id;
	        this.actionType = actionType;
	    }
	}
	
	enum ActionType {
		PLACE,
		MOVE,
		PICKUP,
		DISCARD
	}
}
