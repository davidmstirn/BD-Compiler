/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
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
        String output = indent+"ITER-STMT {\n";
        
        output+=indent+"    COND {\n";
        output+=condition.printTree(indent+"        ")+"\n";
        output+=indent+"    }\n";
        
        output+=indent+"    WHILE-PART {\n";
        output+=whilePart.printTree(indent+"        ")+"\n";
        output+=indent+"    }\n";
        
        output+=indent+"}";
        return output;
    }
}
