public class RentalResponse<T> 
{
	private T obj;
	private Status status;
	
	public RentalResponse(T obj, Status status)
	{
		this.obj = obj;
		this.status = status;
	}

	public T getResult() 
	{
		return obj;
	}

	public Status getStatus() 
	{
		return status;
	}
}

enum Status
{
	SUCCEED(), FAIL();
}
