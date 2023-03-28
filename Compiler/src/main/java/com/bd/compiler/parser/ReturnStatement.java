/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
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
