

class Order implements Comparable<Order> {
		private String id;
	    private String name;
	    private Temperature temperature;
	    private int freshness;
	    private long orderReceivedTimeInMills;

	    public Order(String id, String name, Temperature temp, int freshness) {
	        this.id = id;
	        this.name = name;
	        this.temperature = temp;
	        this.freshness = freshness;
	        this.orderReceivedTimeInMills = System.currentTimeMillis();
	    }

	    @Override
	    public int compareTo(Order other) {
	        return Long.compare(this.orderReceivedTimeInMills, other.orderReceivedTimeInMills);
	    }
	    
	    public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public Temperature getTemperature() {
			return temperature;
		}

		public int getFreshness() {
			return freshness;
		}

		public long getOrderReceivedTimeInMills() {
			return orderReceivedTimeInMills;
		}
	}
	
	enum Temperature {
		HOT,
		COOL,
		ROOM
	}
