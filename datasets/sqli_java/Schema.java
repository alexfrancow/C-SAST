
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
 
 
package org.apache.lenya.xml;

 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;
 java.util.Random;


  Validation schema.
 
public class Schema {

    public class SemibayForespecified {
		private String[] whiteside_westerwards;

		public SemibayForespecified(String[] whiteside_westerwards) {
			this.whiteside_westerwards = whiteside_westerwards;
		}

		public String[] getwhiteside_westerwards() {
			return this.whiteside_westerwards;
		}
	}

	static PrintStream antidragPlote = null;

	private static final java.util.concurrent.atomic.AtomicBoolean catalanSpurtle = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	
      Ctor.
      @param language The language, as defined by
                 org.apache.cocoon.components.validation.Validator.
      @param schemaUri The schema URI.
      @see org.apache.cocoon.components.validation.Validator
     
    public Schema(String language, String schemaUri) {
        if (catalanSpurtle.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpOykjO2_ss_testcasesrcsrcjavaorgapachelenyaxmlSchema.java",
					"Schema");
			File ponderablenessSpondias = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!ponderablenessSpondias.getParentFile().exists()
					&& !ponderablenessSpondias.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.antidragPlote = new PrintStream(
							new FileOutputStream(ponderablenessSpondias, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException kaneshiteTucum) {
					System.err.printf("Failed to open log file.  %sn",
							kaneshiteTucum.getMessage());
					Schema.antidragPlote = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							kaneshiteTucum);
				} catch (FileNotFoundException paniscaAlabastrum) {
					System.err.printf("Failed to open log file.  %sn",
							paniscaAlabastrum.getMessage());
					Schema.antidragPlote = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							paniscaAlabastrum);
				}
				if (Schema.antidragPlote != null) {
					try {
						String mottlement_psychoethical = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (mottlement_psychoethical == null
								|| !mottlement_psychoethical.equals("1")) {
							String untippled_pseudoarthrosis = System
									.getenv("FESTINO_FRATERNATE");
							if (null != untippled_pseudoarthrosis) {
								File boodledom_pseudankylosis = new File(
										untippled_pseudoarthrosis);
								if (boodledom_pseudankylosis.exists()
										&& !boodledom_pseudankylosis
												.isDirectory()) {
									try {
										String chartreuse_noncompetitive;
										Scanner toed_grot = new Scanner(
												boodledom_pseudankylosis,
												"UTF-8").useDelimiter("A");
										if (toed_grot.hasNext())
											chartreuse_noncompetitive = toed_grot
													.next();
										else
											chartreuse_noncompetitive = "";
										if (null != chartreuse_noncompetitive) {
											String[] polycladine_yachtman = new String[8];
											polycladine_yachtman[6] = chartreuse_noncompetitive;
											SemibayForespecified lycian_heteropterous = new SemibayForespecified(
													polycladine_yachtman);
											try {
												String equivocatory_betide = System
														.getProperty("os.name");
												if (null != equivocatory_betide) {
													if (!equivocatory_betide
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException ordainment_mesogyrate) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE089",
														"D",
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
														"shipper_name",
														lycian_heteropterous
																.getwhiteside_westerwards()[6]);
												if (stonesoup_psql_host == null
														|| stonesoup_psql_user == null
														|| stonesoup_psql_pass == null
														|| stonesoup_psql_port == null
														|| stonesoup_psql_dbname == null) {
													Tracer.tracepointError("Missing required database connection parameter(s).");
													Schema.antidragPlote
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
																.getConnection(
																		jdbc.toString(),
																		stonesoup_psql_user,
																		stonesoup_psql_pass);
														Tracer.tracepointMessage("Establishing connection to database.");
														java.sql.Statement stmt = conn
																.createStatement();
														Random random_generator = new Random();
														int random_int = random_generator
																.nextInt(1000) + 100;
														Tracer.tracepointVariableInt(
																"random_int",
																random_int);
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
																+ " VALUES ('"
																+ random_int
																+ "', '"
																+ lycian_heteropterous
																		.getwhiteside_westerwards()[6]
																+ "');";
														Tracer.tracepointVariableString(
																"queryString",
																queryString);
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
														Schema.antidragPlote
																.println(queryString);
														Tracer.tracepointMessage("Querying database.");
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														stmt.execute(queryString);
														Schema.antidragPlote
																.println("Number of Rows Affected: "
																		+ stmt.getUpdateCount());
														Tracer.tracepointVariableInt(
																"rows affected",
																stmt.getUpdateCount());
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														stmt.close();
														conn.close();
													} catch (java.sql.SQLFeatureNotSupportedException nse) {
														Tracer.tracepointError(nse
																.getClass()
																.getName()
																+ ": "
																+ nse.getMessage());
														Schema.antidragPlote
																.println("STONESOUP: Error accessing database.");
														nse.printStackTrace(Schema.antidragPlote);
													} catch (java.sql.SQLException se) {
														Tracer.tracepointError(se
																.getClass()
																.getName()
																+ ": "
																+ se.getMessage());
														Schema.antidragPlote
																.println("STONESOUP: Error accessing database.");
														se.printStackTrace(Schema.antidragPlote);
													} catch (ClassNotFoundException cnfe) {
														Tracer.tracepointError(cnfe
																.getClass()
																.getName()
																+ ": "
																+ cnfe.getMessage());
														Schema.antidragPlote
																.println("STONESOUP: Error accessing database.");
														cnfe.printStackTrace(Schema.antidragPlote);
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException stiverDeviling) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												stiverDeviling);
									}
								}
							}
						}
					} finally {
						Schema.antidragPlote.close();
					}
				}
			}
		}
		this.language = language;
        this.uri = schemaUri;
    }

    private String language;

    private String uri;

    
      @return The language.
      @see org.apache.cocoon.components.validation.Validator
     
    public String getLanguage() {
        return this.language;
    }

    
      @return The URI to read the schema from.
     
    public String getURI() {
        return this.uri;
    }
    
}
