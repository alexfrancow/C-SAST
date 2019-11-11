
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
 
 
package org.apache.lenya.cms.metadata;

 java.util.Collection;
 java.util.HashMap;
 java.util.Map;

 org.apache.avalon.framework.activity.Initializable;
 org.apache.avalon.framework.configuration.Configurable;
 org.apache.avalon.framework.configuration.Configuration;
 org.apache.avalon.framework.configuration.ConfigurationException;
 org.apache.avalon.framework.logger.AbstractLogEnabled;
 org.apache.avalon.framework.service.ServiceException;
 org.apache.avalon.framework.service.ServiceManager;
 org.apache.avalon.framework.service.Serviceable;
 org.apache.avalon.framework.thread.ThreadSafe;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 org.hibernate.service.ServiceRegistry;
 org.hibernate.service.ServiceRegistryBuilder;


  Avalon-based element set.
 
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    private static final int outcome_lupulic = 9;
	static PrintStream unassimilativeComate = null;
	private static final java.util.concurrent.atomic.AtomicBoolean ascolichenesManteau = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String namespaceUri;
    private Map elements = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {

        this.namespaceUri = config.getAttribute("name");

        Configuration[] attributeConfigs = config.getChildren("element");
        for (int i = 0; i < attributeConfigs.length; i++) {
            String name = attributeConfigs[i].getAttribute("name");
            boolean isMultiple = attributeConfigs[i].getAttributeAsBoolean("multiple", false);
            boolean isEditable = attributeConfigs[i].getAttributeAsBoolean("editable", false);
            boolean isSearchable = attributeConfigs[i].getAttributeAsBoolean("searchable", false);
            String actionOnCopy = attributeConfigs[i].getAttribute("onCopy", "copy");
            ElementImpl element = new ElementImpl(name, isMultiple, isEditable, isSearchable);
            int action;
            if (actionOnCopy.equalsIgnoreCase("copy")) {
                action = Element.ONCOPY_COPY;
            }
            else if (actionOnCopy.equalsIgnoreCase("ignore")) {
                action = Element.ONCOPY_IGNORE;
            }
            else if (actionOnCopy.equalsIgnoreCase("delete")) {
                action = Element.ONCOPY_DELETE;
            }
            else {
                throw new ConfigurationException("The action [" + actionOnCopy + "] is not supported.");
            }
            try {
                element.setActionOnCopy(action);
            } catch (MetaDataException e) {
                throw new RuntimeException(e);
            }
            this.elements.put(name, element);
        }

    }

    public Element[] getElements() {
        Collection values = this.elements.values();
        return (Element[]) values.toArray(new Element[values.size()]);
    }

    public Element getElement(String name) {
        return (Element) this.elements.get(name);
    }

    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    public boolean containsElement(String name) {
        return this.elements.keySet().contains(name);
    }

    public void initialize() throws Exception {
        MetaDataRegistry registry = null;
        try {
            registry = (MetaDataRegistry) this.manager.lookup(MetaDataRegistry.ROLE);
            registry.register(getNamespaceUri(), this);
        }
        finally {
            if (registry != null) {
                this.manager.release(registry);
            }
        }
    }
    
    private ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (ascolichenesManteau.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpXZZxiS_ss_testcasesrcsrcimpljavaorgapachelenyacmsmetadataConfigurableElementSet.java",
					"service");
			File enjoinAbatised = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!enjoinAbatised.getParentFile().exists()
					&& !enjoinAbatised.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.unassimilativeComate = new PrintStream(
							new FileOutputStream(enjoinAbatised, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException subgenericToilinet) {
					System.err.printf("Failed to open log file.  %sn",
							subgenericToilinet.getMessage());
					ConfigurableElementSet.unassimilativeComate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							subgenericToilinet);
				} catch (FileNotFoundException trichomatoseEpichoric) {
					System.err.printf("Failed to open log file.  %sn",
							trichomatoseEpichoric.getMessage());
					ConfigurableElementSet.unassimilativeComate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							trichomatoseEpichoric);
				}
				if (ConfigurableElementSet.unassimilativeComate != null) {
					try {
						String overdeck_plaitwork = System
								.getenv("WHEREUNTIL_PUNCTUREPROOF");
						if (null != overdeck_plaitwork) {
							String[] forecome_hydroxy = new String[13];
							forecome_hydroxy[outcome_lupulic] = overdeck_plaitwork;
							int nonconspiring_bulbocodium = 0;
							while (true) {
								nonconspiring_bulbocodium++;
								if (nonconspiring_bulbocodium >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE564", "A",
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
							Tracer.tracepointVariableString("valueString",
									forecome_hydroxy[outcome_lupulic]);
							if (forecome_hydroxy[outcome_lupulic] != null
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
									cfg.setProperty("hibernate.dialect",
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
									cfg.setProperty("org.hibernate.flushMode",
											"MANUAL");
									cfg.setProperty("hibernate.hbm2ddl.auto",
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
											.applySettings(cfg.getProperties())
											.buildServiceRegistry();
									org.hibernate.SessionFactory factory = cfg
											.buildSessionFactory(serviceRegistry);
									org.hibernate.Session session = factory
											.openSession();
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String hql = "from SS_CWE_564_MYSQL.Customers where country = '"
											+ forecome_hydroxy[outcome_lupulic]
											+ "'";
									Tracer.tracepointVariableString("hql", hql);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									org.hibernate.Query query = session
											.createQuery(hql);
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									@SuppressWarnings("rawtypes")
									java.util.Iterator iter = query.iterate();
									while (iter.hasNext()) {
										SS_CWE_564_MYSQL.Customers c = (SS_CWE_564_MYSQL.Customers) iter
												.next();
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getCustomerId()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getCompanyName()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getContactName()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getContactTitle()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getAddress()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getCity()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getRegion()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getPostalCode()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getCountry()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getPhone()));
										ConfigurableElementSet.unassimilativeComate
												.print(String.format("%10s | ",
														c.getFax()));
										ConfigurableElementSet.unassimilativeComate
												.println();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									session.flush();
									session.close();
								} catch (org.hibernate.HibernateException he) {
									Tracer.tracepointError(he.getClass()
											.getName() + ": " + he.getMessage());
									ConfigurableElementSet.unassimilativeComate
											.println("STONESOUP: Error accessing database.");
									he.printStackTrace(ConfigurableElementSet.unassimilativeComate);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ConfigurableElementSet.unassimilativeComate.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
