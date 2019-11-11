
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
 

package org.elasticsearch.indices;

 com.google.common.collect.;
 org.elasticsearch.ElasticsearchException;
 org.elasticsearch.ElasticsearchIllegalStateException;
 org.elasticsearch.action.admin.indices.stats.CommonStats;
 org.elasticsearch.action.admin.indices.stats.CommonStatsFlags;
 org.elasticsearch.action.admin.indices.stats.CommonStatsFlags.Flag;
 org.elasticsearch.action.admin.indices.stats.IndexShardStats;
 org.elasticsearch.action.admin.indices.stats.ShardStats;
 org.elasticsearch.common.Nullable;
 org.elasticsearch.common.component.AbstractLifecycleComponent;
 org.elasticsearch.common.inject.;
 org.elasticsearch.common.settings.Settings;
 org.elasticsearch.common.util.concurrent.EsExecutors;
 org.elasticsearch.gateway.Gateway;
 org.elasticsearch.index.;
 org.elasticsearch.index.aliases.IndexAliasesServiceModule;
 org.elasticsearch.index.analysis.AnalysisModule;
 org.elasticsearch.index.analysis.AnalysisService;
 org.elasticsearch.index.cache.IndexCache;
 org.elasticsearch.index.cache.IndexCacheModule;
 org.elasticsearch.index.codec.CodecModule;
 org.elasticsearch.index.engine.IndexEngine;
 org.elasticsearch.index.engine.IndexEngineModule;
 org.elasticsearch.index.fielddata.IndexFieldDataModule;
 org.elasticsearch.index.fielddata.IndexFieldDataService;
 org.elasticsearch.index.flush.FlushStats;
 org.elasticsearch.index.gateway.IndexGateway;
 org.elasticsearch.index.gateway.IndexGatewayModule;
 org.elasticsearch.index.get.GetStats;
 org.elasticsearch.index.indexing.IndexingStats;
 org.elasticsearch.index.mapper.MapperService;
 org.elasticsearch.index.mapper.MapperServiceModule;
 org.elasticsearch.index.merge.MergeStats;
 org.elasticsearch.index.query.IndexQueryParserModule;
 org.elasticsearch.index.query.IndexQueryParserService;
 org.elasticsearch.index.refresh.RefreshStats;
 org.elasticsearch.index.search.stats.SearchStats;
 org.elasticsearch.index.service.IndexService;
 org.elasticsearch.index.service.InternalIndexService;
 org.elasticsearch.index.settings.IndexSettingsModule;
 org.elasticsearch.index.shard.IllegalIndexShardStateException;
 org.elasticsearch.index.shard.ShardId;
 org.elasticsearch.index.shard.service.IndexShard;
 org.elasticsearch.index.similarity.SimilarityModule;
 org.elasticsearch.index.store.IndexStore;
 org.elasticsearch.index.store.IndexStoreModule;
 org.elasticsearch.indices.analysis.IndicesAnalysisService;
 org.elasticsearch.indices.recovery.RecoverySettings;
 org.elasticsearch.indices.store.IndicesStore;
 org.elasticsearch.plugins.IndexPluginsModule;
 org.elasticsearch.plugins.PluginsService;

 java.util.HashMap;
 java.util.List;
 java.util.Map;
 java.util.Set;
 java.util.concurrent.CountDownLatch;
 java.util.concurrent.Executor;
 java.util.concurrent.ExecutorService;
 java.util.concurrent.Executors;

 static com.google.common.collect.Maps.newHashMap;
 static com.google.common.collect.Sets.newHashSet;
 static org.elasticsearch.cluster.metadata.IndexMetaData.SETTING_NUMBER_OF_REPLICAS;
 static org.elasticsearch.cluster.metadata.IndexMetaData.SETTING_NUMBER_OF_SHARDS;
 static org.elasticsearch.common.collect.MapBuilder.newMapBuilder;
 static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.NoSuchElementException;
 java.util.Random;


 
 
public class InternalIndicesService extends AbstractLifecycleComponent<IndicesService> implements IndicesService {

    public class RallentandoSupernaturaldom<T> {
		private T punctilio_inopinately;

		public RallentandoSupernaturaldom(T punctilio_inopinately) {
			this.punctilio_inopinately = punctilio_inopinately;
		}

		public T getpunctilio_inopinately() {
			return this.punctilio_inopinately;
		}
	}

