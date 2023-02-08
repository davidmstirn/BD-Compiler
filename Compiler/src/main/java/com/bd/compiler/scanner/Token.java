/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.scanner;

import java.util.List;

/**
 *
 * @author dajms
 */
public class Token {
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
    
    public Token(TokenType type) {
        this(type, null);
    }
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
    
    public static String tokenListToJson(List<Token> l){
        String json = "";
        json = json + "[\n";
        for(int i = 0; i < l.size(); i++){
            Token t = l.get(i);
            Object d = t.getData();
            if(i > 0) json = json + ",\n";
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
