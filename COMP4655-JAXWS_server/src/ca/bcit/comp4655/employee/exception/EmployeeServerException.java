package ca.bcit.comp4655.employee.exception;

public class EmployeeServerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static String stdMsg = "Sever experience problem at this time, Comeback later !!!.";

	public EmployeeServerException() {
		super(stdMsg);
	}

	public EmployeeServerException(String message) {
		super(message);
	}

	public EmployeeServerException(Throwable cause) {
		super(cause);
	}

	public EmployeeServerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeServerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
