package com.bd.compiler.scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JFlexWrapper
 * File: JFlexWrapper.java
 * A scanner for the C- language. Uses the JFlex yylex function to implement
 * the scanner class.
 * 
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 2/14/23
 * Copyright of the authors
 */
public class JFlexWrapper implements Scanner {
    private Yylex lexer;
    private Token nextToken;
    
    public JFlexWrapper(BufferedReader file) throws ScannerException {
        lexer = new Yylex(file);
        try {
        nextToken = lexer.yylex();
        } catch (IOException e) {
            throw new ScannerException();
        }
    }
    
    public Token getNextToken() throws ScannerException {
        Token returnToken = nextToken;
        if (nextToken.getType() != Token.TokenType.EOF_TOKEN)
            try {
                nextToken = lexer.yylex();
                } catch (IOException e) {
                    throw new ScannerException();
                }
        return returnToken;
    }
    
    
    public Token viewNextToken() {
        return nextToken;
    }

    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("ERROR: Scanner requires 1 argument, the path to the C- Code");
            return;
        }

        String filename = args[0];

        List<Token> tokens = new ArrayList<>();
        try (FileReader fr = new FileReader(filename)){
            BufferedReader br = new BufferedReader(fr);

            Scanner scan = new JFlexWrapper(br);

            Token t = scan.getNextToken();
            tokens.add(t);
            // Read all of the tokens
            while(t.getType() != Token.TokenType.EOF_TOKEN){
                t = scan.getNextToken();
                tokens.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error opening file");
            return;
        } catch (ScannerException e) {
            System.out.println("Scan error");
        }

        // Output the tokens to a file
        String outputFilename = filename.substring(0, filename.indexOf('.'));
        try(FileWriter fw = new FileWriter(outputFilename+".json")){
            String json = Token.tokenListToJson(tokens);
            fw.append(json);
        }catch(IOException e){
            System.out.println("Error opening file");
        }
    }
}
