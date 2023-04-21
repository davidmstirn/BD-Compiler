package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.lowlevel.Operand;
import com.bd.compiler.lowlevel.Operation;
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
    
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        int destReg = curr.getNewRegNum();
        this.setRegNum(destReg);
        
        Operation oper = new Operation(null, curr.getCurrBlock());
        switch(op){
            case LT_EQ_BINOP:
                oper.setType(Operation.OperationType.LTE);
                break;
            case LT_BINOP:
                oper.setType(Operation.OperationType.LT);
                break;
            case GT_BINOP:
                oper.setType(Operation.OperationType.GT);
                break;
            case GT_EQ_BINOP:
                oper.setType(Operation.OperationType.GTE);
                break;
            case EQ_BINOP:
                oper.setType(Operation.OperationType.EQUAL);
                break;
            case NE_BINOP:
                oper.setType(Operation.OperationType.NOT_EQUAL);
                break;
            case PLUS_BINOP:
                oper.setType(Operation.OperationType.ADD_I);
                break;
            case MINUS_BINOP:
                oper.setType(Operation.OperationType.SUB_I);
                break;
            case MUL_BINOP:
                oper.setType(Operation.OperationType.MUL_I);
                break;
            case DIV_BINOP:
                oper.setType(Operation.OperationType.DIV_I);
                break;
        }
        lhs.genLLCode(curr);
        oper.setSrcOperand(0, new Operand(Operand.OperandType.REGISTER, lhs.getRegNum()));
        rhs.genLLCode(curr);
        oper.setSrcOperand(1, new Operand(Operand.OperandType.REGISTER, rhs.getRegNum()));
        oper.setDestOperand(0, new Operand(Operand.OperandType.REGISTER, destReg));
        curr.getCurrBlock().appendOper(oper);
    }
}
