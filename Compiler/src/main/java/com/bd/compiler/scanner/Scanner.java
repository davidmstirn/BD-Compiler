/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bd.compiler.scanner;

/**
 *
 * @author dajms
 */
public interface Scanner {
    public Token getNextToken ();
    public Token viewNextToken ();
}
