package com.bd.compiler.parser;

import com.bd.compiler.scanner.Token;

/**
 * Expression
 * File: Expression.java
 * TODO: describe this file!
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class Expression {

    private Expression parseExpression() {
        Expression lhs = parseTerm();
        while (isAddop(currentToken.tokenType)) {
            Token oldToken = advanceToken();
            Expression rhs = parseTerm();
            // make lhs the result, so set up for next iter
            lhs = createBinopExpr(oldToken.tokenType, lhs, rhs);
        }
        return lhs;
    }

    private Expression parseFactor() {
        switch (currentToken.tokenType) {
            case Token.TokenType.LPAR_TOKEN:
                advanceToken();
                Expression returnExpr = parseExpression();
                matchToken(Token.TokenType.RPAR_TOKEN);
                return returnExpr;
                break;
            case Token.TokenType.ID_TOKEN:
                Token oldToken = advanceToken();
                return createIdentExpr(oldToken);
                break;
            case Token.TokenType.NUM_TOKEN:
                Token oldToken = advanceToken();
                return createNumExpr(oldToken);
                break;
            default:
                logParseError();
                return null;
        }
    }
}
