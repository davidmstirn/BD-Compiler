/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

import com.bd.compiler.parser.CMinusParser.BinaryOperation;

/**
 *
 * @author dajms
 */
public class BinaryExpression extends Expression {
    private final Expression lhs;
    private final Expression rhs;
    private final BinaryOperation op;
    
    public BinaryExpression(Expression lhs, BinaryOperation op, Expression rhs){
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }
    
    public Expression getLhs() { return lhs; }
    public Expression getRhs() { return rhs; }
    public BinaryOperation getOp() { return op; }
        
    @Override
    public String printTree(String indent) {
        String output = indent+"BIN-EXP: " + op.name() + " {\n";
        
        output+=lhs.printTree(indent+"    ")+"\n";
        output+=rhs.printTree(indent+"    ")+"\n";
        
        output+=indent+"}";
        return output;
    }
}
