package com.glarimy.bank.exceptions;

@SuppressWarnings("serial")
public class InvalidAccountException extends BankException {

	public InvalidAccountException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
