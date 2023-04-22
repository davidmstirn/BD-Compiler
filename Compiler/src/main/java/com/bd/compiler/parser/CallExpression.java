package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.Attribute;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.lowlevel.Operand;
import com.bd.compiler.lowlevel.Operation;
import java.util.List;

/**
 * CallExpression
 * File: CallExpression.java
 * A C- Function call Expression grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class CallExpression extends Expression {
    private final String identifier;
    private final List<Expression> arguments;
    
    public CallExpression(String id, List<Expression> arguments){
        this.identifier = id;
        this.arguments = arguments;
    }
    
    public String getIdentifier() { return identifier; }
    public List<Expression> getArguments() { return arguments; }
    
    @Override
    public String printTree(String indent) {
        String output = indent + identifier + "(";
        
        if(arguments != null){
            output+="\n";
            for(Expression e : arguments) {
                output+=e.printTree(indent+"    ")+",\n";
            }
            output=output.substring(0, output.length()-2)+"\n"+indent;
        }
        
        output+=")";
        
        return output;
    }
    
    @Override
    public void genLLCode(Function curr) throws CompilerException {
        int regNum = curr.getNewRegNum();
        
        // Pass argumetns
        if(arguments != null){
            for(int i = 0; i < arguments.size(); ++i) {
                Expression currArg = arguments.get(i);
                currArg.genLLCode(curr);
                
                Operation oper = new Operation(Operation.OperationType.PASS, curr.getCurrBlock());
                oper.addAttribute(new Attribute("PARAM_NUM", String.valueOf(i)));
                oper.setSrcOperand(0, new Operand(Operand.OperandType.REGISTER, currArg.getRegNum()));
                curr.getCurrBlock().appendOper(oper);
            }
        }
        // Generate call
        Operation call = new Operation(Operation.OperationType.CALL, curr.getCurrBlock());
        call.setSrcOperand(0, new Operand(Operand.OperandType.STRING, identifier));
        call.addAttribute(new Attribute("numParams", String.valueOf(arguments.size())));
        curr.getCurrBlock().appendOper(call);
        
        // Mov retReg into regNum
        Operation returnAssign = new Operation(Operation.OperationType.ASSIGN, curr.getCurrBlock());
        returnAssign.setSrcOperand(0, new Operand(Operand.OperandType.MACRO,"RetReg"));
        returnAssign.setDestOperand(0, new Operand(Operand.OperandType.REGISTER, regNum));
        curr.getCurrBlock().appendOper(returnAssign);
        
        this.setRegNum(regNum);
        
    }
}
