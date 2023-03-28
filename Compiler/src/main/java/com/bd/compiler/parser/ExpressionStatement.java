/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
 */
public class ExpressionStatement extends Statement {
    private final Expression exp;
    
    public ExpressionStatement(Expression e) {
        exp = e;
    }
    
    public Expression getExpression() { return exp; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"EXP-STMT {\n";
        if (exp != null) {
            output+= exp.printTree(indent+"    ")+"\n";
        }
        output+= indent+"}";
        return output;
    }
}
