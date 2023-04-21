package com.bd.compiler.parser;

import com.bd.compiler.CMinusCompiler;
import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.lowlevel.Operand;
import com.bd.compiler.lowlevel.Operation;

/**
 * VariableExpression
 * File: VariableExpression.java
 * A C- Variable Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class VariableExpression extends Expression {
    private final String identifier;
    private final Expression arraySubscript;
    
    public VariableExpression(String id, Expression arraySubscript){
        this.identifier = id;
        this.arraySubscript = arraySubscript;
    }
    
    public String getIdentifier() { return identifier; }
    public Expression getArraySubscript() { return arraySubscript; }
    
    @Override
    public String printTree(String indent) {
        String output = indent + identifier;
        
        if(arraySubscript != null) {
            output+="[\n";
            output+=arraySubscript.printTree(indent+"    ")+"\n";
            output+=indent+"]";
        }
        
        return output;
    }
    
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        Integer regNum = curr.getTable().get(identifier);
        // Variable in local symbol table
        if(regNum != null){
            this.setRegNum(regNum);
        } else {
            String globalID = CMinusCompiler.globalHash.get(identifier);
            // Variable in global table
            if(globalID != null){
                regNum = curr.getNewRegNum();
                this.setRegNum(regNum);
                
                Operation o = new Operation(Operation.OperationType.LOAD_I, curr.getCurrBlock());
                o.setSrcOperand(0, new Operand(Operand.OperandType.STRING, globalID));
                o.setDestOperand(0, new Operand(Operand.OperandType.REGISTER, regNum));
                curr.getCurrBlock().appendOper(o);
            } else {
                throw new CompilerException("Identifier " + identifier + " not found");
            }
        }
    }
}
