import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

class Kitchen {
		private final int SHELF_CAPACITY = 12;
		private final int COOLER_CAPACITY = 12;
		private final int HEATER_CAPACITY = 12;
		
	    private final Storage cooler = new Storage(COOLER_CAPACITY);
	    private final Storage heater = new Storage(HEATER_CAPACITY);
	    private final Storage shelf = new Storage(SHELF_CAPACITY);
	    private final List<Action> actions = Collections.synchronizedList(new ArrayList<>());

	    public void placeOrder(Order order) {
	        synchronized (this) {
	            boolean success = false;
	            
	            if (order.getTemperature() == Temperature.COOL) {
	                success = cooler.placeOrder(order);
	            } else if (order.getTemperature() == Temperature.HOT) {
	                success = heater.placeOrder(order);
	            }
	            
	            if (!success) {
	                success = shelf.placeOrder(order);
	            }
	            
	            if (!success) {
	                handleFullShelf(order);
	            } else {
	                logAction(order.id, ActionType.PLACE);
	            }
	        }
	    }

	    private void handleFullShelf(Order newOrder) {
	        Order eligibleOrder = shelf.getEligibleOrder();
	        
	        if (eligibleOrder != null) {
	            boolean moved = false;
	            
	            if (eligibleOrder.getTemperature() == Temperature.COOL) {
	                moved = cooler.placeOrder(eligibleOrder);
	            } else if (eligibleOrder.getTemperature() == Temperature.HOT) {
	                moved = heater.placeOrder(eligibleOrder);
	            }
	            
	            if (moved) {
	                logAction(eligibleOrder.getId(), ActionType.MOVE);
	            } else {
	                logAction(eligibleOrder.id, ActionType.DISCARD);
	            }
	            
	            shelf.placeOrder(newOrder);
	            logAction(newOrder.id, ActionType.PLACE);
	        }
	    }

	    public void pickupOrder(String orderId) {
	        synchronized (this) {
	            if (cooler.removeOrder(orderId) || heater.removeOrder(orderId) || shelf.removeOrder(orderId)) {
	                logAction(orderId, ActionType.PICKUP);
	            }
	        }
	    }

	    private void logAction(String orderId, ActionType actionType) {
	        actions.add(new Action(System.currentTimeMillis(), orderId, actionType));
	    }

	    public List<Action> getActions() {
	        return actions;
	    }
	}
