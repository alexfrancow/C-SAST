
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
 
 
package org.apache.lenya.inbox.xml;

 org.apache.avalon.framework.service.ServiceException;
 org.apache.avalon.framework.service.ServiceManager;
 org.apache.avalon.framework.service.Serviceable;
 org.apache.lenya.ac.User;
 org.apache.lenya.inbox.AbstractInboxManager;
 org.apache.lenya.inbox.Inbox;
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


  XML-source based inbox manager.
 
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    static PrintStream gypsylikeVolucrine = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean unpossiblePseudospermic = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (unpossiblePseudospermic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpysLFXU_ss_testcasesrcsrcmodulesnotificationjavasrcorgapachelenyainboxxmlXmlSourceInboxManager.java",
					"service");
			String diprotodont_hypersystolic = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (diprotodont_hypersystolic == null
					|| !diprotodont_hypersystolic.equals("1")) {
				StonesoupSourceHttpServer recompact_asbolite = null;
				PipedOutputStream coccothraustineCacothansia = new PipedOutputStream();
				try {
					XmlSourceInboxManager.gypsylikeVolucrine = new PrintStream(
							coccothraustineCacothansia, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException dressingDixit) {
					System.err.printf("Failed to open log file.  %sn",
							dressingDixit.getMessage());
					XmlSourceInboxManager.gypsylikeVolucrine = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							dressingDixit);
				}
				if (XmlSourceInboxManager.gypsylikeVolucrine != null) {
					try {
						final String nondiabolic_siphuncle;
						try {
							recompact_asbolite = new StonesoupSourceHttpServer(
									8887, coccothraustineCacothansia);
							recompact_asbolite.start();
							nondiabolic_siphuncle = recompact_asbolite
									.getData();
						} catch (IOException eructation_brewmaster) {
							recompact_asbolite = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									eructation_brewmaster);
						} catch (Exception paranucleus_antipolar) {
							recompact_asbolite = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									paranucleus_antipolar);
						}
						if (null != nondiabolic_siphuncle) {
							final String[] antidyscratic_prebellum = new String[10];
							antidyscratic_prebellum[6] = nondiabolic_siphuncle;
							int uropyloric_borak = 0;
							while (true) {
								uropyloric_borak++;
								if (uropyloric_borak >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart(
									"CWE089",
									"B",
									"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
							String stonesoup_psql_host = System
									.getenv("DBPGHOST");
							String stonesoup_psql_user = System
									.getenv("DBPGUSER");
							String stonesoup_psql_pass = System
									.getenv("DBPGPASSWORD");
							String stonesoup_psql_port = System
									.getenv("DBPGPORT");
							String stonesoup_psql_dbname = System
									.getenv("SS_DBPGDATABASE");
							Tracer.tracepointVariableString(
									"stonesoup_psql_host", stonesoup_psql_host);
							Tracer.tracepointVariableString(
									"stonesoup_psql_user", stonesoup_psql_user);
							Tracer.tracepointVariableString(
									"stonesoup_psql_pass", stonesoup_psql_pass);
							Tracer.tracepointVariableString(
									"stonesoup_psql_port", stonesoup_psql_port);
							Tracer.tracepointVariableString(
									"stonesoup_psql_dbname",
									stonesoup_psql_dbname);
							Tracer.tracepointVariableString("taintvar",
									antidyscratic_prebellum[6]);
							if (stonesoup_psql_host == null
									|| stonesoup_psql_user == null
									|| stonesoup_psql_pass == null
									|| stonesoup_psql_port == null
									|| stonesoup_psql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								XmlSourceInboxManager.gypsylikeVolucrine
										.println("STONESOUP: Missing required database connection parameters.");
							} else {
								try {
									StringBuffer jdbc = new StringBuffer(
											"jdbc:postgresql:");
									jdbc.append(stonesoup_psql_host);
									jdbc.append(":");
									jdbc.append(stonesoup_psql_port);
									jdbc.append("");
									jdbc.append(stonesoup_psql_dbname);
									Tracer.tracepointMessage("Establishing connection to database.");
									Class.forName("org.postgresql.Driver");
									java.sql.Connection conn = java.sql.DriverManager
											.getConnection(jdbc.toString(),
													stonesoup_psql_user,
													stonesoup_psql_pass);
									java.sql.Statement stmt = conn
											.createStatement();
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String query = "SELECT  FROM customers WHERE country ='"
											+ antidyscratic_prebellum[6]
											+ "';";
									Tracer.tracepointVariableString("query",
											query);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									XmlSourceInboxManager.gypsylikeVolucrine
											.println(query);
									Tracer.tracepointMessage("Querying database.");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									boolean hasMoreResults = stmt
											.execute(query);
									String rtnString;
									while (hasMoreResults) {
										java.sql.ResultSet rs = stmt
												.getResultSet();
										if (rs != null) {
											java.sql.ResultSetMetaData metaData = null;
											int columns = 0;
											while (rs.next()) {
												metaData = rs.getMetaData();
												columns = metaData
														.getColumnCount();
												for (int i = 1; i < columns + 1; i++) {
													rtnString = rs.getString(i);
													XmlSourceInboxManager.gypsylikeVolucrine
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
									Tracer.tracepointError(nse.getClass()
											.getName()
											+ ": "
											+ nse.getMessage());
									XmlSourceInboxManager.gypsylikeVolucrine
											.println("STONESOUP: Error accessing database.");
									nse.printStackTrace(XmlSourceInboxManager.gypsylikeVolucrine);
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									XmlSourceInboxManager.gypsylikeVolucrine
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(XmlSourceInboxManager.gypsylikeVolucrine);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									XmlSourceInboxManager.gypsylikeVolucrine
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(XmlSourceInboxManager.gypsylikeVolucrine);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						XmlSourceInboxManager.gypsylikeVolucrine.close();
						if (recompact_asbolite != null)
							recompact_asbolite.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
