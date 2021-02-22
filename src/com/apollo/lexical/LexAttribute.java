package com.apollo.lexical;

import com.apollo.SyntaxAnalysis.Syntax;

public class LexAttribute {
	
	public Typelex attribute;
	
	/**
	 * lexical value
	 * */
	public String value;
	/**
	 * lexical element
	 * */
	public String get;
	/**
	 * morpheme marker
	 * */
	public String tag;
	/**
	 * lexical description
	 * */
	public String description;

	public int a;
	
	public LexAttribute(Typelex attribute, String value) {
		this.attribute = attribute;
		this.tag = value;
		this.value = this.tag;
		get = attribute.toString().concat("(").concat(this.tag).concat(")");
	}
	
	public LexAttribute(Typelex attribute, String value, int tag) {
		this.attribute = attribute;
		this.tag = String.valueOf(tag);
		this.value = value;
		get = attribute.toString().concat("(").concat(this.tag).concat(")");
	}
	
	/**
	 * lexical description
	 * */
	public void Description(String descript) {
		this.description = descript.trim();
	}
}