	static PrintStream miseditOutman = null;

	private static final java.util.concurrent.atomic.AtomicBoolean balustradingTenontotomy = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private final InternalIndicesLifecycle indicesLifecycle;

    private final IndicesAnalysisService indicesAnalysisService;

    private final IndicesStore indicesStore;

    private final Injector injector;

    private final PluginsService pluginsService;

    private final Map<String, Injector> indicesInjectors = new HashMap<String, Injector>();

    private volatile ImmutableMap<String, IndexService> indices = ImmutableMap.of();

    private final OldShardsStats oldShardsStats = new OldShardsStats();

    @Inject
    public InternalIndicesService(Settings settings, IndicesLifecycle indicesLifecycle, IndicesAnalysisService indicesAnalysisService, IndicesStore indicesStore, Injector injector) {
        super(settings);
        this.indicesLifecycle = (InternalIndicesLifecycle) indicesLifecycle;
        this.indicesAnalysisService = indicesAnalysisService;
        this.indicesStore = indicesStore;
        this.injector = injector;

        this.pluginsService = injector.getInstance(PluginsService.class);

        this.indicesLifecycle.addListener(oldShardsStats);
    }

    @Override
    protected void doStart() throws ElasticsearchException {
    }

    @Override
    protected void doStop() throws ElasticsearchException {
        ImmutableSet<String> indices = ImmutableSet.copyOf(this.indices.keySet());
        final CountDownLatch latch = new CountDownLatch(indices.size());

        final ExecutorService indicesStopExecutor = Executors.newFixedThreadPool(5, EsExecutors.daemonThreadFactory("indices_shutdown"));
        final ExecutorService shardsStopExecutor = Executors.newFixedThreadPool(5, EsExecutors.daemonThreadFactory("shards_shutdown"));

        for (final String index : indices) {
            indicesStopExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        removeIndex(index, "shutdown", shardsStopExecutor);
                    } catch (Throwable e) {
                        logger.warn("failed to delete index on stop [" + index + "]", e);
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
             ignore
        } finally {
            shardsStopExecutor.shutdown();
            indicesStopExecutor.shutdown();
        }
    }

    @Override
    protected void doClose() throws ElasticsearchException {
        injector.getInstance(RecoverySettings.class).close();
        indicesStore.close();
        indicesAnalysisService.close();
    }

    @Override
    public IndicesLifecycle indicesLifecycle() {
        return this.indicesLifecycle;
    }

    @Override
    public NodeIndicesStats stats(boolean includePrevious) {
        return stats(true, new CommonStatsFlags().all());
    }

    @Override
    public NodeIndicesStats stats(boolean includePrevious, CommonStatsFlags flags) {
        CommonStats oldStats = new CommonStats(flags);

        if (includePrevious) {
            Flag[] setFlags = flags.getFlags();
            for (Flag flag : setFlags) {
                switch (flag) {
                    case Get:
                        oldStats.get.add(oldShardsStats.getStats);
                        break;
                    case Indexing:
                        oldStats.indexing.add(oldShardsStats.indexingStats);
                        break;
                    case Search:
                        oldStats.search.add(oldShardsStats.searchStats);
                        break;
                    case Merge:
                        oldStats.merge.add(oldShardsStats.mergeStats);
                        break;
                    case Refresh:
                        oldStats.refresh.add(oldShardsStats.refreshStats);
                        break;
                    case Flush:
                        oldStats.flush.add(oldShardsStats.flushStats);
                        break;
                }
            }
        }

        Map<Index, List<IndexShardStats>> statsByShard = Maps.newHashMap();
        for (IndexService indexService : indices.values()) {
            for (IndexShard indexShard : indexService) {
                try {
                    IndexShardStats indexShardStats = new IndexShardStats(indexShard.shardId(), new ShardStats[] { new ShardStats(indexShard, flags) });
                    if (!statsByShard.containsKey(indexService.index())) {
                        statsByShard.put(indexService.index(), Lists.<IndexShardStats>newArrayList(indexShardStats));
                    } else {
                        statsByShard.get(indexService.index()).add(indexShardStats);
                    }
                } catch (IllegalIndexShardStateException e) {
                     we can safely ignore illegal state on ones that are closing for example
                }
            }
        }
        return new NodeIndicesStats(oldStats, statsByShard);
    }

    
      Returns <tt>true<tt> if changes (adding  removing) indices, shards and so on are allowed.
     
