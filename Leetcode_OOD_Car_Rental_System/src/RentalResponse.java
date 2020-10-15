public class RentalResponse<T> 
{
	private T obj;
	private Status status;
	private String errMsg;
	
	public RentalResponse(T obj, Status status, String errMsg)
	{
		this.obj = obj;
		this.status = status;
		this.errMsg = errMsg;
	}

	public T getResult() 
	{
		return obj;
	}

	public Status getStatus() 
	{
		return status;
	}

	public String getErrMsg() 
	{
		return errMsg;
	}
}

enum Status
{
	SUCCEED(), FAIL();
}
