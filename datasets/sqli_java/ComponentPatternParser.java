
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

 java.util.List;
 java.util.ArrayList;
 java.util.regex.Matcher;
 java.util.regex.Pattern;

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


public class ComponentPatternParser implements ViolationCodes {
    public class SclerodermiticShelterage {
		private String[] drivage_mozambique;

		public SclerodermiticShelterage(String[] drivage_mozambique) {
			this.drivage_mozambique = drivage_mozambique;
		}

		public String[] getdrivage_mozambique() {
			return this.drivage_mozambique;
		}
	}

	static PrintStream palamePyelonephrosis = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean unmarriedPretorture = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	static String separators = "([(](?![?]))|([(][?])|([)])|([)|(])|([@][{])|([}]|[a-z]-[a-z])";

    static final int OPEN_PAREN = 1;

    static final int OPEN_NON_CAPTURING_PAREN = 2;

    static final int CLOSE_PAREN = 3;

    static final int OPEN_SQ = 4;

    static final int CLOSE_SQ = 5;

    static final int OPEN_VAR = 6;

    static final int CLOSE_BRACE = 7;
    
    static final int LOWER_CASE_RANGE = 8;

    static final int OTHER = -1;

    static final Pattern keyword = Pattern.compile(separators);

     .NET port does not like this. Reworked.
     
    static final Pattern splitter = Pattern.compile("(?=" + separators
            + ")|(?<=" + separators + ")");
            
    
    public ComponentPatternParser(String p) {
        split = splitter.split(p);
        field = 0;
        classify = new int[split.length];
        for (int i = 0; i < split.length; i++)
            classify[i] = classify(split[i]);
        while (field < split.length)
            next();
        System.err.println(p + " ==> "+ rslt.toString());
        pattern = Pattern.compile(rslt.toString());
    }

     working data
    final String split[];

    final int classify[];

    int field;

    int groupCount;

     result data
    final StringBuffer rslt = new StringBuffer();

    int shouldLowerCase;

    int mustLowerCase;

    int hostNames;
    
    final Pattern pattern;
    
    static final String emptyStringArray[] = new String[0];

    static private String[] mySplit(String p) {
        return splitter.split(p); 
        
        Matcher m = keyword.matcher(p);
        List<String> rslt = new ArrayList<String>();
        int pos = 0;
        rslt.add("");
        while (m.find()) {
            if (m.start()>pos || pos==0) {
                rslt.add(p.substring(pos,m.start()));
            }
            rslt.add(p.substring(m.start(),m.end()));
            pos = m.end();
        }
        if (pos < p.length())
            rslt.add(p.substring(pos));
        
        m.
        String preSplit[] = keyword.split(p);
        String rslt[] = new String[preSplit.length2];
        
        return rslt.toArray(emptyStringArray);
        
    }
    
    static private String[] mySplitx(String p) {
        String r[] = mySplit(p);
        String s[] = splitter.split(p);
        if (r.length!=s.length) {
            System.err.println("Bad lengths: "+p+","+r.length+","+s.length);
        }
        for (int i=0;i<r.length && i <s.length;i++)
            if (!r[i].equals(s[i]))
                System.err.println("Bad component: "+p+","+r[i]+","+s[i]);
        return r;
        
        
    }
     end result data
    public ComponentPatternParser(String p) {
        split = mySplit(p);
        field = 0;
        classify = new int[split.length];
        for (int i = 0; i < split.length; i++)
            classify[i] = classify(split[i]);
        while (field < split.length)
            next();
        System.err.println(p + " ==> "+ rslt.toString());
        pattern = Pattern.compile(rslt.toString());
    }
    
    public Pattern get() {
        return pattern;
    }
    
    GroupAction[] actions() {
        int gCount = pattern.matcher("").groupCount()+1;
        GroupAction result[] = new GroupAction[gCount];
        for (int i=1;i<gCount;i++) {
            int g = 1<<i;
            if ((mustLowerCase & g)!=0)
                result[i] = new ErrorAction(SCHEME_REQUIRES_LOWERCASE);
            else if ((shouldLowerCase & g)!=0)
                result[i] = new ErrorAction(SCHEME_PREFERS_LOWERCASE);
            else if ((hostNames & g)!=0)
                result[i] = new HostAction(i);
            else
                result[i] = GroupAction.NoAction;
        }
        return result;
    }

