package com.douzone.mysite.exception;

public class GuestBookRepositoryException extends RuntimeException {
	
	public GuestBookRepositoryException(String message) {
		super(message);
	}

	public GuestBookRepositoryException() {
		super("GuestBookRepositoryException Occurs...");
	}

}
