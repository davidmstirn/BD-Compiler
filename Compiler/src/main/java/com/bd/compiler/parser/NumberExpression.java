package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.BasicBlock;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.lowlevel.Operand;
import com.bd.compiler.lowlevel.Operation;

/**
 * NumberExpression
 * File: NumberExpression.java
 * A C- Number Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class NumberExpression extends Expression {
    private final Integer value;
    
    public NumberExpression(Integer value){
        this.value = value;
    }
    
    public Integer getValue() { return value; }
    
    @Override
    public String printTree(String indent) {
        String output = indent + value ;
        
        return output;
    }

    @Override
    public void genLLCode(Function curr) throws CompilerException {
        int regNum = curr.getNewRegNum();
        this.setRegNum(regNum);
        BasicBlock b = curr.getCurrBlock();
        
        Operation o = new Operation(Operation.OperationType.ASSIGN, b);
        Operand dst = new Operand(Operand.OperandType.REGISTER, regNum);
        Operand src = new Operand(Operand.OperandType.INTEGER, value);
        o.setDestOperand(0, dst);
        o.setSrcOperand(0, src);
        
        b.appendOper(o);
    }
}
