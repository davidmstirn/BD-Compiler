/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
 */
public abstract class Declaration {
    private final String id;
    
    public Declaration(String id) {
        this.id = id;
    }
    
    public String getID() { return id; }
    
    public abstract String printTree(String indent);
}
