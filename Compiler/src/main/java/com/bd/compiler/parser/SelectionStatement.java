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
        // 1. Create basic blocks to reference
        BasicBlock thenBlock = new BasicBlock(curr);
        BasicBlock postBlock = new BasicBlock(curr);
        BasicBlock elseBlock = null;
        if (elsePart != null) {
            elseBlock = new BasicBlock(curr);
        }
        
        // 2. Generate condition code
        condition.genLLCode(curr);
        
        // 3. Create BEQ
        Operation branchOper = new Operation(Operation.OperationType.BEQ, curr.getCurrBlock());
        branchOper.setSrcOperand(0, new Operand(Operand.OperandType.REGISTER, condition.getRegNum()));
        branchOper.setSrcOperand(1, new Operand(Operand.OperandType.INTEGER, 0));
        if (elsePart != null) {
            branchOper.setSrcOperand(2, new Operand(Operand.OperandType.BLOCK, elseBlock.getBlockNum()));
        } else {
            branchOper.setSrcOperand(2, new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum()));
        }
        curr.getCurrBlock().appendOper(branchOper);
        
        // 4. Append then block
        curr.appendToCurrentBlock(thenBlock);
        
        // 5. currBlock = thenBlock
        curr.setCurrBlock(thenBlock);
        
        // 6. Generate then code
        ifPart.genLLCode(curr);
        
        // 7. Append post block
        curr.appendToCurrentBlock(postBlock);
        
        // If there is an else, generate else code and hook up blocks and jumps
        if (elsePart != null) {
            // 8. currBlock = elseBlock
            curr.setCurrBlock(elseBlock);
            // 9. Generate else code
            elsePart.genLLCode(curr);
            // 10. Append jmp to post block
            Operation elseJump = new Operation(Operation.OperationType.JMP, curr.getCurrBlock());
            elseJump.setSrcOperand(0, new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum()));
            curr.getCurrBlock().appendOper(elseJump);
            // 11. Add else to unconnected chain
            curr.appendUnconnectedBlock(elseBlock);
        }
        
        // 12. currBlock = postBlock
        curr.setCurrBlock(postBlock);
    }
}
