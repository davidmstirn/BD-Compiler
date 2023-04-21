package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.lowlevel.Operand;
import com.bd.compiler.lowlevel.Operation;

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
        String output = "";
        
        output+=variableExpression.printTree(indent);
        output+=" =\n";
        output+=rhs.printTree(indent+"    ");
        
        return output;
    }
    
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        Operation oper = new Operation(Operation.OperationType.ASSIGN, curr.getCurrBlock());
        variableExpression.genLLCode(curr);
        oper.setDestOperand(0, new Operand(Operand.OperandType.REGISTER, variableExpression.getRegNum()));
        rhs.genLLCode(curr);
        oper.setSrcOperand(0, new Operand(Operand.OperandType.REGISTER, rhs.getRegNum()));
        curr.getCurrBlock().appendOper(oper);
    }
}
