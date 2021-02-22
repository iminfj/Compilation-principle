package com.apollo.SyntaxAnalysis;

import java.util.ArrayList;
import java.util.Hashtable;

public class producemap extends lowlevelcache {
	/**
	 * Non-terminal symbol
	 * */
	public String Vn;
	/**
	 * Start symbol
	 * */
	public Hashtable<Integer, String> Start = new Hashtable<>();
	/**
	 * producemap table size
	 * */
	public int size;
	/**
	 * The maximum acceptable position of producemap
	 * */
	public int max;
	/**
	 * minimum desirable position of producemap
	 * */
	public int min = 0;
	/**
	 * Table of productions
	 * */
	public Hashtable<Integer, String> list = new Hashtable<>();
	/**
	 * <p>Convolution scheme algorithm that stores the production corresponding to Vn
	 * */
	public producemap(String vn, Integer index, String p) {
		if (index < 0) return;
		Vn = vn;
		list.put(index, p);
		size = list.size();
		max = list.size() -1;
		Start.put(index, p.split(" ")[0]);
	}
	
	/**
	 * Integer	: @param index += 1<br>
	 * String	: @param p
	 * */
	public producemap put(String p) {
		list.put(size++, p);
		max = list.size() -1;
		Start.put(size-1, p.split(" ")[0]);
		return this;
	}
	/**
	 * Integer	: @param index<br>
	 * String	: @param p
	 * */
	public producemap put(int index, String p) {
		list.put(index, p);
		max = list.size() -1;
		Start.put(size-1, p.split(" ")[0]);
		return this;
	}
	
	/**
	 * <p>Traverse the beginning symbols of all productions
	 * */
	public void starts() {
		Start.forEach((i, s) -> System.out.println("In " + i + " --\t-- Start : " + s));
	}
	
	/**
	 * <p>Traverse all p of the specified Vn
	 * */
	public void show() {
		list.forEach((i, s) -> System.out.println("Index " + i + " --\t-- P : " + s));
	}

	public Object[] listToArray() {
		ArrayList<String> array = new ArrayList<String>();
		list.forEach((i, s) -> {
			array.add(s);
		});
		return array.toArray();
	}

}

