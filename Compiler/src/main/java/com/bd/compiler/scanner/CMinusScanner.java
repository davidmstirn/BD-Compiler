package com.bd.compiler.scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CMinusScanner
 * File: CMinusScanner.java
 * A scanner for the C- language. Reads a file and returns tokens when called by
 * a parser
 * 
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 2/7/23
 * Copyright of the authors
 */
public class CMinusScanner implements Scanner {
    private BufferedReader inFile;
    private Token nextToken;
    
    /**
     * Creates a scanner that reads an ASCII file and generates tokens
     * 
     * @param file the file from which to read ASCII from
     * @throws ScannerException if the scan failed
     */
    public CMinusScanner(BufferedReader file) throws ScannerException {
        inFile = file;
        nextToken = scanToken();
    }
    
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
            nextToken = scanToken();
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
        ERROR
    }
    
    // If the input token's data is a C- keyword, return that keyword token
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
    
    private Token scanToken() throws ScannerException {
        Token currentToken = null;
        State state = State.START;
        String tokenValue = "";
        try {
            while (state != State.DONE) {
                inFile.mark(1);
                char c = (char) inFile.read();
                switch (state) {
                    // Determine the next state based on the current state and c
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
                            // If the character is whitespace, ignore it
                            break;
                        } else {
                            // The following are all single-character tokens
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
                                // BufferedReader returns -1 at EOF
                                case (char) -1 -> currentToken = new Token(Token.TokenType.EOF_TOKEN);
                                default -> {
                                    // Anything other character is an error
                                    System.out.println("Invalid token starting with " + c);
                                    return new Token(Token.TokenType.ERROR_TOKEN, c);
                                }
                            }
                        }
                        break;
                    case IDENTIFIER:
                        if (!Character.isAlphabetic(c)) {
                            // if the next character is a digit, go to error
                            if(Character.isDigit(c)) {
                                state = State.ERROR;
                                tokenValue += c;
                            } else {
                                // if the next character is not alphanumeric, backup and return the string up until now
                                inFile.reset();
                                state = State.DONE;
                                currentToken = new Token(Token.TokenType.ID_TOKEN, tokenValue);
                            }
                        } else {
                            // Add alphabetic characters to the data
                            tokenValue += c;
                        }
                        break;
                    case NUMBER:
                        if (!Character.isDigit(c)) {
                            // if the next character is a letter, go to error
                            if(Character.isAlphabetic(c)) {
                                state = State.ERROR;
                                tokenValue += c;
                            } else {
                                // if the next character is not alphanumeric, backup and return the string up until now
                                inFile.reset();
                                currentToken = new Token(Token.TokenType.NUM_TOKEN, Integer.parseInt(tokenValue));
                                state = State.DONE;
                            }
                        } else {
                            // Add digits to the data
                            tokenValue += c;
                        }
                        break;
                    case BANG:
                        // The next character must be =, otherwise it is an error
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
                        // Is this a comment or a divide
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
                        // Move to closecomment state (second star)
                        if (c == '*') {
                            state = State.CLOSECOMMENT;
                        }
                        break;
                    case CLOSECOMMENT:
                        // if not a /, move back to incomment state (first star)
                        if (c != '/') {
                            state = State.INCOMMENT;
                        } else {
                            state = State.START;
                        }
                        break;
                    case ERROR:
                        // Keep consuming characters until a non-letter or a
                        //   non-digit is found
                        if(Character.isAlphabetic(c) || Character.isDigit(c)) {
                            tokenValue += c;
                        } else {
                            inFile.reset();
                            return new Token(Token.TokenType.ERROR_TOKEN, tokenValue);    
                        }
                        break;
                    case DONE:
                    default:
                        throw new ScannerException();
                }
            }
        } catch (IOException e) {
            throw new ScannerException();
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