package com.bd.compiler.parser;

import com.bd.compiler.parser.CMinusParser.BinaryOperation;

/**
 * BinaryExpression
 * File: BinaryExpression.java
 * A C- Binary Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class BinaryExpression extends Expression {
    private final Expression lhs;
    private final Expression rhs;
    private final BinaryOperation op;
    
    public BinaryExpression(Expression lhs, BinaryOperation op, Expression rhs){
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }
    
    public Expression getLhs() { return lhs; }
    public Expression getRhs() { return rhs; }
    public BinaryOperation getOp() { return op; }
        
    @Override
    public String printTree(String indent) {
        String output = "";
        
        output+=lhs.printTree(indent+"   ")+"\n";
        
        output+=indent;
        switch(op){                
            case LT_EQ_BINOP:
                output+="<=";
                break;
            case LT_BINOP:
                output+="<";
                break;
            case GT_BINOP:
                output+=">";
                break;
            case GT_EQ_BINOP:
                output+=">=";
                break;
            case EQ_BINOP:
                output+="==";
                break;
            case NE_BINOP:
                output+="!=";
                break;
            case PLUS_BINOP:
                output+="+";
                break;
            case MINUS_BINOP:
                output+="-";
                break;
            case MUL_BINOP:
                output+="*";
                break;
            case DIV_BINOP:
                output+="/";
                break;
        }
        output+="\n";
        
        output+=rhs.printTree(indent+"   ");
        
        return output;
    }
}
