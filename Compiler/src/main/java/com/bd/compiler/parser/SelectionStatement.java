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
