/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

import java.util.List;

/**
 *
 * @author dajms
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
        String output = indent+"CMPD-STMT {\n";
        
        for(Declaration d : localDeclarationList) {
            output+=d.printTree(indent+"    ")+"\n";
        }
        for(Statement s : statementList) {
            output+=s.printTree(indent+"    ")+"\n";
        }
        
        output+=indent+"}";
        return output;
    }
}
