/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
 */
public class AssignExpression extends Expression {
    private final VariableExpression variableExpression;
    private final Expression rhs;
    
    public AssignExpression(VariableExpression ve, Expression rhs) {
        this.variableExpression = ve;
        this.rhs = rhs;
    }
    
    public VariableExpression getVariableExpression() { return variableExpression; }
    public Expression getRhs() { return rhs; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"ASSIGN-EXP {\n";
        
        output+=indent+"    LHS {"+"\n";
        output+=variableExpression.printTree(indent+"        ")+"\n";
        output+=indent+"    }"+"\n";
        
        output+=indent+"    RHS {"+"\n";
        output+=rhs.printTree(indent+"        ")+"\n";
        output+=indent+"    }"+"\n";
        
        output+=indent+"}";
        
        return output;
    }
}
