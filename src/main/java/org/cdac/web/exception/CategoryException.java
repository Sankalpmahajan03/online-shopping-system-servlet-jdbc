package org.cdac.web.exception;

public class CategoryException extends Exception {
	
	public CategoryException() {
		
	}
	
	public CategoryException (String message) {
		super(message);
	}
	
	public CategoryException(Throwable cause) {
		super(cause);
	}
	public CategoryException(String message,Throwable cause) {
		super(message,cause);
	}
	public CategoryException(String message,Throwable cause,boolean enableSuppersion,boolean writableStackTrace) {
		super(message,cause,enableSuppersion,writableStackTrace);
	}

}
