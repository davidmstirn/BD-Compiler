package com.bd.compiler.parser;

/**
 * ExpressionStatement
 * File: ExpressionStatement.java
 * A C- Expression Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class ExpressionStatement extends Statement {
    private final Expression exp;
    
    public ExpressionStatement(Expression e) {
        exp = e;
    }
    
    public Expression getExpression() { return exp; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"EXP-STMT {\n";
        if (exp != null) {
            output+= exp.printTree(indent+"    ")+"\n";
        }
        output+= indent+"}";
        return output;
    }
}
