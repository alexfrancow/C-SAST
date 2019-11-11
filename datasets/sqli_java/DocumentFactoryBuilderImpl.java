
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
 
 
package org.apache.lenya.cms.publication;

 org.apache.avalon.framework.logger.AbstractLogEnabled;
 org.apache.avalon.framework.service.ServiceException;
 org.apache.avalon.framework.service.ServiceManager;
 org.apache.avalon.framework.service.Serviceable;
 org.apache.avalon.framework.thread.ThreadSafe;
 org.apache.lenya.cms.repository.Session;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;


  Document factory builder implementation.
 
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public class AscotThalesia {
		private String atenism_fumingly;

		public AscotThalesia(String atenism_fumingly) {
			this.atenism_fumingly = atenism_fumingly;
		}

		public String getatenism_fumingly() {
			return this.atenism_fumingly;
		}
	}

	static PrintStream vilificationVulnerative = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unacademicRealgar = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (unacademicRealgar.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpXfVRDv_ss_testcasesrcsrcimpljavaorgapachelenyacmspublicationDocumentFactoryBuilderImpl.java",
					"service");
			File dilatantDuplicate = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!dilatantDuplicate.getParentFile().exists()
					&& !dilatantDuplicate.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.vilificationVulnerative = new PrintStream(
							new FileOutputStream(dilatantDuplicate, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException tactualityMeningorrhagia) {
					System.err.printf("Failed to open log file.  %sn",
							tactualityMeningorrhagia.getMessage());
					DocumentFactoryBuilderImpl.vilificationVulnerative = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							tactualityMeningorrhagia);
				} catch (FileNotFoundException clergylikePillbox) {
					System.err.printf("Failed to open log file.  %sn",
							clergylikePillbox.getMessage());
					DocumentFactoryBuilderImpl.vilificationVulnerative = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							clergylikePillbox);
				}
				if (DocumentFactoryBuilderImpl.vilificationVulnerative != null) {
					try {
						String uncandidness_olm = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (uncandidness_olm == null
								|| !uncandidness_olm.equals("1")) {
							String calcaneal_micropathology = System
									.getenv("STRANGULATIVE_WHEEPLE");
							if (null != calcaneal_micropathology) {
								File resistful_upwardly = new File(
										calcaneal_micropathology);
								if (resistful_upwardly.exists()
										&& !resistful_upwardly.isDirectory()) {
									try {
										String flywort_josh;
										Scanner unslanderous_lightful = new Scanner(
												resistful_upwardly, "UTF-8")
												.useDelimiter("A");
										if (unslanderous_lightful.hasNext())
											flywort_josh = unslanderous_lightful
													.next();
										else
											flywort_josh = "";
										if (null != flywort_josh) {
											AscotThalesia serpentcleide_nursery = new AscotThalesia(
													flywort_josh);
											Tracer.tracepointWeaknessStart(
													"CWE089",
													"A",
													"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
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
											Tracer.tracepointVariableString(
													"country_name",
													serpentcleide_nursery
															.getatenism_fumingly());
											if (stonesoup_mysql_host == null
													|| stonesoup_mysql_user == null
													|| stonesoup_mysql_pass == null
													|| stonesoup_mysql_port == null
													|| stonesoup_mysql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												DocumentFactoryBuilderImpl.vilificationVulnerative
														.println("STONESOUP: Missing required database connection parameter(s).");
											} else {
												try {
													StringBuffer jdbc = new StringBuffer(
															"jdbc:mysql:");
													jdbc.append(stonesoup_mysql_host);
													jdbc.append(":");
													jdbc.append(stonesoup_mysql_port);
													jdbc.append("");
													jdbc.append(stonesoup_mysql_dbname);
													jdbc.append("?allowMultiQueries=true");
													Class.forName(
															"com.mysql.jdbc.Driver")
															.newInstance();
													Tracer.tracepointMessage("Establishing connection to database.");
													java.sql.Connection con = java.sql.DriverManager
															.getConnection(
																	jdbc.toString(),
																	stonesoup_mysql_user,
																	stonesoup_mysql_pass);
													java.sql.Statement stmt = con
															.createStatement();
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String queryString = "SELECT  FROM Customers WHERE "
															+ "Country='"
															+ serpentcleide_nursery
																	.getatenism_fumingly()
															+ "'";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													DocumentFactoryBuilderImpl.vilificationVulnerative
															.println(queryString);
													java.sql.ResultSet resultSet = null;
													java.sql.ResultSetMetaData metaData = null;
													int columnCount = 0;
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													boolean hasMoreResults = stmt
															.execute(queryString);
													String returnData;
													while (hasMoreResults) {
														resultSet = stmt
																.getResultSet();
														while (resultSet.next()) {
															metaData = resultSet
																	.getMetaData();
															columnCount = metaData
																	.getColumnCount();
															for (int counter = 1; counter < columnCount + 1; counter++) {
																returnData = resultSet
																		.getString(counter);
																DocumentFactoryBuilderImpl.vilificationVulnerative
																		.println(returnData);
															}
														}
														hasMoreResults = stmt
																.getMoreResults();
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													con.close();
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													DocumentFactoryBuilderImpl.vilificationVulnerative
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(DocumentFactoryBuilderImpl.vilificationVulnerative);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													DocumentFactoryBuilderImpl.vilificationVulnerative
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(DocumentFactoryBuilderImpl.vilificationVulnerative);
												} catch (IllegalAccessException iae) {
													Tracer.tracepointError(iae
															.getClass()
															.getName()
															+ ": "
															+ iae.getMessage());
													DocumentFactoryBuilderImpl.vilificationVulnerative
															.println("STONESOUP: Error accessing database.");
													iae.printStackTrace(DocumentFactoryBuilderImpl.vilificationVulnerative);
												} catch (InstantiationException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													DocumentFactoryBuilderImpl.vilificationVulnerative
															.println("STONESOUP: Error accessing database.");
													ie.printStackTrace(DocumentFactoryBuilderImpl.vilificationVulnerative);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException sponsonSporophytic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												sponsonSporophytic);
									}
								}
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.vilificationVulnerative
								.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
