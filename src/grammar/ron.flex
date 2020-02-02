package org.ronplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

import static org.ronplugin.psi.RonTypes.*;

%%

%{}
  private int zzPostponedMarkedPos = 0;
  private int zzNestedCommentLevel = 0;
%}

%{
  IElementType imbueBlockComment() {
      assert(zzNestedCommentLevel == 0);
      yybegin(YYINITIAL);

      zzStartRead = zzPostponedMarkedPos;
      zzPostponedMarkedPos = -1;

      return BLOCK_COMMENT;
  }
%}

%class RonLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}
%unicode

%state IN_BLOCK_COMMENT

WHITE_SPACE=\s+

IDENTIFIER = [_a-zA-Z] [_a-zA-Z0-9]*

DEC_LITERAL = [0-9] [0-9_]*
HEX_LITERAL = "0x" [a-fA-F0-9_]*
OCT_LITERAL = "0o" [0-7_]*
BIN_LITERAL = "0b" [01_]*

EXPONENT = [eE] [-+]? [0-9_]+

FLT_LITERAL = ( {DEC_LITERAL} \. ({DEC_LITERAL} {EXPONENT}?)? )
            | ( {DEC_LITERAL} {EXPONENT} )
            | ( \. {DEC_LITERAL} {EXPONENT}?)

INT_LITERAL = {DEC_LITERAL}
            | {HEX_LITERAL}
            | {OCT_LITERAL}
            | {BIN_LITERAL}

CHAR_LITERAL   = \' ( [^\\\'\r\n] | "\\x" [a-fA-F0-9]+ | "\\u{" [a-fA-F0-9][a-fA-F0-9_]* "}" )? \'
STRING_LITERAL = \" ( [^\\\"] | \\[^] )* \"

EXTENSION = "#![" .* "]"

%%

<YYINITIAL> {
    {WHITE_SPACE} { return TokenType.WHITE_SPACE; }

    ","              { return COMMA; }
    ":"              { return COL; }
    "("              { return LP; }
    ")"              { return RP; }
    "{"              { return LCB; }
    "}"              { return RCB; }
    "["              { return LSB; }
    "]"              { return RSB; }
    "+"              { return PLUS; }
    "-"              { return MINUS; }
    "_"              { return UNDERSCORE; }
    "true"           { return TRUE; }
    "false"          { return FALSE; }
    "Some"           { return SOME; }
    "None"           { return NONE; }

    "/*"             { yybegin(IN_BLOCK_COMMENT); yypushback(2); }
    "//" .*          { return LINE_COMMENT; }

    {EXTENSION}      { return EXTENSION; }
    {IDENTIFIER}     { return ID; }
    {FLT_LITERAL}    { return FLOAT_LITERAL; }
    {INT_LITERAL}    { return INTEGER_LITERAL; }
    {CHAR_LITERAL}   { return CHAR_LITERAL; }
    {STRING_LITERAL} { return STRING_LITERAL; }
}

<IN_BLOCK_COMMENT> {
    "/*"    { if (zzNestedCommentLevel++ == 0) zzPostponedMarkedPos = zzStartRead; }
    "*/"    { if (--zzNestedCommentLevel == 0) return imbueBlockComment(); }
    <<EOF>> { zzNestedCommentLevel = 0; return imbueBlockComment(); }
    [^]     { }
}

[^] { return TokenType.BAD_CHARACTER; }