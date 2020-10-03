public abstract class Vehicle {
	protected String plateNum;
	protected int size;
	
	public Vehicle(String plateNum)
	{
		this.plateNum = plateNum;
		setSize(0);
	}

	public String getPlateNum() {
		return plateNum;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
