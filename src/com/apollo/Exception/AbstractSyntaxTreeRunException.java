package com.apollo.Exception;

public class AbstractSyntaxTreeRunException extends Exception {
	public AbstractSyntaxTreeRunException(Class c) {
		setException(c, "I can't get my head around it.", "Please check your code the source!");
	}
}
