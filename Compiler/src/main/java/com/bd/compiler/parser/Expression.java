package com.bd.compiler.parser;

/**
 * Expression
 * File: Expression.java
 * A C- abstract Expression to be inherited by grammar nodes
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public abstract class Expression {
    public abstract String printTree(String indent);
}
