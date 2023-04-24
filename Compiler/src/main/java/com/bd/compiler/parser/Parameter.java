package com.bd.compiler.parser;

import com.bd.compiler.CompilerException;
import com.bd.compiler.lowlevel.CodeItem;
import com.bd.compiler.lowlevel.Data;
import com.bd.compiler.lowlevel.FuncParam;
import com.bd.compiler.lowlevel.Function;
import com.bd.compiler.parser.CMinusParser.TypeSpecifier;

/**
 * Parameter
 * File: Parameter.java
 * A C- Parameter grammar node
 *
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 Mar 11, 2023
 * Copyright of the authors
 */
public class Parameter {
    private final TypeSpecifier type;
    private final String identifier;
    private final boolean isArray;
    
    public Parameter(TypeSpecifier type, String identifier, boolean isArray) {
        this.type = type;
        this.identifier = identifier;
        this.isArray = isArray;
    }
    
    public TypeSpecifier getType() { return type; }
    public String getIdentifier() { return identifier; }
    public boolean isArray() { return isArray; }
    
    public String printTree(String indent) {
        String output = "";
        
        if(type == TypeSpecifier.INT_TYPE) {
            output+="int " + identifier;
        } else if (type == TypeSpecifier.VOID_TYPE) {
            output+="void";
        }
        
        if(isArray) {
            output+= "[]";
        }
                        
        return output;
    }
    
    public FuncParam genLLCode(Function curr) throws CompilerException{
        int paramType = -1;
        if(type == TypeSpecifier.VOID_TYPE){
            paramType = Data.TYPE_VOID;
        } else if(type == TypeSpecifier.INT_TYPE){
            paramType = Data.TYPE_INT;
        }

        String paramName = identifier;

        int regNum = curr.getNewRegNum();
        curr.getTable().put(paramName, regNum);

        return new FuncParam(paramType, paramName);
    }
}
