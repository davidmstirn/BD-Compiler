/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
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
        String output = indent+"VAR-EXP: " + identifier;
        
        if(arraySubscript != null) {
            output+="[\n";
            output+=indent+"    EXP {\n";
            output+=arraySubscript.printTree(indent+"    ")+"\n";
            output+=indent+"    }\n";
            output+=indent+"]";
        }
        
        return output;
    }
}
