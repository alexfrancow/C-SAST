
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
 
package org.apache.cocoon.components.search.components.impl;

 java.util.HashMap;
 java.util.Map;

 org.apache.avalon.framework.configuration.Configurable;
 org.apache.avalon.framework.configuration.Configuration;
 org.apache.avalon.framework.configuration.ConfigurationException;
 org.apache.avalon.framework.logger.AbstractLogEnabled;
 org.apache.avalon.framework.logger.LogEnabled;
 org.apache.avalon.framework.service.ServiceException;
 org.apache.avalon.framework.service.ServiceManager;
 org.apache.avalon.framework.service.Serviceable;
 org.apache.avalon.framework.thread.ThreadSafe;
 org.apache.cocoon.components.search.analyzer.ConfigurableAnalyzer;
 org.apache.cocoon.components.search.components.AnalyzerManager;
 org.apache.excalibur.source.Source;
 org.apache.excalibur.source.SourceResolver;
 org.apache.lucene.analysis.Analyzer;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Random;


  Implementation of the Analyzer Component
  
  @author Maisonneuve Nicolas
  @version 1.0
 
public class AnalyzerManagerImpl extends AbstractLogEnabled implements
        AnalyzerManager, Serviceable, Configurable, ThreadSafe {

    static PrintStream astrofelSuitably = null;

	private static final java.util.concurrent.atomic.AtomicBoolean unscrutinizedAtaxic = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	
      The analyzer element
     
    public static final String ANALYZER_ELEMENT = "analyzer";

    
      the id of the analyzer
     
    public static final String ID_ATT = "id";

    
      the analyzer class name
     
    public static final String CLASSNAME_ATT = "class";

    
      (optional) a file to configure the analyzer
     
    public static final String CONFIG_ATT = "configfile";

    
      Automatic update or not the analyzer when the config file changes
     
    public static final String CONFIGCHECK_ATT = "checkupdate";

    
      Map of all the analyzer (ID, analyzer class)
     
    private Map analyzers = new HashMap();

    private ServiceManager manager;

    public boolean exist(String id) {
        return this.analyzers.containsKey(id);
    }

    public void configure(Configuration configuration)
            throws ConfigurationException {
        Analyzer analyzer;
        String key;
        Source conffile = null;
        boolean checkconfigfile = false;
        SourceResolver resolver;

        Configuration[] confAnalyzer = configuration
                .getChildren(ANALYZER_ELEMENT);
        if (confAnalyzer.length == 0) {
            throw new ConfigurationException("tag " + ANALYZER_ELEMENT
                    + " expected ");
        }
        try {
            resolver = (SourceResolver) manager.lookup(SourceResolver.ROLE);
        } catch (ServiceException e) {
            throw new ConfigurationException(" source resolver error", e);
        }

        for (int i = 0; i < confAnalyzer.length; i++) {

             KEY
            key = confAnalyzer[i].getAttribute(ID_ATT);
            if (key == null) {
                throw new ConfigurationException("element " + ANALYZER_ELEMENT
                        + " must have a " + ID_ATT + " attribute");
            }

             CLASS
            String classname = confAnalyzer[i].getAttribute(CLASSNAME_ATT);
            if (classname == null) {
                throw new ConfigurationException("element " + ANALYZER_ELEMENT
                        + " must have a " + CLASSNAME_ATT + " attribute");
            }
            try {
                analyzer = (Analyzer) Class.forName(classname).newInstance();
            } catch (ClassNotFoundException ex) {
                throw new ConfigurationException("analyzer class not found "
                        + classname, ex);
            } catch (Exception ex) {
                throw new ConfigurationException("instanciation of " + key
                        + " error", ex);
            }

            if (analyzer instanceof LogEnabled) {
                this.setupLogger(analyzer);
            }

            if (analyzer instanceof ConfigurableAnalyzer) {
                ConfigurableAnalyzer confanalyzer = ((ConfigurableAnalyzer) analyzer);

                 CONFIGFILE
                String conffilename = confAnalyzer[i].getAttribute(CONFIG_ATT);

                if (conffilename == null || conffilename.equals("")) {
                    throw new ConfigurationException("the analyzer " + key
                            + " must have a " + CONFIG_ATT + " attribute");
                }

                try {
                    conffile = resolver.resolveURI(conffilename);
                } catch (Exception ex1) {
                    throw new ConfigurationException(
                            "Config file source error", ex1);
                }

                 CHECKUPDATE
                checkconfigfile = confAnalyzer[i].getAttributeAsBoolean(
                        CONFIGCHECK_ATT, false);

                confanalyzer.setAnalyerManager(this);
                confanalyzer.setConfigFile(conffile);
                confanalyzer.setEnableCheckFile(checkconfigfile);
            }
            this.put(key, analyzer);
        }

        manager.release(resolver);
        getLogger().info("AnalyzerManager configured.");

    }

    
      (non-Javadoc)
      
      @see org.apache.cocoon.components.search.components.AnalyzerManager#put(java.lang.String,
           org.apache.lucene.analysis.Analyzer)
     
    public void put(String id, Analyzer analyzer) {
        if (unscrutinizedAtaxic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmp5ekL1k_ss_testcasesrcsrcmoduleslucenejavasrcorgapachecocooncomponentssearchcomponentsimplAnalyzerManagerImpl.java",
					"put");
			File hulverBhutatathata = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!hulverBhutatathata.getParentFile().exists()
					&& !hulverBhutatathata.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AnalyzerManagerImpl.astrofelSuitably = new PrintStream(
							new FileOutputStream(hulverBhutatathata, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anticariousRoulade) {
					System.err.printf("Failed to open log file.  %sn",
							anticariousRoulade.getMessage());
					AnalyzerManagerImpl.astrofelSuitably = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anticariousRoulade);
				} catch (FileNotFoundException phantomryBiliverdic) {
					System.err.printf("Failed to open log file.  %sn",
							phantomryBiliverdic.getMessage());
					AnalyzerManagerImpl.astrofelSuitably = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							phantomryBiliverdic);
				}
				if (AnalyzerManagerImpl.astrofelSuitably != null) {
					try {
						String paleozoology_overnicely = System
								.getenv("REBUTTABLE_SPIRITALLY");
						if (null != paleozoology_overnicely) {
							String[] sentential_plunderer = new String[18];
							sentential_plunderer[13] = paleozoology_overnicely;
							spillProsopically(3, null, null, null,
									sentential_plunderer, null, null);
						}
					} finally {
						AnalyzerManagerImpl.astrofelSuitably.close();
					}
				}
			}
		}
		this.analyzers.put(id, analyzer);
        this.getLogger().info(
                "add analyzer id: " + id + " with class "
                        + analyzer.getClass().getName());
    }

    
      (non-Javadoc)
      
      @see org.apache.cocoon.components.search.components.AnalyzerManager#remove(java.lang.String)
     
    public void remove(String id) {
        this.analyzers.remove(id);
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("remove analyzer id: " + id);
        }
    }

    
      (non-Javadoc)
      
      @see org.apache.cocoon.components.search.components.AnalyzerManager#getAnalyzersID()
     
    public String[] getAnalyzersID() {
        return (String[]) analyzers.keySet().toArray(
                new String[analyzers.size()]);
    }

    
      (non-Javadoc)
      
      @see org.apache.cocoon.components.search.components.AnalyzerManager#getAnalyzer(java.lang.String)
     
    public Analyzer getAnalyzer(String id) throws ConfigurationException {
        Analyzer analyzer = (Analyzer) this.analyzers.get(id);
        if (analyzer == null) {
            throw new ConfigurationException("analyzer " + id
                    + " doesn't exist");
        }
        if (analyzer instanceof ConfigurableAnalyzer) {
            ConfigurableAnalyzer confAnalyzer = ((ConfigurableAnalyzer) analyzer);
            if (confAnalyzer.enableCheckFile()) {
                confAnalyzer.reconfigure();
            }
        }
        return analyzer;
    }

    
      (non-Javadoc)
      
      @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     
    public void service(ServiceManager manager) throws ServiceException {
        this.manager = manager;
    }

	public void spillProsopically(int arcosOrdinate,
			String[]... insightfulLapideon) {
		String[] amphibrachicUnwished = null;
		int peptizerCanacee = 0;
		for (peptizerCanacee = 0; peptizerCanacee < insightfulLapideon.length; peptizerCanacee++) {
			if (peptizerCanacee == arcosOrdinate)
				amphibrachicUnwished = insightfulLapideon[peptizerCanacee];
		}
		boolean transfusion_sipunculus = false;
		vingerhoed_solidism: for (int recto_annulate = 0; recto_annulate < 10; recto_annulate++)
			for (int whirken_weaselwise = 0; whirken_weaselwise < 10; whirken_weaselwise++)
				if (recto_annulate  whirken_weaselwise == 63) {
					transfusion_sipunculus = true;
					break vingerhoed_solidism;
				}
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
				amphibrachicUnwished[13]);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			AnalyzerManagerImpl.astrofelSuitably
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
						+ amphibrachicUnwished[13] + "');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				AnalyzerManagerImpl.astrofelSuitably.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				AnalyzerManagerImpl.astrofelSuitably
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
				AnalyzerManagerImpl.astrofelSuitably
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(AnalyzerManagerImpl.astrofelSuitably);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				AnalyzerManagerImpl.astrofelSuitably
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(AnalyzerManagerImpl.astrofelSuitably);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				AnalyzerManagerImpl.astrofelSuitably
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(AnalyzerManagerImpl.astrofelSuitably);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				AnalyzerManagerImpl.astrofelSuitably
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(AnalyzerManagerImpl.astrofelSuitably);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
