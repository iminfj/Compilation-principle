package com.apollo.Exception;

public class ApolloRuntimeException extends Exception {
	public ApolloRuntimeException(String s) {
		System.err.println("Compile time error :" + s);
	}
	public ApolloRuntimeException(String s, String p) {
		System.err.println("Compile time error [" + p + "]");
		System.err.println("\t" + s);
	}
}
