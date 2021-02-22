package com.apollo.Exception;

public class SymbolItemCollisionException extends Exception {
	@SuppressWarnings("rawtypes")
	public SymbolItemCollisionException(Class c) {
		setException(c.getName(), "Symbol Item Collision", "Item �����˳�ͻ������Լ��");
	}
}
