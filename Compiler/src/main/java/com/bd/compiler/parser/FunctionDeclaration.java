package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.BasicBlock;
import com.bd.compiler.lowlevel.CodeItem;
import com.bd.compiler.lowlevel.Data;
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

        Function curr = new Function(type, name);
        ((Function) curr).createBlock0();
        //TODO genCode on cmpdstmt
        BasicBlock retBlock = ((Function) curr).getReturnBlock();
        ((Function) curr).appendBlock(retBlock);
        
        return curr;
    }
}
