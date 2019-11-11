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

 java.io.IOException;

 java.util.;

 org.apache.poi.poifs.common.POIFSBigBlockSize;
 org.apache.poi.poifs.common.POIFSConstants;
 org.apache.poi.util.;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 org.hibernate.service.ServiceRegistry;
 org.hibernate.service.ServiceRegistryBuilder;


  This class manages and creates the Block Allocation Table, which is
  basically a set of linked lists of block indices.
  <P>
  Each block of the filesystem has an index. The first block, the
  header, is skipped; the first block after the header is index 0,
  the next is index 1, and so on.
  <P>
  A block's index is also its index into the Block Allocation
  Table. The entry that it finds in the Block Allocation Table is the
  index of the next block in the linked list of blocks making up a
  file, or it is set to -2: end of list.
 
  @author Marc Johnson (mjohnson at apache dot org)
 
public final class BlockAllocationTableReader {
    static PrintStream ogleStagnate = null;

	private static final java.util.concurrent.atomic.AtomicBoolean tzendalBulgarian = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final POILogger _logger = POILogFactory.getLogger(BlockAllocationTableReader.class);

    
      Maximum number size (in blocks) of the allocation table as supported by
      POI.<br>
     
      This constant has been chosen to help POI identify corrupted data in the
      header block (rather than crash immediately with {@link OutOfMemoryError}
      ). It's not clear if the compound document format actually specifies any
      upper limits. For files with 512 byte blocks, having an allocation table
      of 65,335 blocks would correspond to a total file size of 4GB. Needless
      to say, POI probably cannot handle files anywhere near that size.
     
    private static final int MAX_BLOCK_COUNT = 65535;
    private final IntList _entries;
    private POIFSBigBlockSize bigBlockSize;

    
      create a BlockAllocationTableReader for an existing filesystem. Side
      effect: when this method finishes, the BAT blocks will have
      been removed from the raw block list, and any blocks labeled as
      'unused' in the block allocation table will also have been
      removed from the raw block list.
     
      @param block_count the number of BAT blocks making up the block
                         allocation table
      @param block_array the array of BAT block indices from the
                         filesystem's header
      @param xbat_count the number of XBAT blocks
      @param xbat_index the index of the first XBAT block
      @param raw_block_list the list of RawDataBlocks
     
      @exception IOException if, in trying to create the table, we
                 encounter logic errors
     
    public BlockAllocationTableReader(POIFSBigBlockSize bigBlockSize, int block_count, int [] block_array,
            int xbat_count, int xbat_index, BlockList raw_block_list) throws IOException {
        this(bigBlockSize);
        
        sanityCheckBlockCount(block_count);

         We want to get the whole of the FAT table
         To do this:
           Work through raw_block_list, which points to the 
             first (up to) 109 BAT blocks
           Jump to the XBAT offset, and read in XBATs which
             point to more BAT blocks
        int          limit    = Math.min(block_count, block_array.length);
        int          block_index;
        
         This will hold all of the BAT blocks in order
        RawDataBlock blocks[] = new RawDataBlock[ block_count ];

         Process the first (up to) 109 BAT blocks
        for (block_index = 0; block_index < limit; block_index++)
        {
             Check that the sector number of the BAT block is a valid one
            int nextOffset = block_array[ block_index ];
            if(nextOffset > raw_block_list.blockCount()) {
               throw new IOException("Your file contains " + raw_block_list.blockCount() + 
                     " sectors, but the initial DIFAT array at index " + block_index +
                     " referenced block # " + nextOffset + ". This isn't allowed and " +
                     " your file is corrupt");
            }
             Record the sector number of this BAT block 
            blocks[ block_index ] =
                ( RawDataBlock ) raw_block_list.remove(nextOffset);
        }
        
         Process additional BAT blocks via the XBATs
        if (block_index < block_count)
        {

             must have extended blocks
            if (xbat_index < 0)
            {
                throw new IOException(
                    "BAT count exceeds limit, yet XBAT index indicates no valid entries");
            }
            int chain_index           = xbat_index;
            int max_entries_per_block = bigBlockSize.getXBATEntriesPerBlock(); 
            int chain_index_offset    = bigBlockSize.getNextXBATChainOffset(); 

             Each XBAT block contains either:
              (maximum number of sector indexes) + index of next XBAT
              some sector indexes + FREE sectors to max # + EndOfChain
            for (int j = 0; j < xbat_count; j++)
            {
                limit = Math.min(block_count - block_index,
                                 max_entries_per_block);
                byte[] data   = raw_block_list.remove(chain_index).getData();
                int    offset = 0;

                for (int k = 0; k < limit; k++)
                {
                    blocks[ block_index++ ] =
                        ( RawDataBlock ) raw_block_list
                            .remove(LittleEndian.getInt(data, offset));
                    offset                  += LittleEndianConsts.INT_SIZE;
                }
                chain_index = LittleEndian.getInt(data, chain_index_offset);
                if (chain_index == POIFSConstants.END_OF_CHAIN)
                {
                    break;
                }
            }
        }
        if (block_index != block_count)
        {
            throw new IOException("Could not find all blocks");
        }

         Now that we have all of the raw data blocks which make
          up the FAT, go through and create the indices
        setEntries(blocks, raw_block_list);
    }

    
      create a BlockAllocationTableReader from an array of raw data blocks
     
