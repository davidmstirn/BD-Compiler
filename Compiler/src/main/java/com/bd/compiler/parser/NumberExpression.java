package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Function;

/**
 * NumberExpression
 * File: NumberExpression.java
 * A C- Number Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class NumberExpression extends Expression {
    private final Integer value;
    
    public NumberExpression(Integer value){
        this.value = value;
    }
    
    public Integer getValue() { return value; }
    
    @Override
    public String printTree(String indent) {
        String output = indent + value ;
        
        return output;
    }

    @Override
    public void genLLCode(Function curr) throws CompilerException {
        // TODO: What is this used for?
    }
}
