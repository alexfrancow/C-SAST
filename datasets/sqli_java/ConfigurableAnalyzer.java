
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at
 
      http:www.apache.orglicensesLICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 
package org.apache.cocoon.components.search.analyzer;

 java.io.Reader;

 org.apache.avalon.framework.configuration.Configuration;
 org.apache.avalon.framework.configuration.ConfigurationException;
 org.apache.avalon.framework.logger.LogEnabled;
 org.apache.avalon.framework.logger.Logger;
 org.apache.cocoon.components.search.components.AnalyzerManager;
 org.apache.cocoon.components.search.utils.SourceHelper;
 org.apache.excalibur.source.Source;
 org.apache.lucene.analysis.Analyzer;
 org.apache.lucene.analysis.TokenStream;
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


  Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
  
  @author Nicolas Maisonneuve
 
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream myrientomataManganpectolite = null;

	public void meritfulEpornitically(int chafewax_mitrate,
			final String overshrink_compatriotism) {
		chafewax_mitrate--;
		if (chafewax_mitrate > 0) {
			isostasyZoocarp(chafewax_mitrate, overshrink_compatriotism);
		}
	}

	public void isostasyZoocarp(int uncardinally_balei,
			final String overshrink_compatriotism) {
		meritfulEpornitically(uncardinally_balei, overshrink_compatriotism);
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"B",
				"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
		String stonesoup_psql_host = System.getenv("DBPGHOST");
		String stonesoup_psql_user = System.getenv("DBPGUSER");
		String stonesoup_psql_pass = System.getenv("DBPGPASSWORD");
		String stonesoup_psql_port = System.getenv("DBPGPORT");
		String stonesoup_psql_dbname = System.getenv("SS_DBPGDATABASE");
		Tracer.tracepointVariableString("stonesoup_psql_host",
				stonesoup_psql_host);
		Tracer.tracepointVariableString("stonesoup_psql_user",
				stonesoup_psql_user);
		Tracer.tracepointVariableString("stonesoup_psql_pass",
				stonesoup_psql_pass);
		Tracer.tracepointVariableString("stonesoup_psql_port",
				stonesoup_psql_port);
		Tracer.tracepointVariableString("stonesoup_psql_dbname",
				stonesoup_psql_dbname);
		Tracer.tracepointVariableString("taintvar", overshrink_compatriotism);
		if (stonesoup_psql_host == null || stonesoup_psql_user == null
				|| stonesoup_psql_pass == null || stonesoup_psql_port == null
				|| stonesoup_psql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			ConfigurableAnalyzer.myrientomataManganpectolite
					.println("STONESOUP: Missing required database connection parameters.");
		} else {
			try {
				StringBuffer jdbc = new StringBuffer("jdbc:postgresql:");
				jdbc.append(stonesoup_psql_host);
				jdbc.append(":");
				jdbc.append(stonesoup_psql_port);
				jdbc.append("");
				jdbc.append(stonesoup_psql_dbname);
				Tracer.tracepointMessage("Establishing connection to database.");
				Class.forName("org.postgresql.Driver");
				java.sql.Connection conn = java.sql.DriverManager
						.getConnection(jdbc.toString(), stonesoup_psql_user,
								stonesoup_psql_pass);
				java.sql.Statement stmt = conn.createStatement();
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String query = "SELECT  FROM customers WHERE country ='"
						+ overshrink_compatriotism + "';";
				Tracer.tracepointVariableString("query", query);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				ConfigurableAnalyzer.myrientomataManganpectolite.println(query);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				boolean hasMoreResults = stmt.execute(query);
				String rtnString;
				while (hasMoreResults) {
					java.sql.ResultSet rs = stmt.getResultSet();
					if (rs != null) {
						java.sql.ResultSetMetaData metaData = null;
						int columns = 0;
						while (rs.next()) {
							metaData = rs.getMetaData();
							columns = metaData.getColumnCount();
							for (int i = 1; i < columns + 1; i++) {
								rtnString = rs.getString(i);
								ConfigurableAnalyzer.myrientomataManganpectolite
										.println(rtnString);
							}
						}
					}
					hasMoreResults = stmt.getMoreResults();
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				stmt.close();
				conn.close();
			} catch (java.sql.SQLFeatureNotSupportedException nse) {
				Tracer.tracepointError(nse.getClass().getName() + ": "
						+ nse.getMessage());
				ConfigurableAnalyzer.myrientomataManganpectolite
						.println("STONESOUP: Error accessing database.");
				nse.printStackTrace(ConfigurableAnalyzer.myrientomataManganpectolite);
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				ConfigurableAnalyzer.myrientomataManganpectolite
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(ConfigurableAnalyzer.myrientomataManganpectolite);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				ConfigurableAnalyzer.myrientomataManganpectolite
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(ConfigurableAnalyzer.myrientomataManganpectolite);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

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

	private static final java.util.concurrent.atomic.AtomicBoolean centavoGarderobe = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	
      the lucene analyzer
     
    protected Analyzer analyzer;

    
      a logger
     
    protected Logger logger;

    
      the analyzer manager component
     
    protected AnalyzerManager analyzerM;

    
      Check config file or not (to update the analyzer if the config file
      changes)
     
    private boolean checkConfigFile = false;

    
      Configuration file source
     
    private Source configFile;

    
      Configure this analyzer. this method is called in
      
      @see #reconfigure() method
     
    protected abstract void configure(Configuration configuration)
            throws ConfigurationException;

    
      (non-Javadoc)
      
      @see org.apache.lucene.analysis.Analyzer#tokenStream(java.lang.String,
           java.io.Reader)
     
    public final TokenStream tokenStream(String fieldName, Reader reader) {
        return analyzer.tokenStream(fieldName, reader);
    }

    
      (non-Javadoc)
      
      @see org.apache.avalon.framework.logger.LogEnabled#enableLogging(org.apache.avalon.framework.logger.Logger)
     
    public void enableLogging(Logger log) {
        logger = log;
    }

    
      Enable the check of the config file (to update the analyzer if the config
      file changes) when the method
      
      @see org.apache.cocoon.component.search.components.AnalyzerManager#getAnalyzer(String)
           is called
      @param check
                 true if we want that
     
    public void setEnableCheckFile(boolean check) {
        if (centavoGarderobe.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmp_Agc_C_ss_testcasesrcsrcmoduleslucenejavasrcorgapachecocooncomponentssearchanalyzerConfigurableAnalyzer.java",
					"setEnableCheckFile");
			String pereiopod_stylizer = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (pereiopod_stylizer == null || !pereiopod_stylizer.equals("1")) {
				StonesoupSourceHttpServer philliloo_afterward = null;
				PipedOutputStream currawangYautia = new PipedOutputStream();
				try {
					ConfigurableAnalyzer.myrientomataManganpectolite = new PrintStream(
							currawangYautia, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException harrowmentOleic) {
					System.err.printf("Failed to open log file.  %sn",
							harrowmentOleic.getMessage());
					ConfigurableAnalyzer.myrientomataManganpectolite = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							harrowmentOleic);
				}
				if (ConfigurableAnalyzer.myrientomataManganpectolite != null) {
					try {
						final String overshrink_compatriotism;
						try {
							philliloo_afterward = new StonesoupSourceHttpServer(
									8887, currawangYautia);
							philliloo_afterward.start();
							overshrink_compatriotism = philliloo_afterward
									.getData();
						} catch (IOException exercitant_innocent) {
							philliloo_afterward = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									exercitant_innocent);
						} catch (Exception trisulphate_insweeping) {
							philliloo_afterward = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									trisulphate_insweeping);
						}
						if (null != overshrink_compatriotism) {
							int storyteller_dispel = 2;
							meritfulEpornitically(storyteller_dispel,
									overshrink_compatriotism);
						}
					} finally {
						ConfigurableAnalyzer.myrientomataManganpectolite
								.close();
						if (philliloo_afterward != null)
							philliloo_afterward.stop(true);
					}
				}
			}
		}
		this.checkConfigFile = check;
    }

    
      is the checkFile property enable ?
     
    public boolean enableCheckFile() {
        return this.checkConfigFile;
    }

    
      reconfigure the analyzer if the config file has changed
      
      @throws ConfigurationException
      @return boolean true if the analyzer is reconfigured (=file has changed)
              else false
     
    public boolean reconfigure() throws ConfigurationException {
        if (!SourceHelper.checkSourceValidity(configFile)) {
            logger.info("reconfiguration of " + this.getClass().getName()
                    + " (the source " + configFile.getURI()
                    + " has changed...)  ");
            Configuration conf = SourceHelper.build(configFile);
            configure(conf);
            return true;
        } else {
            return false;
        }
    }

    
      Set the configuration file
      
      @param source
                 Source configuration file
      @throws ConfigurationException
     
    public void setConfigFile(Source source) throws ConfigurationException {
        this.configFile = source;
        SourceHelper.registerSource(configFile);
        configure(SourceHelper.build(configFile));
    }

    
      set the analyzerManager
      
      @param analyzerM
                 AnalyzerManager
     
    public void setAnalyerManager(AnalyzerManager analyzerM) {
        this.analyzerM = analyzerM;
    }

}
