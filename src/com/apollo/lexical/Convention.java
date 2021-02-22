package com.apollo.lexical;

public class Convention extends Lexical {
	public void analyzer(Object o) {
		String s = String.valueOf(o);
		if (s.trim().length() == 0) return;
		
		super.put(Typelex.ID, s);
		super.clearCache();
		System.out.println("ID --->\t" + lexlist.get(listp-1).get);
		
	}
}
