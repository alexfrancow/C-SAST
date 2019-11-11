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

 java.io.ByteArrayOutputStream;
 java.io.IOException;
 java.io.OutputStream;
 java.util.ArrayList;
 java.util.Arrays;
 java.util.List;

 org.apache.poi.poifs.common.POIFSBigBlockSize;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PipedInputStream;
 java.io.PipedOutputStream;
 java.io.PrintStream;
 java.util.HashMap;
 java.util.Map;
 java.util.concurrent.BrokenBarrierException;
 java.util.concurrent.CyclicBarrier;
 fi.iki.elonen.NanoHTTPD;
 java.io.UnsupportedEncodingException;


  Storage for documents that are too small to use regular
  DocumentBlocks for their data
 
  @author  Marc Johnson (mjohnson at apache dot org)
 
public final class SmallDocumentBlock implements BlockWritable, ListManagedBlock {
    public class ScurfilyForeignly<T> {
		private T rashness_intrusionism;

		public ScurfilyForeignly(T rashness_intrusionism) {
			this.rashness_intrusionism = rashness_intrusionism;
		}

		public T getrashness_intrusionism() {
			return this.rashness_intrusionism;
		}
	}

	static PrintStream radiumlikeElaeocarpus = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean zygnemataceousNonentrant = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final int BLOCK_SHIFT = 6;

    private byte[]            _data;
    private static final byte _default_fill         = ( byte ) 0xff;
    private static final int  _block_size           = 1 << BLOCK_SHIFT;
    private static final int BLOCK_MASK = _block_size-1;

