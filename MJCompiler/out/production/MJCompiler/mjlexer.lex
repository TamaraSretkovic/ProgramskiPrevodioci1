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
"new"       { return new_symbol(sym.NEW, yytext());}
"return" 	{ return new_symbol(sym.RETURN, yytext()); }

"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read" 	    { return new_symbol(sym.READ, yytext()); }

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
"*=" 		{ return new_symbol(sym.MUL_ASSIGN, yytext()); }
"/=" 		{ return new_symbol(sym.DIV_ASSIGN, yytext()); }
"%=" 		{ return new_symbol(sym.MOD_ASSIGN, yytext()); }
"+=" 		{ return new_symbol(sym.PLUS_ASSIGN, yytext()); }
"-=" 		{ return new_symbol(sym.MINUS_ASSIGN, yytext()); }

"==" 		{ return new_symbol(sym.EQUALS, yytext()); }
"!=" 		{ return new_symbol(sym.NOT_EQUALS, yytext()); }
">" 		{ return new_symbol(sym.GREATER, yytext()); }
"<" 		{ return new_symbol(sym.LESS, yytext()); }
">=" 		{ return new_symbol(sym.GREATER_EQUALS, yytext()); }
"<=" 		{ return new_symbol(sym.LESS_EQUALS, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }

"," 		{ return new_symbol(sym.COMMA, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"(" 		{ return new_symbol(sym.L_PAREN, yytext()); }
")" 		{ return new_symbol(sym.R_PAREN, yytext()); }
"{" 		{ return new_symbol(sym.LC_BRACE, yytext()); }
"}"			{ return new_symbol(sym.RC_BRACE, yytext()); }
"["			{ return new_symbol(sym.L_BRACE, yytext()); }
"]"			{ return new_symbol(sym.R_BRACE, yytext()); }

"if"		{ return new_symbol(sym.IF, yytext()); }
"else"		{ return new_symbol(sym.ELSE, yytext()); }
"for"		{ return new_symbol(sym.FOR, yytext()); }
"foreach"		{ return new_symbol(sym.FOREACH, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }


"//"             { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }
<COMMENT> "\n"   { yybegin(YYINITIAL); }
<COMMENT> "\r"   { yybegin(YYINITIAL); }


"true"|"false"                  { return new_symbol(sym.BOOL, new Integer (yytext().equals("true") ? 1 : 0)); }
[0-9]+                          { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{ return new_symbol (sym.IDENT, yytext()); }
'[a-zA-Z]'                         { return new_symbol(sym.CHAR, new Character (yytext().charAt(1))); }


. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }
























