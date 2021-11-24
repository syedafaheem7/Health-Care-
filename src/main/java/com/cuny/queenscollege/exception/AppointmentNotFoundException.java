package com.cuny.queenscollege.exception;

public class AppointmentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public AppointmentNotFoundException() {
		super();
		
	}
     public AppointmentNotFoundException(String message) {
		super(message);
	}
}
