package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Function;

/**
 * ExpressionStatement
 * File: ExpressionStatement.java
 * A C- Expression Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class ExpressionStatement extends Statement {
    private final Expression exp;
    
    public ExpressionStatement(Expression e) {
        exp = e;
    }
    
    public Expression getExpression() { return exp; }
    
    @Override
    public String printTree(String indent) {
        String output = "";
        
        if(exp != null){
            output+= exp.printTree(indent)+";";
        }
        
        return output;
    }
    
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        if(exp != null){
            exp.genLLCode(curr);
        }
    }
}
