package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.BasicBlock;
import com.bd.compiler.lowlevel.CodeItem;
import com.bd.compiler.lowlevel.Data;
import com.bd.compiler.lowlevel.Function;
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
        CodeItem first = null;
        CodeItem prev = null;
        for(int i = 0; i < declarationList.size(); i++){
            Declaration d = declarationList.get(i);
            CodeItem curr = d.genLLCode();
            
            if(i == 0) { 
                first = curr;
            } else{
                prev.setNextItem(curr);
            }
            prev = curr;
        }
        return first;
    }
}
