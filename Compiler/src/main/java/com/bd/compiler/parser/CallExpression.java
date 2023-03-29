package com.bd.compiler.parser;

import java.util.List;

/**
 * CallExpression
 * File: CallExpression.java
 * A C- Function call Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class CallExpression extends Expression {
    private final String identifier;
    private final List<Expression> arguments;
    
    public CallExpression(String id, List<Expression> arguments){
        this.identifier = id;
        this.arguments = arguments;
    }
    
    public String getIdentifier() { return identifier; }
    public List<Expression> getArguments() { return arguments; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"CALL-EXP: " + identifier;
        output+="(\n";
        
        
        if(arguments != null){
            output+=indent+"    ARGS {\n";
            for(Expression e : arguments) {
                output+=e.printTree(indent+"        ")+"\n";
            }
            output+=indent+"    }\n";
        }
        
        output+=indent+")";
        
        return output;
    }
}
