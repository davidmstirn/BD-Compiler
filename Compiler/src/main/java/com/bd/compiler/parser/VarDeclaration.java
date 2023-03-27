/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
 */
public class VarDeclaration extends Declaration {
    private final Integer arrayLength;
    
    public VarDeclaration(String id, Integer arrayLength){
        super(id);
        this.arrayLength = arrayLength;
    }
    
    public Integer getArrayLength() { return arrayLength; }
    
    @Override
    public void printTree(String indent) {
        String len = arrayLength != null ? "["+String.valueOf(arrayLength)+"]" : "";
        System.out.println(indent + "VAR-DECL: " + this.getID() + len);
    }
}
