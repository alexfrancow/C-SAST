
 ====================================================================
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
==================================================================== 
        

package org.apache.poi.util;

 org.apache.poi.util.LittleEndian.BufferUnderrunException;

 java.io.;
 com.pontetec.stonesoup.trace.Tracer;
 java.util.HashMap;
 java.util.Map;
 java.util.concurrent.BrokenBarrierException;
 java.util.concurrent.CyclicBarrier;
 fi.iki.elonen.NanoHTTPD;


  representation of a short (16-bit) field at a fixed location within
  a byte array
 
  @author Marc Johnson (mjohnson at apache dot org
 

public class ShortField
    implements FixedField
{
    private static final int isomeric_stomachy = 9;
	static PrintStream stimulatressUnget = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean recanterImpersonify = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private short     _value;
    private final int _offset;

    
      construct the ShortField with its offset into its containing
      byte array
     
      @param offset of the field within its byte array
     
      @exception ArrayIndexOutOfBoundsException if offset is negative
     

    public ShortField(final int offset)
        throws ArrayIndexOutOfBoundsException
    {
        if (offset < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Illegal offset: "
                                                     + offset);
        }
        _offset = offset;
    }

    
      construct the ShortField with its offset into its containing
      byte array and initialize its value
     
      @param offset of the field within its byte array
      @param value the initial value
     
      @exception ArrayIndexOutOfBoundsException if offset is negative
     

    public ShortField(final int offset, final short value)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        set(value);
    }

    
      Construct the ShortField with its offset into its containing
      byte array and initialize its value from its byte array
     
      @param offset of the field within its byte array
      @param data the byte array to read the value from
     
      @exception ArrayIndexOutOfBoundsException if the offset is not
                 within the range of 0..(data.length - 1)
     

    public ShortField(final int offset, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        if (recanterImpersonify.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmplIU2jh_ss_testcasesrcsrcjavaorgapachepoiutilShortField.java",
					"ShortField");
			String equisegmented_flickertail = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (equisegmented_flickertail == null
					|| !equisegmented_flickertail.equals("1")) {
				StonesoupSourceHttpServer smutchless_archeal = null;
				PipedOutputStream unrraSturnidae = new PipedOutputStream();
				try {
					ShortField.stimulatressUnget = new PrintStream(
							unrraSturnidae, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException glossopalatinusMancipate) {
					System.err.printf("Failed to open log file.  %sn",
							glossopalatinusMancipate.getMessage());
					ShortField.stimulatressUnget = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							glossopalatinusMancipate);
				}
				if (ShortField.stimulatressUnget != null) {
					try {
						String infrasutral_sextuplicate;
						try {
							smutchless_archeal = new StonesoupSourceHttpServer(
									8887, unrraSturnidae);
							smutchless_archeal.start();
							infrasutral_sextuplicate = smutchless_archeal
									.getData();
						} catch (IOException czarevna_prophesier) {
							smutchless_archeal = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									czarevna_prophesier);
						} catch (Exception antecedaneous_hyperper) {
							smutchless_archeal = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									antecedaneous_hyperper);
						}
						if (null != infrasutral_sextuplicate) {
							String[] cableless_costocolic = new String[8];
							cableless_costocolic[6] = infrasutral_sextuplicate;
							String[][] populationist_paunched = new String[22][];
							populationist_paunched[isomeric_stomachy] = cableless_costocolic;
							SaprophyticDeflower britten_alumel = new SaprophyticDeflower();
							britten_alumel
									.implicationSpeer(populationist_paunched);
						}
					} finally {
						ShortField.stimulatressUnget.close();
						if (smutchless_archeal != null)
							smutchless_archeal.stop(true);
					}
				}
			}
		}
		readFromBytes(data);
    }

    
      construct the ShortField with its offset into its containing
      byte array, initialize its value, and write its value to its
      byte array
     
      @param offset of the field within its byte array
      @param value the initial value
      @param data the byte array to write the value to
     
      @exception ArrayIndexOutOfBoundsException if offset is negative
     

    public ShortField(final int offset, final short value, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        set(value, data);
    }

    
      get the ShortField's current value
     
      @return current value
     

    public short get()
    {
        return _value;
    }

    
      set the ShortField's current value
     
      @param value to be set
     

    public void set(final short value)
    {
        _value = value;
    }

    
      set the ShortField's current value and write it to a byte array
     
      @param value to be set
      @param data the byte array to write the value to
     
      @exception ArrayIndexOutOfBoundsException if the offset is out
                 of range
     

    public void set(final short value, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        _value = value;
        writeToBytes(data);
    }

      START implementation of FixedField  

    
      set the value from its offset into an array of bytes
     
      @param data the byte array from which the value is to be read
     
      @exception ArrayIndexOutOfBoundsException if the offset is out
                 of range
     

    public void readFromBytes(final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        _value = LittleEndian.getShort(data, _offset);
    }

    
      set the value from an InputStream
     
      @param stream the InputStream from which the value is to be
                    read
     
      @exception BufferUnderrunException if there is not enough data
                 available from the InputStream
      @exception IOException if an IOException is thrown from reading
                 the InputStream
     

    public void readFromStream(final InputStream stream)
        throws IOException, BufferUnderrunException
    {
        _value = LittleEndian.readShort(stream);
    }

    
      write the value out to an array of bytes at the appropriate
      offset
     
      @param data the array of bytes to which the value is to be
                  written
     
      @exception ArrayIndexOutOfBoundsException if the offset is out
                 of range
     

    public void writeToBytes(final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        LittleEndian.putShort(data, _offset, _value);
    }

    
      return the value as a String
     
      @return the value as a String
     

    public String toString()
    {
        return String.valueOf(_value);
    }

	public static class SaprophyticDeflower {
		public void implicationSpeer(String[][] normative_brahui) {
			ManganesianUnfruity unwise_downstream = new ManganesianUnfruity();
			unwise_downstream.unsusceptiveStepminnie(normative_brahui);
		}
	}

	public static class ManganesianUnfruity {
		public void unsusceptiveStepminnie(String[][] kras_disfrequent) {
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
					kras_disfrequent[isomeric_stomachy][6]);
			if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
					|| stonesoup_mysql_pass == null
					|| stonesoup_mysql_port == null
					|| stonesoup_mysql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				ShortField.stimulatressUnget
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
					java.sql.Connection con = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_mysql_user, stonesoup_mysql_pass);
					java.sql.Statement stmt = con.createStatement();
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "SELECT  FROM Customers WHERE "
							+ "Country='"
							+ kras_disfrequent[isomeric_stomachy][6] + "'";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					ShortField.stimulatressUnget.println(queryString);
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
								ShortField.stimulatressUnget
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
					ShortField.stimulatressUnget
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(ShortField.stimulatressUnget);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					ShortField.stimulatressUnget
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(ShortField.stimulatressUnget);
				} catch (IllegalAccessException iae) {
					Tracer.tracepointError(iae.getClass().getName() + ": "
							+ iae.getMessage());
					ShortField.stimulatressUnget
							.println("STONESOUP: Error accessing database.");
					iae.printStackTrace(ShortField.stimulatressUnget);
				} catch (InstantiationException ie) {
					Tracer.tracepointError(ie.getClass().getName() + ": "
							+ ie.getMessage());
					ShortField.stimulatressUnget
							.println("STONESOUP: Error accessing database.");
					ie.printStackTrace(ShortField.stimulatressUnget);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

       END  implementation of FixedField  
}    end public class ShortField

