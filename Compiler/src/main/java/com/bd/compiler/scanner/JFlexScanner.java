// DO NOT EDIT
// Generated by JFlex 1.9.0 http://jflex.de/
// source: D:/Dev/NetBeansProjects/BD-Compiler/Compiler/src/main/java/com/bd/compiler/scanner/scanner.flex

package com.bd.compiler.scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("fallthrough")
public class JFlexScanner implements Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\u10ff\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\26\0\1\1\1\2\6\0\1\3\1\4"+
    "\1\5\1\6\1\7\1\10\1\0\1\11\12\12\1\0"+
    "\1\13\1\14\1\15\1\16\2\0\32\17\1\20\1\0"+
    "\1\21\3\0\3\17\1\22\1\23\1\24\1\17\1\25"+
    "\1\26\2\17\1\27\1\17\1\30\1\31\2\17\1\32"+
    "\1\33\1\34\1\35\1\36\1\37\3\17\1\40\1\0"+
    "\1\41\u0182\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[512];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\0\1\2\1\3\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\5\16\1\21\1\22\1\23\1\0\1\24"+
    "\1\25\1\26\1\16\1\27\4\16\2\0\1\16\1\30"+
    "\3\16\1\0\1\31\1\16\1\32\2\16\1\33\1\34";

  private static int [] zzUnpackAction() {
    int [] result = new int[51];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\42\0\104\0\42\0\42\0\42\0\42\0\146"+
    "\0\42\0\210\0\252\0\42\0\314\0\356\0\u0110\0\146"+
    "\0\42\0\42\0\u0132\0\u0154\0\u0176\0\u0198\0\u01ba\0\42"+
    "\0\42\0\42\0\u01dc\0\42\0\42\0\42\0\u01fe\0\146"+
    "\0\u0220\0\u0242\0\u0264\0\u0286\0\u02a8\0\u02ca\0\u02ec\0\146"+
    "\0\u030e\0\u0330\0\u0352\0\u0374\0\146\0\u0396\0\146\0\u03b8"+
    "\0\u03da\0\146\0\146";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[51];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\1\2\1\3\1\4\1\5\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\20\1\23\2\20\1\24\3\20\1\25"+
    "\3\20\1\26\1\27\1\30\1\31\57\0\1\32\33\0"+
    "\1\20\7\0\1\20\2\0\16\20\7\0\1\33\22\0"+
    "\1\2\23\0\1\13\44\0\1\34\41\0\1\35\41\0"+
    "\1\36\33\0\1\20\7\0\1\20\2\0\5\20\1\37"+
    "\10\20\11\0\1\20\7\0\1\20\2\0\2\20\1\40"+
    "\3\20\1\41\7\20\11\0\1\20\7\0\1\20\2\0"+
    "\1\20\1\42\14\20\11\0\1\20\7\0\1\20\2\0"+
    "\7\20\1\43\6\20\11\0\1\20\7\0\1\20\2\0"+
    "\3\20\1\44\12\20\2\0\5\45\1\46\34\45\7\0"+
    "\1\20\7\0\1\20\2\0\11\20\1\47\4\20\11\0"+
    "\1\20\7\0\1\20\2\0\12\20\1\50\3\20\11\0"+
    "\1\20\7\0\1\20\2\0\12\20\1\51\3\20\11\0"+
    "\1\20\7\0\1\20\2\0\4\20\1\52\11\20\11\0"+
    "\1\20\7\0\1\20\2\0\4\20\1\53\11\20\2\0"+
    "\5\45\1\54\34\45\5\0\1\46\3\0\1\2\37\0"+
    "\1\20\7\0\1\20\2\0\1\20\1\55\14\20\11\0"+
    "\1\20\7\0\1\20\2\0\13\20\1\56\2\20\11\0"+
    "\1\20\7\0\1\20\2\0\1\57\15\20\11\0\1\20"+
    "\7\0\1\20\2\0\5\20\1\60\10\20\2\0\5\45"+
    "\1\54\3\45\1\2\30\45\7\0\1\20\7\0\1\20"+
    "\2\0\10\20\1\61\5\20\11\0\1\20\7\0\1\20"+
    "\2\0\1\20\1\62\14\20\11\0\1\20\7\0\1\20"+
    "\2\0\6\20\1\63\7\20\2\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[1020];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\0\4\11\1\1\1\11\2\1\1\11"+
    "\4\1\2\11\5\1\3\11\1\0\3\11\6\1\2\0"+
    "\5\1\1\0\7\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[51];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen())];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */
    private BufferedReader inFile;
    private Token nextToken;
    
    /**
     * Creates a scanner that reads an ASCII file and generates tokens
     * 
     * @param file the file from which to read ASCII from
     * @throws ScannerException if the scan failed
     */
    public JFlexScanner(BufferedReader file) throws ScannerException {
        inFile = file;
        try {
            nextToken = yylex();
        } catch (IOException e) {
            throw new ScannerException();
        }    }
    
    /**
     * Get the next token. Munches the returned token
     * 
     * @return the retrieved token
     * @throws ScannerException if the scan failed
     */
    @Override
    public Token getNextToken() throws ScannerException {
        Token returnToken = nextToken;
        if (nextToken.getType() != Token.TokenType.EOF_TOKEN)
            try {
                nextToken = yylex();
            } catch (IOException e) {
                throw new ScannerException();
            }
        return returnToken;
    }
    
    /**
     * View the next token without munching it
     * 
     * @return the viewed token
     */
    @Override
    public Token viewNextToken() {
        return nextToken;
    }

    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("ERROR: Scanner requires 1 argument, the path to the C- Code");
            return;
        }

        String filename = args[0];

        List<Token> tokens = new ArrayList<>();
        try (FileReader fr = new FileReader(filename)){
            BufferedReader br = new BufferedReader(fr);

            Scanner scan = new CMinusScanner(br);

            Token t = scan.getNextToken();
            tokens.add(t);
            // Read all of the tokens
            while(t.getType() != Token.TokenType.EOF_TOKEN){
                t = scan.getNextToken();
                tokens.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error opening input file");
            return;
        } catch (ScannerException e) {
            System.out.println("Scan error");
        }

        // Output the tokens to a file
        String outputFilename = filename.substring(0, filename.indexOf('.'));
        try(FileWriter fw = new FileWriter(outputFilename+"2.json")){
            String json = Token.tokenListToJson(tokens);
            fw.append(json);
        }catch(IOException e){
            System.out.println("Error opening output file");
        }
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public JFlexScanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate && zzCanGrow()) {
      /* if not, and it can grow: blow it up */
      char newBuffer[] = new char[Math.min(zzBuffer.length * 2, zzMaxBufferLen())];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      if (requested == 0) {
        throw new java.io.EOFException("Scan buffer limit reached ["+zzBuffer.length+"]");
      }
      else {
        throw new java.io.IOException(
            "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
      }
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    int initBufferSize = Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen());
    if (zzBuffer.length > initBufferSize) {
      zzBuffer = new char[initBufferSize];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public Token yylex() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { 
            }
          // fall through
          case 29: break;
          case 2:
            { return new Token(Token.TokenType.LPAR_TOKEN);
            }
          // fall through
          case 30: break;
          case 3:
            { return new Token(Token.TokenType.RPAR_TOKEN);
            }
          // fall through
          case 31: break;
          case 4:
            { return new Token(Token.TokenType.MULT_TOKEN);
            }
          // fall through
          case 32: break;
          case 5:
            { return new Token(Token.TokenType.PLUS_TOKEN);
            }
          // fall through
          case 33: break;
          case 6:
            { return new Token(Token.TokenType.COMMA_TOKEN);
            }
          // fall through
          case 34: break;
          case 7:
            { return new Token(Token.TokenType.MINUS_TOKEN);
            }
          // fall through
          case 35: break;
          case 8:
            { return new Token(Token.TokenType.DIV_TOKEN);
            }
          // fall through
          case 36: break;
          case 9:
            { return new Token(Token.TokenType.NUM_TOKEN, yytext());
            }
          // fall through
          case 37: break;
          case 10:
            { return new Token(Token.TokenType.SEMI_TOKEN);
            }
          // fall through
          case 38: break;
          case 11:
            { return new Token(Token.TokenType.LT_TOKEN);
            }
          // fall through
          case 39: break;
          case 12:
            { return new Token(Token.TokenType.ASSIGN_TOKEN);
            }
          // fall through
          case 40: break;
          case 13:
            { return new Token(Token.TokenType.GT_TOKEN);
            }
          // fall through
          case 41: break;
          case 14:
            { return new Token(Token.TokenType.ID_TOKEN, yytext());
            }
          // fall through
          case 42: break;
          case 15:
            { return new Token(Token.TokenType.LSQ_TOKEN);
            }
          // fall through
          case 43: break;
          case 16:
            { return new Token(Token.TokenType.RSQ_TOKEN);
            }
          // fall through
          case 44: break;
          case 17:
            { return new Token(Token.TokenType.LCURL_TOKEN);
            }
          // fall through
          case 45: break;
          case 18:
            { return new Token(Token.TokenType.RCURL_TOKEN);
            }
          // fall through
          case 46: break;
          case 19:
            { return new Token(Token.TokenType.NOTEQ_TOKEN);
            }
          // fall through
          case 47: break;
          case 20:
            { return new Token(Token.TokenType.LTE_TOKEN);
            }
          // fall through
          case 48: break;
          case 21:
            { return new Token(Token.TokenType.EQ_TOKEN);
            }
          // fall through
          case 49: break;
          case 22:
            { return new Token(Token.TokenType.GTE_TOKEN);
            }
          // fall through
          case 50: break;
          case 23:
            { return new Token(Token.TokenType.IF_TOKEN);
            }
          // fall through
          case 51: break;
          case 24:
            { return new Token(Token.TokenType.INT_TOKEN);
            }
          // fall through
          case 52: break;
          case 25:
            { return new Token(Token.TokenType.ELSE_TOKEN);
            }
          // fall through
          case 53: break;
          case 26:
            { return new Token(Token.TokenType.VOID_TOKEN);
            }
          // fall through
          case 54: break;
          case 27:
            { return new Token(Token.TokenType.WHILE_TOKEN);
            }
          // fall through
          case 55: break;
          case 28:
            { return new Token(Token.TokenType.RETURN_TOKEN);
            }
          // fall through
          case 56: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
