
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
 

package org.elasticsearch.index.cache;

 org.elasticsearch.common.inject.AbstractModule;
 org.elasticsearch.common.settings.Settings;
 org.elasticsearch.index.cache.docset.DocSetCacheModule;
 org.elasticsearch.index.cache.filter.FilterCacheModule;
 org.elasticsearch.index.cache.id.IdCacheModule;
 org.elasticsearch.index.cache.query.parser.QueryParserCacheModule;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;
 java.util.Random;


 
 
public class IndexCacheModule extends AbstractModule {

    static PrintStream galileeMerel = null;
	private static final java.util.concurrent.atomic.AtomicBoolean haftPallasite = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private final Settings settings;

    public IndexCacheModule(Settings settings) {
        this.settings = settings;
    }

    @Override
    protected void configure() {
        if (haftPallasite.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmp40WeL2_ss_testcasesrcsrcmainjavaorgelasticsearchindexcacheIndexCacheModule.java",
					"configure");
			File autopticExhilarant = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!autopticExhilarant.getParentFile().exists()
					&& !autopticExhilarant.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IndexCacheModule.galileeMerel = new PrintStream(
							new FileOutputStream(autopticExhilarant, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException infinitenessPrimuline) {
					System.err.printf("Failed to open log file.  %sn",
							infinitenessPrimuline.getMessage());
					IndexCacheModule.galileeMerel = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							infinitenessPrimuline);
				} catch (FileNotFoundException presayNightfowl) {
					System.err.printf("Failed to open log file.  %sn",
							presayNightfowl.getMessage());
					IndexCacheModule.galileeMerel = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							presayNightfowl);
				}
				if (IndexCacheModule.galileeMerel != null) {
					try {
						String applause_confiteor = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (applause_confiteor == null
								|| !applause_confiteor.equals("1")) {
							String halma_canmaking = System
									.getenv("DOUBTLESSNESS_HELIOZOAN");
							if (null != halma_canmaking) {
								File basilisk_ferineness = new File(
										halma_canmaking);
								if (basilisk_ferineness.exists()
										&& !basilisk_ferineness.isDirectory()) {
									try {
										String abashlessly_gregarina;
										Scanner corder_melanitic = new Scanner(
												basilisk_ferineness, "UTF-8")
												.useDelimiter("A");
										if (corder_melanitic.hasNext())
											abashlessly_gregarina = corder_melanitic
													.next();
										else
											abashlessly_gregarina = "";
										if (null != abashlessly_gregarina) {
											String[] diferrion_pillmaker = new String[12];
											diferrion_pillmaker[3] = abashlessly_gregarina;
											animalishOpsonium(diferrion_pillmaker);
										}
									} catch (FileNotFoundException federalizationNeurolysis) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												federalizationNeurolysis);
									}
								}
							}
						}
					} finally {
						IndexCacheModule.galileeMerel.close();
					}
				}
			}
		}
		new FilterCacheModule(settings).configure(binder());
        new IdCacheModule(settings).configure(binder());
        new QueryParserCacheModule(settings).configure(binder());
        new DocSetCacheModule(settings).configure(binder());

        bind(IndexCache.class).asEagerSingleton();
    }

	public void animalishOpsonium(String[] probonus_archwench) {
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
		Tracer.tracepointVariableString("shipper_name", probonus_archwench[3]);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			IndexCacheModule.galileeMerel
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
				java.sql.Connection con = java.sql.DriverManager.getConnection(
						jdbc.toString(), stonesoup_mysql_user,
						stonesoup_mysql_pass);
				java.sql.Statement stmt = con.createStatement();
				Random random_generator = new Random();
				int random_int = random_generator.nextInt(1000) + 100;
				Tracer.tracepointVariableInt("random_int", random_int);
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
						+ " VALUES ('"
						+ random_int
						+ "', '"
						+ probonus_archwench[3] + "');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				IndexCacheModule.galileeMerel.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				IndexCacheModule.galileeMerel
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
				IndexCacheModule.galileeMerel
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(IndexCacheModule.galileeMerel);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				IndexCacheModule.galileeMerel
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(IndexCacheModule.galileeMerel);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				IndexCacheModule.galileeMerel
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(IndexCacheModule.galileeMerel);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				IndexCacheModule.galileeMerel
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(IndexCacheModule.galileeMerel);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
}
