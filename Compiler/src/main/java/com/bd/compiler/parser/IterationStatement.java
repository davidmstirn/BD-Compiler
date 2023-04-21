package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.BasicBlock;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.lowlevel.Operand;
import com.bd.compiler.lowlevel.Operation;

/**
 * IterationStatement
 * File: IterationStatement.java
 * A C- Iteration Statement grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class IterationStatement extends Statement {
    private final Expression condition;
    private final Statement whilePart;
    
    public IterationStatement(Expression condition, Statement whilePart) {
        this.condition = condition;
        this.whilePart = whilePart;
    }
    
    public Expression getCondition() { return condition; }
    public Statement getWhilePart() { return whilePart; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"while(\n";
        
        output+=condition.printTree(indent+"    ")+"\n";
        output+=indent+")\n";
        output+=whilePart.printTree(indent+"    ");
        
        return output;
    }
    
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        // Create basic blocks to reference
        BasicBlock thenBlock = new BasicBlock(curr);
        BasicBlock postBlock = new BasicBlock(curr);
        
        // Generate condition code and branch
        condition.genLLCode(curr);
        Operation branch = new Operation(Operation.OperationType.BNE, curr.getCurrBlock());
        branch.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, postBlock));
        curr.getCurrBlock().appendOper(branch);
        
        // Generate then code and attach then and post blocks
        curr.appendToCurrentBlock(thenBlock);
        curr.setCurrBlock(thenBlock);
        whilePart.genLLCode(curr);
        
        // Generate second condition code with branch
        condition.genLLCode(curr);
        Operation secondBranch = new Operation(Operation.OperationType.BEQ, curr.getCurrBlock());
        secondBranch.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, thenBlock));
        curr.getCurrBlock().appendOper(secondBranch);        
        
        // Add post block
        curr.appendToCurrentBlock(postBlock);        
        curr.setCurrBlock(postBlock);
    }
}
