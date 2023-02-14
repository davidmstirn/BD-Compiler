package com.bd.compiler.scanner;

%%
digit = [0-9]
number = {digit}+

%%

"="         {return new Token(Token.TokenType.ASSIGN_TOKEN);}
{number}    {return new Token(Token.TokenType.NUM_TOKEN);}
