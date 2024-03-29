package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Function;
import java.util.List;

/**
 * CompoundStatement
 * File: CompoundStatement.java
 * A C- Compound Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class CompoundStatement extends Statement {
    private final List<Declaration> localDeclarationList;
    private final List<Statement> statementList;
    
    public CompoundStatement(List<Declaration> ldl, List<Statement> sl) {
        localDeclarationList = ldl;
        statementList = sl;
    }
    
    public List<Declaration> getDeclarationList() { return localDeclarationList; }
    public List<Statement> getStatementList() { return statementList; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"{\n";
        
        for(Declaration d : localDeclarationList) {
            output+=d.printTree(indent+"    ")+"\n";
        }
        for(Statement s : statementList) {
            output+=s.printTree(indent+"    ")+"\n";
        }
        
        output+=indent+"}";
        return output;
    }
    
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        // Let local declarations add themselves to function symbol table
        if (localDeclarationList != null) {
            for(Declaration d : localDeclarationList) {
                ((VariableDeclaration) d).genLLCode(curr);
            }
        }
        // Let statements add their code to blocks
        if (statementList != null) {
            for(Statement s : statementList) {
                s.genLLCode(curr);
            }
        }
    }
}
