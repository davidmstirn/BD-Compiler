package com.bd.compiler.parser;

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
}
