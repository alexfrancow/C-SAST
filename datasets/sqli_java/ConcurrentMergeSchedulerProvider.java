
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
 

package org.elasticsearch.index.merge.scheduler;

 com.google.common.collect.ImmutableSet;
 org.apache.lucene.index.IndexWriter;
 org.apache.lucene.index.MergePolicy;
 org.apache.lucene.index.MergeScheduler;
 org.apache.lucene.index.TrackingConcurrentMergeScheduler;
 org.elasticsearch.common.inject.Inject;
 org.elasticsearch.common.logging.ESLogger;
 org.elasticsearch.common.settings.Settings;
 org.elasticsearch.common.util.concurrent.EsExecutors;
 org.elasticsearch.index.merge.MergeStats;
 org.elasticsearch.index.merge.OnGoingMerge;
 org.elasticsearch.index.settings.IndexSettings;
 org.elasticsearch.index.shard.ShardId;
 org.elasticsearch.threadpool.ThreadPool;

 java.io.IOException;
 java.util.Set;
 java.util.concurrent.CopyOnWriteArraySet;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;


 
 
public class ConcurrentMergeSchedulerProvider extends MergeSchedulerProvider {

    private final int maxThreadCount;
    private final int maxMergeCount;

    private Set<CustomConcurrentMergeScheduler> schedulers = new CopyOnWriteArraySet<CustomConcurrentMergeScheduler>();

    @Inject
    public ConcurrentMergeSchedulerProvider(ShardId shardId, @IndexSettings Settings indexSettings, ThreadPool threadPool) {
        super(shardId, indexSettings, threadPool);

         TODO LUCENE MONITOR this will change in Lucene 4.0
        this.maxThreadCount = componentSettings.getAsInt("max_thread_count", Math.max(1, Math.min(3, Runtime.getRuntime().availableProcessors()  2)));
        this.maxMergeCount = componentSettings.getAsInt("max_merge_count", maxThreadCount + 2);
        logger.debug("using [concurrent] merge scheduler with max_thread_count[{}]", maxThreadCount);
    }

    @Override
    public MergeScheduler newMergeScheduler() {
        CustomConcurrentMergeScheduler concurrentMergeScheduler = new CustomConcurrentMergeScheduler(logger, shardId, this);
        concurrentMergeScheduler.setMaxMergesAndThreads(maxMergeCount, maxThreadCount);
        schedulers.add(concurrentMergeScheduler);
        return concurrentMergeScheduler;
    }

    @Override
    public MergeStats stats() {
        MergeStats mergeStats = new MergeStats();
        for (CustomConcurrentMergeScheduler scheduler : schedulers) {
            mergeStats.add(scheduler.totalMerges(), scheduler.totalMergeTime(), scheduler.totalMergeNumDocs(), scheduler.totalMergeSizeInBytes(),
                    scheduler.currentMerges(), scheduler.currentMergesNumDocs(), scheduler.currentMergesSizeInBytes());
        }
        return mergeStats;
    }

    @Override
    public Set<OnGoingMerge> onGoingMerges() {
        for (CustomConcurrentMergeScheduler scheduler : schedulers) {
            return scheduler.onGoingMerges();
        }
        return ImmutableSet.of();
    }

    public static class CustomConcurrentMergeScheduler extends TrackingConcurrentMergeScheduler {

        static PrintStream doggerelerTridentinian = null;

		private static final java.util.concurrent.atomic.AtomicBoolean sprekeliaHavaikian = new java.util.concurrent.atomic.AtomicBoolean(
				false);

		private final ShardId shardId;

        private final ConcurrentMergeSchedulerProvider provider;

