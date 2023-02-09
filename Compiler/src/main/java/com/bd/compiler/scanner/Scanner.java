package com.bd.compiler.scanner;

/**
 * Scanner
 * File: Scanner.java
 * A scanner that provides tokens to a parser
 * 
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 2/7/23
 * Copyright of the authors
 */
public interface Scanner {
    public Token getNextToken () throws ScannerException;
    public Token viewNextToken () throws ScannerException;
}
