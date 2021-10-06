package dalibor.jelicanin.exception;

public class MyEntityNotPresentedException extends MyApplicationException {
	private static final long serialVersionUID = 1L;
	
	public MyEntityNotPresentedException(String message) {
		super(message);
	}
	
}