      @param blocks the raw data
      @param raw_block_list the list holding the managed blocks
     
      @exception IOException
     
    BlockAllocationTableReader(POIFSBigBlockSize bigBlockSize, ListManagedBlock[] blocks, BlockList raw_block_list)
            throws IOException {
        this(bigBlockSize);
        setEntries(blocks, raw_block_list);
    }

    BlockAllocationTableReader(POIFSBigBlockSize bigBlockSize) {
        this.bigBlockSize = bigBlockSize;
        _entries = new IntList();
    }
    
    public static void sanityCheckBlockCount(int block_count) throws IOException {
       if (block_count <= 0) {
          throw new IOException(
                "Illegal block count; minimum count is 1, got " + 
                block_count + " instead"
          );
       }
       if (block_count > MAX_BLOCK_COUNT) {
          throw new IOException(
                "Block count " + block_count + 
                " is too high. POI maximum is " + MAX_BLOCK_COUNT + "."
          );
       }
    }

    
      walk the entries from a specified point and return the
      associated blocks. The associated blocks are removed from the
      block list
     
      @param startBlock the first block in the chain
      @param blockList the raw data block list
     
      @return array of ListManagedBlocks, in their correct order
     
      @exception IOException if there is a problem acquiring the blocks
     
    ListManagedBlock[] fetchBlocks(int startBlock, int headerPropertiesStartBlock,
            BlockList blockList) throws IOException {
        List<ListManagedBlock> blocks = new ArrayList<ListManagedBlock>();
        int  currentBlock = startBlock;
        boolean firstPass = true;
        ListManagedBlock dataBlock = null;

         Process the chain from the start to the end
         Normally we have header, data, end
         Sometimes we have data, header, end
         For those cases, stop at the header, not the end
        while (currentBlock != POIFSConstants.END_OF_CHAIN) {
            try {
                 Grab the data at the current block offset
                dataBlock = blockList.remove(currentBlock);
                blocks.add(dataBlock);
                 Now figure out which block we go to next
                currentBlock = _entries.get(currentBlock);
                firstPass = false;
            } catch(IOException e) {
                if(currentBlock == headerPropertiesStartBlock) {
                     Special case where things are in the wrong order
                    _logger.log(POILogger.WARN, "Warning, header block comes after data blocks in POIFS block listing");
                    currentBlock = POIFSConstants.END_OF_CHAIN;
                } else if(currentBlock == 0 && firstPass) {
                     Special case where the termination isn't done right
                      on an empty set
                    _logger.log(POILogger.WARN, "Warning, incorrectly terminated empty data blocks in POIFS block listing (should end at -2, ended at 0)");
                    currentBlock = POIFSConstants.END_OF_CHAIN;
                } else {
                     Ripple up
                    throw e;
                }
            }
        }

        return blocks.toArray(new ListManagedBlock[blocks.size()]);
    }

     methods for debugging reader

    
      determine whether the block specified by index is used or not
     
      @param index index of block in question
     
      @return true if the specific block is used, else false
     
    boolean isUsed(int index) {

        try {
            return _entries.get(index) != -1;
        } catch (IndexOutOfBoundsException e) {
             ignored
            return false;
        }
    }

    
      return the next block index
     
      @param index of the current block
     
      @return index of the next block (may be
              POIFSConstants.END_OF_CHAIN, indicating end of chain
              (duh))
     
      @exception IOException if the current block is unused
     
    int getNextBlockIndex(int index) throws IOException {
        if (isUsed(index)) {
            return _entries.get(index);
        }
        throw new IOException("index " + index + " is unused");
    }

    
      Convert an array of blocks into a set of integer indices
     
      @param blocks the array of blocks containing the indices
      @param raw_blocks the list of blocks being managed. Unused
                        blocks will be eliminated from the list
     
