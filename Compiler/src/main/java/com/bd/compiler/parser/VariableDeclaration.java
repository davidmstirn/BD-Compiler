package com.bd.compiler.parser;

import com.bd.compiler.parser.CMinusParser.TypeSpecifier;

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
    
    public VariableDeclaration(TypeSpecifier type, String id, Integer arrayLength){
        super(type, id);
        this.arrayLength = arrayLength;
    }
    
    public Integer getArrayLength() { return arrayLength; }
    
    @Override
    public String printTree(String indent) {
        String output = indent;
        
        if(this.getType() == TypeSpecifier.INT_TYPE) {
            output+="int " + getID();
        } else if (this.getType() == TypeSpecifier.VOID_TYPE) {
            output+="void " + getID();
        }
        
        output+=arrayLength != null ? "["+String.valueOf(arrayLength)+"];" : ";";
        return output;
    }
}
