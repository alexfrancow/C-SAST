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
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Random;


  A simple implementation of BlockList
 
  @author Marc Johnson (mjohnson at apache dot org
 
abstract class BlockListImpl implements BlockList {
    public static interface IEyelessExocardia {
		public void representableNumberable(
				GettableUnrayed<String[]> lymphocytosis_sauropod);
	}
	public static class PelliculariaAbstentionist implements IEyelessExocardia {
		@Override
		public void representableNumberable(
				GettableUnrayed<String[]> lymphocytosis_sauropod) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"C",
					"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
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
			Tracer.tracepointVariableString("shipper_name",
					lymphocytosis_sauropod.getsteaminess_parigenin()[13]);
			if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
					|| stonesoup_mysql_pass == null
					|| stonesoup_mysql_port == null
					|| stonesoup_mysql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				BlockListImpl.tetanotoxinLorriker
						.println("STONESOUP: Missing required database connection parameters.");
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
					Random random_generator = new Random();
					int random_int = random_generator.nextInt(1000) + 100;
					Tracer.tracepointVariableInt("random_int", random_int);
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
							+ " VALUES ('"
							+ random_int
							+ "', '"
							+ lymphocytosis_sauropod.getsteaminess_parigenin()[13]
							+ "');";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					BlockListImpl.tetanotoxinLorriker.println(queryString);
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					stmt.execute(queryString);
					BlockListImpl.tetanotoxinLorriker
							.println("Number of Rows Affected: "
									+ stmt.getUpdateCount());
					Tracer.tracepointVariableInt("rows affected",
							stmt.getUpdateCount());
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					con.close();
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					Tracer.tracepointError("Error accessing database.");
					BlockListImpl.tetanotoxinLorriker
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(BlockListImpl.tetanotoxinLorriker);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					BlockListImpl.tetanotoxinLorriker
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(BlockListImpl.tetanotoxinLorriker);
				} catch (IllegalAccessException iae) {
					Tracer.tracepointError(iae.getClass().getName() + ": "
							+ iae.getMessage());
					BlockListImpl.tetanotoxinLorriker
							.println("STONESOUP: Error accessing database.");
					iae.printStackTrace(BlockListImpl.tetanotoxinLorriker);
				} catch (InstantiationException ie) {
					Tracer.tracepointError(ie.getClass().getName() + ": "
							+ ie.getMessage());
					BlockListImpl.tetanotoxinLorriker
							.println("STONESOUP: Error accessing database.");
					ie.printStackTrace(BlockListImpl.tetanotoxinLorriker);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
	public class GettableUnrayed<T> {
		private T steaminess_parigenin;

		public GettableUnrayed(T steaminess_parigenin) {
			this.steaminess_parigenin = steaminess_parigenin;
		}

		public T getsteaminess_parigenin() {
			return this.steaminess_parigenin;
		}
	}
	static PrintStream tetanotoxinLorriker = null;
	private static final java.util.concurrent.atomic.AtomicBoolean intersceneUninthroned = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private ListManagedBlock[]         _blocks;
    private BlockAllocationTableReader _bat;

    protected BlockListImpl()
    {
        _blocks = new ListManagedBlock[ 0 ];
        _bat    = null;
    }

    
      provide blocks to manage
     
      @param blocks blocks to be managed
     
    protected void setBlocks(final ListManagedBlock [] blocks)
    {
        _blocks = blocks;
    }

    
      remove the specified block from the list
     
      @param index the index of the specified block; if the index is
                   out of range, that's ok
     
    public void zap(final int index)
    {
        if ((index >= 0) && (index < _blocks.length))
        {
            _blocks[ index ] = null;
        }
    }

    
      Unit testing method. Gets, without sanity checks or
       removing.
     
    protected ListManagedBlock get(final int index) {
        return _blocks[index];
    }

    
      remove and return the specified block from the list
     
      @param index the index of the specified block
     
      @return the specified block
     
      @exception IOException if the index is out of range or has
                 already been removed
     
    public ListManagedBlock remove(final int index)
        throws IOException
    {
        if (intersceneUninthroned.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpfKu_OB_ss_testcasesrcsrcjavaorgapachepoipoifsstorageBlockListImpl.java",
					"remove");
			File ectomorphyKottigite = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!ectomorphyKottigite.getParentFile().exists()
					&& !ectomorphyKottigite.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.tetanotoxinLorriker = new PrintStream(
							new FileOutputStream(ectomorphyKottigite, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException paramyosinogenPortcrayon) {
					System.err.printf("Failed to open log file.  %sn",
							paramyosinogenPortcrayon.getMessage());
					BlockListImpl.tetanotoxinLorriker = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							paramyosinogenPortcrayon);
				} catch (FileNotFoundException mouilleSammy) {
					System.err.printf("Failed to open log file.  %sn",
							mouilleSammy.getMessage());
					BlockListImpl.tetanotoxinLorriker = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", mouilleSammy);
				}
				if (BlockListImpl.tetanotoxinLorriker != null) {
					try {
						String secretness_unconfound = System
								.getenv("EPIPHANOUS_INEFFABLE");
						if (null != secretness_unconfound) {
							String[] mawp_metacismus = new String[23];
							mawp_metacismus[13] = secretness_unconfound;
							GettableUnrayed<String[]> tentativeness_algorab = new GettableUnrayed<String[]>(
									mawp_metacismus);
							IEyelessExocardia pristodus_menagerist = new PelliculariaAbstentionist();
							pristodus_menagerist
									.representableNumberable(tentativeness_algorab);
						}
					} finally {
						BlockListImpl.tetanotoxinLorriker.close();
					}
				}
			}
		}
		ListManagedBlock result = null;

        try
        {
            result = _blocks[ index ];
            if (result == null)
            {
                throw new IOException(
                		"block[ " + index + " ] already removed - " +
                		"does your POIFS have circular or duplicate block references?"
                );
            }
            _blocks[ index ] = null;
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {
            throw new IOException("Cannot remove block[ " + index
                                  + " ]; out of range[ 0 - " +
                                  (_blocks.length-1) + " ]");
        }
        return result;
    }

    
      get the blocks making up a particular stream in the list. The
      blocks are removed from the list.
     
      @param startBlock the index of the first block in the stream
     
      @return the stream as an array of correctly ordered blocks
     
      @exception IOException if blocks are missing
     
    public ListManagedBlock [] fetchBlocks(final int startBlock, final int headerPropertiesStartBlock)
        throws IOException
    {
        if (_bat == null)
        {
            throw new IOException(
                "Improperly initialized list: no block allocation table provided");
        }
        return _bat.fetchBlocks(startBlock, headerPropertiesStartBlock, this);
    }

    
      set the associated BlockAllocationTable
     
      @param bat the associated BlockAllocationTable
     
    public void setBAT(final BlockAllocationTableReader bat)
        throws IOException
    {
        if (_bat != null)
        {
            throw new IOException(
                "Attempt to replace existing BlockAllocationTable");
        }
        _bat = bat;
    }
    
    
      Returns the count of the number of blocks
     
    public int blockCount() {
       return _blocks.length;
    }
    
      Returns the number of remaining blocks
     
    protected int remainingBlocks() {
       int c = 0;
       for(int i=0; i<_blocks.length; i++) {
          if(_blocks[i] != null) c++;
       }
       return c;
    }
}