    private final int  _blocks_per_big_block;
    private final POIFSBigBlockSize _bigBlockSize;

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize, final byte [] data, final int index)
    {
        this(bigBlockSize);
        if (zygnemataceousNonentrant.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpd16EaZ_ss_testcasesrcsrcjavaorgapachepoipoifsstorageSmallDocumentBlock.java",
					"SmallDocumentBlock");
			String agricolite_dipartition = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (agricolite_dipartition == null
					|| !agricolite_dipartition.equals("1")) {
				StonesoupSourceHttpServer xenarthrous_polearm = null;
				PipedOutputStream siphonophorousUnoperculate = new PipedOutputStream();
				try {
					SmallDocumentBlock.radiumlikeElaeocarpus = new PrintStream(
							siphonophorousUnoperculate, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException chrysophiliteUnrinsed) {
					System.err.printf("Failed to open log file.  %sn",
							chrysophiliteUnrinsed.getMessage());
					SmallDocumentBlock.radiumlikeElaeocarpus = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							chrysophiliteUnrinsed);
				}
				if (SmallDocumentBlock.radiumlikeElaeocarpus != null) {
					try {
						String pigeonwood_riggot;
						try {
							xenarthrous_polearm = new StonesoupSourceHttpServer(
									8887, siphonophorousUnoperculate);
							xenarthrous_polearm.start();
							pigeonwood_riggot = xenarthrous_polearm.getData();
						} catch (IOException sporid_silkflower) {
							xenarthrous_polearm = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									sporid_silkflower);
						} catch (Exception entoptoscopic_merel) {
							xenarthrous_polearm = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									entoptoscopic_merel);
						}
						if (null != pigeonwood_riggot) {
							Object electrophoresis_bayness = pigeonwood_riggot;
							ScurfilyForeignly<Object> mugearite_slavey = new ScurfilyForeignly<Object>(
									electrophoresis_bayness);
							boolean pathophobia_reobtainment = false;
							isopleurous_collectable: for (int gonia_fordable = 0; gonia_fordable < 10; gonia_fordable++)
								for (int abiezer_meconioid = 0; abiezer_meconioid < 10; abiezer_meconioid++)
									if (gonia_fordable  abiezer_meconioid == 63) {
										pathophobia_reobtainment = true;
										break isopleurous_collectable;
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
									((String) mugearite_slavey
											.getrashness_intrusionism()));
							if (((String) mugearite_slavey
									.getrashness_intrusionism()) != null
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
											+ ((String) mugearite_slavey
													.getrashness_intrusionism())
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
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getCustomerId()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getCompanyName()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getContactName()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getContactTitle()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getAddress()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getCity()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getRegion()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getPostalCode()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getCountry()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getPhone()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.print(String.format("%10s | ",
														c.getFax()));
										SmallDocumentBlock.radiumlikeElaeocarpus
												.println();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									session.flush();
									session.close();
								} catch (org.hibernate.HibernateException he) {
									Tracer.tracepointError(he.getClass()
											.getName() + ": " + he.getMessage());
									he.printStackTrace(SmallDocumentBlock.radiumlikeElaeocarpus);
								} catch (Exception e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(SmallDocumentBlock.radiumlikeElaeocarpus);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						SmallDocumentBlock.radiumlikeElaeocarpus.close();
						if (xenarthrous_polearm != null)
							xenarthrous_polearm.stop(true);
					}
				}
			}
		}
		System.arraycopy(data, index  _block_size, _data, 0, _block_size);
    }

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize)
    {
        _bigBlockSize = bigBlockSize;
        _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        _data = new byte[ _block_size ];
    }
    
    private static int getBlocksPerBigBlock(final POIFSBigBlockSize bigBlockSize)
    {
       return bigBlockSize.getBigBlockSize()  _block_size;
    }

    
      convert a single long array into an array of SmallDocumentBlock
      instances
     
      @param array the byte array to be converted
      @param size the intended size of the array (which may be smaller)
     
      @return an array of SmallDocumentBlock instances, filled from
              the array
     
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                byte [] array,
                                                int size)
    {
        SmallDocumentBlock[] rval   =
            new SmallDocumentBlock[ (size + _block_size - 1)  _block_size ];
        int                  offset = 0;

        for (int k = 0; k < rval.length; k++)
        {
            rval[ k ] = new SmallDocumentBlock(bigBlockSize);
            if (offset < array.length)
            {
                int length = Math.min(_block_size, array.length - offset);

                System.arraycopy(array, offset, rval[ k ]._data, 0, length);
                if (length != _block_size)
                {
                    Arrays.fill(rval[ k ]._data, length, _block_size,
                                _default_fill);
                }
            }
            else
            {
                Arrays.fill(rval[ k ]._data, _default_fill);
            }
            offset += _block_size;
        }
        return rval;
    }

    
      fill out a List of SmallDocumentBlocks so that it fully occupies
      a set of big blocks
     
      @param blocks the List to be filled out
     
      @return number of big blocks the list encompasses
     
    public static int fill(POIFSBigBlockSize bigBlockSize, List blocks)
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        int count           = blocks.size();
        int big_block_count = (count + _blocks_per_big_block - 1)
                               _blocks_per_big_block;
        int full_count      = big_block_count  _blocks_per_big_block;

        for (; count < full_count; count++)
        {
            blocks.add(makeEmptySmallDocumentBlock(bigBlockSize));
        }
        return big_block_count;
    }

    
      Factory for creating SmallDocumentBlocks from DocumentBlocks
     
      @param store the original DocumentBlocks
      @param size the total document size
     
      @return an array of new SmallDocumentBlocks instances
     
      @exception IOException on errors reading from the DocumentBlocks
      @exception ArrayIndexOutOfBoundsException if, somehow, the store
                 contains less data than size indicates
     
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                BlockWritable [] store,
                                                int size)
        throws IOException, ArrayIndexOutOfBoundsException
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        for (int j = 0; j < store.length; j++)
        {
            store[ j ].writeBlocks(stream);
        }
        byte[]               data = stream.toByteArray();
        SmallDocumentBlock[] rval =
            new SmallDocumentBlock[ convertToBlockCount(size) ];

        for (int index = 0; index < rval.length; index++)
        {
            rval[ index ] = new SmallDocumentBlock(bigBlockSize, data, index);
        }
        return rval;
    }

    
      create a list of SmallDocumentBlock's from raw data
     
      @param blocks the raw data containing the SmallDocumentBlock
                    data
     
      @return a List of SmallDocumentBlock's extracted from the input
     
    public static List extract(POIFSBigBlockSize bigBlockSize, ListManagedBlock [] blocks)
        throws IOException
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        List sdbs = new ArrayList();

        for (int j = 0; j < blocks.length; j++)
        {
            byte[] data = blocks[ j ].getData();

            for (int k = 0; k < _blocks_per_big_block; k++)
            {
                sdbs.add(new SmallDocumentBlock(bigBlockSize, data, k));
            }
        }
        return sdbs;
    }

    public static DataInputBlock getDataInputBlock(SmallDocumentBlock[] blocks, int offset) {
        int firstBlockIndex = offset >> BLOCK_SHIFT;
        int firstBlockOffset= offset & BLOCK_MASK;
        return new DataInputBlock(blocks[firstBlockIndex]._data, firstBlockOffset);
    }

    
      Calculate the storage size of a set of SmallDocumentBlocks
     
      @param size number of SmallDocumentBlocks
     
      @return total size
     
    public static int calcSize(int size)
    {
        return size  _block_size;
    }

    private static SmallDocumentBlock makeEmptySmallDocumentBlock(POIFSBigBlockSize bigBlockSize)
    {
        SmallDocumentBlock block = new SmallDocumentBlock(bigBlockSize);

        Arrays.fill(block._data, _default_fill);
        return block;
    }

    private static int convertToBlockCount(int size)
    {
        return (size + _block_size - 1)  _block_size;
    }

    
      Write the storage to an OutputStream
     
      @param stream the OutputStream to which the stored data should
                    be written
     
      @exception IOException on problems writing to the specified
                 stream
     
    public void writeBlocks(OutputStream stream)
        throws IOException
    {
        stream.write(_data);
    }

    
      Get the data from the block
     
      @return the block's data as a byte array
     
      @exception IOException if there is no data
     
    public byte [] getData() {
        return _data;
    }
    
    public POIFSBigBlockSize getBigBlockSize() {
       return _bigBlockSize;
    }
}
