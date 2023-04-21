package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Function;

/**
 * Statement
 * File: Statement.java
 * A C- abstract Statement to be inherited by grammar nodes
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public abstract class Statement {
    public abstract String printTree(String indent);
    
    public abstract void genLLCode(Function curr) throws CompilerException;
}
