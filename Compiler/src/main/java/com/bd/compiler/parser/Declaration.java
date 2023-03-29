package com.bd.compiler.parser;

/**
 * Declaration
 * File: Declaration.java
 * A C- abstract Declaration to be inherited by grammar nodes
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public abstract class Declaration {
    private final String id;
    
    public Declaration(String id) {
        this.id = id;
    }
    
    public String getID() { return id; }
    
    public abstract String printTree(String indent);
}
