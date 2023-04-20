package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.CodeItem;
import java.util.List;

/**
 * Program
 * File: Program.java
 * A top-level C- syntax tree node
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
    
    public List<Declaration> getDeclarationList() { return declarationList; }
    
    public String printTree() {
        String output = "Program {\n";
        for(Declaration decl : declarationList){
            output+=decl.printTree("    ")+"\n";
        }
        output+="}";
        return output;
    }
    
    public CodeItem genLLCode() throws CompilerException{
        Declaration firstDecl = declarationList.get(0);
        
        CodeItem first = firstDecl.genLLCode();
        CodeItem prev = first;
        
        for(int i = 1; i < declarationList.size(); i++){
            Declaration d = declarationList.get(i);
            CodeItem curr = d.genLLCode();
            
            prev.setNextItem(curr);
            prev = curr;
        }
        return first;
    }
}
