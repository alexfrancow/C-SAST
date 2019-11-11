 This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;


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
 
final class BulkOperationPacked2 extends BulkOperationPacked {

  public class WristbandClarain {
		private String[] phractamphibia_anchovy;

		public WristbandClarain(String[] phractamphibia_anchovy) {
			this.phractamphibia_anchovy = phractamphibia_anchovy;
		}

		public String[] getphractamphibia_anchovy() {
			return this.phractamphibia_anchovy;
		}
	}

	public void seaweedyImmechanical(int undemolishable_algesthesis,
			WristbandClarain nonreproduction_blowoff) {
		undemolishable_algesthesis--;
		if (undemolishable_algesthesis > 0) {
			birleOrthotonesis(undemolishable_algesthesis,
					nonreproduction_blowoff);
		}
	}

	public void birleOrthotonesis(int tailward_nilotic,
			WristbandClarain nonreproduction_blowoff) {
		seaweedyImmechanical(tailward_nilotic, nonreproduction_blowoff);
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
				nonreproduction_blowoff.getphractamphibia_anchovy()[15]);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			BulkOperationPacked2.euhemerismColeopterous
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
						+ "Country='"
						+ nonreproduction_blowoff.getphractamphibia_anchovy()[15]
						+ "'";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				BulkOperationPacked2.euhemerismColeopterous
						.println(queryString);
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
							BulkOperationPacked2.euhemerismColeopterous
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
				BulkOperationPacked2.euhemerismColeopterous
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(BulkOperationPacked2.euhemerismColeopterous);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				BulkOperationPacked2.euhemerismColeopterous
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(BulkOperationPacked2.euhemerismColeopterous);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				BulkOperationPacked2.euhemerismColeopterous
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(BulkOperationPacked2.euhemerismColeopterous);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				BulkOperationPacked2.euhemerismColeopterous
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(BulkOperationPacked2.euhemerismColeopterous);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream euhemerismColeopterous = null;
	private static final java.util.concurrent.atomic.AtomicBoolean koraBeeswing = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (koraBeeswing.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"tmptmp6piCVh_ss_testcasesrccoresrcjavaorgapacheluceneutilpackedBulkOperationPacked2.java",
				"BulkOperationPacked2");
		File shankpieceBiodyne = new File(
				"optstonesoupworkspacetestDatalogfile.txt");
		if (!shankpieceBiodyne.getParentFile().exists()
				&& !shankpieceBiodyne.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.euhemerismColeopterous = new PrintStream(
						new FileOutputStream(shankpieceBiodyne, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException dabbaGalleried) {
				System.err.printf("Failed to open log file.  %sn",
						dabbaGalleried.getMessage());
				BulkOperationPacked2.euhemerismColeopterous = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", dabbaGalleried);
			} catch (FileNotFoundException glossotheriumUnhandsomely) {
				System.err.printf("Failed to open log file.  %sn",
						glossotheriumUnhandsomely.getMessage());
				BulkOperationPacked2.euhemerismColeopterous = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						glossotheriumUnhandsomely);
			}
			if (BulkOperationPacked2.euhemerismColeopterous != null) {
				try {
					String tole_tintometer = System
							.getenv("SARCOSOMA_BLANDILOQUOUS");
					if (null != tole_tintometer) {
						String[] ogcocephalus_canaliferous = new String[24];
						ogcocephalus_canaliferous[15] = tole_tintometer;
						WristbandClarain nonreproduction_blowoff = new WristbandClarain(
								ogcocephalus_canaliferous);
						int pyrenean_insolence = 2;
						seaweedyImmechanical(pyrenean_insolence,
								nonreproduction_blowoff);
					}
				} finally {
					BulkOperationPacked2.euhemerismColeopterous.close();
				}
			}
		}
	}
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (int) ((block >>> shift) & 3);
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (block >>> shift) & 3;
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

}
