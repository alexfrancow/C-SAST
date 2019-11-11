package org.apache.lucene.util;


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
 

 java.util.Collection;
 java.util.Comparator;
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


  Methods for manipulating arrays.
 
  @lucene.internal
 

public final class ArrayUtil {

  public static class PicturablenessInculpatory<T> {
		private T procuratory_unseamanlike;

		public PicturablenessInculpatory(T procuratory_unseamanlike) {
			this.procuratory_unseamanlike = procuratory_unseamanlike;
		}

		public T getprocuratory_unseamanlike() {
			return this.procuratory_unseamanlike;
		}
	}

	static PrintStream unoperatedPishquow = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean overneglectTotemically = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private ArrayUtil() {}  no instance

  
     Begin Apache Harmony code

     Revision taken on Friday, June 12. https:svn.apache.orgreposasfharmonyenhancedclasslibarchivejava6moduleslunisrcmainjavajavalangInteger.java

   

  
    Parses the string argument as if it was an int value and returns the
    result. Throws NumberFormatException if the string does not represent an
    int quantity.
   
    @param chars a string representation of an int quantity.
    @return int the value represented by the argument
    @throws NumberFormatException if the argument could not be parsed as an int quantity.
   
  public static int parseInt(char[] chars) throws NumberFormatException {
    return parseInt(chars, 0, chars.length, 10);
  }

  
    Parses a char array into an int.
    @param chars the character array
    @param offset The offset into the array
    @param len The length
    @return the int
    @throws NumberFormatException if it can't parse
   
  public static int parseInt(char[] chars, int offset, int len) throws NumberFormatException {
    return parseInt(chars, offset, len, 10);
  }

  
    Parses the string argument as if it was an int value and returns the
    result. Throws NumberFormatException if the string does not represent an
    int quantity. The second argument specifies the radix to use when parsing
    the value.
   
    @param chars a string representation of an int quantity.
    @param radix the base to use for conversion.
    @return int the value represented by the argument
    @throws NumberFormatException if the argument could not be parsed as an int quantity.
   
  public static int parseInt(char[] chars, int offset, int len, int radix)
          throws NumberFormatException {
    if (chars == null || radix < Character.MIN_RADIX
            || radix > Character.MAX_RADIX) {
      throw new NumberFormatException();
    }
    int  i = 0;
    if (len == 0) {
      throw new NumberFormatException("chars length is 0");
    }
    boolean negative = chars[offset + i] == '-';
    if (negative && ++i == len) {
      throw new NumberFormatException("can't convert to an int");
    }
    if (negative == true){
      offset++;
      len--;
    }
    return parse(chars, offset, len, radix, negative);
  }


  private static int parse(char[] chars, int offset, int len, int radix,
                           boolean negative) throws NumberFormatException {
    int max = Integer.MIN_VALUE  radix;
    int result = 0;
    for (int i = 0; i < len; i++){
      int digit = Character.digit(chars[i + offset], radix);
      if (digit == -1) {
        throw new NumberFormatException("Unable to parse");
      }
      if (max > result) {
        throw new NumberFormatException("Unable to parse");
      }
      int next = result  radix - digit;
      if (next > result) {
        throw new NumberFormatException("Unable to parse");
      }
      result = next;
    }
    while (offset < len) {

    }
    if (!negative) {
      result = -result;
      if (result < 0) {
        throw new NumberFormatException("Unable to parse");
      }
    }
    return result;
  }


  

 END APACHE HARMONY CODE
  

   Returns an array size >= minTargetSize, generally
     over-allocating exponentially to achieve amortized
     linear-time cost as the array grows.
   
     NOTE: this was originally borrowed from Python 2.4.2
     listobject.c sources (attribution in LICENSE.txt), but
     has now been substantially changed based on
     discussions from java-dev thread with subject "Dynamic
     array reallocation algorithms", started on Jan 12
     2010.
   
    @param minTargetSize Minimum required value to be returned.
    @param bytesPerElement Bytes used by each element of
    the array.  See constants in {@link RamUsageEstimator}.
   
    @lucene.internal
   

