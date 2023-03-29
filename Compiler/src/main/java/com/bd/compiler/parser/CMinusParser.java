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
 * A parser for the C- Language
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
    private FileReader fr;
    private BufferedReader br;
    
    public enum TypeSpecifier {
        VOID_TYPE,
        INT_TYPE
    }
    
    public enum BinaryOperation {
        LT_EQ_BINOP,
        LT_BINOP,
        GT_BINOP,
        GT_EQ_BINOP,
        EQ_BINOP,
        NE_BINOP,
        PLUS_BINOP,
        MINUS_BINOP,
        MUL_BINOP,
        DIV_BINOP
    }

    public CMinusParser(String filename){
        file = filename;
        
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
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
        if(program == null){
            try{
                parseProgram();
                br.close();
                fr.close();
            } catch (ParserException e){
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error closing input file");
            }
        }
        
        return program;
    }
    
    // Parses a top-level program
    private Program parseProgram() throws ParserException {
        // program -> declaration-list -> declaration {declaration}
        List<Declaration> declList = new ArrayList<>();
        // First and Subsequent decls
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
        // declaration -> void ID fun-declaration
            matchToken(TokenType.VOID_TOKEN);
            Token id = matchToken(TokenType.ID_TOKEN);
            d = parseFunDeclaration(TypeSpecifier.VOID_TYPE, (String) id.getData());
            
        } else if(currentToken.getType() == TokenType.INT_TOKEN){
        // declaration -> int ID declaration'
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
        // declaration' -> ;
            matchToken(TokenType.SEMI_TOKEN);
            d = new VariableDeclaration(id, null);
            
        } else if(currentToken.getType() == TokenType.LSQ_TOKEN) {
        // declaration' -> [ NUM ] ;
            matchToken(TokenType.LSQ_TOKEN);
            Token num = matchToken(TokenType.NUM_TOKEN);
            matchToken(TokenType.RSQ_TOKEN);
            matchToken(TokenType.SEMI_TOKEN);
            d = new VariableDeclaration(id, (Integer) num.getData());
            
        } else if(currentToken.getType() == TokenType.LPAR_TOKEN) {
        // declaration' -> fun-declaration
            d = parseFunDeclaration(TypeSpecifier.INT_TYPE, id);
            
        } else {
            throw new ParserException("Error parsing decl' : invalid token " + currentToken.getType());
        }
        
        return d;
    }
    
    private FunctionDeclaration parseFunDeclaration(TypeSpecifier type, String id) throws ParserException {
        // fun-declaration -> ( params ) compound-stmt
        matchToken(TokenType.LPAR_TOKEN);
        List<Parameter> params = parseParams();
        matchToken(TokenType.RPAR_TOKEN);
        CompoundStatement body = parseCompoundStatement();

        return new FunctionDeclaration(type, id, params, body);
    }
    
    private List<Parameter> parseParams() throws ParserException {
        List<Parameter> params = new ArrayList<Parameter>();
        
        if (currentToken.getType() == TokenType.VOID_TOKEN) {
        // params -> void
            params.add(new Parameter(TypeSpecifier.VOID_TYPE, "", false));
            matchToken(TokenType.VOID_TOKEN);
        } else if (currentToken.getType() == TokenType.INT_TOKEN) {
        // params -> param-list
            params = parseParamList();
        } else {
            throw new ParserException("Error parsing params : invalid token " + currentToken.getType());
        }
        return params;
    }
    
    private List<Parameter> parseParamList() throws ParserException {
        // param-list -> param {, param}
        List<Parameter> params = new ArrayList<Parameter>();
        
        // First param
        if (currentToken.getType() == TokenType.INT_TOKEN) {
            params.add(parseParam());
        } else {
            throw new ParserException("Error parsing param-list : invalid token " + currentToken.getType());
        }
        
        // Closure for , param
        while(currentToken.getType() == TokenType.COMMA_TOKEN) {
            matchToken(TokenType.COMMA_TOKEN);
            if (currentToken.getType() == TokenType.INT_TOKEN) {
                params.add(parseParam());
            } else {
                throw new ParserException("Error parsing param-list : invalid token " + currentToken.getType());
            }
        }
        
        return params;
    }
    
    private Parameter parseParam() throws ParserException {
        // param -> int ID [[ ]]
        
        matchToken(TokenType.INT_TOKEN);
        // Cast this later so that if the token is incorrect, the parser error is thrown first
        Object id = currentToken.getData();
        matchToken(TokenType.ID_TOKEN);

        // Optional square brackets
        if (currentToken.getType() == TokenType.LSQ_TOKEN) {
            matchToken(TokenType.LSQ_TOKEN);
            matchToken(TokenType.RSQ_TOKEN);
            return new Parameter(TypeSpecifier.INT_TYPE, (String) id, true);
        } else {
            return new Parameter(TypeSpecifier.INT_TYPE, (String) id, false);
        }
    }
       
    private CompoundStatement parseCompoundStatement() throws ParserException {
        // compound-stmt -> { local-declarations statement-list }
        
        matchToken(TokenType.LCURL_TOKEN);
        List<Declaration> locals = parseLocalDeclarations();
        List<Statement> stmts = parseStatementList();
        matchToken(TokenType.RCURL_TOKEN);
        return new CompoundStatement(locals, stmts);
    }
    
    private List<Declaration> parseLocalDeclarations() throws ParserException {
        // local-declarations -> {local-declaration}
        
        List<Declaration> decls = new ArrayList<Declaration>();
        
        // First and subsequent decls
        while (currentToken.getType() == TokenType.INT_TOKEN) {
            decls.add(parseLocalDeclaration());
        }
        return decls;
    }
    
    private Declaration parseLocalDeclaration() throws ParserException {
        // local-declaration -> int ID [[ NUM ]] ;
        
        VariableDeclaration decl;
        
        matchToken(TokenType.INT_TOKEN);
        // Cast this later so that if the token is incorrect, the parser error is thrown first
        Object id = currentToken.getData();
        matchToken(TokenType.ID_TOKEN);
        
        // Optional [ NUM ]
        if (currentToken.getType() == TokenType.LSQ_TOKEN) {
            matchToken(TokenType.LSQ_TOKEN);
            // Cast this later so that if the token is incorrect, the parser error is thrown first
            Object len = currentToken.getData();
            matchToken(TokenType.NUM_TOKEN);
            matchToken(TokenType.RSQ_TOKEN);
            decl = new VariableDeclaration((String) id, (Integer) len);
        } else {
            decl = new VariableDeclaration((String) id, null);
        }
        
        matchToken(TokenType.SEMI_TOKEN);
        
        return decl;
    }
    
    private List<Statement> parseStatementList() throws ParserException {
        // statement-list -> {statement}
        
        List<Statement> stmtList = new ArrayList<Statement>();
        TokenType currType = currentToken.getType();
        
        // First and subsequent statements
        while ( currType == TokenType.LCURL_TOKEN ||
                currType == TokenType.SEMI_TOKEN ||
                currType == TokenType.IF_TOKEN ||
                currType == TokenType.WHILE_TOKEN ||
                currType == TokenType.RETURN_TOKEN ||
                currType == TokenType.ID_TOKEN ||
                currType == TokenType.NUM_TOKEN ||
                currType == TokenType.LPAR_TOKEN )
        {
            stmtList.add(parseStatement());
            currType = currentToken.getType();
        }

        return stmtList;
    }

    private Statement parseStatement() throws ParserException {
        if (currentToken.getType() == TokenType.LCURL_TOKEN) {
        // statement -> compound-stmt
            return parseCompoundStatement();
        } else if (currentToken.getType() == TokenType.IF_TOKEN) {
        // statement -> selection-stmt
            return parseSelectionStatement();
        } else if (currentToken.getType() == TokenType.WHILE_TOKEN) {
        // statement -> iteration-stmt
            return parseIterationStatement();
        } else if (currentToken.getType() == TokenType.RETURN_TOKEN) {
        // statement -> return-stmt
            return parseReturnStatement();
        } else if ( currentToken.getType() == TokenType.SEMI_TOKEN ||
                    currentToken.getType() == TokenType.ID_TOKEN ||
                    currentToken.getType() == TokenType.NUM_TOKEN ||
                    currentToken.getType() == TokenType.LPAR_TOKEN ) {
        // statement -> expression-stmt
            return parseExpressionStatement();
        } else {
            throw new ParserException("Error parsing stmt : invalid token " + currentToken.getType());
        }
    }
    
    private ExpressionStatement parseExpressionStatement() throws ParserException {
        // expression-stmt -> [expression] ;
        ExpressionStatement exprStmt = new ExpressionStatement(null);
        
        // Optional expression
        if (currentToken.getType() == TokenType.ID_TOKEN ||
            currentToken.getType() == TokenType.NUM_TOKEN ||
            currentToken.getType() == TokenType.LPAR_TOKEN )
        {
            exprStmt = new ExpressionStatement(parseExpression());
        }
        
        matchToken(TokenType.SEMI_TOKEN);
        return exprStmt;
    }
    
    private SelectionStatement parseSelectionStatement() throws ParserException {
        // selection-stmt -> if ( expression ) statement [else statement]
        matchToken(TokenType.IF_TOKEN);
        matchToken(TokenType.LPAR_TOKEN);
        Expression expr = parseExpression();
        matchToken(TokenType.RPAR_TOKEN);
        Statement stmt = parseStatement();
        
        // Optional else part
        Statement elseStmt = null;
        if (currentToken.getType() == TokenType.ELSE_TOKEN) {
            matchToken(TokenType.ELSE_TOKEN);
            elseStmt = parseStatement();
        }
        return new SelectionStatement(expr, stmt, elseStmt);
    }
    
    private IterationStatement parseIterationStatement() throws ParserException {
        // iteration-stmt -> while ( expression ) statement
        matchToken(TokenType.WHILE_TOKEN);
        matchToken(TokenType.LPAR_TOKEN);
        Expression expr = parseExpression();
        matchToken(TokenType.RPAR_TOKEN);
        Statement stmt = parseStatement();
        return new IterationStatement(expr, stmt);
    }
    
    private ReturnStatement parseReturnStatement() throws ParserException {
        // return-stmt -> return [expression] ;
        ReturnStatement retStmt = new ReturnStatement(null);

        matchToken(TokenType.RETURN_TOKEN);
        
        // Optional expression
        if (currentToken.getType() == TokenType.ID_TOKEN ||
            currentToken.getType() == TokenType.NUM_TOKEN ||
            currentToken.getType() == TokenType.LPAR_TOKEN )
        {
            retStmt = new ReturnStatement(parseExpression());
        }
        
        matchToken(TokenType.SEMI_TOKEN);
        return retStmt;
    }
                
    private Expression parseExpression() throws ParserException {
        Expression e;
        
        if(currentToken.getType() == TokenType.ID_TOKEN) {
        // expression -> ID expression'
            Token id = matchToken(TokenType.ID_TOKEN);
            e = parseExpressionPrime((String) id.getData());
        } else if(currentToken.getType() == TokenType.NUM_TOKEN) {
        // expression -> NUM simple-expression
            Token num = matchToken(TokenType.NUM_TOKEN);
            e = new NumberExpression((Integer) num.getData());
            e = parseSimpleExpression(e);
        } else if(currentToken.getType() == TokenType.LPAR_TOKEN) {
        // expression -> ( expression ) simple-expression
            matchToken(TokenType.LPAR_TOKEN);
            e = parseExpression();
            matchToken(TokenType.RPAR_TOKEN);
            e = parseSimpleExpression(e);
        } else {
            throw new ParserException("Error parsing expr : invalid token " + currentToken.getType());
        }
        
        return e;
    }
    
    private Expression parseExpressionPrime(String id) throws ParserException {
        Expression e;
        
        if(currentToken.getType() == TokenType.ASSIGN_TOKEN) {
        // expression' -> = expression
            matchToken(TokenType.ASSIGN_TOKEN);
            VariableExpression ve = new VariableExpression(id, null);
            e = parseExpression();
            e = new AssignExpression(ve, e);
        } else if(currentToken.getType() == TokenType.LSQ_TOKEN) {
        // expression' -> [ expression ] expression''
            matchToken(TokenType.LSQ_TOKEN);
            e = parseExpression();
            matchToken(TokenType.RSQ_TOKEN);
            VariableExpression ve = new VariableExpression(id, e);
            e = parseExpressionPrimePrime(ve);
        } else if(currentToken.getType() == TokenType.LPAR_TOKEN) {
        // expression' -> ( args ) simple-expression
            matchToken(TokenType.LPAR_TOKEN);
            List<Expression> args = parseArguments();
            matchToken(TokenType.RPAR_TOKEN);
            e = new CallExpression(id, args);
            e = parseSimpleExpression(e);
        } else if (isMulop(currentToken)
                || isAddop(currentToken)
                || isRelop(currentToken)
                || currentToken.getType() == TokenType.SEMI_TOKEN
                || currentToken.getType() == TokenType.RPAR_TOKEN
                || currentToken.getType() == TokenType.RSQ_TOKEN
                || currentToken.getType() == TokenType.COMMA_TOKEN) {
        // expression' -> simple-expression (if follow(factor))
            Expression ve = new VariableExpression(id, null);
            e = parseSimpleExpression(ve);
        } else {
            throw new ParserException("Error parsing expr' : invalid token " + currentToken.getType());
        }
        
        return e;
    }
    
    private Expression parseExpressionPrimePrime(VariableExpression ve) throws ParserException {
        Expression e;
        
        if(currentToken.getType() == TokenType.ASSIGN_TOKEN) {
        // expression'' -> = expression
            matchToken(TokenType.ASSIGN_TOKEN);
            Expression rhs = parseExpression();
            e = new AssignExpression(ve, rhs);
        } else if (isMulop(currentToken)
                || isAddop(currentToken)
                || isRelop(currentToken)
                || currentToken.getType() == TokenType.SEMI_TOKEN
                || currentToken.getType() == TokenType.RPAR_TOKEN
                || currentToken.getType() == TokenType.RSQ_TOKEN
                || currentToken.getType() == TokenType.COMMA_TOKEN) {
        // expression'' -> simple-expression (if follow(factor))
            e = parseSimpleExpression(ve);
        } else {
            throw new ParserException("Error parsing expr'' : invalid token " + currentToken.getType());
        }
        
        return e;
    }
    
    private Expression parseSimpleExpression(Expression e) throws ParserException {
        // simple-expression -> additive-expression' [relop additive-expression]
        Expression lhs = parseAdditiveExpressionPrime(e);
        Expression rhs;
        
        // Optional relop additive-expression
        switch (currentToken.getType()) {
            case LTE_TOKEN -> {
                matchToken(TokenType.LTE_TOKEN);
                rhs = parseAdditiveExpression();
                lhs = new BinaryExpression(lhs, BinaryOperation.LT_EQ_BINOP, rhs);
            }
            case LT_TOKEN -> {
                matchToken(TokenType.LT_TOKEN);
                rhs = parseAdditiveExpression();
                lhs = new BinaryExpression(lhs, BinaryOperation.LT_BINOP, rhs);
            }
            case GT_TOKEN -> {
                matchToken(TokenType.GT_TOKEN);
                rhs = parseAdditiveExpression();
                lhs = new BinaryExpression(lhs, BinaryOperation.GT_BINOP, rhs);
            }
            case GTE_TOKEN -> {
                matchToken(TokenType.GTE_TOKEN);
                rhs = parseAdditiveExpression();
                lhs = new BinaryExpression(lhs, BinaryOperation.GT_EQ_BINOP, rhs);
            }
            case EQ_TOKEN -> {
                matchToken(TokenType.EQ_TOKEN);
                rhs = parseAdditiveExpression();
                lhs = new BinaryExpression(lhs, BinaryOperation.EQ_BINOP, rhs);
            }
            case NOTEQ_TOKEN -> {
                matchToken(TokenType.NOTEQ_TOKEN);
                rhs = parseAdditiveExpression();
                lhs = new BinaryExpression(lhs, BinaryOperation.NE_BINOP, rhs);
            }
            default -> {
            }
        }
        
        return lhs;
    }
    
    private Expression parseAdditiveExpression() throws ParserException {
        // additive-expression -> term {addop term}
        Expression lhs = parseTerm();
        Expression rhs;
        
        // Closure for addop term
        while(isAddop(currentToken)) {
            if(currentToken.getType() == TokenType.PLUS_TOKEN){
                matchToken(TokenType.PLUS_TOKEN);
                rhs = parseTerm();
                lhs = new BinaryExpression(lhs, BinaryOperation.PLUS_BINOP, rhs);
            } else if(currentToken.getType() == TokenType.MINUS_TOKEN) {
                matchToken(TokenType.MINUS_TOKEN);
                rhs = parseTerm();
                lhs = new BinaryExpression(lhs, BinaryOperation.MINUS_BINOP, rhs);
            }
        }
        
        return lhs;
    }
    
    private Expression parseAdditiveExpressionPrime(Expression e) throws ParserException {
        // additive-expression' -> term' {addop term}
        Expression lhs = parseTermPrime(e);
        Expression rhs;
        
        // Closure for addop term
        while(isAddop(currentToken)) {
            if(currentToken.getType() == TokenType.PLUS_TOKEN){
                matchToken(TokenType.PLUS_TOKEN);
                rhs = parseTerm();
                lhs = new BinaryExpression(lhs, BinaryOperation.PLUS_BINOP, rhs);
            } else if(currentToken.getType() == TokenType.MINUS_TOKEN) {
                matchToken(TokenType.MINUS_TOKEN);
                rhs = parseTerm();
                lhs = new BinaryExpression(lhs, BinaryOperation.MINUS_BINOP, rhs);
            }
        }
        
        return lhs;
    }
    
    private Expression parseTerm() throws ParserException {
        // term -> factor {mulop factor}
        Expression lhs = parseFactor();
        Expression rhs;
        
        // Closure for mulop factor
        while(isMulop(currentToken)) {
            if(currentToken.getType() == TokenType.MULT_TOKEN){
                matchToken(TokenType.MULT_TOKEN);
                rhs = parseFactor();
                lhs = new BinaryExpression(lhs, BinaryOperation.MUL_BINOP, rhs);
            } else if(currentToken.getType() == TokenType.DIV_TOKEN) {
                matchToken(TokenType.DIV_TOKEN);
                rhs = parseFactor();
                lhs = new BinaryExpression(lhs, BinaryOperation.DIV_BINOP, rhs);
            }
        }
        
        return lhs;
    }
    
    private Expression parseTermPrime(Expression e) throws ParserException {
        // term' -> {mulop factor}
        Expression lhs = e;
        Expression rhs;
        
        // Closure for mulop factor
        while(isMulop(currentToken)) {
            if(currentToken.getType() == TokenType.MULT_TOKEN){
                matchToken(TokenType.MULT_TOKEN);
                rhs = parseFactor();
                lhs = new BinaryExpression(lhs, BinaryOperation.MUL_BINOP, rhs);
            } else if(currentToken.getType() == TokenType.DIV_TOKEN) {
                matchToken(TokenType.DIV_TOKEN);
                rhs = parseFactor();
                lhs = new BinaryExpression(lhs, BinaryOperation.DIV_BINOP, rhs);
            }
        }
        
        return lhs;
    }
    
    private Expression parseFactor() throws ParserException {
        Expression e;
        
        if(currentToken.getType() == TokenType.LPAR_TOKEN) {
        // factor -> ( expression )
            matchToken(TokenType.LPAR_TOKEN);
            e = parseExpression();
            matchToken(TokenType.RPAR_TOKEN);
        } else if(currentToken.getType() == TokenType.ID_TOKEN) {
        // factor -> ID varcall
            Token id = matchToken(TokenType.ID_TOKEN);
            e = parseVarCall((String) id.getData());
        } else if(currentToken.getType() == TokenType.NUM_TOKEN) {
        // factor -> NUM
            Token num = matchToken(TokenType.NUM_TOKEN);
            e = new NumberExpression((Integer) num.getData());
        } else {
            throw new ParserException("Error parsing factor : invalid token " + currentToken.getType());
        }
        
         return e;
    }
    
    private Expression parseVarCall(String id) throws ParserException {
        Expression e;
        
        // Follow set. Go away quietly
        if(        isMulop(currentToken)
                || isAddop(currentToken)
                || isRelop(currentToken)
                || currentToken.getType() == TokenType.SEMI_TOKEN
                || currentToken.getType() == TokenType.RPAR_TOKEN
                || currentToken.getType() == TokenType.RSQ_TOKEN
                || currentToken.getType() == TokenType.COMMA_TOKEN) {
            e = new VariableExpression(id, null);
        } else if(currentToken.getType() == TokenType.LSQ_TOKEN) {
        // varcall -> [ expression ]
            matchToken(TokenType.LSQ_TOKEN);
            e = parseExpression();
            matchToken(TokenType.RSQ_TOKEN);
            e = new VariableExpression(id, e);
        } else if(currentToken.getType() == TokenType.LPAR_TOKEN) {
        // varcall -> ( args )
            matchToken(TokenType.LPAR_TOKEN);
            List<Expression> args = parseArguments();
            matchToken(TokenType.RPAR_TOKEN);
            e = new CallExpression(id, args);
        } else {
            throw new ParserException("Error parsing varcall : invalid token " + currentToken.getType());
        }
        
        return e;
    }
    
    private List<Expression> parseArguments() throws ParserException {
        // args -> [arg-list]
        List<Expression> args = null;
        
        // Optional arg-list
        if(        currentToken.getType() == TokenType.ID_TOKEN
                || currentToken.getType() == TokenType.NUM_TOKEN
                || currentToken.getType() == TokenType.LPAR_TOKEN) {
            args = parseArgumentList();
        }
        
        return args;
    }
    
    private List<Expression> parseArgumentList() throws ParserException {
        // arg-list -> expression {, expression}
        List<Expression> args = new ArrayList<>();
        
        Expression e = parseExpression();
        args.add(e);
        
        // Closure for , expression
        while(currentToken.getType() == TokenType.COMMA_TOKEN) {
            matchToken(TokenType.COMMA_TOKEN);
            e = parseExpression();
            args.add(e);
        }
        
        return args;
    }
    
    // Matches and advances the currentToken
    private Token matchToken(TokenType t) throws ParserException {
        Token prev = currentToken;
        
        if(prev.getType() == t){
            try {
                currentToken = scanner.getNextToken();
            } catch(ScannerException e) {
                throw new ParserException("Error scanning: last token: " + prev.getType());
            }
        } else {
            throw new ParserException("Error matching: " + t + " and " + prev.getType());
        }
        
        return prev;
    }
    
    private boolean isRelop(Token t) {
        return     t.getType() == TokenType.LTE_TOKEN
                || t.getType() == TokenType.LT_TOKEN
                || t.getType() == TokenType.GT_TOKEN
                || t.getType() == TokenType.GTE_TOKEN
                || t.getType() == TokenType.EQ_TOKEN
                || t.getType() == TokenType.NOTEQ_TOKEN;
    }
    
    private boolean isAddop(Token t) {
        return     t.getType() == TokenType.PLUS_TOKEN
                || t.getType() == TokenType.MINUS_TOKEN;
    }
    
    private boolean isMulop(Token t) {
        return     t.getType() == TokenType.MULT_TOKEN
                || t.getType() == TokenType.DIV_TOKEN;
    }
    
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("ERROR: Parser requires 1 argument, the path to the C- Code");
            return;
        }

        String filename = args[0];
        
        // Parse
        CMinusParser cmp = new CMinusParser(filename);
        Program p = cmp.parse();
        
        // Print output
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
