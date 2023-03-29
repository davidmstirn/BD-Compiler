package com.bd.compiler.parser;

/**
 * IterationStatement
 * File: IterationStatement.java
 * A C- Iteration Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class IterationStatement extends Statement {
    private final Expression condition;
    private final Statement whilePart;
    
    public IterationStatement(Expression condition, Statement whilePart) {
        this.condition = condition;
        this.whilePart = whilePart;
    }
    
    public Expression getCondition() { return condition; }
    public Statement getWhilePart() { return whilePart; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"while(\n";
        
        output+=condition.printTree(indent+"    ")+"\n";
        output+=indent+") {\n";
        output+=whilePart.printTree(indent+"    ")+"\n";
        output+=indent+"}";
        
        return output;
    }
}
