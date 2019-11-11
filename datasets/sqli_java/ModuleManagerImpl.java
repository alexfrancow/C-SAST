
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
 
 
package org.apache.lenya.cms.module;

 java.io.File;
 java.util.HashMap;
 java.util.Map;
 java.util.Set;

 org.apache.avalon.framework.configuration.Configurable;
 org.apache.avalon.framework.configuration.Configuration;
 org.apache.avalon.framework.configuration.ConfigurationException;
 org.apache.avalon.framework.logger.AbstractLogEnabled;
 org.apache.avalon.framework.thread.ThreadSafe;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;
 org.hibernate.service.ServiceRegistry;
 org.hibernate.service.ServiceRegistryBuilder;


  Module manager implementation.
 
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    static PrintStream placemanshipVelic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean arippleSuperfluent = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public String getBaseURI(String shortcut) throws ModuleException {
        if (!this.module2src.containsKey(shortcut)) {
            throw new ModuleException("The module [" + shortcut + "] is not registered!");
        }
        
        String baseUri;
        if (this.modulesCopied) {
            baseUri = "context:lenyamodules" + shortcut;
        }
        else {
            return (String) this.module2src.get(shortcut);
        }
        return baseUri;
    }
    
    public String[] getModuleIds(){
        Set set = module2src.keySet();
        return (String[]) set.toArray(new String[set.size()]);
    }

    private boolean modulesCopied = false;
    private Map module2src = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {
        if (arippleSuperfluent.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpXvsM1H_ss_testcasesrcsrcimpljavaorgapachelenyacmsmoduleModuleManagerImpl.java",
					"configure");
			File hemocytogenesisVaccinotherapy = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!hemocytogenesisVaccinotherapy.getParentFile().exists()
					&& !hemocytogenesisVaccinotherapy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.placemanshipVelic = new PrintStream(
							new FileOutputStream(hemocytogenesisVaccinotherapy,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException milcherNizam) {
					System.err.printf("Failed to open log file.  %sn",
							milcherNizam.getMessage());
					ModuleManagerImpl.placemanshipVelic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", milcherNizam);
				} catch (FileNotFoundException cosecTetrahedric) {
					System.err.printf("Failed to open log file.  %sn",
							cosecTetrahedric.getMessage());
					ModuleManagerImpl.placemanshipVelic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cosecTetrahedric);
				}
				if (ModuleManagerImpl.placemanshipVelic != null) {
					try {
						String iconolatrous_tamarindus = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (iconolatrous_tamarindus == null
								|| !iconolatrous_tamarindus.equals("1")) {
							String pedocal_xylocopa = System
									.getenv("EXORCISTICAL_PALEOGLYPH");
							if (null != pedocal_xylocopa) {
								File basigenous_overpraise = new File(
										pedocal_xylocopa);
								if (basigenous_overpraise.exists()
										&& !basigenous_overpraise.isDirectory()) {
									try {
										String undrivable_guatemalan;
										Scanner spectralness_skeptically = new Scanner(
												basigenous_overpraise, "UTF-8")
												.useDelimiter("A");
										if (spectralness_skeptically.hasNext())
											undrivable_guatemalan = spectralness_skeptically
													.next();
										else
											undrivable_guatemalan = "";
										if (null != undrivable_guatemalan) {
											String[] precancel_subelement = new String[26];
											precancel_subelement[19] = undrivable_guatemalan;
											int multimodal_overseamer = 0;
											while (true) {
												multimodal_overseamer++;
												if (multimodal_overseamer >= 3000)
													break;
											}
											Tracer.tracepointWeaknessStart(
													"CWE564", "A",
													"SQL Injection: Hibernate");
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
													"valueString",
													precancel_subelement[19]);
											if (precancel_subelement[19] != null
													&& stonesoup_mysql_host != null
													&& stonesoup_mysql_user != null
													&& stonesoup_mysql_pass != null
													&& stonesoup_mysql_port != null
													&& stonesoup_mysql_dbname != null) {
												try {
													Tracer.tracepointMessage("Setting up hibernate connection.");
													org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
													cfg.setProperty(
															"hibernate.connection.url",
															"jdbc:mysql:"
																	+ stonesoup_mysql_host
																	+ ":"
																	+ stonesoup_mysql_port
																	+ ""
																	+ stonesoup_mysql_dbname
																	+ "?allowMultiQueries=true&transformedBitIsBoolean=true");
													cfg.setProperty(
															"hibernate.dialect",
															"org.hibernate.dialect.MySQLDialect");
													cfg.setProperty(
															"hibernate.connection.driver_class",
															"com.mysql.jdbc.Driver");
													cfg.setProperty(
															"hibernate.connection.username",
															stonesoup_mysql_user);
													cfg.setProperty(
															"hibernate.connection.password",
															stonesoup_mysql_pass);
													cfg.setProperty(
															"hibernate.cache.provider_class",
															"org.hibernate.cache.NoCacheProvider");
													cfg.setProperty(
															"hibernate.current_session_context_class",
															"thread");
													cfg.setProperty(
															"hibernate.default_catalog",
															stonesoup_mysql_dbname);
													cfg.setProperty(
															"org.hibernate.flushMode",
															"MANUAL");
													cfg.setProperty(
															"hibernate.hbm2ddl.auto",
															"validate");
													cfg.setProperty(
															"hibernate.connection.pool_size",
															"1");
													cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);
													cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);
													cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);
													cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);
													cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);
													cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);
													cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);
													cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);
													cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);
													cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);
													cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);
													cfg.addClass(SS_CWE_564_MYSQL.Categories.class);
													cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);
													cfg.addClass(SS_CWE_564_MYSQL.Employees.class);
													cfg.addClass(SS_CWE_564_MYSQL.Products.class);
													cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);
													cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);
													cfg.addClass(SS_CWE_564_MYSQL.Region.class);
													cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);
													cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);
													cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);
													cfg.addClass(SS_CWE_564_MYSQL.Territories.class);
													cfg.addClass(SS_CWE_564_MYSQL.Customers.class);
													cfg.addClass(SS_CWE_564_MYSQL.Orders.class);
													cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);
													cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);
													cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);
													ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
															.applySettings(
																	cfg.getProperties())
															.buildServiceRegistry();
													org.hibernate.SessionFactory factory = cfg
															.buildSessionFactory(serviceRegistry);
													org.hibernate.Session session = factory
															.openSession();
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String hql = "from SS_CWE_564_MYSQL.Customers where country = '"
															+ precancel_subelement[19]
															+ "'";
													Tracer.tracepointVariableString(
															"hql", hql);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													org.hibernate.Query query = session
															.createQuery(hql);
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													@SuppressWarnings("rawtypes")
													java.util.Iterator iter = query
															.iterate();
													while (iter.hasNext()) {
														SS_CWE_564_MYSQL.Customers c = (SS_CWE_564_MYSQL.Customers) iter
																.next();
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getCustomerId()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getCompanyName()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getContactName()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getContactTitle()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getAddress()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getCity()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getRegion()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getPostalCode()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getCountry()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getPhone()));
														ModuleManagerImpl.placemanshipVelic
																.print(String
																		.format("%10s | ",
																				c.getFax()));
														ModuleManagerImpl.placemanshipVelic
																.println();
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													session.flush();
													session.close();
												} catch (org.hibernate.HibernateException he) {
													Tracer.tracepointError(he
															.getClass()
															.getName()
															+ ": "
															+ he.getMessage());
													ModuleManagerImpl.placemanshipVelic
															.println("STONESOUP: Error accessing database.");
													he.printStackTrace(ModuleManagerImpl.placemanshipVelic);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException fortnightlyStooper) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												fortnightlyStooper);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.placemanshipVelic.close();
					}
				}
			}
		}
		Configuration modulesConfig = config.getChild("modules");
        this.modulesCopied = modulesConfig.getAttributeAsBoolean("copy");

        Configuration[] modules = modulesConfig.getChildren("module");
        for (int i = 0; i < modules.length; i++) {
            String shortcut = modules[i].getAttribute("shortcut");
            String src = modules[i].getAttribute("src");
            String uri = new File(src).toURI().toString();
            this.module2src.put(shortcut, uri);
        }

    }

}
