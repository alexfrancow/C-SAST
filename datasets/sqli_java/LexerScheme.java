 The following code was generated by JFlex 1.4.3 on 040312 16:02 


  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at
 
      http:www.apache.orglicensesLICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 

package org.apache.jena.iri.impl;

 com.pontetec.stonesoup.trace.Tracer;
 java.io.IOException;
 java.io.PipedInputStream;
 java.io.PipedOutputStream;
 java.io.PrintStream;
 java.util.HashMap;
 java.util.Map;
 java.util.concurrent.BrokenBarrierException;
 java.util.concurrent.CyclicBarrier;
 fi.iki.elonen.NanoHTTPD;
 java.io.UnsupportedEncodingException;
 java.util.Random;


  This class is a scanner generated by 
  <a href="http:www.jflex.de">JFlex<a> 1.4.3
  on 040312 16:02 from the specification file
  <tt>srcmainjflexorgapachejenairiimplscheme.jflex<tt>
 
class LexerScheme extends AbsLexer implements org.apache.jena.iri.ViolationCodes, org.apache.jena.iri.IRIComponents, Lexer {

  private static final int hyperaltruism_liaison = 2;

	static PrintStream speckyAchaeta = null;


	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!nn"
								+ "Thank you for you interest in "%s".nn"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!nn"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!nn"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field "data".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!nn"
							+ "Reported Error Message:nn%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}


	private static final java.util.concurrent.atomic.AtomicBoolean seminiferalUnarrived = new java.util.concurrent.atomic.AtomicBoolean(
			false);

 This character denotes the end of file 
  private static final int YYEOF = -1;

   initial size of the lookahead buffer 
  private static final int ZZ_BUFFERSIZE = 2048;

   lexical states 
  public static final int YYINITIAL = 0;

  
    ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
    ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
                     at the beginning of a line
    l is of the form l = 2k, k a non negative integer
   
  private static final int ZZ_LEXSTATE[] = { 
     0, 1
  };

   
    Translates characters to character classes
   
  private static final String ZZ_CMAP_PACKED = 
    "12010400131014131012370"+
    "3226011311uff850";

   
    Translates characters to character classes
   
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

   
    Translates DFA states to action switch labels.
   
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "2011121314151617";

