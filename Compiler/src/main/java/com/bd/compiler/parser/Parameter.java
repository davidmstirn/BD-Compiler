package com.bd.compiler.parser;

import com.bd.compiler.parser.CMinusParser.TypeSpecifier;

/**
 * Parameter
 * File: Parameter.java
 * A C- Parameter grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class Parameter {
    private final TypeSpecifier type;
    private final String identifier;
    private final boolean isArray;
    
    public Parameter(TypeSpecifier type, String identifier, boolean isArray) {
        this.type = type;
        this.identifier = identifier;
        this.isArray = isArray;
    }
    
    public TypeSpecifier getType() { return type; }
    public String getIdentifier() { return identifier; }
    public boolean isArray() { return isArray; }
    
    public String printTree(String indent) {
        String output = indent+"PARAM: " + type + " " + identifier;
        
        if(isArray) {
            output+= "[]";
        }
                        
        return output;
    }
}
