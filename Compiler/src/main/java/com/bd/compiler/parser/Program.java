package com.bd.compiler.parser;

import java.util.List;

/**
 * Program
 File: Program.java
 TODO: describe this file!
 * 
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 3/11/23
 * Copyright of the authors
 */
public class Program {
    private final List<Declaration> declarationList;
    
    public Program(List<Declaration> declList){
        declarationList = declList;
    }
    
    public void printTree() {
        System.out.println("Program {");
        for(Declaration decl : declarationList){
            decl.printTree("    ");
        }
        System.out.println("}");
    }
}
