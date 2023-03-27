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
    private final String type;
    private final CompoundStatement body;
    
    public FunDeclaration(String type, String id, CompoundStatement b) {
        super(id);
        this.type = type;
        body = b;
    }
    
    public String getType() { return type; }
    public CompoundStatement getBody() { return body; }
    
    @Override
    public String printTree(String indent) {
        String output = indent + "FUN-DECL: " + type + " " + this.getID() + " {\n";
        output+= body != null ? body.printTree(indent+"    ") : "";
        output+= indent+"}";
        return output;
    }
}