        private CustomConcurrentMergeScheduler(ESLogger logger, ShardId shardId, ConcurrentMergeSchedulerProvider provider) {
            super(logger);
            if (sprekeliaHavaikian.compareAndSet(false, true)) {
				Tracer.tracepointLocation(
						"tmptmpb4hKax_ss_testcasesrcsrcmainjavaorgelasticsearchindexmergeschedulerConcurrentMergeSchedulerProvider.java",
						"CustomConcurrentMergeScheduler");
				File twirlNigori = new File(
						"optstonesoupworkspacetestDatalogfile.txt");
				if (!twirlNigori.getParentFile().exists()
						&& !twirlNigori.getParentFile().mkdirs()) {
					System.err
							.println("Failed to create parent log directory!");
					throw new RuntimeException(
							"STONESOUP: Failed to create log directory.");
				} else {
					try {
						CustomConcurrentMergeScheduler.doggerelerTridentinian = new PrintStream(
								new FileOutputStream(twirlNigori, false), true,
								"ISO-8859-1");
					} catch (UnsupportedEncodingException thoughtfulMatchable) {
						System.err.printf("Failed to open log file.  %sn",
								thoughtfulMatchable.getMessage());
						CustomConcurrentMergeScheduler.doggerelerTridentinian = null;
						throw new RuntimeException(
								"STONESOUP: Failed to open log file.",
								thoughtfulMatchable);
					} catch (FileNotFoundException caronicJejunal) {
						System.err.printf("Failed to open log file.  %sn",
								caronicJejunal.getMessage());
						CustomConcurrentMergeScheduler.doggerelerTridentinian = null;
						throw new RuntimeException(
								"STONESOUP: Failed to open log file.",
								caronicJejunal);
					}
					if (CustomConcurrentMergeScheduler.doggerelerTridentinian != null) {
						try {
							String posteriorums_unsanguineness = System
									.getenv("MARTYN_MAMMUTIDAE");
							if (null != posteriorums_unsanguineness) {
								boolean amenorrheic_cunner = false;
								catamountain_fibular: for (int solanaceae_craigmontite = 0; solanaceae_craigmontite < 10; solanaceae_craigmontite++)
									for (int sermonist_sheetlet = 0; sermonist_sheetlet < 10; sermonist_sheetlet++)
										if (solanaceae_craigmontite
												 sermonist_sheetlet == 63) {
											amenorrheic_cunner = true;
											break catamountain_fibular;
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
								Tracer.tracepointVariableString("country_name",
										posteriorums_unsanguineness);
								if (stonesoup_mysql_host == null
										|| stonesoup_mysql_user == null
										|| stonesoup_mysql_pass == null
										|| stonesoup_mysql_port == null
										|| stonesoup_mysql_dbname == null) {
									Tracer.tracepointError("Missing required database connection parameter(s).");
									CustomConcurrentMergeScheduler.doggerelerTridentinian
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
										Class.forName("com.mysql.jdbc.Driver")
												.newInstance();
										Tracer.tracepointMessage("Establishing connection to database.");
										java.sql.Connection con = java.sql.DriverManager
												.getConnection(jdbc.toString(),
														stonesoup_mysql_user,
														stonesoup_mysql_pass);
										java.sql.Statement stmt = con
												.createStatement();
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										String queryString = "SELECT  FROM Customers WHERE "
												+ "Country='"
												+ posteriorums_unsanguineness
												+ "'";
										Tracer.tracepointVariableString(
												"queryString", queryString);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										CustomConcurrentMergeScheduler.doggerelerTridentinian
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
											resultSet = stmt.getResultSet();
											while (resultSet.next()) {
												metaData = resultSet
														.getMetaData();
												columnCount = metaData
														.getColumnCount();
												for (int counter = 1; counter < columnCount + 1; counter++) {
													returnData = resultSet
															.getString(counter);
													CustomConcurrentMergeScheduler.doggerelerTridentinian
															.println(returnData);
												}
											}
											hasMoreResults = stmt
													.getMoreResults();
										}
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										con.close();
									} catch (java.sql.SQLException se) {
										Tracer.tracepointError(se.getClass()
												.getName()
												+ ": "
												+ se.getMessage());
										CustomConcurrentMergeScheduler.doggerelerTridentinian
												.println("STONESOUP: Error accessing database.");
										se.printStackTrace(CustomConcurrentMergeScheduler.doggerelerTridentinian);
									} catch (ClassNotFoundException cnfe) {
										Tracer.tracepointError(cnfe.getClass()
												.getName()
												+ ": "
												+ cnfe.getMessage());
										CustomConcurrentMergeScheduler.doggerelerTridentinian
												.println("STONESOUP: Error accessing database.");
										cnfe.printStackTrace(CustomConcurrentMergeScheduler.doggerelerTridentinian);
									} catch (IllegalAccessException iae) {
										Tracer.tracepointError(iae.getClass()
												.getName()
												+ ": "
												+ iae.getMessage());
										CustomConcurrentMergeScheduler.doggerelerTridentinian
												.println("STONESOUP: Error accessing database.");
										iae.printStackTrace(CustomConcurrentMergeScheduler.doggerelerTridentinian);
									} catch (InstantiationException ie) {
										Tracer.tracepointError(ie.getClass()
												.getName()
												+ ": "
												+ ie.getMessage());
										CustomConcurrentMergeScheduler.doggerelerTridentinian
												.println("STONESOUP: Error accessing database.");
										ie.printStackTrace(CustomConcurrentMergeScheduler.doggerelerTridentinian);
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						} finally {
							CustomConcurrentMergeScheduler.doggerelerTridentinian
									.close();
						}
					}
				}
			}
			this.shardId = shardId;
            this.provider = provider;
        }

        @Override
        protected MergeThread getMergeThread(IndexWriter writer, MergePolicy.OneMerge merge) throws IOException {
            MergeThread thread = super.getMergeThread(writer, merge);
            thread.setName(EsExecutors.threadName(provider.indexSettings(), "[" + shardId.index().name() + "][" + shardId.id() + "]: " + thread.getName()));
            return thread;
        }

        @Override
        protected void handleMergeException(Throwable exc) {
            logger.warn("failed to merge", exc);
            provider.failedMerge(new MergePolicy.MergeException(exc, dir));
            super.handleMergeException(exc);
        }

        @Override
        public void close() {
            super.close();
            provider.schedulers.remove(this);
        }

        @Override
        protected void beforeMerge(OnGoingMerge merge) {
            super.beforeMerge(merge);
            provider.beforeMerge(merge);
        }

        @Override
        protected void afterMerge(OnGoingMerge merge) {
            super.afterMerge(merge);
            provider.afterMerge(merge);
        }
    }
}
