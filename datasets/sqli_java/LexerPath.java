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
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 org.hibernate.service.ServiceRegistry;
 org.hibernate.service.ServiceRegistryBuilder;


  This class is a scanner generated by 
  <a href="http:www.jflex.de">JFlex<a> 1.4.3
  on 040312 16:02 from the specification file
  <tt>tmp.jflex<tt>
 
class LexerPath extends AbsLexer implements org.apache.jena.iri.ViolationCodes, org.apache.jena.iri.IRIComponents, Lexer {

  static PrintStream democracySubsecurity = null;

	private static final java.util.concurrent.atomic.AtomicBoolean outbetterBasiotripsy = new java.util.concurrent.atomic.AtomicBoolean(
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
    "111511112151122151141612010"+
    "161776151213111112110712"+
    "141612016120101461224510"+
    "120101201512011351324532015"+
    "6171163217ud76023u040021u040022u200023";

   
    Translates characters to character classes
   
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

   
    Translates DFA states to action switch labels.
   
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "2011122314151617"+
    "11011111211311421511613"+
    "1171203012112210123124"+
    "12512612713013110";

  private static int [] zzUnpackAction() {
    int [] result = new int[35];
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
    "00024050050050074050050"+
    "050012001440500500500500170"+
    "050050021401440240026403100334"+
    "05005003600u0104050050050050"+
    "0u01180500214";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[35];
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
    "1314151617110111112"+
    "410113114115116117120121"+
    "12213141231617110111"+
    "112410124114115116117120"+
    "121122260125310126227130"+
    "24013131013230133230134"+
    "135300136137136140200336"+
    "140200440130141230142220"+
    "143210";

  private static int [] zzUnpackTrans() {
    int [] result = new int[300];
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
    "20311113112141111211"+
    "3130211101141111111"+
    "10";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[35];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
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
    
    
    private int lastChar;
    @Override
    char[] zzBuffer() {
     yyreset(null);
    this.zzAtEOF = true;
    int length = parser.end(range)-parser.start(range);
    lastChar = length - 1;
    zzEndRead = length;
    while (length > zzBuffer.length)
        zzBuffer = new char[zzBuffer.length2];
      return zzBuffer;
    }
    


  
    Creates a new scanner
    There is also a java.io.InputStream version of this constructor.
   
    @param   in  the java.io.Reader to read input from.
   
  LexerPath(java.io.Reader in) {
    this.zzReader = in;
  }

  
    Creates a new scanner.
    There is also java.io.Reader version of this constructor.
   
    @param   in  the java.io.Inputstream to read input from.
   
  LexerPath(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

   
    Unpacks the compressed character translation table.
   
    @param packed   the packed character translation table
    @return         the unpacked character translation table
   
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;   index in packed string  
    int j = 0;   index in unpacked array 
    while (i < 94) {
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
    if (outbetterBasiotripsy.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"tmptmpsU0Ytt_ss_testcasesrcjena-irisrcmainjavaorgapachejenairiimplLexerPath.java",
				"yylex");
		File onomatologistGate = new File(
				"optstonesoupworkspacetestDatalogfile.txt");
		if (!onomatologistGate.getParentFile().exists()
				&& !onomatologistGate.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LexerPath.democracySubsecurity = new PrintStream(
						new FileOutputStream(onomatologistGate, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException phallalgiaFlakily) {
				System.err.printf("Failed to open log file.  %sn",
						phallalgiaFlakily.getMessage());
				LexerPath.democracySubsecurity = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						phallalgiaFlakily);
			} catch (FileNotFoundException promenaderessAffreightment) {
				System.err.printf("Failed to open log file.  %sn",
						promenaderessAffreightment.getMessage());
				LexerPath.democracySubsecurity = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						promenaderessAffreightment);
			}
			if (LexerPath.democracySubsecurity != null) {
				try {
					final String sigillate_outward = System
							.getenv("PAUPERAGE_MANUL");
					if (null != sigillate_outward) {
						final Object capacitate_corella = sigillate_outward;
						int tautochronism_vaagmer = 0;
						while (true) {
							tautochronism_vaagmer++;
							if (tautochronism_vaagmer >= 3000)
								break;
						}
						Tracer.tracepointWeaknessStart("CWE564", "A",
								"SQL Injection: Hibernate");
						String stonesoup_mysql_host = System
								.getenv("DBMYSQLHOST");
						String stonesoup_mysql_user = System
								.getenv("DBMYSQLUSER");
						String stonesoup_mysql_pass = System
								.getenv("DBMYSQLPASSWORD");
						String stonesoup_mysql_port = System
								.getenv("DBMYSQLPORT");
						String stonesoup_mysql_dbname = System
								.getenv("SS_DBMYSQLDATABASE");
						Tracer.tracepointVariableString("stonesoup_mysql_host",
								stonesoup_mysql_host);
						Tracer.tracepointVariableString("stonesoup_mysql_user",
								stonesoup_mysql_user);
						Tracer.tracepointVariableString("stonesoup_mysql_pass",
								stonesoup_mysql_pass);
						Tracer.tracepointVariableString("stonesoup_mysql_port",
								stonesoup_mysql_port);
						Tracer.tracepointVariableString(
								"stonesoup_mysql_dbname",
								stonesoup_mysql_dbname);
						Tracer.tracepointVariableString("valueString",
								((String) capacitate_corella));
						if (((String) capacitate_corella) != null
								&& stonesoup_mysql_host != null
								&& stonesoup_mysql_user != null
								&& stonesoup_mysql_pass != null
								&& stonesoup_mysql_port != null
								&& stonesoup_mysql_dbname != null) {
							try {
								Tracer.tracepointMessage("Setting up hibernate connection.");
								org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
								cfg.setProperty(
										"hibernate.connection.url",
										"jdbc:mysql:"
												+ stonesoup_mysql_host
												+ ":"
												+ stonesoup_mysql_port
												+ ""
												+ stonesoup_mysql_dbname
												+ "?allowMultiQueries=true&transformedBitIsBoolean=true");
								cfg.setProperty("hibernate.dialect",
										"org.hibernate.dialect.MySQLDialect");
								cfg.setProperty(
										"hibernate.connection.driver_class",
										"com.mysql.jdbc.Driver");
								cfg.setProperty(
										"hibernate.connection.username",
										stonesoup_mysql_user);
								cfg.setProperty(
										"hibernate.connection.password",
										stonesoup_mysql_pass);
								cfg.setProperty(
										"hibernate.cache.provider_class",
										"org.hibernate.cache.NoCacheProvider");
								cfg.setProperty(
										"hibernate.current_session_context_class",
										"thread");
								cfg.setProperty("hibernate.default_catalog",
										stonesoup_mysql_dbname);
								cfg.setProperty("org.hibernate.flushMode",
										"MANUAL");
								cfg.setProperty("hibernate.hbm2ddl.auto",
										"validate");
								cfg.setProperty(
										"hibernate.connection.pool_size", "1");
								cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);
								cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);
								cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);
								cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);
								cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);
								cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);
								cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);
								cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);
								cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);
								cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);
								cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);
								cfg.addClass(SS_CWE_564_MYSQL.Categories.class);
								cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);
								cfg.addClass(SS_CWE_564_MYSQL.Employees.class);
								cfg.addClass(SS_CWE_564_MYSQL.Products.class);
								cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);
								cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);
								cfg.addClass(SS_CWE_564_MYSQL.Region.class);
								cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);
								cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);
								cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);
								cfg.addClass(SS_CWE_564_MYSQL.Territories.class);
								cfg.addClass(SS_CWE_564_MYSQL.Customers.class);
								cfg.addClass(SS_CWE_564_MYSQL.Orders.class);
								cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);
								cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);
								cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);
								ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
										.applySettings(cfg.getProperties())
										.buildServiceRegistry();
								org.hibernate.SessionFactory factory = cfg
										.buildSessionFactory(serviceRegistry);
								org.hibernate.Session session = factory
										.openSession();
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								String hql = "from SS_CWE_564_MYSQL.Customers where country = '"
										+ ((String) capacitate_corella) + "'";
								Tracer.tracepointVariableString("hql", hql);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								org.hibernate.Query query = session
										.createQuery(hql);
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								@SuppressWarnings("rawtypes")
								java.util.Iterator iter = query.iterate();
								while (iter.hasNext()) {
									SS_CWE_564_MYSQL.Customers c = (SS_CWE_564_MYSQL.Customers) iter
											.next();
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ",
													c.getCustomerId()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ",
													c.getCompanyName()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ",
													c.getContactName()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ",
													c.getContactTitle()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ", c.getAddress()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ", c.getCity()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ", c.getRegion()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ",
													c.getPostalCode()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ", c.getCountry()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ", c.getPhone()));
									LexerPath.democracySubsecurity.print(String
											.format("%10s | ", c.getFax()));
									LexerPath.democracySubsecurity.println();
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								session.flush();
								session.close();
							} catch (org.hibernate.HibernateException he) {
								Tracer.tracepointError(he.getClass().getName()
										+ ": " + he.getMessage());
								LexerPath.democracySubsecurity
										.println("STONESOUP: Error accessing database.");
								he.printStackTrace(LexerPath.democracySubsecurity);
							}
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					LexerPath.democracySubsecurity.close();
				}
			}
		}
	}
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
        case 17: 
          { rule(-70);
  error(DOUBLE_WHITESPACE);
          }
        case 26: break;
        case 11: 
          { rule(-115);
  error(DISCOURAGED_XML_CHARACTER);
  error(CONTROL_CHARACTER);
          }
        case 27: break;
        case 14: 
          { 
xxxx,xxxx,xxxx,xxxx xxxx,xxxx,xxxx,xxxx
000u,uuuu,xxxx,xxxx,xxxx,xxxx 110110wwww,xxxx,xx 110111xx,xxxx,xxxx

wwww = uuuuu - 1.


  rule(-150);
  difficultChar();
          }
        case 28: break;
        case 20: 
          { rule(5); error(NON_INITIAL_DOT_SEGMENT);
          }
        case 29: break;
        case 18: 
          { rule(-130);
   surrogatePair();
          }
        case 30: break;
        case 25: 
          { rule(4); error(NON_INITIAL_DOT_SEGMENT);
          }
        case 31: break;
        case 4: 
          { rule(3);
          }
        case 32: break;
        case 21: 
          { rule(-40); 
 error(PERCENT);
          }
        case 33: break;
        case 13: 
          { rule(-140);
   error(LONE_SURROGATE);   
   difficultChar();
          }
        case 34: break;
        case 15: 
          { rule(-80);
  error(DOUBLE_WHITESPACE);
          }
        case 35: break;
        case 23: 
          { rule(-50); 
  error(PERCENT); 
  error(PERCENT_ENCODING_SHOULD_BE_UPPERCASE);
          }
        case 36: break;
        case 8: 
          { rule(-90);
  if (yychar==lastChar)
    error(DOUBLE_WHITESPACE);
  else
    error(WHITESPACE);
          }
        case 37: break;
        case 12: 
          { rule(-120);
  error(UNWISE_CHARACTER);
          }
        case 38: break;
        case 10: 
          { rule(-113);
  error(CONTROL_CHARACTER);
          }
        case 39: break;
        case 19: 
          { rule(7); if (yychar == lastChar-2)  error(NON_INITIAL_DOT_SEGMENT);
          }
        case 40: break;
        case 5: 
          { rule(-10);
          }
        case 41: break;
        case 24: 
          { rule(2);
          }
        case 42: break;
        case 22: 
          { rule(-30); 
  error(PERCENT_20);
          }
        case 43: break;
        case 7: 
          { rule(-60);
  error(ILLEGAL_PERCENT_ENCODING);
          }
        case 44: break;
        case 16: 
          { rule(8); if (yychar == lastChar-1) error(NON_INITIAL_DOT_SEGMENT);
          }
        case 45: break;
        case 6: 
          { rule(-20);
          }
        case 46: break;
        case 2: 
          { rule(-100);
  error(CONTROL_CHARACTER);
  error(NOT_XML_SCHEMA_WHITESPACE);
          }
        case 47: break;
        case 1: 
          { rule(-160);
  error(ILLEGAL_CHARACTER);
          }
        case 48: break;
        case 3: 
          { rule(6);
          }
        case 49: break;
        case 9: 
          { rule(-110);
  error(NON_XML_CHARACTER);
  error(CONTROL_CHARACTER);
          }
        case 50: break;
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


}