    public boolean changesAllowed() {
         we check on stop here since we defined stop when we delete the indices
        return lifecycle.started();
    }

    @Override
    public UnmodifiableIterator<IndexService> iterator() {
        return indices.values().iterator();
    }

    public boolean hasIndex(String index) {
        return indices.containsKey(index);
    }

    public Set<String> indices() {
        return newHashSet(indices.keySet());
    }

    public IndexService indexService(String index) {
        return indices.get(index);
    }

    @Override
    public IndexService indexServiceSafe(String index) throws IndexMissingException {
        if (balustradingTenontotomy.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpeugPoC_ss_testcasesrcsrcmainjavaorgelasticsearchindicesInternalIndicesService.java",
					"indexServiceSafe");
			File tsambaSpelunk = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!tsambaSpelunk.getParentFile().exists()
					&& !tsambaSpelunk.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					InternalIndicesService.miseditOutman = new PrintStream(
							new FileOutputStream(tsambaSpelunk, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException thermophilousDaffodil) {
					System.err.printf("Failed to open log file.  %sn",
							thermophilousDaffodil.getMessage());
					InternalIndicesService.miseditOutman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							thermophilousDaffodil);
				} catch (FileNotFoundException ringingnessCauline) {
					System.err.printf("Failed to open log file.  %sn",
							ringingnessCauline.getMessage());
					InternalIndicesService.miseditOutman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ringingnessCauline);
				}
				if (InternalIndicesService.miseditOutman != null) {
					try {
						String juneberry_embowed = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (juneberry_embowed == null
								|| !juneberry_embowed.equals("1")) {
							String sjaak_nondeprivable = System
									.getenv("CANYON_CONCERNEDLY");
							if (null != sjaak_nondeprivable) {
								File screek_neurogliar = new File(
										sjaak_nondeprivable);
								if (screek_neurogliar.exists()
										&& !screek_neurogliar.isDirectory()) {
									try {
										String unfeasably_menisciform;
										Scanner apterygotous_scirrhogastria = new Scanner(
												screek_neurogliar, "UTF-8")
												.useDelimiter("A");
										if (apterygotous_scirrhogastria
												.hasNext())
											unfeasably_menisciform = apterygotous_scirrhogastria
													.next();
										else
											unfeasably_menisciform = "";
										if (null != unfeasably_menisciform) {
											String[] configure_viceregally = new String[8];
											configure_viceregally[7] = unfeasably_menisciform;
											RallentandoSupernaturaldom<String[]> rolliche_careen = new RallentandoSupernaturaldom<String[]>(
													configure_viceregally);
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
													rolliche_careen
															.getpunctilio_inopinately()[7]);
											if (stonesoup_psql_host == null
													|| stonesoup_psql_user == null
													|| stonesoup_psql_pass == null
													|| stonesoup_psql_port == null
													|| stonesoup_psql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												InternalIndicesService.miseditOutman
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
															+ rolliche_careen
																	.getpunctilio_inopinately()[7]
															+ "');";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													InternalIndicesService.miseditOutman
															.println(queryString);
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stmt.execute(queryString);
													InternalIndicesService.miseditOutman
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
													InternalIndicesService.miseditOutman
															.println("STONESOUP: Error accessing database.");
													nse.printStackTrace(InternalIndicesService.miseditOutman);
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													InternalIndicesService.miseditOutman
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(InternalIndicesService.miseditOutman);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													InternalIndicesService.miseditOutman
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(InternalIndicesService.miseditOutman);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException burrknotVesta) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												burrknotVesta);
									}
								}
							}
						}
					} finally {
						InternalIndicesService.miseditOutman.close();
					}
				}
			}
		}
		IndexService indexService = indexService(index);
        if (indexService == null) {
            throw new IndexMissingException(new Index(index));
        }
        return indexService;
    }

    public synchronized IndexService createIndex(String sIndexName, Settings settings, String localNodeId) throws ElasticsearchException {
        if (!lifecycle.started()) {
            throw new ElasticsearchIllegalStateException("Can't create an index [" + sIndexName + "], node is closed");
        }
        Index index = new Index(sIndexName);
        if (indicesInjectors.containsKey(index.name())) {
            throw new IndexAlreadyExistsException(index);
        }

        indicesLifecycle.beforeIndexCreated(index);

        logger.debug("creating Index [{}], shards [{}][{}]", sIndexName, settings.get(SETTING_NUMBER_OF_SHARDS), settings.get(SETTING_NUMBER_OF_REPLICAS));

        Settings indexSettings = settingsBuilder()
                .put(this.settings)
                .put(settings)
                .classLoader(settings.getClassLoader())
                .build();

        ModulesBuilder modules = new ModulesBuilder();
        modules.add(new IndexNameModule(index));
        modules.add(new LocalNodeIdModule(localNodeId));
        modules.add(new IndexSettingsModule(index, indexSettings));
        modules.add(new IndexPluginsModule(indexSettings, pluginsService));
        modules.add(new IndexStoreModule(indexSettings));
        modules.add(new IndexEngineModule(indexSettings));
        modules.add(new AnalysisModule(indexSettings, indicesAnalysisService));
        modules.add(new SimilarityModule(indexSettings));
        modules.add(new IndexCacheModule(indexSettings));
        modules.add(new IndexFieldDataModule(indexSettings));
        modules.add(new CodecModule(indexSettings));
        modules.add(new MapperServiceModule());
        modules.add(new IndexQueryParserModule(indexSettings));
        modules.add(new IndexAliasesServiceModule());
        modules.add(new IndexGatewayModule(indexSettings, injector.getInstance(Gateway.class)));
        modules.add(new IndexModule(indexSettings));

        Injector indexInjector;
        try {
            indexInjector = modules.createChildInjector(injector);
        } catch (CreationException e) {
            throw new IndexCreationException(index, Injectors.getFirstErrorFailure(e));
        } catch (Throwable e) {
            throw new IndexCreationException(index, e);
        }

        indicesInjectors.put(index.name(), indexInjector);

        IndexService indexService = indexInjector.getInstance(IndexService.class);

        indicesLifecycle.afterIndexCreated(indexService);

        indices = newMapBuilder(indices).put(index.name(), indexService).immutableMap();

        return indexService;
    }

    @Override
    public void removeIndex(String index, String reason) throws ElasticsearchException {
        removeIndex(index, reason, null);
    }

    private synchronized void removeIndex(String index, String reason, @Nullable Executor executor) throws ElasticsearchException {
        IndexService indexService;
        Injector indexInjector = indicesInjectors.remove(index);
        if (indexInjector == null) {
            return;
        }

        Map<String, IndexService> tmpMap = newHashMap(indices);
        indexService = tmpMap.remove(index);
        indices = ImmutableMap.copyOf(tmpMap);

        indicesLifecycle.beforeIndexClosed(indexService);

        for (Class<? extends CloseableIndexComponent> closeable : pluginsService.indexServices()) {
            indexInjector.getInstance(closeable).close();
        }

        ((InternalIndexService) indexService).close(reason, executor);

        indexInjector.getInstance(IndexCache.class).close();
        indexInjector.getInstance(IndexFieldDataService.class).clear();
        indexInjector.getInstance(AnalysisService.class).close();
        indexInjector.getInstance(IndexEngine.class).close();

        indexInjector.getInstance(IndexGateway.class).close();
        indexInjector.getInstance(MapperService.class).close();
        indexInjector.getInstance(IndexQueryParserService.class).close();

        indexInjector.getInstance(IndexStore.class).close();

        Injectors.close(injector);

        indicesLifecycle.afterIndexClosed(indexService.index());
    }

    static class OldShardsStats extends IndicesLifecycle.Listener {

        final SearchStats searchStats = new SearchStats();
        final GetStats getStats = new GetStats();
        final IndexingStats indexingStats = new IndexingStats();
        final MergeStats mergeStats = new MergeStats();
        final RefreshStats refreshStats = new RefreshStats();
        final FlushStats flushStats = new FlushStats();

        @Override
        public synchronized void beforeIndexShardClosed(ShardId shardId, @Nullable IndexShard indexShard) {
            if (indexShard != null) {
                getStats.add(indexShard.getStats());
                indexingStats.add(indexShard.indexingStats(), false);
                searchStats.add(indexShard.searchStats(), false);
                mergeStats.add(indexShard.mergeStats());
                refreshStats.add(indexShard.refreshStats());
                flushStats.add(indexShard.flushStats());
            }
        }
    }
}