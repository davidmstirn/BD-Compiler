package com.bd.compiler;

import java.io.IOException;

/**
 * Compiler
 * File: Compiler.java
 * A compiler interface for the C- language. Reads an ASCII file containing C-
 * and generates an assembly code version of the input program
 * 
 * @author Brandon Barker
 * @author David Stirn
 * @version 1.0 2/7/23
 * Copyright of the authors
 */
public interface Compiler {
  public void compile(String filePrefix) throws IOException;
}
