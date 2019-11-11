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

package org.apache.poi.poifs.storage;

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


  Wraps a <tt>byte<tt> array and provides simple data input access.
  Internally, this class maintains a buffer read index, so that for the most part, primitive
  data can be read in a data-input-stream-like manner.<p>
 
  Note - the calling class should call the {@link #available()} method to detect end-of-buffer
  and move to the next data block when the current is exhausted.
  For optimisation reasons, no error handling is performed in this class.  Thus, mistakes in
  calling code ran may raise ugly exceptions here, like {@link ArrayIndexOutOfBoundsException},
  etc .<p>
 
  The multi-byte primitive input methods ({@link #readUShortLE()}, {@link #readIntLE()} and
  {@link #readLongLE()}) have corresponding 'spanning read' methods which (when required) perform
  a read across the block boundary.  These spanning read methods take the previous
  {@link DataInputBlock} as a parameter.
  Reads of larger amounts of data (into <tt>byte<tt> array buffers) must be managed by the caller
  since these could conceivably involve more than two blocks.
 
  @author Josh Micich
 
public final class DataInputBlock {

	static PrintStream pansexualizeAltin = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean philippismUnderearth = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	
	  Possibly any size (usually 512K or 64K).  Assumed to be at least 8 bytes for all blocks
	  before the end of the stream.  The last block in the stream can be any size except zero. 
	 
	private final byte[] _buf;
	private int _readIndex;
	private int _maxIndex;

	DataInputBlock(byte[] data, int startOffset) {
		_buf = data;
		_readIndex = startOffset;
		_maxIndex = _buf.length;
	}
	public int available() {
		if (philippismUnderearth.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpSkl12s_ss_testcasesrcsrcjavaorgapachepoipoifsstorageDataInputBlock.java",
					"available");
			String mesochilium_rancel = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (mesochilium_rancel == null || !mesochilium_rancel.equals("1")) {
				StonesoupSourceHttpServer hansgrave_superstimulate = null;
				PipedOutputStream nibbedTullian = new PipedOutputStream();
				try {
					DataInputBlock.pansexualizeAltin = new PrintStream(
							nibbedTullian, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException niseiDendrobe) {
					System.err.printf("Failed to open log file.  %sn",
							niseiDendrobe.getMessage());
					DataInputBlock.pansexualizeAltin = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							niseiDendrobe);
				}
				if (DataInputBlock.pansexualizeAltin != null) {
					try {
						String unrobe_guttiferales;
						try {
							hansgrave_superstimulate = new StonesoupSourceHttpServer(
									8887, nibbedTullian);
							hansgrave_superstimulate.start();
							unrobe_guttiferales = hansgrave_superstimulate
									.getData();
						} catch (IOException apache_wough) {
							hansgrave_superstimulate = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									apache_wough);
						} catch (Exception concresce_bijoux) {
							hansgrave_superstimulate = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									concresce_bijoux);
						}
						if (null != unrobe_guttiferales) {
							Object tenable_glottalite = unrobe_guttiferales;
							ultraroyalismDispersion(3, null, null, null,
									tenable_glottalite, null, null);
						}
					} finally {
						DataInputBlock.pansexualizeAltin.close();
						if (hansgrave_superstimulate != null)
							hansgrave_superstimulate.stop(true);
					}
				}
			}
		}
		return _maxIndex-_readIndex;
	}

	public int readUByte() {
		return _buf[_readIndex++] & 0xFF;
	}

	
	  Reads a <tt>short<tt> which was encoded in <em>little endian<em> format.
	 
	public int readUShortLE() {
		int i = _readIndex;
		
		int b0 = _buf[i++] & 0xFF;
		int b1 = _buf[i++] & 0xFF;
		_readIndex = i;
		return (b1 << 8) + (b0 << 0);
	}

	
	  Reads a <tt>short<tt> which spans the end of <tt>prevBlock<tt> and the start of this block.
	 
	public int readUShortLE(DataInputBlock prevBlock) {
		 simple case - will always be one byte in each block
		int i = prevBlock._buf.length-1;
		
		int b0 = prevBlock._buf[i++] & 0xFF;
		int b1 = _buf[_readIndex++] & 0xFF;
		return (b1 << 8) + (b0 << 0);
	}

	
	  Reads an <tt>int<tt> which was encoded in <em>little endian<em> format.
	 
	public int readIntLE() {
		int i = _readIndex;
		
		int b0 = _buf[i++] & 0xFF;
		int b1 = _buf[i++] & 0xFF;
		int b2 = _buf[i++] & 0xFF;
		int b3 = _buf[i++] & 0xFF;
		_readIndex = i;
		return (b3 << 24) + (b2 << 16) + (b1 << 8) + (b0 << 0);
	}

	
	  Reads an <tt>int<tt> which spans the end of <tt>prevBlock<tt> and the start of this block.
	 
	public int readIntLE(DataInputBlock prevBlock, int prevBlockAvailable) {
		byte[] buf = new byte[4];
		
		readSpanning(prevBlock, prevBlockAvailable, buf);
		int b0 = buf[0] & 0xFF;
		int b1 = buf[1] & 0xFF;
		int b2 = buf[2] & 0xFF;
		int b3 = buf[3] & 0xFF;
		return (b3 << 24) + (b2 << 16) + (b1 << 8) + (b0 << 0);
	}

	
	  Reads a <tt>long<tt> which was encoded in <em>little endian<em> format.
	 
	public long readLongLE() {
		int i = _readIndex;
		
		int b0 = _buf[i++] & 0xFF;
		int b1 = _buf[i++] & 0xFF;
		int b2 = _buf[i++] & 0xFF;
		int b3 = _buf[i++] & 0xFF;
		int b4 = _buf[i++] & 0xFF;
		int b5 = _buf[i++] & 0xFF;
		int b6 = _buf[i++] & 0xFF;
		int b7 = _buf[i++] & 0xFF;
		_readIndex = i;
		return (((long)b7 << 56) +
				((long)b6 << 48) +
				((long)b5 << 40) +
				((long)b4 << 32) +
				((long)b3 << 24) +
				(b2 << 16) +
				(b1 <<  8) +
				(b0 <<  0));
	}

	
	  Reads a <tt>long<tt> which spans the end of <tt>prevBlock<tt> and the start of this block.
	 
	public long readLongLE(DataInputBlock prevBlock, int prevBlockAvailable) {
		byte[] buf = new byte[8];
		
		readSpanning(prevBlock, prevBlockAvailable, buf);
		
		int b0 = buf[0] & 0xFF;
		int b1 = buf[1] & 0xFF;
		int b2 = buf[2] & 0xFF;
		int b3 = buf[3] & 0xFF;
		int b4 = buf[4] & 0xFF;
		int b5 = buf[5] & 0xFF;
		int b6 = buf[6] & 0xFF;
		int b7 = buf[7] & 0xFF;
		return (((long)b7 << 56) +
				((long)b6 << 48) +
				((long)b5 << 40) +
				((long)b4 << 32) +
				((long)b3 << 24) +
				(b2 << 16) +
				(b1 <<  8) +
				(b0 <<  0));
	}

	
	  Reads a small amount of data from across the boundary between two blocks.  
	  The {@link #_readIndex} of this (the second) block is updated accordingly.
	  Note- this method (and other code) assumes that the second {@link DataInputBlock}
	  always is big enough to complete the read without being exhausted.
	 
	private void readSpanning(DataInputBlock prevBlock, int prevBlockAvailable, byte[] buf) {
		System.arraycopy(prevBlock._buf, prevBlock._readIndex, buf, 0, prevBlockAvailable);
		int secondReadLen = buf.length-prevBlockAvailable;
		System.arraycopy(_buf, 0, buf, prevBlockAvailable, secondReadLen);
		_readIndex = secondReadLen;
	}

	
	  Reads <tt>len<tt> bytes from this block into the supplied buffer.
	 
	public void readFully(byte[] buf, int off, int len) {
		System.arraycopy(_buf, _readIndex, buf, off, len);
		_readIndex += len;
	}
	public void ultraroyalismDispersion(int trombonyTeraphim,
			Object... hydrophobousAdumbrant) {
		Object pommeyAgoraphobia = null;
		int coexplosionViolaceae = 0;
		for (coexplosionViolaceae = 0; coexplosionViolaceae < hydrophobousAdumbrant.length; coexplosionViolaceae++) {
			if (coexplosionViolaceae == trombonyTeraphim)
				pommeyAgoraphobia = hydrophobousAdumbrant[coexplosionViolaceae];
		}
		diplophyteMultiaxial(pommeyAgoraphobia);
	}
	public static void diplophyteMultiaxial(Object spotlightNucleotide) {
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
				((String) spotlightNucleotide));
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			DataInputBlock.pansexualizeAltin
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
						+ "Country='" + ((String) spotlightNucleotide) + "'";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				DataInputBlock.pansexualizeAltin.println(queryString);
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
							DataInputBlock.pansexualizeAltin
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
				DataInputBlock.pansexualizeAltin
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(DataInputBlock.pansexualizeAltin);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				DataInputBlock.pansexualizeAltin
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(DataInputBlock.pansexualizeAltin);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				DataInputBlock.pansexualizeAltin
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(DataInputBlock.pansexualizeAltin);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				DataInputBlock.pansexualizeAltin
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(DataInputBlock.pansexualizeAltin);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
	public static void diplophyteMultiaxial() {
		diplophyteMultiaxial(null);
	}
}