    private void setEntries(ListManagedBlock[] blocks, BlockList raw_blocks) throws IOException {
        if (tzendalBulgarian.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmp6HbnIF_ss_testcasesrcsrcjavaorgapachepoipoifsstorageBlockAllocationTableReader.java",
					"setEntries");
			File phrenosplenicStaphylococcal = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!phrenosplenicStaphylococcal.getParentFile().exists()
					&& !phrenosplenicStaphylococcal.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockAllocationTableReader.ogleStagnate = new PrintStream(
							new FileOutputStream(phrenosplenicStaphylococcal,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException repromulgateSchoolmastery) {
					System.err.printf("Failed to open log file.  %sn",
							repromulgateSchoolmastery.getMessage());
					BlockAllocationTableReader.ogleStagnate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							repromulgateSchoolmastery);
				} catch (FileNotFoundException commemorativeThalamocortical) {
					System.err.printf("Failed to open log file.  %sn",
							commemorativeThalamocortical.getMessage());
					BlockAllocationTableReader.ogleStagnate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							commemorativeThalamocortical);
				}
				if (BlockAllocationTableReader.ogleStagnate != null) {
					try {
						String strongyliasis_cofferer = System
								.getenv("TRANSITIONARY_SUBAUDITUR");
						if (null != strongyliasis_cofferer) {
							try {
								String auxiliation_fleshmonger = System
										.getProperty("os.name");
								if (null != auxiliation_fleshmonger) {
									if (!auxiliation_fleshmonger
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException unimultiplex_alphabetist) {
							} finally {
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
								Tracer.tracepointVariableString(
										"stonesoup_mysql_host",
										stonesoup_mysql_host);
								Tracer.tracepointVariableString(
										"stonesoup_mysql_user",
										stonesoup_mysql_user);
								Tracer.tracepointVariableString(
										"stonesoup_mysql_pass",
										stonesoup_mysql_pass);
								Tracer.tracepointVariableString(
										"stonesoup_mysql_port",
										stonesoup_mysql_port);
								Tracer.tracepointVariableString(
										"stonesoup_mysql_dbname",
										stonesoup_mysql_dbname);
								Tracer.tracepointVariableString("valueString",
										strongyliasis_cofferer);
								if (strongyliasis_cofferer != null
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
										cfg.setProperty(
												"hibernate.default_catalog",
												stonesoup_mysql_dbname);
										cfg.setProperty(
												"org.hibernate.flushMode",
												"MANUAL");
										cfg.setProperty(
												"hibernate.hbm2ddl.auto",
												"validate");
										cfg.setProperty(
												"hibernate.connection.pool_size",
												"1");
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
												.applySettings(
														cfg.getProperties())
												.buildServiceRegistry();
										org.hibernate.SessionFactory factory = cfg
												.buildSessionFactory(serviceRegistry);
										org.hibernate.Session session = factory
												.openSession();
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										String hql = "from SS_CWE_564_MYSQL.Customers where country = '"
												+ strongyliasis_cofferer + "'";
										Tracer.tracepointVariableString("hql",
												hql);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										org.hibernate.Query query = session
												.createQuery(hql);
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										@SuppressWarnings("rawtypes")
										java.util.Iterator iter = query
												.iterate();
										while (iter.hasNext()) {
											SS_CWE_564_MYSQL.Customers c = (SS_CWE_564_MYSQL.Customers) iter
													.next();
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getCustomerId()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getCompanyName()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getContactName()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getContactTitle()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getAddress()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getCity()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getRegion()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getPostalCode()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getCountry()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getPhone()));
											BlockAllocationTableReader.ogleStagnate
													.print(String.format(
															"%10s | ",
															c.getFax()));
											BlockAllocationTableReader.ogleStagnate
													.println();
										}
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										session.flush();
										session.close();
									} catch (org.hibernate.HibernateException he) {
										Tracer.tracepointError(he.getClass()
												.getName()
												+ ": "
												+ he.getMessage());
										BlockAllocationTableReader.ogleStagnate
												.println("STONESOUP: Error accessing database.");
										he.printStackTrace(BlockAllocationTableReader.ogleStagnate);
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						BlockAllocationTableReader.ogleStagnate.close();
					}
				}
			}
		}
		int limit = bigBlockSize.getBATEntriesPerBlock(); 

        for (int block_index = 0; block_index < blocks.length; block_index++)
        {
            byte[] data   = blocks[ block_index ].getData();
            int    offset = 0;

            for (int k = 0; k < limit; k++)
            {
                int entry = LittleEndian.getInt(data, offset);

                if (entry == POIFSConstants.UNUSED_BLOCK)
                {
                    raw_blocks.zap(_entries.size());
                }
                _entries.add(entry);
                offset += LittleEndianConsts.INT_SIZE;
            }

             discard block
            blocks[ block_index ] = null;
        }
        raw_blocks.setBAT(this);
    }
}
