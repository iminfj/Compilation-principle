package com.apollo.SyntaxAnalysis;

public class SyntaxUnitTemporary {
	
	// OP
	public String[] OP_ONE = {
			"+", "-", "*", "/", "^", "!", "%", "&", "|", "=", ":"
	};
	
	// OP''
	public String[] OP_TWO = {
			"+=", "-=", "*=", "/=", "^=", 
			"==", "&&", "||", "++", "--", 
			"->", "!="
	};
	
	// Bracket m -> b
	public String[] KEY_BRACKET = {
			 "<", ">", "(", ")", "[", "]", "{", "}"
	};
	
	// Some symbol the terminal 
	public String[] KEY_SYMBOL = {
			"if", "else", "switch", "while", "do", "for"
			, "try", "catch", "return", "empty", "#"
			, "import"
	};
	
	public String[] KEY_TYPE = {
			"class", "void", "typedef", "auto"
			, "string", "char", "long", "int", "double", "float"
			, "bool"
	};
	
	public String[] KEY_ACCESS = {
			"public", "private", "protected"
			, "lambda", "λ"
	};
	
	public String[] KEY_MODIFIER = {
			"onlyone", "final", "static"
			, "extern"
	};
}
