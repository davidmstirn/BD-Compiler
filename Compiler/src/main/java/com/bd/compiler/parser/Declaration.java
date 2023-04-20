package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.CodeItem;
import com.bd.compiler.parser.CMinusParser.TypeSpecifier;

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
    private final TypeSpecifier type;
    private final String id;
    
    public Declaration(TypeSpecifier type, String id) {
        this.type = type;
        this.id = id;
    }
    
    public TypeSpecifier getType() { return type; }
    public String getID() { return id; }
    
    public abstract String printTree(String indent);
    public abstract CodeItem genLLCode() throws CompilerException;
}
