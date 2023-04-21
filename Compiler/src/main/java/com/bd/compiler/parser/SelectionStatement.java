package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.BasicBlock;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.lowlevel.Operand;
import com.bd.compiler.lowlevel.Operation;

/**
 * SelectionStatement
 * File: SelectionStatement.java
 * A C- Selection Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class SelectionStatement extends Statement {
    private final Expression condition;
    private final Statement ifPart;
    private final Statement elsePart;
    
    public SelectionStatement(Expression condition, Statement ifPart, Statement elsePart) {
        this.condition = condition;
        this.ifPart = ifPart;
        this.elsePart = elsePart;
    }
    
    public Expression getCondition() { return condition; }
    public Statement getIfPart() { return ifPart; }
    public Statement getElsePart() { return elsePart; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"if(\n";
        
        output+=condition.printTree(indent+"    ")+"\n"+indent+")\n";
        output+=ifPart.printTree(indent+"   ");
        
        if (elsePart != null) {
            output+="\n";
            output+=indent+"else\n";
            output+=elsePart.printTree(indent+"   ");
        }
        
        return output;
    }
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        // Create basic blocks to reference
        BasicBlock thenBlock = new BasicBlock(curr);
        BasicBlock postBlock = new BasicBlock(curr);
        BasicBlock elseBlock = null;
        if (elsePart != null) {
            elseBlock = new BasicBlock(curr);
        }
        
        // Generate condition code and branch
        condition.genLLCode(curr);
        Operation branch = new Operation(Operation.OperationType.BNE, curr.getCurrBlock());
        if (elsePart != null) {
            branch.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, elseBlock));
        } else {
            branch.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, postBlock));
        }
        curr.getCurrBlock().appendOper(branch);
        
        // Generate then code and attach then and post blocks
        curr.appendToCurrentBlock(thenBlock);
        curr.setCurrBlock(thenBlock);
        ifPart.genLLCode(curr);
        
        curr.appendToCurrentBlock(postBlock);
        
        // If there is an else, generate else code and hook up blocks and jumps
        if (elsePart != null) {
            curr.setCurrBlock(elseBlock);
            elsePart.genLLCode(curr);
            Operation jump = new Operation(Operation.OperationType.JMP, curr.getCurrBlock());
            jump.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, postBlock));
            curr.appendUnconnectedBlock(elseBlock);
        }
        
        curr.setCurrBlock(postBlock);
    }
}
