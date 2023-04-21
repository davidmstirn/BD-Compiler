package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.BasicBlock;
import com.bd.compiler.lowlevel.CodeItem;
import com.bd.compiler.lowlevel.Data;
import com.bd.compiler.lowlevel.FuncParam;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.parser.CMinusParser.TypeSpecifier;
import java.util.List;

/**
 * FunctionDeclaration
 * File: FunctionDeclaration.java
 * A C- Function Declaration grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class FunctionDeclaration extends Declaration {
    private final List<Parameter> parameters;
    private final CompoundStatement body;
    
    public FunctionDeclaration(TypeSpecifier type, String identifier, List<Parameter> parameters, CompoundStatement body) {
        super(type, identifier);
        this.parameters = parameters;
        this.body = body;
    }
    
    public List<Parameter> getParameters() { return parameters; }
    public CompoundStatement getBody() { return body; }
    
    @Override
    public String printTree(String indent) {
        String output = indent;
        
        if(this.getType() == TypeSpecifier.INT_TYPE) {
            output+= "int " + this.getID() + "(";
        } else if(this.getType() == TypeSpecifier.VOID_TYPE) {
            output+= "void " + this.getID() + "(";
        }
        
        
        if (parameters != null) {
            for(Parameter p : parameters) {
                output+=p.printTree("")+", ";
            }
            output = output.substring(0, output.length()-2);
        }
        
        output+=")\n";
        
        if (body != null) {
            output+= body.printTree(indent);
        }
                
        return output;
    }
    
    @Override
    public CodeItem genLLCode() throws CompilerException{
        int type = -1;
        if(this.getType() == CMinusParser.TypeSpecifier.VOID_TYPE){
            type = Data.TYPE_VOID;
        } else if (this.getType() == CMinusParser.TypeSpecifier.INT_TYPE){
            type = Data.TYPE_INT;
        }
        
        String name = this.getID();

        // Setup Current function
        Function curr = new Function(type, name);
        curr.createBlock0();
        BasicBlock block1 = new BasicBlock(curr);
        curr.appendBlock(block1);
        curr.setCurrBlock(block1);
        
        // Create Parameters
        FuncParam firstFuncParam = null;
        FuncParam previousFuncParam = null;
        for(int i = 0; i < parameters.size(); i++){
            Parameter p = parameters.get(i);
            
            int paramType = -1;
            if(p.getType() == TypeSpecifier.VOID_TYPE){
                paramType = Data.TYPE_VOID;
            } else if(p.getType() == TypeSpecifier.INT_TYPE){
                paramType = Data.TYPE_INT;
            }
            
            String paramName = p.getIdentifier();
            
            int regNum = curr.getNewRegNum();
            curr.getTable().put(paramName, regNum);
            
            FuncParam currParam = new FuncParam(paramType, paramName);
            
            if(i == 0){
                firstFuncParam = currParam;
            } else {
                previousFuncParam.setNextParam(currParam);
            }
            previousFuncParam = currParam;
        }
        curr.setFirstParam(firstFuncParam);
        
        body.genLLCode(curr);        
        
        // Finalize Function
        curr.appendBlock(curr.getReturnBlock());
        BasicBlock unconnected = curr.getFirstUnconnectedBlock();
        
        if(unconnected != null){
            curr.appendBlock(unconnected);
            curr.setFirstUnconnectedBlock(null);
        }
        
        return curr;
    }
}
