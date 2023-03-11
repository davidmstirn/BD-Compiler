package com.bd.compiler.parser;

import java.util.List;

/**
 * AST
 * File: AST.java
 * TODO: describe this file!
 * 
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 3/11/23
 * Copyright of the authors
 */
class AST {
    private List root;
    
    public List GetRoot() {
        return root;
    }
    public void SetRoot(List node) {
        root = node;
    }
}
