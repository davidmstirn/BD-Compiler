package com.bd.compiler.parser;

/**
 * VariableDeclaration
 * File: VariableDeclaration.java
 * A C- Variable Declaration grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class VariableDeclaration extends Declaration {
    private final Integer arrayLength;
    
    public VariableDeclaration(String id, Integer arrayLength){
        super(id);
        this.arrayLength = arrayLength;
    }
    
    public Integer getArrayLength() { return arrayLength; }
    
    @Override
    public String printTree(String indent) {
        String len = arrayLength != null ? "["+String.valueOf(arrayLength)+"]" : "";
        return indent + "VAR-DECL: " + this.getID() + len;
    }
}
