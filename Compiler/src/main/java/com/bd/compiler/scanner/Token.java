/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.scanner;

/**
 *
 * @author dajms
 */
public class Token {
    public enum TokenType {
        INT_TOKEN,
        DOUBLE_TOKEN,
        IF_TOKEN,
        EOF_TOKEN,
        // rest of tokens ....
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
}
