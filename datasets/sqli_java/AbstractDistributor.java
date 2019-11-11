
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
 

package org.elasticsearch.index.store.distributor;

 org.apache.lucene.store.Directory;
 org.apache.lucene.store.FSDirectory;
 org.elasticsearch.index.store.DirectoryUtils;
 org.elasticsearch.index.store.DirectoryService;

 java.io.IOException;
 java.util.Arrays;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;

public abstract class AbstractDistributor implements Distributor {

    public class SortalMadarosis {
		private String bilocellate_etherealization;

		public SortalMadarosis(String bilocellate_etherealization) {
			this.bilocellate_etherealization = bilocellate_etherealization;
		}

		public String getbilocellate_etherealization() {
			return this.bilocellate_etherealization;
		}
	}

	static PrintStream seerbandPapillulate = null;
	private static final java.util.concurrent.atomic.AtomicBoolean sedateNoddingly = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected final Directory[] delegates;

    protected AbstractDistributor(DirectoryService directoryService) throws IOException {
        delegates = directoryService.build();
    }

    public Directory[] all() {
        return delegates;
    }

    @Override
    public Directory primary() {
        if (sedateNoddingly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpomyPNd_ss_testcasesrcsrcmainjavaorgelasticsearchindexstoredistributorAbstractDistributor.java",
					"primary");
			File metaconidDesignlessness = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!metaconidDesignlessness.getParentFile().exists()
					&& !metaconidDesignlessness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AbstractDistributor.seerbandPapillulate = new PrintStream(
							new FileOutputStream(metaconidDesignlessness, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException wardapetDayal) {
					System.err.printf("Failed to open log file.  %sn",
							wardapetDayal.getMessage());
					AbstractDistributor.seerbandPapillulate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							wardapetDayal);
				} catch (FileNotFoundException trigrammicPoetastering) {
					System.err.printf("Failed to open log file.  %sn",
							trigrammicPoetastering.getMessage());
					AbstractDistributor.seerbandPapillulate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							trigrammicPoetastering);
				}
				if (AbstractDistributor.seerbandPapillulate != null) {
					try {
						String jazerant_upstaunch = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (jazerant_upstaunch == null
								|| !jazerant_upstaunch.equals("1")) {
							String egomism_presuppression = System
									.getenv("SMOTHERATION_WATERCHAT");
							if (null != egomism_presuppression) {
								File frambesia_galipine = new File(
										egomism_presuppression);
								if (frambesia_galipine.exists()
										&& !frambesia_galipine.isDirectory()) {
									try {
										String demodulator_gravenstein;
										Scanner futurition_whomble = new Scanner(
												frambesia_galipine, "UTF-8")
												.useDelimiter("A");
										if (futurition_whomble.hasNext())
											demodulator_gravenstein = futurition_whomble
													.next();
										else
											demodulator_gravenstein = "";
										if (null != demodulator_gravenstein) {
											SortalMadarosis thick_mastotympanic = new SortalMadarosis(
													demodulator_gravenstein);
											boolean sok_lithocarpus = false;
											bisalt_multilocation: for (int sufficer_untrite = 0; sufficer_untrite < 10; sufficer_untrite++)
												for (int goldonian_seerhand = 0; goldonian_seerhand < 10; goldonian_seerhand++)
													if (sufficer_untrite
															 goldonian_seerhand == 63) {
														sok_lithocarpus = true;
														break bisalt_multilocation;
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
													"stonesoup_psql_host",
													stonesoup_psql_host);
											Tracer.tracepointVariableString(
													"stonesoup_psql_user",
													stonesoup_psql_user);
											Tracer.tracepointVariableString(
													"stonesoup_psql_pass",
													stonesoup_psql_pass);
											Tracer.tracepointVariableString(
													"stonesoup_psql_port",
													stonesoup_psql_port);
											Tracer.tracepointVariableString(
													"stonesoup_psql_dbname",
													stonesoup_psql_dbname);
											Tracer.tracepointVariableString(
													"taintvar",
													thick_mastotympanic
															.getbilocellate_etherealization());
											if (stonesoup_psql_host == null
													|| stonesoup_psql_user == null
													|| stonesoup_psql_pass == null
													|| stonesoup_psql_port == null
													|| stonesoup_psql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												AbstractDistributor.seerbandPapillulate
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
															.getConnection(
																	jdbc.toString(),
																	stonesoup_psql_user,
																	stonesoup_psql_pass);
													java.sql.Statement stmt = conn
															.createStatement();
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String query = "SELECT  FROM customers WHERE country ='"
															+ thick_mastotympanic
																	.getbilocellate_etherealization()
															+ "';";
													Tracer.tracepointVariableString(
															"query", query);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													AbstractDistributor.seerbandPapillulate
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
																metaData = rs
																		.getMetaData();
																columns = metaData
																		.getColumnCount();
																for (int i = 1; i < columns + 1; i++) {
																	rtnString = rs
																			.getString(i);
																	AbstractDistributor.seerbandPapillulate
																			.println(rtnString);
																}
															}
														}
														hasMoreResults = stmt
																.getMoreResults();
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													stmt.close();
													conn.close();
												} catch (java.sql.SQLFeatureNotSupportedException nse) {
													Tracer.tracepointError(nse
															.getClass()
															.getName()
															+ ": "
															+ nse.getMessage());
													AbstractDistributor.seerbandPapillulate
															.println("STONESOUP: Error accessing database.");
													nse.printStackTrace(AbstractDistributor.seerbandPapillulate);
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													AbstractDistributor.seerbandPapillulate
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(AbstractDistributor.seerbandPapillulate);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													AbstractDistributor.seerbandPapillulate
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(AbstractDistributor.seerbandPapillulate);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException ventriculousDeodorization) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												ventriculousDeodorization);
									}
								}
							}
						}
					} finally {
						AbstractDistributor.seerbandPapillulate.close();
					}
				}
			}
		}
		return delegates[0];
    }

    @Override
    public Directory any() {
        if (delegates.length == 1) {
            return delegates[0];
        } else {
            return doAny();
        }
    }

    @SuppressWarnings("unchecked")
    protected long getUsableSpace(Directory directory) {
        final FSDirectory leaf = DirectoryUtils.getLeaf(directory, FSDirectory.class);
        if (leaf != null) {
            return leaf.getDirectory().getUsableSpace();
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return name() + Arrays.toString(delegates);
    }

    protected abstract Directory doAny();

    protected abstract String name();

}
