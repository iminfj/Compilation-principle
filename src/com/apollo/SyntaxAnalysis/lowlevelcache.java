package com.apollo.SyntaxAnalysis;

import java.util.*;

/**
 * <h4>
 * The low-level cache class provides methods<br>
 * such as cache, underlying algorithm, value<br>
 * transfer, etc. to each module
 * </h4>
 * */
public class lowlevelcache {
	
	/**
	 * <p>Store the collection of all Vn hashcodes
	 * */
	public static HashSet<Integer> vnSet = new HashSet<Integer>();
	
	/**
	 * <p>HashMap used to find the set of productions corresponding to Vn
	 * */
	public static HashMap<String, producemap> productionMap = new HashMap<>();

	/**
	 * If state is 0, return false<br>
	 * If state is 1, return true
	 * */
	public int state = 0;
	
	/**
	 * <p>Find whether the @param hash exists in the VnSet collection
	 * */
	public boolean existHashcodeOfVn(int hash) {
		for (Integer integer : vnSet) {
			if (Objects.equals(integer, hash)) {
				state = 1;
				break;
			} else state = 0;
		}
		return (state == 1);
	}

	/**
	 * <h4>A-65, Z-90</h4>
	 * <h4>a-97, z-122, 0-48, 9-57</h4>
	 * */
	public boolean isVt (String X) {
		if (X.hashCode() == 0) {
			return false;
		}
		ArrayList<Integer> V = new ArrayList<Integer>();
		for (int x_index = 0; x_index < X.length(); x_index++) {
			if (X.charAt(x_index) >= 65 && X.charAt(x_index) <= 90) {
				// upper case
				V.add(1);
			} else {
				// lower case | number | symbol
				V.add(0);
			}
		}
		boolean decision = true;
		boolean decision2 = true;
		for (Integer e : V) {
			if (e == 1) {
				decision = false;
			} else if (e == 0) {
				decision2 = true;
			}
		}
		return decision == decision2;
	}

	public boolean isVn (String X) {
		return !isVt(X);
	}

	public boolean isLowerCase(char c) {
		return c >= 97 && c <= 122;
	}
	public boolean isUpperCase(char c) {
		return c >= 65 && c <= 90;
	}
	public boolean isNumber(char c) {
		return c >= 48 && c <= 57;
	}
	
	// EXPR -> ( OPT ) = VALUE ;
	// EXPR -> ( OPT ) = OPT ;
	// OPT -> EXPR ? VALUE : VALUE ;
	//	|	OPT ? VALUE : VALUE;
	// OPT ∈ first(VALUE)

	/**
	 * <p>print</p>
	 * */
	public void print(String s, Object ... argx) {
//		synchronized (this) {
			int count = 0;
			boolean typeout = true;
			boolean ln = true;
			for (Object arg : argx) {
				String args = arg.toString();
				/*
				  <p>Analysis of escaped hyperparameters</p>
				  */
				if (Objects.equals(args, "argX:err") || Objects.equals(args, "argX:red")) {
					typeout = false;
				} else if (Objects.equals(args, "non-ln")) {
					ln = false;
				}
				s = s.replace("{"+count+"}", args);
				count++;
			}

			s = s.replace("{date}", new Date().toString());

			if (ln) {
				if (typeout) {
					System.out.println(s);
				} else {
					System.err.println(s);
				}
			} else {
				if (typeout) {
					System.out.print(s);
				} else {
					System.err.print(s);
				}
			}
//		}
	}

	// Decode Vn
	public static Hashtable<String, Integer> decVn = new Hashtable<String, Integer>();
	// Decode GrammarSymbol
	public static Hashtable<String, Integer> decGrammarSymbol = new Hashtable<String, Integer>();
	// Non-terminal
	public static ArrayList<String> Nonterminal = new ArrayList<String>();
	// Grammar Symbol
	public static ArrayList<String> GrammarSymbol = new ArrayList<String>();
	/**
	 * <p>Encode every element</p>
	 * */
	public void encGrammarSymbol(ArrayList<String> Vn, ArrayList<String> Vt) {
		Nonterminal.addAll(Vn);
		for (int pointer = 0; pointer < Vn.size(); pointer++) {
			decVn.put(Vn.get(pointer), pointer);
		}
		GrammarSymbol.addAll(Vn);
		GrammarSymbol.addAll(Vt);
		for (int pointer = 0; pointer < GrammarSymbol.size(); pointer++) {
			decGrammarSymbol.put(GrammarSymbol.get(pointer), pointer);
		}
	}
}
