package com.bd.compiler.parser;

/**
 * VariableExpression
 * File: VariableExpression.java
 * A C- Variable Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class VariableExpression extends Expression {
    private final String identifier;
    private final Expression arraySubscript;
    
    public VariableExpression(String id, Expression arraySubscript){
        this.identifier = id;
        this.arraySubscript = arraySubscript;
    }
    
    public String getIdentifier() { return identifier; }
    public Expression getArraySubscript() { return arraySubscript; }
    
    @Override
    public String printTree(String indent) {
        String output = indent + identifier;
        
        if(arraySubscript != null) {
            output+="[\n";
            output+=arraySubscript.printTree(indent+"    ")+"\n";
            output+=indent+"]";
        }
        
        return output;
    }
}
