package com.bd.compiler.parser;

import com.bd.compiler.scanner.Token;

/**
 * Statement
 * File: Statement.java
 * TODO: describe this file!
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class Statement {

    private Statement parseIfStmt() {
        matchToken(Token.TokenType.IF_TOKEN);
        matchToken(Token.TokenType.LPAR_TOKEN);
        Expression ifExpr = parseExpression();
        matchToken(Token.TokenType.RPAR_TOKEN);
        Statement thenStmt = parseStatement();
        Statement elseStmt = null;
        if (currentToken.tokenType == Token.TokenType.ELSE_TOKEN) {
            AdvanceToken();
            elseStmt = parseStatement();
        }
        Statement returnStmt = new IfStatement(ifExpr, thenStmt, elseStmt);
        return returnStmt;
    }
}
