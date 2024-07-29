import java.util.concurrent.ConcurrentLinkedQueue;

public class Storage {
	private final int capacity;
	private final ConcurrentLinkedQueue<Order> orders = new ConcurrentLinkedQueue<>();

	public Storage(int capacity) {
		this.capacity = capacity;
	}

	public boolean placeOrder(Order order) {
		if (orders.size() < capacity) {
			orders.add(order);
			return true;
		}

		return false;
	}

	public boolean removeOrder(String orderId) {
		return orders.removeIf(order -> order.getId().equals(orderId));
	}

	public Order getEligibleOrder() {
		return orders.isEmpty() ? null : orders.poll();
	}

	public int getStorageSize() {
		return orders.size();
	}

	public int getCapacity() {
		return capacity;
	}

	public ConcurrentLinkedQueue<Order> getOrders() {
		return orders;
	}
}
