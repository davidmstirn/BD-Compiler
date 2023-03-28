/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

import java.util.List;

/**
 *
 * @author dajms
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
