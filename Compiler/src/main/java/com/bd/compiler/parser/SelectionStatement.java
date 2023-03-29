package com.bd.compiler.parser;

/**
 * SelectionStatement
 * File: SelectionStatement.java
 * A C- Selection Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class SelectionStatement extends Statement {
    private final Expression condition;
    private final Statement ifPart;
    private final Statement elsePart;
    
    public SelectionStatement(Expression condition, Statement ifPart, Statement elsePart) {
        this.condition = condition;
        this.ifPart = ifPart;
        this.elsePart = elsePart;
    }
    
    public Expression getCondition() { return condition; }
    public Statement getIfPart() { return ifPart; }
    public Statement getElsePart() { return elsePart; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"IF-STMT {\n";
        
        output+=indent+"    COND {\n";
        output+=condition.printTree(indent+"        ")+"\n";
        output+=indent+"    }\n";
        
        output+=indent+"    IF-PART {\n";
        output+=ifPart.printTree(indent+"        ")+"\n";
        output+=indent+"    }\n";
        
        if (elsePart != null) {
            output+=indent+"    ELSE-PART {\n";
            output+=elsePart.printTree(indent+"        ")+"\n";
            output+=indent+"    }\n";
        }
        
        output+=indent+"}";
        return output;
    }
}
