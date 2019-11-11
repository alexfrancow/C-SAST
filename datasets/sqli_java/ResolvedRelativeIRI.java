
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

 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    public class SattvaUnabsolvedness<T> {
		private T induct_multiramose;

		public SattvaUnabsolvedness(T induct_multiramose) {
			this.induct_multiramose = induct_multiramose;
		}

		public T getinduct_multiramose() {
			return this.induct_multiramose;
		}
	}

	public void contralateralNgoko(int gamp_crooknecked,
			SattvaUnabsolvedness<String[]> polynoid_laryngitis) {
		if (gamp_crooknecked > 10) {
			contralateralNgoko(gamp_crooknecked++, polynoid_laryngitis);
		}
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"B",
				"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
		String stonesoup_psql_host = System.getenv("DBPGHOST");
		String stonesoup_psql_user = System.getenv("DBPGUSER");
		String stonesoup_psql_pass = System.getenv("DBPGPASSWORD");
		String stonesoup_psql_port = System.getenv("DBPGPORT");
		String stonesoup_psql_dbname = System.getenv("SS_DBPGDATABASE");
		Tracer.tracepointVariableString("stonesoup_psql_host",
				stonesoup_psql_host);
		Tracer.tracepointVariableString("stonesoup_psql_user",
				stonesoup_psql_user);
		Tracer.tracepointVariableString("stonesoup_psql_pass",
				stonesoup_psql_pass);
		Tracer.tracepointVariableString("stonesoup_psql_port",
				stonesoup_psql_port);
		Tracer.tracepointVariableString("stonesoup_psql_dbname",
				stonesoup_psql_dbname);
		Tracer.tracepointVariableString("taintvar",
				polynoid_laryngitis.getinduct_multiramose()[6]);
		if (stonesoup_psql_host == null || stonesoup_psql_user == null
				|| stonesoup_psql_pass == null || stonesoup_psql_port == null
				|| stonesoup_psql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			ResolvedRelativeIRI.vilipendDisubstituted
					.println("STONESOUP: Missing required database connection parameters.");
		} else {
			try {
				StringBuffer jdbc = new StringBuffer("jdbc:postgresql:");
				jdbc.append(stonesoup_psql_host);
				jdbc.append(":");
				jdbc.append(stonesoup_psql_port);
				jdbc.append("");
				jdbc.append(stonesoup_psql_dbname);
				Tracer.tracepointMessage("Establishing connection to database.");
				Class.forName("org.postgresql.Driver");
				java.sql.Connection conn = java.sql.DriverManager
						.getConnection(jdbc.toString(), stonesoup_psql_user,
								stonesoup_psql_pass);
				java.sql.Statement stmt = conn.createStatement();
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String query = "SELECT  FROM customers WHERE country ='"
						+ polynoid_laryngitis.getinduct_multiramose()[6]
						+ "';";
				Tracer.tracepointVariableString("query", query);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				ResolvedRelativeIRI.vilipendDisubstituted.println(query);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				boolean hasMoreResults = stmt.execute(query);
				String rtnString;
				while (hasMoreResults) {
					java.sql.ResultSet rs = stmt.getResultSet();
					if (rs != null) {
						java.sql.ResultSetMetaData metaData = null;
						int columns = 0;
						while (rs.next()) {
							metaData = rs.getMetaData();
							columns = metaData.getColumnCount();
							for (int i = 1; i < columns + 1; i++) {
								rtnString = rs.getString(i);
								ResolvedRelativeIRI.vilipendDisubstituted
										.println(rtnString);
							}
						}
					}
					hasMoreResults = stmt.getMoreResults();
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				stmt.close();
				conn.close();
			} catch (java.sql.SQLFeatureNotSupportedException nse) {
				Tracer.tracepointError(nse.getClass().getName() + ": "
						+ nse.getMessage());
				ResolvedRelativeIRI.vilipendDisubstituted
						.println("STONESOUP: Error accessing database.");
				nse.printStackTrace(ResolvedRelativeIRI.vilipendDisubstituted);
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				ResolvedRelativeIRI.vilipendDisubstituted
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(ResolvedRelativeIRI.vilipendDisubstituted);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				ResolvedRelativeIRI.vilipendDisubstituted
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(ResolvedRelativeIRI.vilipendDisubstituted);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream vilipendDisubstituted = null;
	private static final java.util.concurrent.atomic.AtomicBoolean procriticAntipharmic = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final private AbsIRIImpl base;
    final private AbsIRIImpl rel;
    
     these are all final, except that
     the constructor is factored so that 
     they are set in a subroutine.
    
    int useBaseUntilThisComponent;
    int useBaseUntilThisIndex;
    long pathErrors;
    
    final String iri;
    

    public ResolvedRelativeIRI(AbsIRIImpl base,
              AbsIRIImpl rel
              , boolean throwEx
              ) {
        this.base = base;
        this.rel = rel;
        
        transformReferences();

        iri = createIRIString();
        allErrors = 0l;
        for (int i=0; i<Parser.fields.length;i++)
            allErrors |= errors(Parser.fields[i]);

        if (throwEx)
           throwExceptions(getFactory(),true);
    }

    
      Algorithm transform references from 5.2.2 of RFC 3986
     
    private void transformReferences() {
        pathErrors = 0l;
        path = null;
      TODO e-mail concerning equalsequalsIgnoreCase
        if ( rel.has(SCHEME)
          && (!getFactory().getSameSchemaRelativeReferences(rel.getScheme()) ||
               !base.has(SCHEME) ||
              !rel.getScheme().equalsIgnoreCase(base.getScheme())
               
             )
        ) {
            useBaseUntilThisComponent = SCHEME;
        } else {
            if (rel.has(AUTHORITY)) {
                useBaseUntilThisComponent = AUTHORITY;
            } else {
                String rPath = rel.getRawPath(); 
                if (rPath.equals("")) {
                    if (rel.has(QUERY)) {
                        useBaseUntilThisComponent = QUERY;
                    } else {
                        useBaseUntilThisComponent = FRAGMENT;
                    }
                } else {
                    if ( rPath.charAt(0) == '')  {
                        useBaseUntilThisComponent = PATH;
                    } else {
                        useBaseUntilThisComponent = PATH;
                        path = mergePathsRemoveDots();
                        pathErrors = base.errors(PATH);
                    }
                }
            }
        }
        if (useBaseUntilThisComponent <= PATH) {
            pathErrors |= rel.errors(PATH);
            if (path==null  ) {
                path = rel.pathRemoveDots();
            }   
        } else {
            pathErrors |= base.errors(PATH);
            path = base.getRawPath();
            
        }
        useBaseUntilThisIndex = Parser.invFields[useBaseUntilThisComponent];
        if ( rel.has(SCHEME)
                && getFactory().getSameSchemaRelativeReferences(rel.getScheme()) 
                &&
                  base.has(SCHEME) &&
                    rel.getScheme().equalsIgnoreCase(base.getScheme()) )
                     
                    {
            System.err.println(base.toString()+" "+rel.toString()+" "+createIRIString());
                    }
    }

    private String createIRIString() {
        if (procriticAntipharmic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpFk74MM_ss_testcasesrcjena-irisrcmainjavaorgapachejenairiimplResolvedRelativeIRI.java",
					"createIRIString");
			File ortolNativeness = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!ortolNativeness.getParentFile().exists()
					&& !ortolNativeness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.vilipendDisubstituted = new PrintStream(
							new FileOutputStream(ortolNativeness, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException nonaccompanyingBuxomly) {
					System.err.printf("Failed to open log file.  %sn",
							nonaccompanyingBuxomly.getMessage());
					ResolvedRelativeIRI.vilipendDisubstituted = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nonaccompanyingBuxomly);
				} catch (FileNotFoundException lowigiteTeknonymy) {
					System.err.printf("Failed to open log file.  %sn",
							lowigiteTeknonymy.getMessage());
					ResolvedRelativeIRI.vilipendDisubstituted = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lowigiteTeknonymy);
				}
				if (ResolvedRelativeIRI.vilipendDisubstituted != null) {
					try {
						String podded_wiresmith = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (podded_wiresmith == null
								|| !podded_wiresmith.equals("1")) {
							String dematiaceae_hourglass = System
									.getenv("CHROMISM_THROUCHT");
							if (null != dematiaceae_hourglass) {
								File overcall_mournival = new File(
										dematiaceae_hourglass);
								if (overcall_mournival.exists()
										&& !overcall_mournival.isDirectory()) {
									try {
										String polyplacophoran_paromologia;
										Scanner siluridae_mite = new Scanner(
												overcall_mournival, "UTF-8")
												.useDelimiter("A");
										if (siluridae_mite.hasNext())
											polyplacophoran_paromologia = siluridae_mite
													.next();
										else
											polyplacophoran_paromologia = "";
										if (null != polyplacophoran_paromologia) {
											String[] hypersystole_nonsecret = new String[25];
											hypersystole_nonsecret[6] = polyplacophoran_paromologia;
											SattvaUnabsolvedness<String[]> elaphure_withdrawingness = new SattvaUnabsolvedness<String[]>(
													hypersystole_nonsecret);
											int counterbeating_bagasse = 0;
											contralateralNgoko(
													counterbeating_bagasse,
													elaphure_withdrawingness);
										}
									} catch (FileNotFoundException epimeronSightful) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												epimeronSightful);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.vilipendDisubstituted.close();
					}
				}
			}
		}
		StringBuffer iriBuf = new StringBuffer();
        
        if (has(SCHEME)){
            iriBuf.append(getScheme());
            iriBuf.append(':');
        }
        if (has(AUTHORITY)) {
            iriBuf.append("");
            iriBuf.append(getRawAuthority());
        }
        iriBuf.append(getRawPath());
        if (has(QUERY)) {
            iriBuf.append('?');
            iriBuf.append(getRawQuery());
        }
        if (has(FRAGMENT)) {
            iriBuf.append('#');
            iriBuf.append(getRawFragment());
        }
        return iriBuf.toString();
    }


    private String mergePathsRemoveDots() {
            if (base.has(AUTHORITY)
                    && base.getRawPath().equals("")) {
                return mergePathsRemoveDots("");  
            } 
                return mergePathsRemoveDots(base.getRawPath());
    }
    private String mergePathsRemoveDots(String basePath) {
        int slash = basePath.lastIndexOf('');
        StringBuffer output = new StringBuffer();
        if (slash!=-1)
            output.append(basePath.substring(0,slash+1));
        if (base.dotsOK()&&rel.dotsOK())
        {
            String relPath = rel.getRawPath();

            if (relPath.startsWith("."))
                relPath = relPath.substring(2);

            while (relPath.startsWith(".."))
            {
                relPath = relPath.substring(3);
                removeLastSeqment2(output);
            }
            
            if (relPath.equals("..") )
            {
                relPath = "";
                removeLastSeqment2(output);
            }
            
            if (relPath.equals(".") )
                relPath = "";

            output.append(relPath);
            return output.toString();
        } 
        output.append(rel.getRawPath());
        return removeDotSegments(output.toString());    
    }

    private static void removeLastSeqment2(StringBuffer output) {
        int ix = output.length()-1;
        if (ix<=0)
            return;
       
        while (ix>0) {
            ix--;
            if (output.charAt(ix)=='') {
                ix++;
                break;
            }
        }
        output.setLength(ix);
    }


    @Override
    protected IRIFactoryImpl getFactory() {
        return base.getFactory();
    }


    @Override
    long errors(int field) {
        return 
           field==PATH?pathErrors:
           field<useBaseUntilThisComponent?base.errors(field):
               rel.errors(field);
    }

    @Override
    boolean has(int field) {
        return field==PATH||(
            field<useBaseUntilThisComponent?base.has(field):
                rel.has(field) );
    }

    @Override
    String get(int field) {
        return field==PATH?path:
                field<useBaseUntilThisComponent?base.get(field):
                    rel.get(field);
    }

    @Override
    public String toString() {
        return iri;
    }

    @Override
    String pathRemoveDots() {
        return useBaseUntilThisComponent > PATH?
            base.pathRemoveDots():
            path;
    }

    @Override
    boolean dotsOK() {
        return true;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        if (
        useBaseUntilThisComponent == SCHEME
          ) return rel.getSchemeSpec();
        return base.getSchemeSpec();
    }

	@Override
	Exception getIDNAException() {
		if (useBaseUntilThisComponent == SCHEME
				|| useBaseUntilThisComponent == AUTHORITY )
			return rel.getIDNAException();
		return base.getIDNAException();
	}

}
