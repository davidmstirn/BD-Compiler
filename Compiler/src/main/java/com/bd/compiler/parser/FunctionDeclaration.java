/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bd.compiler.parser;

import com.bd.compiler.parser.CMinusParser.TypeSpecifier;

/**
 *
 * @author dajms
 */
public class FunctionDeclaration extends Declaration {
    private final TypeSpecifier type;
    private final CompoundStatement body;
    
    public FunctionDeclaration(TypeSpecifier type, String id, CompoundStatement b) {
        super(id);
        this.type = type;
        body = b;
    }
    
    public TypeSpecifier getType() { return type; }
    public CompoundStatement getBody() { return body; }
    
    @Override
    public String printTree(String indent) {
        String output = indent + "FUN-DECL: " + type.name() + " " + this.getID() + " {\n";
        output+= body != null ? body.printTree(indent+"    ") : "";
        output+= indent+"}";
        return output;
    }
}
