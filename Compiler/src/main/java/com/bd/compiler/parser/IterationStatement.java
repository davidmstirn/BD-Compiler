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
        // 1. Create basic blocks to reference
        BasicBlock thenBlock = new BasicBlock(curr);
        BasicBlock postBlock = new BasicBlock(curr);
        
        // 2. Generate condition code
        condition.genLLCode(curr);
        
        // 3. Create branch
        Operation branch = new Operation(Operation.OperationType.BEQ, curr.getCurrBlock());
        branch.setSrcOperand(0, new Operand(Operand.OperandType.REGISTER, condition.getRegNum()));
        branch.setSrcOperand(1, new Operand(Operand.OperandType.INTEGER, 0));
        branch.setSrcOperand(2, new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum()));
        curr.getCurrBlock().appendOper(branch);
        
        // 4. Append then block
        curr.appendToCurrentBlock(thenBlock);
        
        // 5. currBlock = thenBlock
        curr.setCurrBlock(thenBlock);
        
        // 6. Generate then code
        whilePart.genLLCode(curr);
        
        // 6.5 Generate second condition code with branch
        condition.genLLCode(curr);
        Operation secondBranch = new Operation(Operation.OperationType.BNE, curr.getCurrBlock());
        secondBranch.setSrcOperand(0, new Operand(Operand.OperandType.REGISTER, condition.getRegNum()));
        secondBranch.setSrcOperand(1, new Operand(Operand.OperandType.INTEGER, 0));
        secondBranch.setSrcOperand(2, new Operand(Operand.OperandType.BLOCK, thenBlock.getBlockNum()));
        curr.getCurrBlock().appendOper(secondBranch);
        
        // 7. Append post block
        curr.appendToCurrentBlock(postBlock);
        
        // 12. currBlock = postBlock
        curr.setCurrBlock(postBlock);
    }
}
