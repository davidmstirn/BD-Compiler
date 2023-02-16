package com.bd.compiler.scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

%%

%public
%class JFlexScanner
%implements Scanner

%unicode

%line
%column

%cup
%cupdebug

%{
    private BufferedReader inFile;
    private Token nextToken;
    
    /**
     * Creates a scanner that reads an ASCII file and generates tokens
     * 
     * @param file the file from which to read ASCII from
     * @throws ScannerException if the scan failed
     */
    public JFlexScanner(BufferedReader file) throws ScannerException {
        inFile = file;
        try {
            nextToken = next_token();
        } catch (IOException e) {
            throw new ScannerException();
        }    }
    
    /**
     * Get the next token. Munches the returned token
     * 
     * @return the retrieved token
     * @throws ScannerException if the scan failed
     */
    @Override
    public Token getNextToken() throws ScannerException {
        Token returnToken = nextToken;
        if (nextToken.getType() != Token.TokenType.EOF_TOKEN)
            try {
                nextToken = next_token();
            } catch (IOException e) {
                throw new ScannerException();
            }
        return returnToken;
    }
    
    /**
     * View the next token without munching it
     * 
     * @return the viewed token
     */
    @Override
    public Token viewNextToken() {
        return nextToken;
    }
%}

letter = [a-z,A-Z]
digit = [0-9]

%%

"+"     {return new Token(Token.TokenType.PLUS_TOKEN);}
"-"     {return new Token(Token.TokenType.MINUS_TOKEN);}
"*"     {return new Token(Token.TokenType.MULT_TOKEN);}
"/"     {return new Token(Token.TokenType.DIV_TOKEN);}
"<"     {return new Token(Token.TokenType.LT_TOKEN);}
"<="    {return new Token(Token.TokenType.LTE_TOKEN);}
">"     {return new Token(Token.TokenType.GT_TOKEN);}
">="    {return new Token(Token.TokenType.GTE_TOKEN);}
"=="    {return new Token(Token.TokenType.EQ_TOKEN);}
"!="    {return new Token(Token.TokenType.NOTEQ_TOKEN);}
"="     {return new Token(Token.TokenType.ASSIGN_TOKEN);}
";"     {return new Token(Token.TokenType.SEMI_TOKEN);}
","     {return new Token(Token.TokenType.COMMA_TOKEN);} 
"("     {return new Token(Token.TokenType.LPAR_TOKEN);}
")"     {return new Token(Token.TokenType.RPAR_TOKEN);}
"["     {return new Token(Token.TokenType.LSQ_TOKEN);}
"]"     {return new Token(Token.TokenType.RSQ_TOKEN);}
"{"     {return new Token(Token.TokenType.LCURL_TOKEN);}
"}"     {return new Token(Token.TokenType.RCURL_TOKEN);}

" "     {}
"\t"    {}
"/n"    {}
"/*" .* "*/" {}


"if" {return new Token(Token.TokenType.IF_TOKEN);}
"else" {return new Token(Token.TokenType.ELSE_TOKEN);}
"int" {return new Token(Token.TokenType.INT_TOKEN);}
"return" {return new Token(Token.TokenType.RETURN_TOKEN);}
"void" {return new Token(Token.TokenType.VOID_TOKEN);}
"while" {return new Token(Token.TokenType.WHILE_TOKEN);}

{letter}+ {return new Token(Token.TokenType.ID_TOKEN, yytext());}
{digit}+ {return new Token(Token.TokenType.NUM_TOKEN, yytext());}