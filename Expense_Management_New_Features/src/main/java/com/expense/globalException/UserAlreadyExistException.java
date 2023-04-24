package com.expense.globalException;

public class UserAlreadyExistException extends RuntimeException {
	 public UserAlreadyExistException(String message) {
	        super(message);
	    }
}
