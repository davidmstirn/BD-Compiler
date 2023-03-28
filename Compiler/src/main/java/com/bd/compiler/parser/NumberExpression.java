/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

/**
 *
 * @author dajms
 */
public class NumberExpression extends Expression {
    private final Integer value;
    
    public NumberExpression(Integer value){
        this.value = value;
    }
    
    public Integer getValue() { return value; }
    
    @Override
    public String printTree(String indent) {
        String output = indent+"NUM-EXP: " + value ;
        
        return output;
    }
}
