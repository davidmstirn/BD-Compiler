/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
 */
public class FunDeclaration extends Declaration {
    // TODO type
    // TODO compound statement
    
    public FunDeclaration(String id) {
        super(id);
    }
    
    @Override
    public String printTree(String indent) {
        return indent + "FUN-DECL: " + this.getID();
    }
}
