package com.bd.compiler.parser;

import com.bd.compiler.parser.CMinusParser.BinaryOperation;

/**
 * BinaryExpression
 * File: BinaryExpression.java
 * A C- Binary Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class BinaryExpression extends Expression {
    private final Expression lhs;
    private final Expression rhs;
    private final BinaryOperation op;
    
    public BinaryExpression(Expression lhs, BinaryOperation op, Expression rhs){
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }
    
    public Expression getLhs() { return lhs; }
    public Expression getRhs() { return rhs; }
    public BinaryOperation getOp() { return op; }
        
    @Override
    public String printTree(String indent) {
        String output = indent+"BIN-EXP: " + op.name() + " {\n";
        
        output+=lhs.printTree(indent+"    ")+"\n";
        output+=rhs.printTree(indent+"    ")+"\n";
        
        output+=indent+"}";
        return output;
    }
}
