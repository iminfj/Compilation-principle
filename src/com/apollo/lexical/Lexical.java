package com.apollo.lexical;

import com.apollo.SyntaxAnalysis.lowlevelcache;

import java.util.Hashtable;
import java.util.Objects;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class Lexical {
	
	protected static Hashtable<Integer, LexAttribute> lexlist = new Hashtable<>();
	protected static String cache = "";
	protected static int listp = 0;
	private String data;
	private char lookahead;
	private int next = 0;
	private boolean booltqm = false;
	private boolean nexttqm = false;
	
//	private final Scanner input = new Scanner(System.in);
	
	public Lexical(String program) {
		if (!Objects.equals(program.length(), 0)) {
			lexlist.clear();
			cache = data = "";
			listp = next = 0;
			booltqm = nexttqm = false;
			lookahead = ' ';
			data = program.replace("\r\n", " ").replace("\t", " ").concat(" ");
		} else {
			throw new RuntimeException("Length of file is equal to ".concat(String.valueOf(program.length())));
		}
	}
	public Lexical() {}

	public void start() {
		for (int i = 0; i < data.length(); i++) {
			lookahead = data.charAt(i);

			if (next >= 1) {
				next--;
				continue;
			}
			if (nexttqm) {
				if (!match('"')) {
					cache += lookahead;
					continue;					
				}
			}
			
			switch (lookahead) {
			case '<':
				cacheToEmpty();
				if (data.charAt(i+1) == '=') {
					toCache();
					put(Typelex.MINASG, "<=", 0);
					next = 1;
				} else {
					put(Typelex.LOB, 0);
				}
				break;
			case '>':
				cacheToEmpty();
				if (data.charAt(i+1) == '=') {
					toCache();
					put(Typelex.MAXASG, ">=", 0);
					next = 1;
				} else {
					toCache();
					put(Typelex.ROB, 0);
				}
				break;
			case '=':
				cacheToEmpty();
				if (Objects.equals(data.charAt(i+1), '=')) {
					toCache();
					put(Typelex.EQUAL, "==", 0);
					next = 1;
				} else {
					toCache();
					put(Typelex.ASG, 0);
				}
				break;
			case '"':
				if (booltqm) {
					booltqm = nexttqm = false;	toCache();
					lexlist.put(listp++, new LexAttribute(Typelex.CONTENT, cache));	clearCache();
				} else {
					booltqm = nexttqm = true;	toCache();
					continue;
				}
				break;
			case ':':
				cacheToEmpty();
				if (Objects.equals(data.charAt(i+1), ':')) {
					toCache();
					put(Typelex.PTO, "::", 0);
					next++;
				} else {
					toCache();
					put(Typelex.FSG, 0);
				}
				break;
			case ';':
				cacheToEmpty();
				toCache();
				put(Typelex.SEMICOLON, 0);
				break;
				// empty!
			case ' ':
				cacheToEmpty();
				continue;
			case '(':
				cacheToEmpty();
				toCache();
				put(Typelex.LMINB, 0);
				break;
			case ')':
				cacheToEmpty();
				toCache();
				put(Typelex.RMINB, 0);
				break;
			case '[':
				cacheToEmpty();
				toCache();
				put(Typelex.LAB, 0);
				break;
			case ']':
				cacheToEmpty();
				toCache();
				put(Typelex.RAB, 0);
				break;
			case '{':
				cacheToEmpty();
				toCache();
				put(Typelex.LMAXB, 0);
				break;
			case '}':
				cacheToEmpty();
				toCache();
				put(Typelex.RMAXB, 0);
				break;
			case '#':
				cacheToEmpty();
				toCache();
				put(Typelex.SHARP, 0);
				break;
			case '+':
				cacheToEmpty();
				toCache();
				if (Objects.equals(data.charAt(i+1),'+')) {
					put(Typelex.ADD2, "++", 0);
					next++;
				} else if (Objects.equals(data.charAt(i+1),'=')) {
					put(Typelex.AE, "+=", 0);
					next++;
				} else {
					put(Typelex.ADD, 0);
				}
				break;
			case '-':
				cacheToEmpty();
				if (Objects.equals(data.charAt(i+1),'-')) {
					toCache();
					put(Typelex.SUB2, "--", 0);
					next++;
				} else if (Objects.equals(data.charAt(i+1),'=')) {
					toCache();
					put(Typelex.SE, "-=", 0);
					next++;
				} else if (Objects.equals(data.charAt(i+1), '>')) { 
					toCache();
					put(Typelex.LAMBDA, "->", 0);
					next++;
				} else {
					toCache();
					put(Typelex.SUB, 0);
				}
				break;
			case '*':
				cacheToEmpty();
				toCache();
				if (Objects.equals(data.charAt(i+1), '=')) {
					put(Typelex.ME, "*=", 0);
					next++;
				} else {
					put(Typelex.MUL, 0);
				}
				break;
			case '^':
				cacheToEmpty();
				toCache();
				if (Objects.equals(data.charAt(i+1), '=')) {
					put(Typelex.PE, "^=", 0);
					next++;
				} else {
					put(Typelex.POW, 0);
				}
				break;
			case ',':
				cacheToEmpty();
				toCache();
				put(Typelex.SEPARATOR, 0);
				break;
			case '.':
				cacheToEmpty();
				toCache();
				put(Typelex.DOT, 0);
				break;
			case '/':
				cacheToEmpty();
				toCache();
				if (Objects.equals(data.charAt(i+1), '=')) {
					put(Typelex.DE, "/=", 0);
					next++;
				} else if (Objects.equals(data.charAt(i+1), '/')) {
					put(Typelex.COMMENT, "//", 0);
					next++;
				} else {
					put(Typelex.DIV, 0);
				}
				break;
			default:
				toCache();
				break;
			}
		}
		
		lexlist.forEach((i, a) -> System.out.println( i + "\t$" + a.value + "\t -- $" + a.attribute + "\t -- $" + a.get));
	}
	
	@SuppressWarnings("rawtypes")
	public Hashtable<Integer, LexAttribute> ret() {
		put(Typelex.$, "$");
		return (Hashtable)lexlist.clone();
	}
	
	void toCache() {
		cache += lookahead;
	}
	void clearCache() {
		cache = "";
	}
	void put(Typelex type) {
		lexlist.put(listp++, new LexAttribute(type, cache));
		clearCache();
	}
	void put(Typelex type, String str) {
		lexlist.put(listp++, new LexAttribute(type, str));
		clearCache();
	}
	void put(Typelex type, int tag) {
		lexlist.put(listp++, new LexAttribute(type, cache, tag));
		clearCache();
	}
	void put(Typelex type, String str, int tag) {
		lexlist.put(listp++, new LexAttribute(type, str, tag));
		clearCache();
	}
	
	void cacheToEmpty() {
		if (!cache.equals("")) {
			try {
				int sizeofdigit = 0;
				for (int digit = 0; digit < cache.length(); digit++) {
					if (Character.isDigit(cache.charAt(digit))) {
						sizeofdigit++;
					}
				}
				if (sizeofdigit == cache.length()) {
					put(Typelex.NUM, cache);
					clearCache();
					return;
				}
				for (int i1 = 0; i1 < Typelex.values().length; i1++) {
					if (Typelex.valueOf(cache.toUpperCase()).equals(Typelex.values()[i1])) {
						put(Typelex.values()[i1], cache, 0);
						clearCache();
					}
				}
			} catch (IllegalArgumentException e) {
				new Convention().analyzer(cache);
			}
		}
	}
	
	/**
	 * <p> Compare whether parameter c is the same as lookahead character
	 * */
	private boolean match(char c) {
		return Objects.equals(lookahead, c);
	}
	
}
