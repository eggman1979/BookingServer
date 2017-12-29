package exceptions;

public class DBConnectException extends Exception{

	/**
	 * 
	 */

	private static final long serialVersionUID = 3106421840133379217L;


	public DBConnectException(String message){super(message);}
	public DBConnectException(Exception e){super(e);}
}
