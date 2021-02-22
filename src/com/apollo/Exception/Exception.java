package com.apollo.Exception;

public class Exception {
	public static Object title;
	public static Object message;
	public static Object solution;
	@SuppressWarnings("rawtypes")
	public static Class subExceptionClass;
	static {
		message = "com.apollo.Exception.Exception";
	}
	@SuppressWarnings("rawtypes")
	public Exception(Class e) throws NullPointerException {
		subExceptionClass = e;
		
	}
	public Exception() {
		
	}
	
	public static String setException(Object argTitle, Object argMessage, Object argSolution) {
		title = argTitle;
		message = argMessage;
		solution = argSolution;
		return "";
	}
	
	/**
	 * <p>toString</p>
	 * */
	public static char message() {
		System.err.println(title);
		System.err.println("\t" + message);
		System.err.println("\t" + solution);
		return ' ';
	}
}
