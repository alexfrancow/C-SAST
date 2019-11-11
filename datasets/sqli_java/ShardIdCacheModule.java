
  Licensed to Elasticsearch under one or more contributor
  license agreements. See the NOTICE file distributed with
  this work for additional information regarding copyright
  ownership. Elasticsearch licenses this file to you under
  the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License.
  You may obtain a copy of the License at
 
     http:www.apache.orglicensesLICENSE-2.0
 
  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
 

package org.elasticsearch.index.cache.id;

 org.elasticsearch.common.inject.AbstractModule;
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


 
public class ShardIdCacheModule extends AbstractModule {

    static PrintStream xipeZoophilitic = null;

	public void thalassographerSatrapical(int unenvious_solidungulate,
			final String quarrelsomely_outfielder) {
		if (unenvious_solidungulate > 10) {
			thalassographerSatrapical(unenvious_solidungulate++,
					quarrelsomely_outfielder);
		}
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"A",
				"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
		String stonesoup_mysql_host = System.getenv("DBMYSQLHOST");
		String stonesoup_mysql_user = System.getenv("DBMYSQLUSER");
		String stonesoup_mysql_pass = System.getenv("DBMYSQLPASSWORD");
		String stonesoup_mysql_port = System.getenv("DBMYSQLPORT");
		String stonesoup_mysql_dbname = System.getenv("SS_DBMYSQLDATABASE");
		Tracer.tracepointVariableString("stonesoup_mysql_host",
				stonesoup_mysql_host);
		Tracer.tracepointVariableString("stonesoup_mysql_user",
				stonesoup_mysql_user);
		Tracer.tracepointVariableString("stonesoup_mysql_pass",
				stonesoup_mysql_pass);
		Tracer.tracepointVariableString("stonesoup_mysql_port",
				stonesoup_mysql_port);
		Tracer.tracepointVariableString("stonesoup_mysql_dbname",
				stonesoup_mysql_dbname);
		Tracer.tracepointVariableString("country_name",
				quarrelsomely_outfielder);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			ShardIdCacheModule.xipeZoophilitic
					.println("STONESOUP: Missing required database connection parameter(s).");
		} else {
			try {
				StringBuffer jdbc = new StringBuffer("jdbc:mysql:");
				jdbc.append(stonesoup_mysql_host);
				jdbc.append(":");
				jdbc.append(stonesoup_mysql_port);
				jdbc.append("");
				jdbc.append(stonesoup_mysql_dbname);
				jdbc.append("?allowMultiQueries=true");
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Tracer.tracepointMessage("Establishing connection to database.");
				java.sql.Connection con = java.sql.DriverManager.getConnection(
						jdbc.toString(), stonesoup_mysql_user,
						stonesoup_mysql_pass);
				java.sql.Statement stmt = con.createStatement();
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String queryString = "SELECT  FROM Customers WHERE "
						+ "Country='" + quarrelsomely_outfielder + "'";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				ShardIdCacheModule.xipeZoophilitic.println(queryString);
				java.sql.ResultSet resultSet = null;
				java.sql.ResultSetMetaData metaData = null;
				int columnCount = 0;
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				boolean hasMoreResults = stmt.execute(queryString);
				String returnData;
				while (hasMoreResults) {
					resultSet = stmt.getResultSet();
					while (resultSet.next()) {
						metaData = resultSet.getMetaData();
						columnCount = metaData.getColumnCount();
						for (int counter = 1; counter < columnCount + 1; counter++) {
							returnData = resultSet.getString(counter);
							ShardIdCacheModule.xipeZoophilitic
									.println(returnData);
						}
					}
					hasMoreResults = stmt.getMoreResults();
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				con.close();
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				ShardIdCacheModule.xipeZoophilitic
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(ShardIdCacheModule.xipeZoophilitic);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				ShardIdCacheModule.xipeZoophilitic
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(ShardIdCacheModule.xipeZoophilitic);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				ShardIdCacheModule.xipeZoophilitic
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(ShardIdCacheModule.xipeZoophilitic);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				ShardIdCacheModule.xipeZoophilitic
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(ShardIdCacheModule.xipeZoophilitic);
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

	private static final java.util.concurrent.atomic.AtomicBoolean oilletHelver = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	@Override
    protected void configure() {
        if (oilletHelver.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpjARvfd_ss_testcasesrcsrcmainjavaorgelasticsearchindexcacheidShardIdCacheModule.java",
					"configure");
			String simioid_wuzu = System.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (simioid_wuzu == null || !simioid_wuzu.equals("1")) {
				StonesoupSourceHttpServer noded_resultlessness = null;
				PipedOutputStream cognoscibilityForetalk = new PipedOutputStream();
				try {
					ShardIdCacheModule.xipeZoophilitic = new PrintStream(
							cognoscibilityForetalk, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException prosiphonBipeltate) {
					System.err.printf("Failed to open log file.  %sn",
							prosiphonBipeltate.getMessage());
					ShardIdCacheModule.xipeZoophilitic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							prosiphonBipeltate);
				}
				if (ShardIdCacheModule.xipeZoophilitic != null) {
					try {
						final String tane_pigless;
						try {
							noded_resultlessness = new StonesoupSourceHttpServer(
									8887, cognoscibilityForetalk);
							noded_resultlessness.start();
							tane_pigless = noded_resultlessness.getData();
						} catch (IOException undermatched_counterchange) {
							noded_resultlessness = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									undermatched_counterchange);
						} catch (Exception somatologist_arctic) {
							noded_resultlessness = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									somatologist_arctic);
						}
						if (null != tane_pigless) {
							int sulphobenzide_gutter = 0;
							thalassographerSatrapical(sulphobenzide_gutter,
									tane_pigless);
						}
					} finally {
						ShardIdCacheModule.xipeZoophilitic.close();
						if (noded_resultlessness != null)
							noded_resultlessness.stop(true);
					}
				}
			}
		}
		bind(ShardIdCache.class).asEagerSingleton();
    }
}