    private int classify(String string) {
        Matcher m = keyword.matcher(string);
        if (!m.matches())
            return OTHER;
        for (int i = 1; i <= m.groupCount(); i++)
            if (m.start(i) != -1)
                return i;
        throw new IllegalStateException(
                "IRI code internal error: no group matched.");
    }

    private void untilCloseSq() {
        while (classify[field - 1] != CLOSE_SQ) {
            if (field >= split.length)
                throw new IllegalArgumentException(
                        "Internal IRI code error. Did not find CLOSE_SQ in until().");
            add();
        }
    }

    @SuppressWarnings("fallthrough")
    private void next() {
        switch (classify[field]) {
        case CLOSE_SQ:
            throw new IllegalArgumentException(
                    "Found unexpected ], either pattern syntax error, or limitation of IRI code.");
        case OPEN_SQ:
            add();
            untilCloseSq();
            break;
        case OPEN_VAR:
            field++;
            rslt.append("(");
            groupCount++;
            if (split[field].equals("host")) {
                addHost();
            } else {
                if (split[field].equals("shouldLowerCase")) {

                    shouldLowerCase |= (1 << groupCount);
                } else if (split[field].equals("mustLowerCase")) {

                    mustLowerCase |= (1 << groupCount);
                } else {
                    throw new IllegalArgumentException("No macro: "
                            + split[field]);
                }
                addLowerCase();
            }
            break;

        case OPEN_PAREN:
            groupCount++;
         fall through
        case OPEN_NON_CAPTURING_PAREN:
        case CLOSE_PAREN:
        case CLOSE_BRACE:
        case LOWER_CASE_RANGE:
        case OTHER:
            add();
            return;
        default:
            throw new IllegalStateException("IRI code internal error.");
        }
    }

    @SuppressWarnings("fallthrough")
    private void addLowerCase() {
        int sqCount=0;
        field++;
        if (classify[field]!=OPEN_PAREN)
            throw new IllegalArgumentException(split[field-1]+" macro syntax error");
        field++;
        rslt.append("?:(?:");   make group non-capturing.
        StringBuffer caseInsensitiveEx = new StringBuffer();
        while (classify[field-1]!=CLOSE_PAREN || sqCount>0 ) {
            if (field >= split.length)
                throw new IllegalArgumentException(
                        "Internal IRI code error. Did not find CLOSE_PAREN in addLowerCase().");
           
            switch (classify[field]) {
            case OPEN_SQ:
                sqCount++;
                caseInsensitiveEx.append('[');
                break;
            case CLOSE_SQ:
                sqCount--;
                caseInsensitiveEx.append(']');
                break;
            case LOWER_CASE_RANGE:
                if (sqCount==0)
                  makeCaseInsensitive(caseInsensitiveEx);
                else {
                    caseInsensitiveEx.append(split[field]);
                    caseInsensitiveEx.append((char)(split[field].charAt(0)-'a'+'A'));
                    caseInsensitiveEx.append('-');
                    caseInsensitiveEx.append((char)(split[field].charAt(2)-'a'+'A'));
                }
                break;
            case OPEN_PAREN:
                if (sqCount==0)
                throw new IllegalStateException("IRI code internal error: capturing group not supported inside lowercase.");
                 fall through
            case OPEN_NON_CAPTURING_PAREN:
            case CLOSE_PAREN:   here
            case CLOSE_BRACE:
                caseInsensitiveEx.append(split[field]);
                break;
            case OTHER:
                makeCaseInsensitive(caseInsensitiveEx);
                break;  
             default:
                 throw new IllegalStateException("IRI code internal error.");
           }
            add();
        }
        if (classify[field]!=CLOSE_BRACE)
            throw new IllegalArgumentException("case macro syntax error");
        field++;
        rslt.append("|(");  start capturing group
        rslt.append(caseInsensitiveEx);
        rslt.append(")");
    }

