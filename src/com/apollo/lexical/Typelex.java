package com.apollo.lexical;

public enum Typelex {
	
	// id {@attributes, @values}
	@Deprecated
	id,
	// singleOrderArithmetic {@values -> [+,-,*,/,%,&,|,^]}
	@Deprecated
	singleOrderArithmetic,
	// binocularOperation {@values -> [+=,-=,*=,/=,++,--,||,^=]}
	@Deprecated
	binocularOperation,
	// bracket {@values -> [<,>,(,),[,],{,}]}
	@Deprecated
	bracket,
	// terminalSymbol {@values -> [if, else, while, for, do, as, dynamic, object, string, int, char, switch, ;]}
	@Deprecated
	terminalSymbol,
	// blank {}
	@Deprecated
	blank,
	
	/**
	 * <p> New lexical attribute
	 * */
	//	+		-		*		/		^
		ADD,	SUB,	MUL,	DIV,	POW,
	//	!		%		&		|		=		:		;
		NOT,	MOD,	AND,	OR,		ASG,	FSG,	SEMICOLON,
	//	,				.
		SEPARATOR,		DOT,
	
	//	+=		-=		*=		/=		^=		==		<=		>=
		AE,		SE,		ME,		DE,		PE,		EQUAL,	MINASG,	MAXASG,
	//	&&		||		++		--		->		!=		::	
		AND2,	OR2,	ADD2,	SUB2,	LAMBDA,	NE,		PTO,	
	
	//	<		>		(		)		[		]		{		}
		LOB,	ROB,	LMINB,	RMINB,	LAB,	RAB,	LMAXB,	RMAXB,
	
	//	if		else	switch		while		do		for
		IF,		ELSE,	SWITCH,		WHILE,		DO,		FOR,
	//	try		catch	finally		return		epsilon	empty	
		TRY,	CATCH,	FINALLY,	RETURN,		ε,		EMPTY,
	//	#		import		class		type	void
		SHARP,	IMPORT,		CLASS,		TYPE,	VOID,
	//	typedef	auto
		TYPEDEF,AUTO,
	//	string	char	long		integer		int		double
		STRING,	CHAR,	LONG,		INTEGER,	INT,	DOUBLE,
	//	float	boolean(bool)
		FLOAT,	BOOL,
	//	public	private	protected	lambda	lambda
		PUBLIC,	PRIVATE,PROTECTED,	lambda,	λ,
	//	single	final	static		extern
		SINGLE,	FINAL,	STATIC,		EXTERN,
		
		ID, NUM,
		
		/**
		 * as
		 * */
		AS,
		
		/**
		 * Read string stream
		 * */
		CONTENT,
		
		/**
		 * comment
		 * */
		COMMENT,


	$,
		
		
}