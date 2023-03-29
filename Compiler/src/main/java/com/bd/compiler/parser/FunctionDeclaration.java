package com.bd.compiler.parser;

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
    private final TypeSpecifier type;
    private final List<Parameter> parameters;
    private final CompoundStatement body;
    
    public FunctionDeclaration(TypeSpecifier type, String identifier, List<Parameter> parameters, CompoundStatement body) {
        super(identifier);
        this.type = type;
        this.parameters = parameters;
        this.body = body;
    }
    
    public TypeSpecifier getType() { return type; }
    public List<Parameter> getParameters() { return parameters; }
    public CompoundStatement getBody() { return body; }
    
    @Override
    public String printTree(String indent) {
        String output = indent + "FUN-DECL: " + type.name() + " " + this.getID() + " {\n";
        
        if (parameters != null) {
            output+=indent+"    PARAMS {\n";
            for(Parameter p : parameters) {
                output+=p.printTree(indent+"        ")+"\n";
            }
            output+=indent+"    }\n";
        }
        
        if (body != null) {
            output+=indent+"    BODY {\n";
            output+= body.printTree(indent+"        ")+"\n";
            output+=indent+"    }\n";
        }
        
        output+= indent+"}";
        
        return output;
    }
}
