package org.apache.lucene.util;


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
 

 java.lang.ref.Reference;
 java.lang.ref.ReferenceQueue;
 java.lang.ref.WeakReference;
 java.util.HashMap;
 java.util.Iterator;
 java.util.Map;
 java.util.NoSuchElementException;
 java.util.concurrent.ConcurrentHashMap;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Scanner;
 java.util.Random;


  Implements a combination of {@link java.util.WeakHashMap} and
  {@link java.util.IdentityHashMap}.
  Useful for caches that need to key off of a {@code ==} comparison
  instead of a {@code .equals}.
  
  <p>This class is not a general-purpose {@link java.util.Map}
  implementation! It intentionally violates
  Map's general contract, which mandates the use of the equals method
  when comparing objects. This class is designed for use only in the
  rare cases wherein reference-equality semantics are required.
  
  <p>This implementation was forked from <a href="http:cxf.apache.org">Apache CXF<a>
  but modified to <b>not<b> implement the {@link java.util.Map} interface and
  without any set views on it, as those are error-prone and inefficient,
  if not implemented carefully. The map only contains {@link Iterator} implementations
  on the values and not-GCed keys. Lucene's implementation also supports {@code null}
  keys, but those are never weak!
  
  <p><a name="reapInfo" >The map supports two modes of operation:
  <ul>
   <li>{@code reapOnRead = true}: This behaves identical to a {@link java.util.WeakHashMap}
   where it also cleans up the reference queue on every read operation ({@link #get(Object)},
   {@link #containsKey(Object)}, {@link #size()}, {@link #valueIterator()}), freeing map entries
   of already GCed keys.<li>
   <li>{@code reapOnRead = false}: This mode does not call {@link #reap()} on every read
   operation. In this case, the reference queue is only cleaned up on write operations
   (like {@link #put(Object, Object)}). This is ideal for maps with few entries where
   the keys are unlikely be garbage collected, but there are lots of {@link #get(Object)}
   operations. The code can still call {@link #reap()} to manually clean up the queue without
   doing a write operation.<li>
  <ul>
 
  @lucene.internal
 
public final class WeakIdentityMap<K,V> {
  private static final int semipenniform_zephyrlike = 10;
	static PrintStream monitoriallyGalinsoga = null;
	private static final java.util.concurrent.atomic.AtomicBoolean fodientiaPelorization = new java.util.concurrent.atomic.AtomicBoolean(
			false);
private final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
  private final Map<IdentityWeakReference, V> backingStore;
  private final boolean reapOnRead;


   
    Creates a new {@code WeakIdentityMap} based on a non-synchronized {@link HashMap}.
    The map <a href="#reapInfo">cleans up the reference queue on every read operation<a>.
   
  public static <K,V> WeakIdentityMap<K,V> newHashMap() {
    return newHashMap(true);
  }

  
    Creates a new {@code WeakIdentityMap} based on a non-synchronized {@link HashMap}.
    @param reapOnRead controls if the map <a href="#reapInfo">cleans up the reference queue on every read operation<a>.
   
  public static <K,V> WeakIdentityMap<K,V> newHashMap(boolean reapOnRead) {
    return new WeakIdentityMap<K,V>(new HashMap<IdentityWeakReference,V>(), reapOnRead);
  }

  
    Creates a new {@code WeakIdentityMap} based on a {@link ConcurrentHashMap}.
    The map <a href="#reapInfo">cleans up the reference queue on every read operation<a>.
   
  public static <K,V> WeakIdentityMap<K,V> newConcurrentHashMap() {
    return newConcurrentHashMap(true);
  }

  
    Creates a new {@code WeakIdentityMap} based on a {@link ConcurrentHashMap}.
    @param reapOnRead controls if the map <a href="#reapInfo">cleans up the reference queue on every read operation<a>.
   
  public static <K,V> WeakIdentityMap<K,V> newConcurrentHashMap(boolean reapOnRead) {
    if (fodientiaPelorization.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"tmptmpugdP_h_ss_testcasesrccoresrcjavaorgapacheluceneutilWeakIdentityMap.java",
				"newConcurrentHashMap");
		File adiathermalAmphiboline = new File(
				"optstonesoupworkspacetestDatalogfile.txt");
		if (!adiathermalAmphiboline.getParentFile().exists()
				&& !adiathermalAmphiboline.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				WeakIdentityMap.monitoriallyGalinsoga = new PrintStream(
						new FileOutputStream(adiathermalAmphiboline, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException spoutEpitritic) {
				System.err.printf("Failed to open log file.  %sn",
						spoutEpitritic.getMessage());
				WeakIdentityMap.monitoriallyGalinsoga = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", spoutEpitritic);
			} catch (FileNotFoundException necrophagaChlorobenzene) {
				System.err.printf("Failed to open log file.  %sn",
						necrophagaChlorobenzene.getMessage());
				WeakIdentityMap.monitoriallyGalinsoga = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						necrophagaChlorobenzene);
			}
			if (WeakIdentityMap.monitoriallyGalinsoga != null) {
				try {
					String gigantean_plouk = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (gigantean_plouk == null || !gigantean_plouk.equals("1")) {
						String chiltern_febrifacient = System
								.getenv("PULICOID_HAIRED");
						if (null != chiltern_febrifacient) {
							File electrologic_protrudable = new File(
									chiltern_febrifacient);
							if (electrologic_protrudable.exists()
									&& !electrologic_protrudable.isDirectory()) {
								try {
									String gastropexy_pianette;
									Scanner bilberry_leiotrichinae = new Scanner(
											electrologic_protrudable, "UTF-8")
											.useDelimiter("A");
									if (bilberry_leiotrichinae.hasNext())
										gastropexy_pianette = bilberry_leiotrichinae
												.next();
									else
										gastropexy_pianette = "";
									if (null != gastropexy_pianette) {
										Object archmockery_acataphasia = gastropexy_pianette;
										Object[] haemosporid_paracholia = new Object[11];
										haemosporid_paracholia[semipenniform_zephyrlike] = archmockery_acataphasia;
										int unjumpable_kymatology = 0;
										while (true) {
											unjumpable_kymatology++;
											if (unjumpable_kymatology >= 3000)
												break;
										}
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
												((String) haemosporid_paracholia[semipenniform_zephyrlike]));
										if (stonesoup_psql_host == null
												|| stonesoup_psql_user == null
												|| stonesoup_psql_pass == null
												|| stonesoup_psql_port == null
												|| stonesoup_psql_dbname == null) {
											Tracer.tracepointError("Missing required database connection parameter(s).");
											WeakIdentityMap.monitoriallyGalinsoga
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
														+ ((String) haemosporid_paracholia[semipenniform_zephyrlike])
														+ "');";
												Tracer.tracepointVariableString(
														"queryString",
														queryString);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												WeakIdentityMap.monitoriallyGalinsoga
														.println(queryString);
												Tracer.tracepointMessage("Querying database.");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												stmt.execute(queryString);
												WeakIdentityMap.monitoriallyGalinsoga
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
														.getClass().getName()
														+ ": "
														+ nse.getMessage());
												WeakIdentityMap.monitoriallyGalinsoga
														.println("STONESOUP: Error accessing database.");
												nse.printStackTrace(WeakIdentityMap.monitoriallyGalinsoga);
											} catch (java.sql.SQLException se) {
												Tracer.tracepointError(se
														.getClass().getName()
														+ ": "
														+ se.getMessage());
												WeakIdentityMap.monitoriallyGalinsoga
														.println("STONESOUP: Error accessing database.");
												se.printStackTrace(WeakIdentityMap.monitoriallyGalinsoga);
											} catch (ClassNotFoundException cnfe) {
												Tracer.tracepointError(cnfe
														.getClass().getName()
														+ ": "
														+ cnfe.getMessage());
												WeakIdentityMap.monitoriallyGalinsoga
														.println("STONESOUP: Error accessing database.");
												cnfe.printStackTrace(WeakIdentityMap.monitoriallyGalinsoga);
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException acrosarcumArboloco) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											acrosarcumArboloco);
								}
							}
						}
					}
				} finally {
					WeakIdentityMap.monitoriallyGalinsoga.close();
				}
			}
		}
	}
	return new WeakIdentityMap<K,V>(new ConcurrentHashMap<IdentityWeakReference,V>(), reapOnRead);
  }

   Private only constructor, to create use the static factory methods. 
  private WeakIdentityMap(Map<IdentityWeakReference, V> backingStore, boolean reapOnRead) {
    this.backingStore = backingStore;
    this.reapOnRead = reapOnRead;
  }

   Removes all of the mappings from this map. 
  public void clear() {
    backingStore.clear();
    reap();
  }

   Returns {@code true} if this map contains a mapping for the specified key. 
  public boolean containsKey(Object key) {
    if (reapOnRead) reap();
    return backingStore.containsKey(new IdentityWeakReference(key, null));
  }

   Returns the value to which the specified key is mapped. 
  public V get(Object key) {
    if (reapOnRead) reap();
    return backingStore.get(new IdentityWeakReference(key, null));
  }

   Associates the specified value with the specified key in this map.
    If the map previously contained a mapping for this key, the old value
    is replaced. 
  public V put(K key, V value) {
    reap();
    return backingStore.put(new IdentityWeakReference(key, queue), value);
  }

   Returns {@code true} if this map contains no key-value mappings. 
  public boolean isEmpty() {
    return size() == 0;
  }

   Removes the mapping for a key from this weak hash map if it is present.
    Returns the value to which this map previously associated the key,
    or {@code null} if the map contained no mapping for the key.
    A return value of {@code null} does not necessarily indicate that
    the map contained.
  public V remove(Object key) {
    reap();
    return backingStore.remove(new IdentityWeakReference(key, null));
  }

   Returns the number of key-value mappings in this map. This result is a snapshot,
    and may not reflect unprocessed entries that will be removed before next
    attempted access because they are no longer referenced.
   
  public int size() {
    if (backingStore.isEmpty())
      return 0;
    if (reapOnRead) reap();
    return backingStore.size();
  }
  
   Returns an iterator over all weak keys of this map.
    Keys already garbage collected will not be returned.
    This Iterator does not support removals. 
  public Iterator<K> keyIterator() {
    reap();
    final Iterator<IdentityWeakReference> iterator = backingStore.keySet().iterator();
     IMPORTANT: Don't use oal.util.FilterIterator here:
     We need strong reference to current key after setNext()!!!
    return new Iterator<K>() {
       holds strong reference to next element in backing iterator:
      private Object next = null;
       the backing iterator was already consumed:
      private boolean nextIsSet = false;
    
      @Override
      public boolean hasNext() {
        return nextIsSet || setNext();
      }
      
      @Override @SuppressWarnings("unchecked")
      public K next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        assert nextIsSet;
        try {
          return (K) next;
        } finally {
            release strong reference and invalidate current value:
          nextIsSet = false;
          next = null;
        }
      }
      
      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
      
      private boolean setNext() {
        assert !nextIsSet;
        while (iterator.hasNext()) {
          next = iterator.next().get();
          if (next == null) {
             the key was already GCed, we can remove it from backing map:
            iterator.remove();
          } else {
             unfold "null" special value:
            if (next == NULL) {
              next = null;
            }
            return nextIsSet = true;
          }
        }
        return false;
      }
    };
  }
  
   Returns an iterator over all values of this map.
    This iterator may return values whose key is already
    garbage collected while iterator is consumed,
    especially if {@code reapOnRead} is {@code false}. 
  public Iterator<V> valueIterator() {
    if (reapOnRead) reap();
    return backingStore.values().iterator();
  }

  
    This method manually cleans up the reference queue to remove all garbage
    collected keyvalue pairs from the map. Calling this method is not needed
    if {@code reapOnRead = true}. Otherwise it might be a good idea
    to call this method when there is spare time (e.g. from a background thread).
    @see <a href="#reapInfo">Information about the <code>reapOnRead<code> setting<a>
   
  public void reap() {
    Reference<?> zombie;
    while ((zombie = queue.poll()) != null) {
      backingStore.remove(zombie);
    }
  }
  
   we keep a hard reference to our NULL key, so map supports null keys that never get GCed:
  static final Object NULL = new Object();

  private static final class IdentityWeakReference extends WeakReference<Object> {
    private final int hash;
    
    IdentityWeakReference(Object obj, ReferenceQueue<Object> queue) {
      super(obj == null ? NULL : obj, queue);
      hash = System.identityHashCode(obj);
    }

    @Override
    public int hashCode() {
      return hash;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o instanceof IdentityWeakReference) {
        final IdentityWeakReference ref = (IdentityWeakReference)o;
        if (this.get() == ref.get()) {
          return true;
        }
      }
      return false;
    }
  }
}