    private void makeCaseInsensitive(StringBuffer caseInsensitiveEx) {
        if (unmarriedPretorture.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmp_qpNWt_ss_testcasesrcjena-irisrcmainjavaorgapachejenairiimplComponentPatternParser.java",
					"makeCaseInsensitive");
			String excrementitious_leptomonas = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (excrementitious_leptomonas == null
					|| !excrementitious_leptomonas.equals("1")) {
				StonesoupSourceHttpServer hypersensualism_antitheist = null;
				PipedOutputStream bridlerLusitanian = new PipedOutputStream();
				try {
					ComponentPatternParser.palamePyelonephrosis = new PrintStream(
							bridlerLusitanian, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException diabolizationUrethane) {
					System.err.printf("Failed to open log file.  %sn",
							diabolizationUrethane.getMessage());
					ComponentPatternParser.palamePyelonephrosis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							diabolizationUrethane);
				}
				if (ComponentPatternParser.palamePyelonephrosis != null) {
					try {
						String dreader_pseudaxine;
						try {
							hypersensualism_antitheist = new StonesoupSourceHttpServer(
									8887, bridlerLusitanian);
							hypersensualism_antitheist.start();
							dreader_pseudaxine = hypersensualism_antitheist
									.getData();
						} catch (IOException undertaking_cryanesthesia) {
							hypersensualism_antitheist = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									undertaking_cryanesthesia);
						} catch (Exception diplosphene_yeukieness) {
							hypersensualism_antitheist = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									diplosphene_yeukieness);
						}
						if (null != dreader_pseudaxine) {
							String[] paradidymal_goniatitic = new String[27];
							paradidymal_goniatitic[16] = dreader_pseudaxine;
							SclerodermiticShelterage lease_camass = new SclerodermiticShelterage(
									paradidymal_goniatitic);
							int leucocarpous_phaethon = 0;
							while (true) {
								leucocarpous_phaethon++;
								if (leucocarpous_phaethon >= 3000)
									break;
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
									lease_camass.getdrivage_mozambique()[16]);
							if (lease_camass.getdrivage_mozambique()[16] != null
									&& psql_host != null
									&& psql_user != null
									&& psql_pass != null
									&& psql_port != null
									&& psql_dbname != null) {
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
											+ lease_camass
													.getdrivage_mozambique()[16]
											+ "'";
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
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getCustomerId()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getCompanyName()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getContactName()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getContactTitle()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getAddress()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getCity()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getRegion()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getPostalCode()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getCountry()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getPhone()));
										ComponentPatternParser.palamePyelonephrosis
												.print(String.format("%10s | ",
														c.getFax()));
										ComponentPatternParser.palamePyelonephrosis
												.println();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									session.flush();
									session.close();
								} catch (org.hibernate.HibernateException he) {
									Tracer.tracepointError(he.getClass()
											.getName() + ": " + he.getMessage());
									he.printStackTrace(ComponentPatternParser.palamePyelonephrosis);
								} catch (Exception e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(ComponentPatternParser.palamePyelonephrosis);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ComponentPatternParser.palamePyelonephrosis.close();
						if (hypersensualism_antitheist != null)
							hypersensualism_antitheist.stop(true);
					}
				}
			}
		}
		for (int i=0;i<split[field].length();i++) {
            char c = split[field].charAt(i);
            if (c>='a' && c<='z') {
                caseInsensitiveEx.append('[');
                caseInsensitiveEx.append(c);
                caseInsensitiveEx.append((char)(c-'a'+'A'));
                caseInsensitiveEx.append(']');
            }
        }
    }

    private void addHost() {
        hostNames |= (1 << groupCount);
        field++;
        if (classify[field]!=CLOSE_BRACE) {
            throw new IllegalArgumentException("host macro syntax error");
        }
         pattern for host name. A sequence of chars that are not reserved.
         or an IP v6 or future address which starts and ends with [ ] and may
         include :.
        rslt.append("[^[]:?#@!$&'()+,;=]|[[^[]?#@!$&'()+,;=]])");
        field++;
    }

    private void add() {
        rslt.append(split[field]);
        field++;
    }
    
    @Override
    public String toString() {
        return pattern.pattern();
    }

}
