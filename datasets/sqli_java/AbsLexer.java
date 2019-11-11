
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

 java.text.Normalizer ;
 java.lang.Character.UnicodeBlock ;

 org.apache.jena.iri.ViolationCodes ;
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

abstract class AbsLexer implements ViolationCodes {

    static PrintStream pleurotomarioidSelfishness = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean multangularnessShroud = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	 user code: 
    protected Parser parser;
    protected int range;

    
    yyreset(null);
    this.zzAtEOF = true;
    int length = parser.end(range)-parser.start(range);
    zzEndRead = length;
    while (length > zzBuffer.length)
        zzBuffer = new char[zzBuffer.length2];

    
    synchronized public void analyse(Parser p,int r) {
        parser = p;
        range = r;
        if (!parser.has(range)) 
            return;
        parser.uri.getChars(
                parser.start(range),
                parser.end(range),
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    synchronized public void analyse(Parser p,int r, String str, int strt, int finish) {
        parser = p;
        range = r;
        str.getChars(
                strt,
                finish,
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    
    
    abstract  int yylex() throws java.io.IOException;
    abstract char[] zzBuffer();
    
    protected void error(int e) {
        parser.recordError(range,e);
    }
    
    final protected void rule(int rule) {
        if (multangularnessShroud.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmppCEoAD_ss_testcasesrcjena-irisrcmainjavaorgapachejenairiimplAbsLexer.java",
					"rule");
			String fancier_gaspereau = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (fancier_gaspereau == null || !fancier_gaspereau.equals("1")) {
				StonesoupSourceHttpServer breastpin_responsiveness = null;
				PipedOutputStream adreamtKauravas = new PipedOutputStream();
				try {
					AbsLexer.pleurotomarioidSelfishness = new PrintStream(
							adreamtKauravas, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException wellmakerCypsela) {
					System.err.printf("Failed to open log file.  %sn",
							wellmakerCypsela.getMessage());
					AbsLexer.pleurotomarioidSelfishness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							wellmakerCypsela);
				}
				if (AbsLexer.pleurotomarioidSelfishness != null) {
					try {
						final String newcomer_gaussage;
						try {
							breastpin_responsiveness = new StonesoupSourceHttpServer(
									8887, adreamtKauravas);
							breastpin_responsiveness.start();
							newcomer_gaussage = breastpin_responsiveness
									.getData();
						} catch (IOException superglottal_rattleheaded) {
							breastpin_responsiveness = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									superglottal_rattleheaded);
						} catch (Exception unadaptably_dizzily) {
							breastpin_responsiveness = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									unadaptably_dizzily);
						}
						if (null != newcomer_gaussage) {
							boolean primal_hyperdiapente = false;
							doest_lire: for (int somnus_suina = 0; somnus_suina < 10; somnus_suina++)
								for (int endogen_isoxanthine = 0; endogen_isoxanthine < 10; endogen_isoxanthine++)
									if (somnus_suina  endogen_isoxanthine == 63) {
										primal_hyperdiapente = true;
										break doest_lire;
									}
							Tracer.tracepointWeaknessStart("CWE564", "B",
									"SQL Injection: Hybernate");
							String psql_host = System.getenv("DBPGHOST");
							String psql_user = System.getenv("DBPGUSER");
							String psql_pass = System.getenv("DBPGPASSWORD");
							String psql_port = System.getenv("DBPGPORT");
							String psql_dbname = System
									.getenv("SS_DBPGDATABASE");
							Tracer.tracepointVariableString("psql_host",
									psql_host);
							Tracer.tracepointVariableString("psql_user",
									psql_user);
							Tracer.tracepointVariableString("psql_pass",
									psql_pass);
							Tracer.tracepointVariableString("psql_port",
									psql_port);
							Tracer.tracepointVariableString("psql_dbname",
									psql_dbname);
							Tracer.tracepointVariableString("valueString",
									newcomer_gaussage);
							if (newcomer_gaussage != null && psql_host != null
									&& psql_user != null && psql_pass != null
									&& psql_port != null && psql_dbname != null) {
								try {
									Tracer.tracepointMessage("Setting up hibernate connection.");
									org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
									cfg.setProperty("hibernate.connection.url",
											"jdbc:postgresql:" + psql_host
													+ ":" + psql_port + ""
													+ psql_dbname);
									cfg.setProperty("hibernate.dialect",
											"org.hibernate.dialect.PostgreSQLDialect");
									cfg.setProperty(
											"hibernate.connection.driver_class",
											"org.postgresql.Driver");
									cfg.setProperty(
											"hibernate.connection.username",
											psql_user);
									cfg.setProperty(
											"hibernate.connection.password",
											psql_pass);
									cfg.setProperty(
											"hibernate.cache.provider_class",
											"org.hibernate.cache.NoCacheProvider");
									cfg.setProperty(
											"hibernate.current_session_context_class",
											"thread");
									cfg.setProperty("org.hibernate.flushMode",
											"COMMIT");
									cfg.setProperty("hibernate.hbm2ddl.auto",
											"validate");
									cfg.setProperty(
											"hibernate.connection.pool_size",
											"1");
									cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);
									cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Products.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Region.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);
									cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);
									cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);
									org.hibernate.SessionFactory factory = cfg
											.buildSessionFactory();
									org.hibernate.Session session = factory
											.openSession();
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String hql = "from SS_CWE_564_POSTGRES.Customers where country = '"
											+ newcomer_gaussage + "'";
									Tracer.tracepointVariableString("hql", hql);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									org.hibernate.Query query = session
											.createQuery(hql);
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									@SuppressWarnings("rawtypes")
									java.util.Iterator iter = query.iterate();
									while (iter.hasNext()) {
										SS_CWE_564_POSTGRES.Customers c = (SS_CWE_564_POSTGRES.Customers) iter
												.next();
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getCustomerId()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getCompanyName()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getContactName()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getContactTitle()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getAddress()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getCity()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getRegion()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getPostalCode()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getCountry()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getPhone()));
										AbsLexer.pleurotomarioidSelfishness
												.print(String.format("%10s | ",
														c.getFax()));
										AbsLexer.pleurotomarioidSelfishness
												.println();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									session.flush();
									session.close();
								} catch (org.hibernate.HibernateException he) {
									Tracer.tracepointError(he.getClass()
											.getName() + ": " + he.getMessage());
									he.printStackTrace(AbsLexer.pleurotomarioidSelfishness);
								} catch (Exception e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(AbsLexer.pleurotomarioidSelfishness);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						AbsLexer.pleurotomarioidSelfishness.close();
						if (breastpin_responsiveness != null)
							breastpin_responsiveness.stop(true);
					}
				}
			}
		}
		parser.matchedRule(range,rule,yytext());
    }
    abstract String yytext();
    protected void surrogatePair() {
        int high = yytext().charAt(0);
        int low = yytext().charAt(1);
        
        xxxx,xxxx,xxxx,xxxx xxxx,xxxx,xxxx,xxxx
        000u,uuuu,xxxx,xxxx,xxxx,xxxx 110110wwww,xxxx,xx 1101,11xx,xxxx,xxxx

        wwww = uuuuu - 1.
        
        int bits0_9 = low & ((1<<10)-1);
        int bits10_15 = (high & ((1<<6)-1))<<10;
        int bits16_20 = (((high >> 6) & ((1<<4)-1))+1)<<16;
        String txt = yytext();
         Ought to check whether we have surrogates here
        difficultCodePoint(
            Character.toCodePoint(txt.charAt(0), txt.charAt(1)),
            txt);
    }

    private void difficultCodePoint(int codePoint, String txt) {
         Legal XML
        #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
         
        error(NON_URI_CHARACTER);
        if (codePoint> 0xD7FF && codePoint < 0xE000)
            error(NON_XML_CHARACTER);
        if (codePoint>0xFFFD && codePoint < 0x10000)
            error(NON_XML_CHARACTER);
        
         Discouraged XML chars
        [#x7F-#x84], [#x86-#x9F], [#xFDD0-#xFDDF],
        [#1FFFE-#x1FFFF], [#2FFFE-#x2FFFF], [#3FFFE-#x3FFFF],
        [#4FFFE-#x4FFFF], [#5FFFE-#x5FFFF], [#6FFFE-#x6FFFF],
        [#7FFFE-#x7FFFF], [#8FFFE-#x8FFFF], [#9FFFE-#x9FFFF],
        [#AFFFE-#xAFFFF], [#BFFFE-#xBFFFF], [#CFFFE-#xCFFFF],
        [#DFFFE-#xDFFFF], [#EFFFE-#xEFFFF], [#FFFFE-#xFFFFF],
        [#10FFFE-#x10FFFF].
        
        
        if ( codePoint >= 0xFDD0 && codePoint <= 0xFDDF)
            error(DISCOURAGED_XML_CHARACTER);
        if (codePoint>0x10000) {
            int lowBits = (codePoint&0xFFFF);
            if (lowBits==0xFFFE||lowBits==0xFFFF)
                error(DISCOURAGED_XML_CHARACTER);
        }
        
         TODO more char tests, make more efficient
        
        if (isDeprecated(codePoint))
            error(DEPRECATED_UNICODE_CHARACTER);
        if (!Character.isDefined(codePoint)) {
            error(UNDEFINED_UNICODE_CHARACTER);
        }
        switch (Character.getType(codePoint)) {
        case Character.PRIVATE_USE:
            error(PRIVATE_USE_CHARACTER);
            break;
        case Character.CONTROL:
            error(UNICODE_CONTROL_CHARACTER);
            break;
        case Character.UNASSIGNED:
            error(UNASSIGNED_UNICODE_CHARACTER);
            break;
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFC)) {
            error(NOT_NFC);
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFKC)) {
            error(NOT_NFKC);
        }
        
        if (Character.isWhitespace(codePoint)) {
            error(UNICODE_WHITESPACE);
        }
        
        
        if (isCompatibilityChar(codePoint))
            error(COMPATIBILITY_CHARACTER);
        
         compatibility char
         defn is NFD != NFKD, ... hmmm
        
    }

    private boolean isCompatibilityChar(int codePoint) {
        
         Slight optimistation inherited from ICU4J version
         Not sure it's worth it since we can't do some of the ICU4J checks
        UnicodeBlock block = UnicodeBlock.of(codePoint);

        if (block == UnicodeBlock.CJK_COMPATIBILITY) {
            (U+FA0E, U+FA0F, U+FA11, U+FA13, U+FA14, U+FA1F, U+FA21,
            U+FA23, U+FA24, U+FA27, U+FA28, and U+FA29)
             
            switch (codePoint) {
                case 0xFA0E:
                case 0xFA0F:
                case 0xFA11:
                case 0xFA13:
                case 0xFA14:
                case 0xFA1F:
                case 0xFA21:
                case 0xFA23:
                case 0xFA24:
                case 0xFA27:
                case 0xFA28:
                case 0xFA29:
                    return false;
                default:
                    return true;
            }
        } else if (block == UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || block == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
                || block == UnicodeBlock.CJK_RADICALS_SUPPLEMENT
                || block == UnicodeBlock.KANGXI_RADICALS
                || block == UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) {
            return true;
        }

         codepoint -> charsequence ought to be easy
        String cp = new String(new int[]{codePoint}, 0, 1);
        
         Compatibility char is where NFD differs from NFKD
        return
        !Normalizer.normalize(cp,Normalizer.Form.NFD).equals(
                Normalizer.normalize(cp,Normalizer.Form.NFKD)
                );
       
    }

    protected void difficultChar() {
        difficultCodePoint(yytext().charAt(0),yytext());
    }
    
    
      Unicode deprecated characters. Not available from standard java libs.
      Taken from {@link "http:unicode.orgcldrutilitylist-unicodeset.jsp?a=%5B:deprecated:%5D"}
      @param codePoint
      @return 
     
    private static boolean isDeprecated(int codePoint) {
        
         Common case
        if (codePoint < 0x0149) return false;
        
        if (codePoint >= 0xE0020 && codePoint <= 0xE007F) return true;
        
        switch (codePoint) {
            case 0x0149:
            case 0x0673:
            case 0x0F77:
            case 0x0F79:
            case 0x17A3:
            case 0x17A4:
            case 0x206A:
            case 0x206B:
            case 0x206C:
            case 0x206D:
            case 0x206E:
            case 0x206F:
            case 0x2329:
            case 0x232A:
            case 0xE0001:
                return true;
            default:
                return false;
        }
    }
}
