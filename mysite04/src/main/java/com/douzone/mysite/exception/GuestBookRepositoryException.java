package com.douzone.mysite.exception;

public class GuestBookRepositoryException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public GuestBookRepositoryException(String message) {
		super(message);
	}

	public GuestBookRepositoryException() {
		super("GuestBookRepositoryException Occurs...");
	}

}