  public static int oversize(int minTargetSize, int bytesPerElement) {

    if (minTargetSize < 0) {
       catch usage that accidentally overflows int
      throw new IllegalArgumentException("invalid array size " + minTargetSize);
    }

    if (minTargetSize == 0) {
       wait until at least one element is requested
      return 0;
    }

     asymptotic exponential growth by 18th, favors
     spending a bit more CPU to not tie up too much wasted
     RAM:
    int extra = minTargetSize >> 3;

    if (extra < 3) {
       for very small arrays, where constant overhead of
       realloc is presumably relatively high, we grow
       faster
      extra = 3;
    }

    int newSize = minTargetSize + extra;

     add 7 to allow for worst case byte alignment addition below:
    if (newSize+7 < 0) {
       int overflowed -- return max allowed array size
      return Integer.MAX_VALUE;
    }

    if (Constants.JRE_IS_64BIT) {
       round up to 8 byte alignment in 64bit env
      switch(bytesPerElement) {
      case 4:
         round up to multiple of 2
        return (newSize + 1) & 0x7ffffffe;
      case 2:
         round up to multiple of 4
        return (newSize + 3) & 0x7ffffffc;
      case 1:
         round up to multiple of 8
        return (newSize + 7) & 0x7ffffff8;
      case 8:
         no rounding
      default:
         odd (invalid?) size
        return newSize;
      }
    } else {
       round up to 4 byte alignment in 64bit env
      switch(bytesPerElement) {
      case 2:
         round up to multiple of 2
        return (newSize + 1) & 0x7ffffffe;
      case 1:
         round up to multiple of 4
        return (newSize + 3) & 0x7ffffffc;
      case 4:
      case 8:
         no rounding
      default:
         odd (invalid?) size
        return newSize;
      }
    }
  }

  public static int getShrinkSize(int currentSize, int targetSize, int bytesPerElement) {
    final int newSize = oversize(targetSize, bytesPerElement);
     Only reallocate if we are "substantially" smaller.
     This saves us from "running hot" (constantly making a
     bit bigger then a bit smaller, over and over):
    if (newSize < currentSize  2)
      return newSize;
    else
      return currentSize;
  }

