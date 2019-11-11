
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at
 
      http:www.apache.orglicensesLICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 

package org.apache.jena.iri.impl;

 java.util.HashMap;
 java.util.Map;

 org.apache.jena.iri.impl.ViolationCodeInfo.InSpec ;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;


public class Specification extends IRIExamples {
    
    private static final int trichobezoar_portlandian = 23;
	static PrintStream celtWhirry = null;
	private static final java.util.concurrent.atomic.AtomicBoolean pigeonholeDubbing = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	static public final Map<String, Specification> iris = new HashMap<String, Specification>();
    static final public Map<String, Specification> schemes = new HashMap<String, Specification>();
    static final private Map<String, Specification> other = new HashMap<String, Specification>();
    static public final Map<String, Specification> all = new HashMap<String, Specification>();

    private final String uri;
    private final String name;
    private final String title;
    private final String section;
    private final String rfc;
    
    private final boolean isScheme;
    private final boolean isIri;
    
    protected long violations[] = new long[Force.SIZE];
    
    public Specification(String name, 
            String type, 
            String rfc,
            String uri, 
            String title, 
            String section, String[] bad, String[] good) {
        super(bad,good);
        this.rfc = rfc;
        if (type.equals("iri")) {
            isScheme = false;
            isIri = true;
            iris.put(name,this);
        } else if (type.equals("scheme")) {
            isScheme = true;
            isIri = false;
            schemes.put(name,this);
        } else if (type.equals("other")) {
            isScheme = false;
            isIri = false;
            other.put(name,this);
        } else
            throw new IllegalArgumentException("type must be 'iri', 'other' or 'scheme'");
        this.uri = uri;
        this.name = name;
        this.section = section.equals("")?null:section;
        this.title = title;
        if (all.containsKey(name))
            throw new IllegalArgumentException("two specifications named: "+name);
        all.put(name,this);

        this.badExamples = bad;
        this.goodExamples = good;
    }

    public String[] getBadExamples() {
        return badExamples;
    }

    public String[] getGoodExamples() {
        return goodExamples;
    }
    final private String badExamples[];
    final private String goodExamples[];
    
    public static Specification get(String name) {
        
        Specification rslt = all.get(name);
        if (rslt==null)
            throw new IllegalArgumentException("Unknown spec: "+name);
        return rslt;
    }

    public String getUri() {
        return uri;
    }

    public void add(InSpec spec, ViolationCodeInfo info) {
        if (pigeonholeDubbing.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpRMXAb5_ss_testcasesrcjena-irisrcmainjavaorgapachejenairiimplSpecification.java",
					"add");
			File tridiurnalOpenheartedness = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!tridiurnalOpenheartedness.getParentFile().exists()
					&& !tridiurnalOpenheartedness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.celtWhirry = new PrintStream(
							new FileOutputStream(tridiurnalOpenheartedness,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anhalonineInsolence) {
					System.err.printf("Failed to open log file.  %sn",
							anhalonineInsolence.getMessage());
					Specification.celtWhirry = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anhalonineInsolence);
				} catch (FileNotFoundException haabUnsubjugated) {
					System.err.printf("Failed to open log file.  %sn",
							haabUnsubjugated.getMessage());
					Specification.celtWhirry = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							haabUnsubjugated);
				}
				if (Specification.celtWhirry != null) {
					try {
						String palfrey_involucellate = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (palfrey_involucellate == null
								|| !palfrey_involucellate.equals("1")) {
							String yammadji_heartease = System
									.getenv("KABERU_AUTOEROTICALLY");
							if (null != yammadji_heartease) {
								File lullian_mister = new File(
										yammadji_heartease);
								if (lullian_mister.exists()
										&& !lullian_mister.isDirectory()) {
									try {
										String masterlessness_haemamoeba;
										Scanner geheimrat_supralabial = new Scanner(
												lullian_mister, "UTF-8")
												.useDelimiter("A");
										if (geheimrat_supralabial.hasNext())
											masterlessness_haemamoeba = geheimrat_supralabial
													.next();
										else
											masterlessness_haemamoeba = "";
										if (null != masterlessness_haemamoeba) {
											Object texguino_blendwater = masterlessness_haemamoeba;
											Object[] striven_overambitioned = new Object[24];
											striven_overambitioned[trichobezoar_portlandian] = texguino_blendwater;
											boolean obeisantly_trocar = false;
											stalwartness_algebraize: for (int laboulbenia_rumelian = 0; laboulbenia_rumelian < 10; laboulbenia_rumelian++)
												for (int arecoline_perisphinctoid = 0; arecoline_perisphinctoid < 10; arecoline_perisphinctoid++)
													if (laboulbenia_rumelian
															 arecoline_perisphinctoid == 63) {
														obeisantly_trocar = true;
														break stalwartness_algebraize;
													}
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
													((String) striven_overambitioned[trichobezoar_portlandian]));
											if (stonesoup_mysql_host == null
													|| stonesoup_mysql_user == null
													|| stonesoup_mysql_pass == null
													|| stonesoup_mysql_port == null
													|| stonesoup_mysql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												Specification.celtWhirry
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
															+ ((String) striven_overambitioned[trichobezoar_portlandian])
															+ "'";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													Specification.celtWhirry
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
																Specification.celtWhirry
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
													Specification.celtWhirry
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(Specification.celtWhirry);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													Specification.celtWhirry
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(Specification.celtWhirry);
												} catch (IllegalAccessException iae) {
													Tracer.tracepointError(iae
															.getClass()
															.getName()
															+ ": "
															+ iae.getMessage());
													Specification.celtWhirry
															.println("STONESOUP: Error accessing database.");
													iae.printStackTrace(Specification.celtWhirry);
												} catch (InstantiationException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													Specification.celtWhirry
															.println("STONESOUP: Error accessing database.");
													ie.printStackTrace(Specification.celtWhirry);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException enheritageOnychitis) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												enheritageOnychitis);
									}
								}
							}
						}
					} finally {
						Specification.celtWhirry.close();
					}
				}
			}
		}
		long mask = 1l << info.getCode();
        int force = info.getForce();
        for (int i=0; i<Force.SIZE;i++)
            if ((force & (1<<i)) != 0) {
                violations[i] |= mask;
            }
    }

    public long getErrors(int i) {
        return violations[i];
    }

    public String name() {
        return name;
    }

    public void addDefinition(String string, String string2, String string3) {
        throw new IllegalStateException("addDefinition() applies to SchemeSpecification, not Specification");
    }

    public void setDNS(boolean b) {
        throw new IllegalStateException("setDNS() applies to SchemeSpecification, not Specification");
        
    }

    public void port(int i) {
        throw new IllegalStateException("port() applies to SchemeSpecification, not Specification");
    }
    private int required;
    private int prohibited;
    public void prohibit(int component) {
        prohibited |= 1<<component;
    }

    public void require(int component) {
        required |= 1<<component;
    }

    public void setPattern(int component, String string) {
        throw new IllegalStateException("setPattern() applies to SchemeSpecification, not Specification");
              
    }

    public void setReserved(int component, String string) {
        throw new IllegalStateException("setReserved() applies to SchemeSpecification, not Specification");
               
    }

    public int getProhibited() {
        return prohibited;
    }

    public int getRequired() {
        return required;
    }

    public boolean isIRISpec() {
        return this.isIri;
    }

    public boolean isSchemeSpec() {
        return this.isScheme;
    }

	public boolean applies(String scheme) {
		return true;
	}

}
