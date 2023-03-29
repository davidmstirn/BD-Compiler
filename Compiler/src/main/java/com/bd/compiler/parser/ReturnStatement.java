package com.bd.compiler.parser;

/**
 * ReturnStatement
 * File: ReturnStatement.java
 * A C- Return Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class ReturnStatement extends Statement {
    private final Expression statement;
    
    public ReturnStatement(Expression condition) {
        this.statement = condition;
    }
    
    public Expression getStatement() { return statement; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"RET-STMT {\n";
        
        output+=indent+"    RETURN {\n";
        if (statement != null) {
            output+=statement.printTree(indent+"        ")+"\n";
        output+=indent+"    }\n";
        }
        
        output+=indent+"}";
        return output;
    }
}
