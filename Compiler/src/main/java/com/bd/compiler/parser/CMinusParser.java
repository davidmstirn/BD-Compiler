package com.bd.compiler.parser;

import com.bd.compiler.scanner.CMinusScanner;
import com.bd.compiler.scanner.Scanner;
import com.bd.compiler.scanner.ScannerException;
import com.bd.compiler.scanner.Token;
import com.bd.compiler.scanner.Token.TokenType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CMinusParser
 * File: CMinusParser.java
 * TODO: describe this file!
 * 
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 3/11/23
 * Copyright of the authors
 */
public class CMinusParser implements Parser {
    private Program program;
    private String file;
    private Token currentToken;
    private Scanner scanner;
    
    public enum TypeSpecifier {
        VOID_TYPE,
        INT_TYPE
    }

    public CMinusParser(String filename){
        file = filename;
        
        try (FileReader fr = new FileReader(file)){
            BufferedReader br = new BufferedReader(fr);
            scanner = new CMinusScanner(br);
            currentToken = scanner.getNextToken();
        } catch (IOException e) {
            System.out.println("Error opening input file");
        } catch (ScannerException e) {
            System.out.println("Scan error");
        }
    }
    
    @Override
    public Program parse() {
        try{
            return parseProgram();
        } catch (ParserException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private Program parseProgram() throws ParserException {
        List<Declaration> declList = new ArrayList<>();
        while(currentToken.getType() == TokenType.VOID_TOKEN ||
                currentToken.getType() == TokenType.INT_TOKEN){
            Declaration d = parseDeclaration();
            declList.add(d);
        }
        
        program = new Program(declList);
        
        return program;
    }
    
    private Declaration parseDeclaration() throws ParserException {
        Declaration d;
        
        if (currentToken.getType() == TokenType.VOID_TOKEN){
            matchToken(TokenType.VOID_TOKEN);
            Token id = matchToken(TokenType.ID_TOKEN);
            d = new FunctionDeclaration(TypeSpecifier.VOID_TYPE, (String) id.getData(), null);
            
        } else if(currentToken.getType() == TokenType.INT_TOKEN){
            matchToken(TokenType.INT_TOKEN);
            Token id = matchToken(TokenType.ID_TOKEN);
            d = parseDeclarationPrime((String) id.getData());
            
        } else {
            throw new ParserException("Error parsing decl : invalid token " + currentToken.getType());
        }
        
        return d;
    }
    
    private Declaration parseDeclarationPrime(String id) throws ParserException {
        Declaration d;
        
        if(currentToken.getType() == TokenType.SEMI_TOKEN){
            matchToken(TokenType.SEMI_TOKEN);
            d = new VariableDeclaration(id, null);
            
        } else if(currentToken.getType() == TokenType.LSQ_TOKEN) {
            Token num = matchToken(TokenType.NUM_TOKEN);
            matchToken(TokenType.RSQ_TOKEN);
            matchToken(TokenType.SEMI_TOKEN);
            d = new VariableDeclaration(id, (Integer) num.getData());
            
        } else if(currentToken.getType() == TokenType.LPAR_TOKEN) {
            d = new FunctionDeclaration(TypeSpecifier.INT_TYPE, id, null);
            
        } else {
            throw new ParserException("Error parsing decl' : invalid token " + currentToken.getType());
        }
        
        return d;
    }
    
    private Token matchToken(TokenType t) {
        Token prev = currentToken;
        
        if(prev.getType() == t){
            try {
                currentToken = scanner.getNextToken();
            } catch(ScannerException e) {
                System.out.println("Scan error");
            }
        } else {
            System.out.println("Error matching: " + t + " and " + prev.getType());
            currentToken = null;
        }
        
        return prev;
    }
    
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("ERROR: Parser requires 1 argument, the path to the C- Code");
            return;
        }

        String filename = args[0];
        
        CMinusParser cmp = new CMinusParser(filename);
        Program p = cmp.parse();
        
        String outputFilename = filename.substring(0, filename.indexOf('.'));
        try(FileWriter fw = new FileWriter(outputFilename+".ast")){
            String output = p.printTree()+"\n";
            System.out.println(output);
            fw.append(output);
        }catch(IOException e){
            System.out.println("Error opening file");
        }
    }
}
