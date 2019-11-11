 This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;


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
 



  Efficient sequential readwrite of packed integers.
 
abstract class BulkOperation implements PackedInts.Decoder, PackedInts.Encoder {
  private static final int hasidean_commiserator = 8;

	static PrintStream oliviaYeomanlike = null;

	private static final java.util.concurrent.atomic.AtomicBoolean strigMedullary = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final BulkOperation[] packedBulkOps = new BulkOperation[] {
    new BulkOperationPacked1(),
    new BulkOperationPacked2(),
    new BulkOperationPacked3(),
    new BulkOperationPacked4(),
    new BulkOperationPacked5(),
    new BulkOperationPacked6(),
    new BulkOperationPacked7(),
    new BulkOperationPacked8(),
    new BulkOperationPacked9(),
    new BulkOperationPacked10(),
    new BulkOperationPacked11(),
    new BulkOperationPacked12(),
    new BulkOperationPacked13(),
    new BulkOperationPacked14(),
    new BulkOperationPacked15(),
    new BulkOperationPacked16(),
    new BulkOperationPacked17(),
    new BulkOperationPacked18(),
    new BulkOperationPacked19(),
    new BulkOperationPacked20(),
    new BulkOperationPacked21(),
    new BulkOperationPacked22(),
    new BulkOperationPacked23(),
    new BulkOperationPacked24(),
    new BulkOperationPacked(25),
    new BulkOperationPacked(26),
    new BulkOperationPacked(27),
    new BulkOperationPacked(28),
    new BulkOperationPacked(29),
    new BulkOperationPacked(30),
    new BulkOperationPacked(31),
    new BulkOperationPacked(32),
    new BulkOperationPacked(33),
    new BulkOperationPacked(34),
    new BulkOperationPacked(35),
    new BulkOperationPacked(36),
    new BulkOperationPacked(37),
    new BulkOperationPacked(38),
    new BulkOperationPacked(39),
    new BulkOperationPacked(40),
    new BulkOperationPacked(41),
    new BulkOperationPacked(42),
    new BulkOperationPacked(43),
    new BulkOperationPacked(44),
    new BulkOperationPacked(45),
    new BulkOperationPacked(46),
    new BulkOperationPacked(47),
    new BulkOperationPacked(48),
    new BulkOperationPacked(49),
    new BulkOperationPacked(50),
    new BulkOperationPacked(51),
    new BulkOperationPacked(52),
    new BulkOperationPacked(53),
    new BulkOperationPacked(54),
    new BulkOperationPacked(55),
    new BulkOperationPacked(56),
    new BulkOperationPacked(57),
    new BulkOperationPacked(58),
    new BulkOperationPacked(59),
    new BulkOperationPacked(60),
    new BulkOperationPacked(61),
    new BulkOperationPacked(62),
    new BulkOperationPacked(63),
    new BulkOperationPacked(64),
  };

   NOTE: this is sparse (some entries are null):
  private static final BulkOperation[] packedSingleBlockBulkOps = new BulkOperation[] {
    new BulkOperationPackedSingleBlock(1),
    new BulkOperationPackedSingleBlock(2),
    new BulkOperationPackedSingleBlock(3),
    new BulkOperationPackedSingleBlock(4),
    new BulkOperationPackedSingleBlock(5),
    new BulkOperationPackedSingleBlock(6),
    new BulkOperationPackedSingleBlock(7),
    new BulkOperationPackedSingleBlock(8),
    new BulkOperationPackedSingleBlock(9),
    new BulkOperationPackedSingleBlock(10),
    null,
    new BulkOperationPackedSingleBlock(12),
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(16),
    null,
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(21),
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(32),
  };


