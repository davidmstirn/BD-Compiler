package com.bd.compiler.parser;

import com.bd.compiler.CMinusCompiler;
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
        variableExpression.genLLCode(curr);
        rhs.genLLCode(curr);
        this.setRegNum(rhs.getRegNum());
        
        Integer regNum = curr.getTable().get(variableExpression.getIdentifier());
        // In local table, so create AssignExp
        if(regNum != null){
            Operation oper = new Operation(Operation.OperationType.ASSIGN, curr.getCurrBlock());    
            oper.setDestOperand(0, new Operand(Operand.OperandType.REGISTER, variableExpression.getRegNum()));    
            oper.setSrcOperand(0, new Operand(Operand.OperandType.REGISTER, rhs.getRegNum()));
            curr.getCurrBlock().appendOper(oper);
        } else {
            String globalID = CMinusCompiler.globalHash.get(variableExpression.getIdentifier());
            // Variable in global table
            if(globalID != null){
                Operation o = new Operation(Operation.OperationType.STORE_I, curr.getCurrBlock());
                o.setSrcOperand(0, new Operand(Operand.OperandType.REGISTER, rhs.getRegNum()));
                o.setSrcOperand(1, new Operand(Operand.OperandType.STRING, globalID));
                curr.getCurrBlock().appendOper(o);
            } else {
                throw new CompilerException("ASSIGN: Identifier " + variableExpression.getIdentifier() + " not found");
            }
        }
    }
}
