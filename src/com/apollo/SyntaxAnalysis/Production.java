package com.apollo.SyntaxAnalysis;

import java.util.Objects;

public class Production extends lowlevelcache {
	/**
	 * <p>Used to find Vn corresponding to the Vn production
	 * */
	public String PrdctHead;
	/**
	 * <p>The production to be put
	 * */
	public String PrdctBody;
	/**
	 * Vn's HashCode
	 * */
	public int hashkey = 0;

	public String Prdct;

	public int bodysize = 0, a = 0;

	/**
	 * Put the divided left part and right part of the<br>
	 * production into the designed data structure
	 * */
	@SuppressWarnings("static-access")
	public Production(String left, String right) {
		Prdct = left + " -> " + right;
		if ((!Objects.equals(left.hashCode(), 0)) && (!Objects.equals(right.hashCode(), 0))) {
			if (right.hashCode() == "=>".hashCode()) {
				right = "->";
			}
			if (super.existHashcodeOfVn(left.hashCode())) {
				PrdctHead = left;	PrdctBody = right;
	 			hashkey = left.hashCode();
	 			super.productionMap.put(left, (super.productionMap.get(left).put(right)));
			} else {
				PrdctHead = left;	PrdctBody = right;
				hashkey = left.hashCode();
				super.vnSet.add(hashkey);
				super.productionMap.put(left, new producemap(left, 0, right));
				}
			//super.forEachNonterminalSymbolSet(hashkey);
		}
		bodysize = PrdctBody.split(" ").length;
	}

	public Production() {
		System.err.println("Access to an empty production data structure!");
	}
}
