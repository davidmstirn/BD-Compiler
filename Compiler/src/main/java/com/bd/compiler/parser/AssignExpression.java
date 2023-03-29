package com.bd.compiler.parser;

/**
 * AssignExpression
 * File: AssignExpression.java
 * A C- Assignment Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
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
