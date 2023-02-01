/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.scanner;

import java.io.BufferedReader;

/**
 *
 * @author dajms
 */
public class CMinusScanner implements Scanner {
    private BufferedReader inFile;
    private Token nextToken;
    
    public CMinusScanner(BufferedReader file) {
        inFile = file;
        nextToken = scanToken();
    }
    
    public Token getNextToken() {
        Token returnToken = nextToken;
        if (nextToken.getType() != Token.TokenType.EOF_TOKEN)
            nextToken = scanToken();
        return returnToken;
    }
    
    public Token viewNextToken() {
        return nextToken;
    }
    
    private Token scanToken(){
        return null;
    }
}
