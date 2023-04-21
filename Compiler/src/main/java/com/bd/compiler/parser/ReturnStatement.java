package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.lowlevel.Operand;
import com.bd.compiler.lowlevel.Operation;

/**
 * ReturnStatement
 * File: ReturnStatement.java
 * A C- Return Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class ReturnStatement extends Statement {
    private final Expression statement;
    
    public ReturnStatement(Expression condition) {
        this.statement = condition;
    }
    
    public Expression getStatement() { return statement; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"return\n";
        
        if (statement != null) {
            output+=statement.printTree(indent+"    ")+"\n";
        }
        output+=indent+";";
        
        return output;
    }
    
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        // Create operation to move return value to retReg
        Operation returnAssign = new Operation(Operation.OperationType.ASSIGN, curr.getCurrBlock());
        returnAssign.setDestOperand(0, new Operand(Operand.OperandType.MACRO,"RetReg"));
        // TODO: Statement code gen? Statement Register?
        Operand retVal = new Operand(Operand.OperandType.REGISTER);
        returnAssign.setSrcOperand(0, retVal);
        curr.getCurrBlock().appendOper(returnAssign);        
    }
}