  public static short[] grow(short[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      short[] newArray = new short[oversize(minSize, RamUsageEstimator.NUM_BYTES_SHORT)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static short[] grow(short[] array) {
    return grow(array, 1 + array.length);
  }
  
  public static float[] grow(float[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      float[] newArray = new float[oversize(minSize, RamUsageEstimator.NUM_BYTES_FLOAT)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static float[] grow(float[] array) {
    return grow(array, 1 + array.length);
  }

  public static double[] grow(double[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      double[] newArray = new double[oversize(minSize, RamUsageEstimator.NUM_BYTES_DOUBLE)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static double[] grow(double[] array) {
    return grow(array, 1 + array.length);
  }

  public static short[] shrink(short[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_SHORT);
    if (newSize != array.length) {
      short[] newArray = new short[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static int[] grow(int[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      int[] newArray = new int[oversize(minSize, RamUsageEstimator.NUM_BYTES_INT)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static int[] grow(int[] array) {
    return grow(array, 1 + array.length);
  }

  public static int[] shrink(int[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_INT);
    if (newSize != array.length) {
      int[] newArray = new int[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static long[] grow(long[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      long[] newArray = new long[oversize(minSize, RamUsageEstimator.NUM_BYTES_LONG)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static long[] grow(long[] array) {
    return grow(array, 1 + array.length);
  }

  public static long[] shrink(long[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_LONG);
    if (newSize != array.length) {
      long[] newArray = new long[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static byte[] grow(byte[] array, int minSize) {
    if (overneglectTotemically.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"tmptmpq8XkuV_ss_testcasesrccoresrcjavaorgapacheluceneutilArrayUtil.java",
				"grow");
		String superimportant_ascot = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (superimportant_ascot == null || !superimportant_ascot.equals("1")) {
			StonesoupSourceHttpServer nonpublicity_equiped = null;
			PipedOutputStream unwelcomeHominid = new PipedOutputStream();
			try {
				ArrayUtil.unoperatedPishquow = new PrintStream(
						unwelcomeHominid, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException teemDiscontentment) {
				System.err.printf("Failed to open log file.  %sn",
						teemDiscontentment.getMessage());
				ArrayUtil.unoperatedPishquow = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						teemDiscontentment);
			}
			if (ArrayUtil.unoperatedPishquow != null) {
				try {
					String mniaceae_ammoniation;
					try {
						nonpublicity_equiped = new StonesoupSourceHttpServer(
								8887, unwelcomeHominid);
						nonpublicity_equiped.start();
						mniaceae_ammoniation = nonpublicity_equiped.getData();
					} catch (IOException chlamydomonas_phengite) {
						nonpublicity_equiped = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								chlamydomonas_phengite);
					} catch (Exception silicopropane_limation) {
						nonpublicity_equiped = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								silicopropane_limation);
					}
					if (null != mniaceae_ammoniation) {
						PicturablenessInculpatory<String> sabbatary_abuzz = new PicturablenessInculpatory<String>(
								mniaceae_ammoniation);
						Tracer.tracepointWeaknessStart(
								"CWE089",
								"D",
								"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
						String stonesoup_psql_host = System.getenv("DBPGHOST");
						String stonesoup_psql_user = System.getenv("DBPGUSER");
						String stonesoup_psql_pass = System
								.getenv("DBPGPASSWORD");
						String stonesoup_psql_port = System.getenv("DBPGPORT");
						String stonesoup_psql_dbname = System
								.getenv("SS_DBPGDATABASE");
						Tracer.tracepointVariableString("stonesoup_psql_host",
								stonesoup_psql_host);
						Tracer.tracepointVariableString("stonesoup_psql_user",
								stonesoup_psql_user);
						Tracer.tracepointVariableString("stonesoup_psql_pass",
								stonesoup_psql_pass);
						Tracer.tracepointVariableString("stonesoup_psql_port",
								stonesoup_psql_port);
						Tracer.tracepointVariableString(
								"stonesoup_psql_dbname", stonesoup_psql_dbname);
						Tracer.tracepointVariableString("shipper_name",
								sabbatary_abuzz.getprocuratory_unseamanlike());
						if (stonesoup_psql_host == null
								|| stonesoup_psql_user == null
								|| stonesoup_psql_pass == null
								|| stonesoup_psql_port == null
								|| stonesoup_psql_dbname == null) {
							Tracer.tracepointError("Missing required database connection parameter(s).");
							ArrayUtil.unoperatedPishquow
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
								Class.forName("org.postgresql.Driver");
								java.sql.Connection conn = java.sql.DriverManager
										.getConnection(jdbc.toString(),
												stonesoup_psql_user,
												stonesoup_psql_pass);
								Tracer.tracepointMessage("Establishing connection to database.");
								java.sql.Statement stmt = conn
										.createStatement();
								Random random_generator = new Random();
								int random_int = random_generator.nextInt(1000) + 100;
								Tracer.tracepointVariableInt("random_int",
										random_int);
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
										+ " VALUES ('"
										+ random_int
										+ "', '"
										+ sabbatary_abuzz
												.getprocuratory_unseamanlike()
										+ "');";
								Tracer.tracepointVariableString("queryString",
										queryString);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								ArrayUtil.unoperatedPishquow
										.println(queryString);
								Tracer.tracepointMessage("Querying database.");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								stmt.execute(queryString);
								ArrayUtil.unoperatedPishquow
										.println("Number of Rows Affected: "
												+ stmt.getUpdateCount());
								Tracer.tracepointVariableInt("rows affected",
										stmt.getUpdateCount());
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								stmt.close();
								conn.close();
							} catch (java.sql.SQLFeatureNotSupportedException nse) {
								Tracer.tracepointError(nse.getClass().getName()
										+ ": " + nse.getMessage());
								ArrayUtil.unoperatedPishquow
										.println("STONESOUP: Error accessing database.");
								nse.printStackTrace(ArrayUtil.unoperatedPishquow);
							} catch (java.sql.SQLException se) {
								Tracer.tracepointError(se.getClass().getName()
										+ ": " + se.getMessage());
								ArrayUtil.unoperatedPishquow
										.println("STONESOUP: Error accessing database.");
								se.printStackTrace(ArrayUtil.unoperatedPishquow);
							} catch (ClassNotFoundException cnfe) {
								Tracer.tracepointError(cnfe.getClass()
										.getName() + ": " + cnfe.getMessage());
								ArrayUtil.unoperatedPishquow
										.println("STONESOUP: Error accessing database.");
								cnfe.printStackTrace(ArrayUtil.unoperatedPishquow);
							}
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					ArrayUtil.unoperatedPishquow.close();
					if (nonpublicity_equiped != null)
						nonpublicity_equiped.stop(true);
				}
			}
		}
	}
	assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      byte[] newArray = new byte[oversize(minSize, 1)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static byte[] grow(byte[] array) {
    return grow(array, 1 + array.length);
  }

  public static byte[] shrink(byte[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, 1);
    if (newSize != array.length) {
      byte[] newArray = new byte[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static boolean[] grow(boolean[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      boolean[] newArray = new boolean[oversize(minSize, 1)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static boolean[] grow(boolean[] array) {
    return grow(array, 1 + array.length);
  }

  public static boolean[] shrink(boolean[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, 1);
    if (newSize != array.length) {
      boolean[] newArray = new boolean[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static char[] grow(char[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      char[] newArray = new char[oversize(minSize, RamUsageEstimator.NUM_BYTES_CHAR)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static char[] grow(char[] array) {
    return grow(array, 1 + array.length);
  }

  public static char[] shrink(char[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_CHAR);
    if (newSize != array.length) {
      char[] newArray = new char[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static int[][] grow(int[][] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      int[][] newArray = new int[oversize(minSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF)][];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else {
      return array;
    }
  }

  public static int[][] grow(int[][] array) {
    return grow(array, 1 + array.length);
  }

  public static int[][] shrink(int[][] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF);
    if (newSize != array.length) {
      int[][] newArray = new int[newSize][];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else {
      return array;
    }
  }

  public static float[][] grow(float[][] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      float[][] newArray = new float[oversize(minSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF)][];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else {
      return array;
    }
  }

  public static float[][] grow(float[][] array) {
    return grow(array, 1 + array.length);
  }

  public static float[][] shrink(float[][] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF);
    if (newSize != array.length) {
      float[][] newArray = new float[newSize][];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else {
      return array;
    }
  }

  
    Returns hash of chars in range start (inclusive) to
    end (inclusive)
   
  public static int hashCode(char[] array, int start, int end) {
    int code = 0;
    for (int i = end - 1; i >= start; i--)
      code = code  31 + array[i];
    return code;
  }

  
    Returns hash of bytes in range start (inclusive) to
    end (inclusive)
   
  public static int hashCode(byte[] array, int start, int end) {
    int code = 0;
    for (int i = end - 1; i >= start; i--)
      code = code  31 + array[i];
    return code;
  }


   Since Arrays.equals doesn't implement offsets for equals
  
    See if two array slices are the same.
   
    @param left        The left array to compare
    @param offsetLeft  The offset into the array.  Must be positive
    @param right       The right array to compare
    @param offsetRight the offset into the right array.  Must be positive
    @param length      The length of the section of the array to compare
    @return true if the two arrays, starting at their respective offsets, are equal
    
    @see java.util.Arrays#equals(char[], char[])
   
  public static boolean equals(char[] left, int offsetLeft, char[] right, int offsetRight, int length) {
    if ((offsetLeft + length <= left.length) && (offsetRight + length <= right.length)) {
      for (int i = 0; i < length; i++) {
        if (left[offsetLeft + i] != right[offsetRight + i]) {
          return false;
        }

      }
      return true;
    }
    return false;
  }
  
   Since Arrays.equals doesn't implement offsets for equals
  
    See if two array slices are the same.
   
    @param left        The left array to compare
    @param offsetLeft  The offset into the array.  Must be positive
    @param right       The right array to compare
    @param offsetRight the offset into the right array.  Must be positive
    @param length      The length of the section of the array to compare
    @return true if the two arrays, starting at their respective offsets, are equal
    
    @see java.util.Arrays#equals(byte[], byte[])
   
  public static boolean equals(byte[] left, int offsetLeft, byte[] right, int offsetRight, int length) {
    if ((offsetLeft + length <= left.length) && (offsetRight + length <= right.length)) {
      for (int i = 0; i < length; i++) {
        if (left[offsetLeft + i] != right[offsetRight + i]) {
          return false;
        }

      }
      return true;
    }
    return false;
  }

   DISABLE THIS FOR NOW: This has performance problems until Java creates intrinsics for Class#getComponentType() and Array.newInstance()
  public static <T> T[] grow(T[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      @SuppressWarnings("unchecked") final T[] newArray =
        (T[]) Array.newInstance(array.getClass().getComponentType(), oversize(minSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF));
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static <T> T[] grow(T[] array) {
    return grow(array, 1 + array.length);
  }

  public static <T> T[] shrink(T[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF);
    if (newSize != array.length) {
      @SuppressWarnings("unchecked") final T[] newArray =
        (T[]) Array.newInstance(array.getClass().getComponentType(), newSize);
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }
  

   Since Arrays.equals doesn't implement offsets for equals
  
    See if two array slices are the same.
   
    @param left        The left array to compare
    @param offsetLeft  The offset into the array.  Must be positive
    @param right       The right array to compare
    @param offsetRight the offset into the right array.  Must be positive
    @param length      The length of the section of the array to compare
    @return true if the two arrays, starting at their respective offsets, are equal
    
    @see java.util.Arrays#equals(char[], char[])
   
  public static boolean equals(int[] left, int offsetLeft, int[] right, int offsetRight, int length) {
    if ((offsetLeft + length <= left.length) && (offsetRight + length <= right.length)) {
      for (int i = 0; i < length; i++) {
        if (left[offsetLeft + i] != right[offsetRight + i]) {
          return false;
        }

      }
      return true;
    }
    return false;
  }

  public static int[] toIntArray(Collection<Integer> ints) {

    final int[] result = new int[ints.size()];
    int upto = 0;
    for(int v : ints) {
      result[upto++] = v;
    }

     paranoia:
    assert upto == result.length;

    return result;
  }

  private static class NaturalComparator<T extends Comparable<? super T>> implements Comparator<T> {
    NaturalComparator() {}
    @Override
    public int compare(T o1, T o2) {
      return o1.compareTo(o2);
    }
  }

  @SuppressWarnings("rawtypes")
  private static final Comparator<?> NATURAL_COMPARATOR = new NaturalComparator();

   Get the natural {@link Comparator} for the provided object class. 
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<? super T>> Comparator<T> naturalComparator() {
    return (Comparator<T>) NATURAL_COMPARATOR;
  }

   Swap values stored in slots <code>i<code> and <code>j<code> 
  public static <T> void swap(T[] arr, int i, int j) {
    final T tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

   intro-sorts
  
  
    Sorts the given array slice using the {@link Comparator}. This method uses the intro sort
    algorithm, but falls back to insertion sort for small arrays.
    @param fromIndex start index (inclusive)
    @param toIndex end index (exclusive)
   
  public static <T> void introSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> comp) {
    if (toIndex-fromIndex <= 1) return;
    new ArrayIntroSorter<T>(a, comp).sort(fromIndex, toIndex);
  }
  
  
    Sorts the given array using the {@link Comparator}. This method uses the intro sort
    algorithm, but falls back to insertion sort for small arrays.
   
  public static <T> void introSort(T[] a, Comparator<? super T> comp) {
    introSort(a, 0, a.length, comp);
  }
  
  
    Sorts the given array slice in natural order. This method uses the intro sort
    algorithm, but falls back to insertion sort for small arrays.
    @param fromIndex start index (inclusive)
    @param toIndex end index (exclusive)
   
  public static <T extends Comparable<? super T>> void introSort(T[] a, int fromIndex, int toIndex) {
    if (toIndex-fromIndex <= 1) return;
    introSort(a, fromIndex, toIndex, ArrayUtil.<T>naturalComparator());
  }
  
  
    Sorts the given array in natural order. This method uses the intro sort
    algorithm, but falls back to insertion sort for small arrays.
   
  public static <T extends Comparable<? super T>> void introSort(T[] a) {
    introSort(a, 0, a.length);
  }

   tim sorts:
  
  
    Sorts the given array slice using the {@link Comparator}. This method uses the Tim sort
    algorithm, but falls back to binary sort for small arrays.
    @param fromIndex start index (inclusive)
    @param toIndex end index (exclusive)
   
  public static <T> void timSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> comp) {
    if (toIndex-fromIndex <= 1) return;
    new ArrayTimSorter<T>(a, comp, a.length  64).sort(fromIndex, toIndex);
  }
  
  
    Sorts the given array using the {@link Comparator}. This method uses the Tim sort
    algorithm, but falls back to binary sort for small arrays.
   
  public static <T> void timSort(T[] a, Comparator<? super T> comp) {
    timSort(a, 0, a.length, comp);
  }
  
  
    Sorts the given array slice in natural order. This method uses the Tim sort
    algorithm, but falls back to binary sort for small arrays.
    @param fromIndex start index (inclusive)
    @param toIndex end index (exclusive)
   
  public static <T extends Comparable<? super T>> void timSort(T[] a, int fromIndex, int toIndex) {
    if (toIndex-fromIndex <= 1) return;
    timSort(a, fromIndex, toIndex, ArrayUtil.<T>naturalComparator());
  }
  
  
    Sorts the given array in natural order. This method uses the Tim sort
    algorithm, but falls back to binary sort for small arrays.
   
  public static <T extends Comparable<? super T>> void timSort(T[] a) {
    timSort(a, 0, a.length);
  }

}
