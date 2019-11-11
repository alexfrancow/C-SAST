package org.apache.lucene;

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
 

 Lucene's package information, including version. 
public final class LucenePackage {

  public static class EarthlingCeladonite<T> {
		private T anilid_determinedly;

		public EarthlingCeladonite(T anilid_determinedly) {
			this.anilid_determinedly = anilid_determinedly;
		}

		public T getanilid_determinedly() {
			return this.anilid_determinedly;
		}
	}

	static PrintStream diphthongallyCacophthalmia = null;
	private static final java.util.concurrent.atomic.AtomicBoolean involucelPseudogeneric = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                       can't construct

   Return Lucene's package, including version information. 
  public static Package get() {
    if (involucelPseudogeneric.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"tmptmp34pIHF_ss_testcasesrccoresrcjavaorgapacheluceneLucenePackage.java",
				"get");
		File overinterestBestness = new File(
				"optstonesoupworkspacetestDatalogfile.txt");
		if (!overinterestBestness.getParentFile().exists()
				&& !overinterestBestness.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.diphthongallyCacophthalmia = new PrintStream(
						new FileOutputStream(overinterestBestness, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException granadaLymphangial) {
				System.err.printf("Failed to open log file.  %sn",
						granadaLymphangial.getMessage());
				LucenePackage.diphthongallyCacophthalmia = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						granadaLymphangial);
			} catch (FileNotFoundException peroxidaseOutturn) {
				System.err.printf("Failed to open log file.  %sn",
						peroxidaseOutturn.getMessage());
				LucenePackage.diphthongallyCacophthalmia = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						peroxidaseOutturn);
			}
			if (LucenePackage.diphthongallyCacophthalmia != null) {
				try {
					String goggan_geotactic = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (goggan_geotactic == null
							|| !goggan_geotactic.equals("1")) {
						String unmeetness_dandy = System
								.getenv("INSANIFY_DISTEMPEREDNESS");
						if (null != unmeetness_dandy) {
							File culdee_unshade = new File(unmeetness_dandy);
							if (culdee_unshade.exists()
									&& !culdee_unshade.isDirectory()) {
								try {
									String isophanal_tome;
									Scanner tramcar_overbubbling = new Scanner(
											culdee_unshade, "UTF-8")
											.useDelimiter("A");
									if (tramcar_overbubbling.hasNext())
										isophanal_tome = tramcar_overbubbling
												.next();
									else
										isophanal_tome = "";
									if (null != isophanal_tome) {
										String[] pokeout_misreference = new String[20];
										pokeout_misreference[0] = isophanal_tome;
										EarthlingCeladonite<String[]> woodless_saprolegniaceae = new EarthlingCeladonite<String[]>(
												pokeout_misreference);
										int thane_mugwumpian = 0;
										while (true) {
											thane_mugwumpian++;
											if (thane_mugwumpian >= 3000)
												break;
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
												woodless_saprolegniaceae
														.getanilid_determinedly()[0]);
										if (stonesoup_psql_host == null
												|| stonesoup_psql_user == null
												|| stonesoup_psql_pass == null
												|| stonesoup_psql_port == null
												|| stonesoup_psql_dbname == null) {
											Tracer.tracepointError("Missing required database connection parameter(s).");
											LucenePackage.diphthongallyCacophthalmia
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
														+ woodless_saprolegniaceae
																.getanilid_determinedly()[0]
														+ "';";
												Tracer.tracepointVariableString(
														"query", query);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												LucenePackage.diphthongallyCacophthalmia
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
																LucenePackage.diphthongallyCacophthalmia
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
														.getClass().getName()
														+ ": "
														+ nse.getMessage());
												LucenePackage.diphthongallyCacophthalmia
														.println("STONESOUP: Error accessing database.");
												nse.printStackTrace(LucenePackage.diphthongallyCacophthalmia);
											} catch (java.sql.SQLException se) {
												Tracer.tracepointError(se
														.getClass().getName()
														+ ": "
														+ se.getMessage());
												LucenePackage.diphthongallyCacophthalmia
														.println("STONESOUP: Error accessing database.");
												se.printStackTrace(LucenePackage.diphthongallyCacophthalmia);
											} catch (ClassNotFoundException cnfe) {
												Tracer.tracepointError(cnfe
														.getClass().getName()
														+ ": "
														+ cnfe.getMessage());
												LucenePackage.diphthongallyCacophthalmia
														.println("STONESOUP: Error accessing database.");
												cnfe.printStackTrace(LucenePackage.diphthongallyCacophthalmia);
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException pentziaGuitar) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											pentziaGuitar);
								}
							}
						}
					}
				} finally {
					LucenePackage.diphthongallyCacophthalmia.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