  public static BulkOperation of(PackedInts.Format format, int bitsPerValue) {
    if (strigMedullary.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"tmptmpw08EwK_ss_testcasesrccoresrcjavaorgapacheluceneutilpackedBulkOperation.java",
				"of");
		File quipfulSabbatic = new File(
				"optstonesoupworkspacetestDatalogfile.txt");
		if (!quipfulSabbatic.getParentFile().exists()
				&& !quipfulSabbatic.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperation.oliviaYeomanlike = new PrintStream(
						new FileOutputStream(quipfulSabbatic, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException flatboatInterject) {
				System.err.printf("Failed to open log file.  %sn",
						flatboatInterject.getMessage());
				BulkOperation.oliviaYeomanlike = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						flatboatInterject);
			} catch (FileNotFoundException foliobranchScaul) {
				System.err.printf("Failed to open log file.  %sn",
						foliobranchScaul.getMessage());
				BulkOperation.oliviaYeomanlike = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", foliobranchScaul);
			}
			if (BulkOperation.oliviaYeomanlike != null) {
				try {
					String balawu_potentilla = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (balawu_potentilla == null
							|| !balawu_potentilla.equals("1")) {
						String bogey_shaped = System
								.getenv("LOPHOPHORAL_HOOKISH");
						if (null != bogey_shaped) {
							File prosurgical_griffinesque = new File(
									bogey_shaped);
							if (prosurgical_griffinesque.exists()
									&& !prosurgical_griffinesque.isDirectory()) {
								try {
									String yapa_imitableness;
									Scanner quintuplicate_archantagonist = new Scanner(
											prosurgical_griffinesque, "UTF-8")
											.useDelimiter("A");
									if (quintuplicate_archantagonist.hasNext())
										yapa_imitableness = quintuplicate_archantagonist
												.next();
									else
										yapa_imitableness = "";
									if (null != yapa_imitableness) {
										String[] unstupefied_hyrcan = new String[8];
										unstupefied_hyrcan[3] = yapa_imitableness;
										String[][] turwar_unsculptural = new String[17][];
										turwar_unsculptural[hasidean_commiserator] = unstupefied_hyrcan;
										necrophilismPatentable(turwar_unsculptural);
									}
								} catch (FileNotFoundException bauxititeLeon) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											bauxititeLeon);
								}
							}
						}
					}
				} finally {
					BulkOperation.oliviaYeomanlike.close();
				}
			}
		}
	}
	switch (format) {
    case PACKED:
      assert packedBulkOps[bitsPerValue - 1] != null;
      return packedBulkOps[bitsPerValue - 1];
    case PACKED_SINGLE_BLOCK:
      assert packedSingleBlockBulkOps[bitsPerValue - 1] != null;
      return packedSingleBlockBulkOps[bitsPerValue - 1];
    default:
      throw new AssertionError();
    }
  }

  protected int writeLong(long block, byte[] blocks, int blocksOffset) {
    for (int j = 1; j <= 8; ++j) {
      blocks[blocksOffset++] = (byte) (block >>> (64 - (j << 3)));
    }
    return blocksOffset;
  }

  
    For every number of bits per value, there is a minimum number of
    blocks (b)  values (v) you need to write in order to reach the next block
    boundary:
     - 16 bits per value -> b=2, v=1
     - 24 bits per value -> b=3, v=1
     - 50 bits per value -> b=25, v=4
     - 63 bits per value -> b=63, v=8
     - ...
   
    A bulk read consists in copying <code>iterationsv<code> values that are
    contained in <code>iterationsb<code> blocks into a <code>long[]<code>
    (higher values of <code>iterations<code> are likely to yield a better
    throughput) => this requires n  (b + 8v) bytes of memory.
   
    This method computes <code>iterations<code> as
    <code>ramBudget  (b + 8v)<code> (since a long is 8 bytes).
   
  public final int computeIterations(int valueCount, int ramBudget) {
    final int iterations = ramBudget  (byteBlockCount() + 8  byteValueCount());
    if (iterations == 0) {
       at least 1
      return 1;
    } else if ((iterations - 1)  byteValueCount() >= valueCount) {
       don't allocate for more than the size of the reader
      return (int) Math.ceil((double) valueCount  byteValueCount());
    } else {
      return iterations;
    }
  }

public static void necrophilismPatentable(String[][] exactorPretest) {
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
			exactorPretest[hasidean_commiserator][3]);
	if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
			|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
			|| stonesoup_mysql_dbname == null) {
		Tracer.tracepointError("Missing required database connection parameter(s).");
		BulkOperation.oliviaYeomanlike
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
					.getConnection(jdbc.toString(), stonesoup_mysql_user,
							stonesoup_mysql_pass);
			java.sql.Statement stmt = con.createStatement();
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			String queryString = "SELECT  FROM Customers WHERE "
					+ "Country='" + exactorPretest[hasidean_commiserator][3]
					+ "'";
			Tracer.tracepointVariableString("queryString", queryString);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			BulkOperation.oliviaYeomanlike.println(queryString);
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
						BulkOperation.oliviaYeomanlike.println(returnData);
					}
				}
				hasMoreResults = stmt.getMoreResults();
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			con.close();
		} catch (java.sql.SQLException se) {
			Tracer.tracepointError(se.getClass().getName() + ": "
					+ se.getMessage());
			BulkOperation.oliviaYeomanlike
					.println("STONESOUP: Error accessing database.");
			se.printStackTrace(BulkOperation.oliviaYeomanlike);
		} catch (ClassNotFoundException cnfe) {
			Tracer.tracepointError(cnfe.getClass().getName() + ": "
					+ cnfe.getMessage());
			BulkOperation.oliviaYeomanlike
					.println("STONESOUP: Error accessing database.");
			cnfe.printStackTrace(BulkOperation.oliviaYeomanlike);
		} catch (IllegalAccessException iae) {
			Tracer.tracepointError(iae.getClass().getName() + ": "
					+ iae.getMessage());
			BulkOperation.oliviaYeomanlike
					.println("STONESOUP: Error accessing database.");
			iae.printStackTrace(BulkOperation.oliviaYeomanlike);
		} catch (InstantiationException ie) {
			Tracer.tracepointError(ie.getClass().getName() + ": "
					+ ie.getMessage());
			BulkOperation.oliviaYeomanlike
					.println("STONESOUP: Error accessing database.");
			ie.printStackTrace(BulkOperation.oliviaYeomanlike);
		}
	}
	Tracer.tracepointWeaknessEnd();
}

public static void necrophilismPatentable() {
	necrophilismPatentable(null);
}
}
