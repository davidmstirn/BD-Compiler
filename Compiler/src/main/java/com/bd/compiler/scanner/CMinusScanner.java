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
    
    private enum State {
        START,
        IDENTIFIER,
        NUMBER,
        DONE,
        BANG,
        SLASH,
        INCOMMENT,
        CLOSECOMMENT,
        LESSTHAN,
        GREATERTHAN,
        EQUALS,
        ERROR_ID,
        ERROR_NUM
    }
    
    private Token reservedLookup(Token input) {
        return switch ((String)input.getData()) {
            case "if" -> new Token(Token.TokenType.IF_TOKEN);
            case "else" -> new Token(Token.TokenType.ELSE_TOKEN);
            case "int" -> new Token(Token.TokenType.INT_TOKEN);
            case "return" -> new Token(Token.TokenType.RETURN_TOKEN);
            case "void" -> new Token(Token.TokenType.VOID_TOKEN);
            case "while" -> new Token(Token.TokenType.WHILE_TOKEN);
            default -> input;
        };
    }
    
    private Token scanToken(){
        Token currentToken = null;
        State state = State.START;
        String tokenValue = "";
        try {
            while (state != State.DONE) {
                inFile.mark(1);
                char c = (char) inFile.read();
                switch (state) {
                    case START:
                        if (Character.isAlphabetic(c)) {
                            state = State.IDENTIFIER;
                            tokenValue += c;
                        } else if (Character.isDigit(c)) {
                            state = State.NUMBER;
                            tokenValue += c;
                        } else if (c == '!') {
                            state = State.BANG;
                        } else if (c == '/') {
                            state = State.SLASH;
                        } else if (c == '<') {
                            state = State.LESSTHAN;
                        } else if (c == '>') {
                            state = State.GREATERTHAN;
                        } else if (c == '=') {
                            state = State.EQUALS;
                        } else if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
                            break; // If the character is whitespace, ignore it
                        } else {
                            state = State.DONE;
                            switch (c) {
                                case '+' -> currentToken = new Token(Token.TokenType.PLUS_TOKEN);
                                case '-' -> currentToken = new Token(Token.TokenType.MINUS_TOKEN);
                                case '*' -> currentToken = new Token(Token.TokenType.MULT_TOKEN);
                                case ';' -> currentToken = new Token(Token.TokenType.SEMI_TOKEN);
                                case ',' -> currentToken = new Token(Token.TokenType.COMMA_TOKEN);
                                case '(' -> currentToken = new Token(Token.TokenType.LPAR_TOKEN);
                                case ')' -> currentToken = new Token(Token.TokenType.RPAR_TOKEN);
                                case '[' -> currentToken = new Token(Token.TokenType.LSQ_TOKEN);
                                case ']' -> currentToken = new Token(Token.TokenType.RSQ_TOKEN);
                                case '{' -> currentToken = new Token(Token.TokenType.LCURL_TOKEN);
                                case '}' -> currentToken = new Token(Token.TokenType.RCURL_TOKEN);
                                case (char) -1 -> currentToken = new Token(Token.TokenType.EOF_TOKEN);
                                default -> {
                                    System.out.println("Invalid token starting with " + c);
                                    return new Token(Token.TokenType.ERROR_TOKEN, c);
                                }
                            }
                        }
                        break;
                    case IDENTIFIER:
                        // if the next character is not alphabetic, backup and return the string up until now
                        if (!Character.isAlphabetic(c)) {
                            // if the next character is a digit, go to error
                            if(Character.isDigit(c)) {
                                state = State.ERROR_ID;
                                tokenValue += c;
                            } else {
                                inFile.reset();
                                state = State.DONE;
                                currentToken = new Token(Token.TokenType.ID_TOKEN, tokenValue);
                            }
                        } else {
                            tokenValue += c;
                        }
                        break;
                    case NUMBER:
                        // if the next character is not a digit, backup and return the number up until now
                        if (!Character.isDigit(c)) {
                            // if the next character is a letter, go to error
                            if(Character.isAlphabetic(c)) {
                                state = State.ERROR_NUM;
                                tokenValue += c;
                            } else {
                                inFile.reset();
                                currentToken = new Token(Token.TokenType.NUM_TOKEN, Integer.parseInt(tokenValue));
                                state = State.DONE;
                            }
                        } else {
                            tokenValue += c;
                        }
                        break;
                    case BANG:
                        // if the next character is not an =, it is a parse error
                        if (c == '=') {
                            currentToken = new Token(Token.TokenType.NOTEQ_TOKEN);
                        } else {
                            inFile.reset();
                            System.out.println("Invalid token starting with !");
                            return new Token(Token.TokenType.ERROR_TOKEN, "!");
                        }
                        state = State.DONE;
                        break;
                    case SLASH:
                        // if the next character is not a *, this is a divide
                        if (c == '*') {
                            state = State.INCOMMENT;
                        } else {
                            inFile.reset();
                            currentToken = new Token(Token.TokenType.DIV_TOKEN);
                            state = State.DONE;
                        }
                        break;
                    case LESSTHAN:
                        // Is this < or <=
                        if (c == '=') {
                            currentToken = new Token(Token.TokenType.LTE_TOKEN);
                            state = State.DONE;
                        } else {
                            inFile.reset();
                            currentToken = new Token(Token.TokenType.LT_TOKEN);
                            state = State.DONE;
                        }
                        break;
                    case GREATERTHAN:
                        // Is this > or >=
                        if (c == '=') {
                            currentToken = new Token(Token.TokenType.GTE_TOKEN);
                            state = State.DONE;
                        } else {
                            inFile.reset();
                            currentToken = new Token(Token.TokenType.GT_TOKEN);
                            state = State.DONE;
                        }
                        break;
                    case EQUALS:
                        // Is this = or ==
                        if (c == '=') {
                            currentToken = new Token(Token.TokenType.EQ_TOKEN);
                            state = State.DONE;
                        } else {
                            inFile.reset();
                            currentToken = new Token(Token.TokenType.ASSIGN_TOKEN);
                            state = State.DONE;
                        }
                        break;
                    case INCOMMENT:
                        // Move to second star state
                        if (c == '*') {
                            state = State.CLOSECOMMENT;
                        }
                        break;
                    case CLOSECOMMENT:
                        // if not a /, move back to first star state
                        if (c != '/') {
                            state = State.INCOMMENT;
                        } else {
                            state = State.START;
                        }
                        break;
                    case ERROR_ID:
                        // Keep consuming characters until a non-letter or a
                        //   non-digit is found
                        if(Character.isAlphabetic(c) || Character.isDigit(c)) {
                            state = State.ERROR_ID;
                            tokenValue += c;
                        } else {
                            inFile.reset();
                            System.out.println("Invalid identifier");
                            return new Token(Token.TokenType.ERROR_TOKEN, tokenValue);    
                        }
                        break;
                    case ERROR_NUM:
                        // Keep consuming characters until a non-letter or a
                        //   non-digit is found
                        if(Character.isAlphabetic(c) || Character.isDigit(c)) {
                            state = State.ERROR_NUM;
                            tokenValue += c;
                        } else {
                            inFile.reset();
                            System.out.println("Invalid number");
                            return new Token(Token.TokenType.ERROR_TOKEN, tokenValue);    
                        }
                        break;
                    case DONE:
                    default:
                        throw new IOException();
                }
            }
        } catch (IOException e) {
            System.out.println("Scan Error");
            return new Token(Token.TokenType.ERROR_TOKEN);
        }
        // We are in the done state
        if (currentToken != null && currentToken.getType() == Token.TokenType.ID_TOKEN) {
            currentToken = reservedLookup(currentToken);
        }
        return currentToken;
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
        String outputFilename = filename.substring(0, filename.indexOf('.'));
        try(FileWriter fw = new FileWriter(outputFilename+".json")){
            String json = Token.tokenListToJson(tokens);
            fw.append(json);
        }catch(IOException e){
            System.out.println("Error opening file");
            return;
        }
    }
}