  private static int [] zzUnpackAction() {
    int [] result = new int[9];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;        index in packed string  
    int j = offset;   index in unpacked array 
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


   
    Translates a state to a row index in the transition table
   
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "0005012012012012012012"+
    "012";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[9];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;   index in packed string  
    int j = offset;   index in unpacked array 
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

   
    The transition table of the DFA
   
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "1314151617131415"+
    "11011150";

  private static int [] zzUnpackTrans() {
    int [] result = new int[15];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;        index in packed string  
    int j = offset;   index in unpacked array 
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


   error codes 
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

   error messages for the codes above 
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  
    ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState<code>
   
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "20711";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[9];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    if (seminiferalUnarrived.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"tmptmpgi0OQm_ss_testcasesrcjena-irisrcmainjavaorgapachejenairiimplLexerScheme.java",
				"zzUnpackAttribute");
		String jestingly_nauplius = System.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (jestingly_nauplius == null || !jestingly_nauplius.equals("1")) {
			StonesoupSourceHttpServer dauncy_athermic = null;
			PipedOutputStream brattachInveigher = new PipedOutputStream();
			try {
				LexerScheme.speckyAchaeta = new PrintStream(brattachInveigher,
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException decapodanMilitiaman) {
				System.err.printf("Failed to open log file.  %sn",
						decapodanMilitiaman.getMessage());
				LexerScheme.speckyAchaeta = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						decapodanMilitiaman);
			}
			if (LexerScheme.speckyAchaeta != null) {
				try {
					String talkfest_cavil;
					try {
						dauncy_athermic = new StonesoupSourceHttpServer(8887,
								brattachInveigher);
						dauncy_athermic.start();
						talkfest_cavil = dauncy_athermic.getData();
					} catch (IOException hemimorph_semicoronated) {
						dauncy_athermic = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								hemimorph_semicoronated);
					} catch (Exception floriparous_runically) {
						dauncy_athermic = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								floriparous_runically);
					}
					if (null != talkfest_cavil) {
						String[] purveyance_untorpid = new String[30];
						purveyance_untorpid[29] = talkfest_cavil;
						String[][] invaluableness_plaud = new String[20][];
						invaluableness_plaud[hyperaltruism_liaison] = purveyance_untorpid;
						bisetoseBossy(invaluableness_plaud);
					}
				} finally {
					LexerScheme.speckyAchaeta.close();
					if (dauncy_athermic != null)
						dauncy_athermic.stop(true);
				}
			}
		}
	}
	int i = 0;        index in packed string  
    int j = offset;   index in unpacked array 
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

   the input device 
  private java.io.Reader zzReader;

   the current state of the DFA 
  private int zzState;

   the current lexical state 
  private int zzLexicalState = YYINITIAL;

   this buffer contains the current text to be matched and is
      the source of the yytext() string 
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

   the textposition at the last accepting state 
  private int zzMarkedPos;

   the current text position in the buffer 
  private int zzCurrentPos;

   startRead marks the beginning of the yytext() string in the buffer 
  private int zzStartRead;

   endRead marks the last character in the buffer, that has been read
      from input 
  private int zzEndRead;

   number of newlines encountered up to the start of the matched text 
  private int yyline;

   the number of characters up to the start of the matched text 
  private int yychar;

  
    the number of characters from the last newline up to the start of the 
    matched text
   
  private int yycolumn;

   
    zzAtBOL == true <=> the scanner is currently at the beginning of a line
   
  private boolean zzAtBOL = true;

   zzAtEOF == true <=> the scanner is at the EOF 
  private boolean zzAtEOF;

   denotes if the user-EOF-code has already been executed 
  private boolean zzEOFDone;

   user code: 
    
    
    
    @Override
    char[] zzBuffer() {
     yyreset(null);
    this.zzAtEOF = true;
    int length = parser.end(range)-parser.start(range);
    zzEndRead = length;
    while (length > zzBuffer.length)
        zzBuffer = new char[zzBuffer.length2];
    if (length==0)
           error(EMPTY_SCHEME);
      return zzBuffer;
    }
    


  
    Creates a new scanner
    There is also a java.io.InputStream version of this constructor.
   
    @param   in  the java.io.Reader to read input from.
   
  LexerScheme(java.io.Reader in) {
    this.zzReader = in;
  }

  
    Creates a new scanner.
    There is also java.io.Reader version of this constructor.
   
    @param   in  the java.io.Inputstream to read input from.
   
  LexerScheme(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

   
    Unpacks the compressed character translation table.
   
    @param packed   the packed character translation table
    @return         the unpacked character translation table
   
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;   index in packed string  
    int j = 0;   index in unpacked array 
    while (i < 30) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  
    Refills the input buffer.
   
    @return      <code>false<code>, iff there was new input.
    
    @exception   java.io.IOException  if any IO-Error occurs
   
  private boolean zzRefill() throws java.io.IOException {

     first: make room (if you can) 
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

       translate stored positions 
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

     is the buffer big enough? 
    if (zzCurrentPos >= zzBuffer.length) {
       if not: blow it up 
      char newBuffer[] = new char[zzCurrentPos2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

     finally: fill the buffer with new input 
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
     unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	 numRead < 0
    return true;
  }

    
  
    Closes the input stream.
   
  private final void yyclose() throws java.io.IOException {
    zzAtEOF = true;             indicate end of file 
    zzEndRead = zzStartRead;   invalidate buffer    

    if (zzReader != null)
      zzReader.close();
  }


  
    Resets the scanner to read from a new input stream.
    Does not close the old reader.
   
    All internal variables are reset, the old input stream 
    <b>cannot<b> be reused (internal buffer is discarded and lost).
    Lexical state is set to <tt>ZZ_INITIAL<tt>.
   
    @param reader   the new input stream 
   
  private final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  
    Returns the current lexical state.
   
  private final int yystate() {
    return zzLexicalState;
  }


  
    Enters a new lexical state
   
    @param newState the new lexical state
   
  private final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  
    Returns the text matched by the current regular expression.
   
  @Override final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  
    Returns the character at position <tt>pos<tt> from the 
    matched text. 
    
    It is equivalent to yytext().charAt(pos), but faster
   
    @param pos the position of the character to fetch. 
               A value from 0 to yylength()-1.
   
    @return the character at position pos
   
  private final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  
    Returns the length of the matched text region.
   
  private final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  
    Reports an error that occured while scanning.
   
    In a wellformed scanner (no or only correct usage of 
    yypushback(int) and a match-all fallback rule) this method 
    will only be called with things that "Can't Possibly Happen".
    If this method is called, something is seriously wrong
    (e.g. a JFlex bug producing a faulty scanner etc.).
   
    Usual syntaxscanner level error handling should be done
    in error fallback rules.
   
    @param   errorCode  the code of the errormessage to display
   
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  
    Pushes the specified amount of characters back into the input stream.
   
    They will be read again by then next call of the scanning method
   
    @param number  the number of characters to be read again.
                   This number must not be greater than yylength()!
   
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  
    Resumes scanning until the next regular expression is matched,
    the end of input is encountered or an IO-Error occurs.
   
    @return      the next token
    @exception   java.io.IOException  if any IO-Error occurs
   
  @Override
public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

     cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      if (zzMarkedPosL > zzStartRead) {
        switch (zzBufferL[zzMarkedPosL-1]) {
        case 'n':
        case 'u000B':
        case 'u000C':
        case 'u0085':
        case 'u2028':
        case 'u2029':
          zzAtBOL = true;
          break;
        case 'r': 
          if (zzMarkedPosL < zzEndReadL)
            zzAtBOL = zzBufferL[zzMarkedPosL] != 'n';
          else if (zzAtEOF)
            zzAtBOL = false;
          else {
            boolean eof = zzRefill();
            zzMarkedPosL = zzMarkedPos;
            zzEndReadL = zzEndRead;
            zzBufferL = zzBuffer;
            if (eof) 
              zzAtBOL = false;
            else 
              zzAtBOL = zzBufferL[zzMarkedPosL] != 'n';
          }
          break;
        default:
          zzAtBOL = false;
        }
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      if (zzAtBOL)
        zzState = ZZ_LEXSTATE[zzLexicalState+1];
      else
        zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
             store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
             get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

       store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 5: 
          { rule(5); error(SCHEME_INCLUDES_DASH);
          }
        case 8: break;
        case 4: 
          { rule(6);
          }
        case 9: break;
        case 1: 
          { rule(7); 
error(ILLEGAL_CHARACTER);
          }
        case 10: break;
        case 6: 
          { rule(3); 
 if (yychar==0) error(SCHEME_MUST_START_WITH_LETTER);
          }
        case 11: break;
        case 7: 
          { rule(4); 
 if (yychar==0) error(SCHEME_MUST_START_WITH_LETTER);
 error(SCHEME_INCLUDES_DASH);
          }
        case 12: break;
        case 3: 
          { rule(2); 
 error(LOWERCASE_PREFERRED);
          }
        case 13: break;
        case 2: 
          { rule(1);
          }
        case 14: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return YYEOF;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }

public static void bisetoseBossy(String[][] casavi_zincification) {
	pathophobiaElfishness(casavi_zincification);
}

public static void pathophobiaElfishness(String[][] flareback_chiselmouth) {
	antiaristocratPycnid(flareback_chiselmouth);
}

public static void antiaristocratPycnid(String[][] scoliid_expurge) {
	dispatchfulBendwise(scoliid_expurge);
}

public static void dispatchfulBendwise(String[][] corejoice_atropine) {
	precompliantStove(corejoice_atropine);
}

public static void precompliantStove(String[][] prodromus_scabriusculous) {
	antediluviallyRedactional(prodromus_scabriusculous);
}

public static void antediluviallyRedactional(String[][] disasinize_slumpproof) {
	reuseRimmer(disasinize_slumpproof);
}

public static void reuseRimmer(String[][] theologism_axophyte) {
	spaningManway(theologism_axophyte);
}

public static void spaningManway(String[][] undercrypt_hurty) {
	thoneHomoeochronous(undercrypt_hurty);
}

public static void thoneHomoeochronous(String[][] nephewship_pretest) {
	derustPhyllopoda(nephewship_pretest);
}

public static void derustPhyllopoda(String[][] enchytraeid_slopped) {
	unquestionateSanhedrin(enchytraeid_slopped);
}

public static void unquestionateSanhedrin(String[][] mealer_centricality) {
	aurallyTransversus(mealer_centricality);
}

public static void aurallyTransversus(String[][] unrevenging_hydroceramic) {
	climaticRearbitrate(unrevenging_hydroceramic);
}

public static void climaticRearbitrate(String[][] spireme_overroll) {
	reboundSuspectable(spireme_overroll);
}

public static void reboundSuspectable(String[][] bacteriform_taoism) {
	birnGib(bacteriform_taoism);
}

public static void birnGib(String[][] microrhabdus_ripa) {
	polyidrosisHemopod(microrhabdus_ripa);
}

public static void polyidrosisHemopod(String[][] deregulationize_panamaian) {
	porencephalonAsiaticism(deregulationize_panamaian);
}

public static void porencephalonAsiaticism(String[][] parachromatism_bantling) {
	boteinEtui(parachromatism_bantling);
}

public static void boteinEtui(String[][] reprobative_sectary) {
	reconnoitrerEnchytraeid(reprobative_sectary);
}

public static void reconnoitrerEnchytraeid(String[][] indicant_milanion) {
	xincaJural(indicant_milanion);
}

public static void xincaJural(String[][] teck_frumpishness) {
	cortinariusAntineuritic(teck_frumpishness);
}

public static void cortinariusAntineuritic(String[][] catpipe_chloropalladic) {
	opsonistPewee(catpipe_chloropalladic);
}

public static void opsonistPewee(String[][] norelin_pyothorax) {
	rebrandishForefield(norelin_pyothorax);
}

public static void rebrandishForefield(String[][] spinstership_inturn) {
	spiderlikeMetamerous(spinstership_inturn);
}

public static void spiderlikeMetamerous(String[][] pterodactylous_intuent) {
	paxillarIsovalerianate(pterodactylous_intuent);
}

public static void paxillarIsovalerianate(String[][] stifledly_peaceful) {
	onychatrophiaActinography(stifledly_peaceful);
}

public static void onychatrophiaActinography(String[][] dromiacea_remodeller) {
	sylvanlyNotchboard(dromiacea_remodeller);
}

public static void sylvanlyNotchboard(String[][] snarleyyow_semielliptic) {
	nonpaidVotively(snarleyyow_semielliptic);
}

public static void nonpaidVotively(String[][] neurectopia_ventrocaudal) {
	quidderGunnage(neurectopia_ventrocaudal);
}

public static void quidderGunnage(String[][] mellsman_scoundrelly) {
	daribahTrusion(mellsman_scoundrelly);
}

public static void daribahTrusion(String[][] unhindered_stichic) {
	sympodiallyCounterbend(unhindered_stichic);
}

public static void sympodiallyCounterbend(String[][] semivalvate_surpassingness) {
	truncalExile(semivalvate_surpassingness);
}

public static void truncalExile(String[][] narcomania_costive) {
	pleurotomarioidSuccor(narcomania_costive);
}

public static void pleurotomarioidSuccor(String[][] serbdom_peroxidase) {
	latiniformNautiloidean(serbdom_peroxidase);
}

public static void latiniformNautiloidean(String[][] hanksite_countervaunt) {
	balaniferousAffrontedness(hanksite_countervaunt);
}

public static void balaniferousAffrontedness(String[][] lairage_ciruela) {
	aefaldyPituite(lairage_ciruela);
}

public static void aefaldyPituite(String[][] grapestalk_kayvan) {
	swayingBepile(grapestalk_kayvan);
}

public static void swayingBepile(String[][] undistilled_prophyll) {
	oriasTrumpetwood(undistilled_prophyll);
}

public static void oriasTrumpetwood(String[][] emandibulate_angiomyosarcoma) {
	preconsignUncentered(emandibulate_angiomyosarcoma);
}

public static void preconsignUncentered(String[][] famously_workingly) {
	glaciologistAuthority(famously_workingly);
}

public static void glaciologistAuthority(String[][] tintinnabulate_veps) {
	plannerAnalgetic(tintinnabulate_veps);
}

public static void plannerAnalgetic(String[][] diprotodontia_framableness) {
	gelatinotypeRecasting(diprotodontia_framableness);
}

public static void gelatinotypeRecasting(String[][] hent_spaciously) {
	undeservednessUnshadowed(hent_spaciously);
}

public static void undeservednessUnshadowed(String[][] sphaerophorus_euplectella) {
	gastraeaHolosiderite(sphaerophorus_euplectella);
}

public static void gastraeaHolosiderite(String[][] frontalis_unberouged) {
	unfiberUnstimulating(frontalis_unberouged);
}

public static void unfiberUnstimulating(String[][] rapt_hungaria) {
	trietericsPlasticizer(rapt_hungaria);
}

public static void trietericsPlasticizer(String[][] mammiferous_rectigrade) {
	prancingActivin(mammiferous_rectigrade);
}

public static void prancingActivin(String[][] quadruplicity_clivus) {
	agathosmaDisgulf(quadruplicity_clivus);
}

public static void agathosmaDisgulf(String[][] pulmobranchial_procatarctic) {
	longitudinallyTricolon(pulmobranchial_procatarctic);
}

public static void longitudinallyTricolon(String[][] fountful_serif) {
	stegocephaliaOversourly(fountful_serif);
}

public static void stegocephaliaOversourly(String[][] tsubo_kaska) {
	Tracer.tracepointWeaknessStart(
			"CWE089",
			"D",
			"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
	String stonesoup_psql_host = System.getenv("DBPGHOST");
	String stonesoup_psql_user = System.getenv("DBPGUSER");
	String stonesoup_psql_pass = System.getenv("DBPGPASSWORD");
	String stonesoup_psql_port = System.getenv("DBPGPORT");
	String stonesoup_psql_dbname = System.getenv("SS_DBPGDATABASE");
	Tracer.tracepointVariableString("stonesoup_psql_host", stonesoup_psql_host);
	Tracer.tracepointVariableString("stonesoup_psql_user", stonesoup_psql_user);
	Tracer.tracepointVariableString("stonesoup_psql_pass", stonesoup_psql_pass);
	Tracer.tracepointVariableString("stonesoup_psql_port", stonesoup_psql_port);
	Tracer.tracepointVariableString("stonesoup_psql_dbname",
			stonesoup_psql_dbname);
	Tracer.tracepointVariableString("shipper_name",
			tsubo_kaska[hyperaltruism_liaison][29]);
	if (stonesoup_psql_host == null || stonesoup_psql_user == null
			|| stonesoup_psql_pass == null || stonesoup_psql_port == null
			|| stonesoup_psql_dbname == null) {
		Tracer.tracepointError("Missing required database connection parameter(s).");
		LexerScheme.speckyAchaeta
				.println("STONESOUP: Missing required database connection parameters.");
	} else {
		try {
			StringBuffer jdbc = new StringBuffer("jdbc:postgresql:");
			jdbc.append(stonesoup_psql_host);
			jdbc.append(":");
			jdbc.append(stonesoup_psql_port);
			jdbc.append("");
			jdbc.append(stonesoup_psql_dbname);
			Class.forName("org.postgresql.Driver");
			java.sql.Connection conn = java.sql.DriverManager.getConnection(
					jdbc.toString(), stonesoup_psql_user, stonesoup_psql_pass);
			Tracer.tracepointMessage("Establishing connection to database.");
			java.sql.Statement stmt = conn.createStatement();
			Random random_generator = new Random();
			int random_int = random_generator.nextInt(1000) + 100;
			Tracer.tracepointVariableInt("random_int", random_int);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
					+ " VALUES ('"
					+ random_int
					+ "', '"
					+ tsubo_kaska[hyperaltruism_liaison][29] + "');";
			Tracer.tracepointVariableString("queryString", queryString);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			LexerScheme.speckyAchaeta.println(queryString);
			Tracer.tracepointMessage("Querying database.");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			stmt.execute(queryString);
			LexerScheme.speckyAchaeta.println("Number of Rows Affected: "
					+ stmt.getUpdateCount());
			Tracer.tracepointVariableInt("rows affected", stmt.getUpdateCount());
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			stmt.close();
			conn.close();
		} catch (java.sql.SQLFeatureNotSupportedException nse) {
			Tracer.tracepointError(nse.getClass().getName() + ": "
					+ nse.getMessage());
			LexerScheme.speckyAchaeta
					.println("STONESOUP: Error accessing database.");
			nse.printStackTrace(LexerScheme.speckyAchaeta);
		} catch (java.sql.SQLException se) {
			Tracer.tracepointError(se.getClass().getName() + ": "
					+ se.getMessage());
			LexerScheme.speckyAchaeta
					.println("STONESOUP: Error accessing database.");
			se.printStackTrace(LexerScheme.speckyAchaeta);
		} catch (ClassNotFoundException cnfe) {
			Tracer.tracepointError(cnfe.getClass().getName() + ": "
					+ cnfe.getMessage());
			LexerScheme.speckyAchaeta
					.println("STONESOUP: Error accessing database.");
			cnfe.printStackTrace(LexerScheme.speckyAchaeta);
		}
	}
	Tracer.tracepointWeaknessEnd();
}


}
