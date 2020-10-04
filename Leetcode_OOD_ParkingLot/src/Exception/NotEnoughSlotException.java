package Exception;

public class NotEnoughSlotException extends RuntimeException
{
	private String message;
	
	public NotEnoughSlotException(String message)
	{
		this.message = message;
	}
	
	@Override
    public String getMessage()
    {
        return message;
    }
}
