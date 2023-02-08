/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("ERROR: Scanner requires 1 argument, the path to the C- Code");
            return;
        }
        
        List<Token> tokens = new ArrayList<Token>();
        try (FileReader fr = new FileReader(args[0])){
            BufferedReader br = new BufferedReader(fr);
            
            Scanner scan = new CMinusScanner(br);
            
            Token t = scan.getNextToken();
            tokens.add(t);
            while(t.getType() != Token.TokenType.EOF_TOKEN){
                t = scan.getNextToken();
                tokens.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error opening file");
            return;
        }
        
        // Output the tokens to a file
        try(FileWriter fw = new FileWriter("scan.json")){
            String json = Token.tokenListToJson(tokens);
            fw.append(json);
        }catch(IOException e){
            System.out.println("Error opening file");
            return;
        }
    }
}
