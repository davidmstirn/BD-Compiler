/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
 */
public class AssignExpression extends Expression {
    private final String identifier;
    private final Expression rhs;
    
    public AssignExpression(String id, Expression rhs){
        this.identifier = id;
        this.rhs = rhs;
    }
    
    public String getIdentifier() { return identifier; }
    public Expression getRhs() { return rhs; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"ASSIGN-EXP: " + identifier + " {\n";
        
        output+=rhs.printTree(indent+"    ")+"\n";
        output+="}\n";
        
        return output;
    }
}
