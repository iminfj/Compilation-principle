package com.apollo.main;

import com.apollo.SyntaxAnalysis.Syntax;
import com.apollo.lexical.Lexical;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Apollo {
	
	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		
		if (args.length == 0) {
			System.out.println("Apollo programming language");
			System.out.println("alpha-i2-x1");
		} else {
			System.out.println(args[0]);
			Path file = Paths.get(args[0].toString());
			String data = Files.readString(file);
			
			Lexical lex = new Lexical(data);
				lex.start();

			Syntax syntax = new Syntax(lex.ret());

		}
		
	}

}