package rs.ac.bg.etf.pp1;
import java_cup.runtime.Symbol;

%%

%{
    // ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}
%}


%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%


" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\n" 	{ }
"\r" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROGRAM, yytext());}
"static"    { return new_symbol(sym.STATIC, yytext());}
"class"     { return new_symbol(sym.CLASS, yytext());}
"abstract"  { return new_symbol(sym.ABSTRACT, yytext());}
"extends"   { return new_symbol(sym.EXTENDS, yytext());}
"new"       { return new_symbol(sym.NEW, yytext());}
"this"      {return new_symbol(sym.THIS, yytext());}
"return" 	{ return new_symbol(sym.RETURN, yytext()); }

"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read" 	    { return new_symbol(sym.READ, yytext()); }

"int" 		{ return new_symbol(sym.INT, yytext()); }
"char" 		{ return new_symbol(sym.CHAR, yytext()); }
"bool" 		{ return new_symbol(sym.BOOL, yytext()); }
"const" 	{ return new_symbol(sym.CONST, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }


"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"++" 		{ return new_symbol(sym.INC, yytext()); }
"--" 		{ return new_symbol(sym.DEC, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }

"==" 		{ return new_symbol(sym.EQUALS, yytext()); }
"!=" 		{ return new_symbol(sym.NOT_EQUALS, yytext()); }
">" 		{ return new_symbol(sym.GR, yytext()); }
"<" 		{ return new_symbol(sym.LE, yytext()); }
">=" 		{ return new_symbol(sym.GRE, yytext()); }
"<=" 		{ return new_symbol(sym.LEE, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }

"," 		{ return new_symbol(sym.COMMA, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"(" 		{ return new_symbol(sym.L_PAREN, yytext()); }
")" 		{ return new_symbol(sym.R_PAREN, yytext()); }
"{" 		{ return new_symbol(sym.LC_BRACE, yytext()); }
"}"			{ return new_symbol(sym.RC_BRACE, yytext()); }
"["			{ return new_symbol(sym.L_BRACE, yytext()); }
"]"			{ return new_symbol(sym.R_BRACE, yytext()); }

"if"		{ return new_symbol(sym.IF, yytext()); }
"else"		{ return new_symbol(sym.ELSE, yytext()); }
"for"		{ return new_symbol(sym.FOR, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }


"//"             {yybegin(COMMENT);}
<COMMENT> .      {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }


"true"|"false"                  { return new_symbol(sym.BOOL, new Boolean (yytext().equals("true") ? true : false)); }
[0-9]+                          { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{ return new_symbol (sym.IDENTIFIER, yytext()); }
"'"."'"                         { return new_symbol(sym.CHAR, new Character (yytext().charAt(1))); }


. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }
























