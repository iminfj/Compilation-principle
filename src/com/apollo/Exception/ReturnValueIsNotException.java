package com.apollo.Exception;

public class ReturnValueIsNotException extends Exception {
	@SuppressWarnings("rawtypes")
	public ReturnValueIsNotException(Class c) {
		setException(c, "This is not a the Exception", "Nothing . ..");
	}
}
