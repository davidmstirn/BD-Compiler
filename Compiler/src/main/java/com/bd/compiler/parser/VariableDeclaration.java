package com.bd.compiler.parser;

import com.bd.compiler.CMinusCompiler;
import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.CodeItem;
import com.bd.compiler.lowlevel.Data;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.parser.CMinusParser.TypeSpecifier;

/**
 * VariableDeclaration
 * File: VariableDeclaration.java
 * A C- Variable Declaration grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class VariableDeclaration extends Declaration {
    private final Integer arrayLength;
    
    public VariableDeclaration(TypeSpecifier type, String id, Integer arrayLength){
        super(type, id);
        this.arrayLength = arrayLength;
    }
    
    public Integer getArrayLength() { return arrayLength; }
    
    @Override
    public String printTree(String indent) {
        String output = indent;
        
        if(this.getType() == TypeSpecifier.INT_TYPE) {
            output+="int " + getID();
        } else if (this.getType() == TypeSpecifier.VOID_TYPE) {
            output+="void " + getID();
        }
        
        output+=arrayLength != null ? "["+String.valueOf(arrayLength)+"];" : ";";
        return output;
    }
    
    @Override
    public CodeItem genLLCode() throws CompilerException{
        int type = -1;
        if(this.getType() == CMinusParser.TypeSpecifier.VOID_TYPE){
            type = Data.TYPE_VOID;
        } else if(this.getType() == CMinusParser.TypeSpecifier.INT_TYPE){
            type = Data.TYPE_INT;
        }
        
        String name = this.getID();
        
        CMinusCompiler.globalHash.put(name, name);

        // We are not required to handle arrays
        int size = 0;
                
        return new Data(type, name, false, size);
    }
    
    public CodeItem genLLCode(Function parentFunction) throws CompilerException{
        int type = -1;
        if(this.getType() == CMinusParser.TypeSpecifier.VOID_TYPE){
            type = Data.TYPE_VOID;
        } else if(this.getType() == CMinusParser.TypeSpecifier.INT_TYPE){
            type = Data.TYPE_INT;
        }
        
        String name = this.getID();
        
        CMinusCompiler.globalHash.put(name, name);

        int size = 0;
        if(arrayLength != null){
            size = arrayLength;
        }
                
        return new Data(type, name, arrayLength != null, size);
    }
}
