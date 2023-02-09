package com.bd.compiler.scanner;

import java.util.List;

/**
 * Token
 * File: Token.java
 * A lexical token for the C- language passed from a scanner to a parser.
 * Includes identifier, number, operator, keyword, and EOF tokens. See page
 * 491 in the textbook for all lexical conventions.
 * 
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 2/7/23
 * Copyright of the authors
 */
public class Token {
    /**
     * A list of token types
     */
    public enum TokenType {
        EOF_TOKEN,
        PLUS_TOKEN,
        MINUS_TOKEN,
        MULT_TOKEN,
        DIV_TOKEN,
        LT_TOKEN,
        LTE_TOKEN,
        GT_TOKEN,
        GTE_TOKEN,
        EQ_TOKEN,
        NOTEQ_TOKEN,
        ASSIGN_TOKEN,
        SEMI_TOKEN,
        COMMA_TOKEN,
        LPAR_TOKEN,
        RPAR_TOKEN,
        LSQ_TOKEN,
        RSQ_TOKEN,
        LCURL_TOKEN,
        RCURL_TOKEN,
        COMMENT_TOKEN,
        ID_TOKEN,
        NUM_TOKEN,
        IF_TOKEN,
        ELSE_TOKEN,
        INT_TOKEN,
        RETURN_TOKEN,
        VOID_TOKEN,
        WHILE_TOKEN,
        ERROR_TOKEN
    }
    
    private TokenType tokenType;
    private Object tokenData;
    
    /**
     * Creates a token with no data
     * 
     * @param type the token type (see TokenType enum)
     */
    public Token(TokenType type) {
        this(type, null);
    }
    /**
     * Creates a token which stores the provided data
     * 
     * @param type the token type (see TokenType enum)
     * @param data the object to associate with the new token
     */
    public Token (TokenType type, Object data) {
        tokenType = type;
        tokenData = data;
    }
 
    public TokenType getType() {
        return this.tokenType;
    }
    
    public Object getData() {
        return this.tokenData;
    }
    
    /**
     * Converts the input token list to a JSON string
     * 
     * @param l a list of Tokens
     * @return a JSON representation of the tokens
     */
    public static String tokenListToJson(List<Token> l){
        String json = "";
        json = json + "[\n";
        for(int i = 0; i < l.size(); i++){
            Token t = l.get(i);
            Object d = t.getData();
            if(i > 0) json = json + ",\n";
            
            // Print the token type. If it has associated data, print that as well
            if(d != null){
                json = json + "  { \"" + t.getType().name() + "\" : \"" + d.toString() + "\" }";
            } else {
                json = json + "  \"" + t.getType().name() + "\"";
            }
        }
        json = json + "\n]";
        return json;
    }
}
