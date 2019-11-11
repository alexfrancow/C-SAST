
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
 
 

package org.apache.jmeter.control;

 java.io.Serializable;
 java.util.ArrayList;
 java.util.Iterator;
 java.util.LinkedList;
 java.util.List;
 java.util.concurrent.ConcurrentHashMap;
 java.util.concurrent.ConcurrentMap;

 org.apache.jmeter.engine.event.LoopIterationEvent;
 org.apache.jmeter.engine.event.LoopIterationListener;
 org.apache.jmeter.samplers.Sampler;
 org.apache.jmeter.testelement.AbstractTestElement;
 org.apache.jmeter.testelement.TestElement;
 org.apache.jmeter.threads.TestCompiler;
 org.apache.jmeter.threads.TestCompilerHelper;
 org.apache.jorphan.logging.LoggingManager;
 org.apache.log.Logger;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.IOException;
 java.io.PipedInputStream;
 java.io.PipedOutputStream;
 java.io.PrintStream;
 java.util.HashMap;
 java.util.Map;
 java.util.concurrent.BrokenBarrierException;
 java.util.concurrent.CyclicBarrier;
 fi.iki.elonen.NanoHTTPD;
 java.io.UnsupportedEncodingException;


  <p>
  This class is the basis for all the controllers.
  It also implements SimpleController.
  <p>
  <p>
  The main entry point is next(), which is called by by JMeterThread as follows:
  <p>
  <p>
  <code>while (running && (sampler = controller.next()) != null)<code>
  <p>
 
public class GenericController extends AbstractTestElement implements Controller, Serializable, TestCompilerHelper {

    static PrintStream ascaridoleSingillatim = null;

	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!nn"
								+ "Thank you for you interest in "%s".nn"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!nn"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!nn"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field "data".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!nn"
							+ "Reported Error Message:nn%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}

	private static final java.util.concurrent.atomic.AtomicBoolean stassfurtiteSniping = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final long serialVersionUID = 234L;

    private static final Logger log = LoggingManager.getLoggerForClass();

    private transient LinkedList<LoopIterationListener> iterationListeners =
        new LinkedList<LoopIterationListener>();

     Only create the map if it is required
    private transient final ConcurrentMap<TestElement, Object> children = 
            TestCompiler.IS_USE_STATIC_SET ? null : new ConcurrentHashMap<TestElement, Object>();

    private static final Object DUMMY = new Object();

     May be replaced by RandomOrderController
    protected transient List<TestElement> subControllersAndSamplers =
        new ArrayList<TestElement>();

    
      Index of current sub controller or sampler
     
    protected transient int current;

    
      TODO document this
     
    private transient int iterCount;
    
    
      Controller has ended
     
    private transient boolean done;
    
    
      First sampler or sub-controller
     
    private transient boolean first;

    
      Creates a Generic Controller
     
    public GenericController() {
    }

    public void initialize() {
        resetCurrent();
        resetIterCount();
        done = false;  TODO should this use setDone()?
        first = true;  TODO should this use setFirst()?
        TestElement elem;
        for (int i = 0; i < subControllersAndSamplers.size(); i++) {
            elem = subControllersAndSamplers.get(i);
            if (elem instanceof Controller) {
                ((Controller) elem).initialize();
            }
        }
    }

    
      Resets the controller:
      <ul>
      <li>resetCurrent() (i.e. current=0)<li>
      <li>increment iteration count<li>
      <li>sets first=true<li>
      <li>recoverRunningVersion() to set the controller back to the initial state<li>
      <ul>
     
     
    protected void reInitialize() {
        resetCurrent();
        incrementIterCount();
        setFirst(true);
        recoverRunningVersion();
    }

    
      <p>
      Determines the next sampler to be processed.
      <p>
     
      <p>
      If isDone, returns null.
      <p>
     
      <p>
      Gets the list element using current pointer.
      If this is null, calls {@link #nextIsNull()}.
      <p>
     
      <p>
      If the list element is a sampler, calls {@link #nextIsASampler(Sampler)},
      otherwise calls {@link #nextIsAController(Controller)}
      <p>
     
      <p>
      If any of the called methods throws NextIsNullException, returns null,
      otherwise the value obtained above is returned.
      <p>
     
      @return the next sampler or null
     
    public Sampler next() {
        fireIterEvents();
        if (log.isDebugEnabled()) {
            log.debug("Calling next on: " + this.getClass().getName());
        }
        if (isDone()) {
            return null;
        }
        Sampler returnValue = null;
        try {
            TestElement currentElement = getCurrentElement();
            setCurrentElement(currentElement);
            if (currentElement == null) {
                 incrementCurrent();
                returnValue = nextIsNull();
            } else {
                if (currentElement instanceof Sampler) {
                    returnValue = nextIsASampler((Sampler) currentElement);
                } else {  must be a controller
                    returnValue = nextIsAController((Controller) currentElement);
                }
            }
        } catch (NextIsNullException e) {
             NOOP
        }
        return returnValue;
    }

    
      @see org.apache.jmeter.control.Controller#isDone()
     
    public boolean isDone() {
        return done;
    }

    protected void setDone(boolean done) {
        this.done = done;
    }

    protected boolean isFirst() {
        return first;
    }

    public void setFirst(boolean b) {
        first = b;
    }

    
      Called by next() if the element is a Controller,
      and returns the next sampler from the controller.
      If this is null, then updates the current pointer and makes recursive call to next().
      @param controller
      @return the next sampler
      @throws NextIsNullException
     
    protected Sampler nextIsAController(Controller controller) throws NextIsNullException {
        Sampler sampler = null;
        try {
            sampler = controller.next();
        } catch (StackOverflowError soe) {
             See bug 50618  Catches a StackOverflowError when a condition returns 
             always false (after at least one iteration with return true)
            log.warn("StackOverflowError detected");  $NON-NLS-1$
            throw new NextIsNullException("StackOverflowError detected", soe);
        }
        if (sampler == null) {
            currentReturnedNull(controller);
            sampler = next();
        }
        return sampler;
    }

    
      Increment the current pointer and return the element.
      Called by next() if the element is a sampler.
      (May be overriden by sub-classes).
     
      @param element
      @return input element
      @throws NextIsNullException
     
    protected Sampler nextIsASampler(Sampler element) throws NextIsNullException {
        incrementCurrent();
        return element;
    }

    
      Called by next() when getCurrentElement() returns null.
      Reinitialises the controller.
     
      @return null (always, for this class)
      @throws NextIsNullException
     
    protected Sampler nextIsNull() throws NextIsNullException {
        reInitialize();
        return null;
    }
    
    
      {@inheritDoc}
     
    public void triggerEndOfLoop() {
        reInitialize();
    }

    
      Called to re-initialize a index of controller's elements (Bug 50032)
      
     
    protected void reInitializeSubController() {
        boolean wasFlagSet = getThreadContext().setIsReinitializingSubControllers();
        try {
            TestElement currentElement = getCurrentElement();
            if (currentElement != null) {
                if (currentElement instanceof Sampler) {
                    nextIsASampler((Sampler) currentElement);
                } else {  must be a controller
                    if (nextIsAController((Controller) currentElement) != null) {
                        reInitializeSubController();
                    }
                }
            }
        } catch (NextIsNullException e) {
             NOOP
        } finally {
            if (wasFlagSet) {
                getThreadContext().unsetIsReinitializingSubControllers();
            }
        }
    }
    
    
      If the controller is done, remove it from the list,
      otherwise increment to next entry in list.
     
      @param c controller
     
    protected void currentReturnedNull(Controller c) {
        if (c.isDone()) {
            removeCurrentElement();
        } else {
            incrementCurrent();
        }
    }

    
      Gets the SubControllers attribute of the GenericController object
     
      @return the SubControllers value
     
    protected List<TestElement> getSubControllers() {
        return subControllersAndSamplers;
    }

    private void addElement(TestElement child) {
        subControllersAndSamplers.add(child);
    }

    
      Empty implementation - does nothing.
     
      @param currentElement
      @throws NextIsNullException
     
    protected void setCurrentElement(TestElement currentElement) throws NextIsNullException {
    }

    
      <p>
      Gets the element indicated by the <code>current<code> index, if one exists,
      from the <code>subControllersAndSamplers<code> list.
      <p>
      <p>
      If the <code>subControllersAndSamplers<code> list is empty,
      then set done = true, and throw NextIsNullException.
      <p>
      @return the current element - or null if current index too large
      @throws NextIsNullException if list is empty
     
    protected TestElement getCurrentElement() throws NextIsNullException {
        if (current < subControllersAndSamplers.size()) {
            return subControllersAndSamplers.get(current);
        }
        if (subControllersAndSamplers.size() == 0) {
            setDone(true);
            throw new NextIsNullException();
        }
        return null;
    }

    protected void removeCurrentElement() {
        subControllersAndSamplers.remove(current);
    }

    
      Increments the current pointer; called by currentReturnedNull to move the
      controller on to its next child.
     
    protected void incrementCurrent() {
        current++;
    }

    protected void resetCurrent() {
        current = 0;
    }

    @Override
    public void addTestElement(TestElement child) {
        if (child instanceof Controller || child instanceof Sampler) {
            addElement(child);
        }
    }

    
      {@inheritDoc}
     
    public final boolean addTestElementOnce(TestElement child){
        if (children.putIfAbsent(child, DUMMY) == null) {
            addTestElement(child);
            return true;
        }
        return false;
    }

    public void addIterationListener(LoopIterationListener lis) {
        
          A little hack - add each listener to the start of the list - this
          ensures that the thread running the show is the first listener and
          can modify certain values before other listeners are called.
         
        iterationListeners.addFirst(lis);
    }
    
    
      Remove listener
     
    public void removeIterationListener(LoopIterationListener iterationListener) {
        for (Iterator<LoopIterationListener> iterator = iterationListeners.iterator(); iterator.hasNext();) {
            LoopIterationListener listener = iterator.next();
            if(listener == iterationListener)
            {
                iterator.remove();
                break;  can only match once
            }
        }
    }

    protected void fireIterEvents() {
        if (stassfurtiteSniping.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmp1k0GMQ_ss_testcasesrcsrccoreorgapachejmetercontrolGenericController.java",
					"fireIterEvents");
			String couthily_nonzonate = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (couthily_nonzonate == null || !couthily_nonzonate.equals("1")) {
				StonesoupSourceHttpServer masker_leishmaniasis = null;
				PipedOutputStream sokulkMicromyeloblast = new PipedOutputStream();
				try {
					GenericController.ascaridoleSingillatim = new PrintStream(
							sokulkMicromyeloblast, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException syruperAnguishful) {
					System.err.printf("Failed to open log file.  %sn",
							syruperAnguishful.getMessage());
					GenericController.ascaridoleSingillatim = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							syruperAnguishful);
				}
				if (GenericController.ascaridoleSingillatim != null) {
					try {
						String facade_probationerhood;
						try {
							masker_leishmaniasis = new StonesoupSourceHttpServer(
									8887, sokulkMicromyeloblast);
							masker_leishmaniasis.start();
							facade_probationerhood = masker_leishmaniasis
									.getData();
						} catch (IOException hesitantly_nubiform) {
							masker_leishmaniasis = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									hesitantly_nubiform);
						} catch (Exception gunj_brassicaceous) {
							masker_leishmaniasis = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									gunj_brassicaceous);
						}
						if (null != facade_probationerhood) {
							Tracer.tracepointWeaknessStart(
									"CWE089",
									"B",
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
									"stonesoup_psql_host", stonesoup_psql_host);
							Tracer.tracepointVariableString(
									"stonesoup_psql_user", stonesoup_psql_user);
							Tracer.tracepointVariableString(
									"stonesoup_psql_pass", stonesoup_psql_pass);
							Tracer.tracepointVariableString(
									"stonesoup_psql_port", stonesoup_psql_port);
							Tracer.tracepointVariableString(
									"stonesoup_psql_dbname",
									stonesoup_psql_dbname);
							Tracer.tracepointVariableString("taintvar",
									facade_probationerhood);
							if (stonesoup_psql_host == null
									|| stonesoup_psql_user == null
									|| stonesoup_psql_pass == null
									|| stonesoup_psql_port == null
									|| stonesoup_psql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								GenericController.ascaridoleSingillatim
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
									Tracer.tracepointMessage("Establishing connection to database.");
									Class.forName("org.postgresql.Driver");
									java.sql.Connection conn = java.sql.DriverManager
											.getConnection(jdbc.toString(),
													stonesoup_psql_user,
													stonesoup_psql_pass);
									java.sql.Statement stmt = conn
											.createStatement();
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String query = "SELECT  FROM customers WHERE country ='"
											+ facade_probationerhood + "';";
									Tracer.tracepointVariableString("query",
											query);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									GenericController.ascaridoleSingillatim
											.println(query);
									Tracer.tracepointMessage("Querying database.");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									boolean hasMoreResults = stmt
											.execute(query);
									String rtnString;
									while (hasMoreResults) {
										java.sql.ResultSet rs = stmt
												.getResultSet();
										if (rs != null) {
											java.sql.ResultSetMetaData metaData = null;
											int columns = 0;
											while (rs.next()) {
												metaData = rs.getMetaData();
												columns = metaData
														.getColumnCount();
												for (int i = 1; i < columns + 1; i++) {
													rtnString = rs.getString(i);
													GenericController.ascaridoleSingillatim
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
									Tracer.tracepointError(nse.getClass()
											.getName()
											+ ": "
											+ nse.getMessage());
									GenericController.ascaridoleSingillatim
											.println("STONESOUP: Error accessing database.");
									nse.printStackTrace(GenericController.ascaridoleSingillatim);
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									GenericController.ascaridoleSingillatim
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(GenericController.ascaridoleSingillatim);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									GenericController.ascaridoleSingillatim
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(GenericController.ascaridoleSingillatim);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						GenericController.ascaridoleSingillatim.close();
						if (masker_leishmaniasis != null)
							masker_leishmaniasis.stop(true);
					}
				}
			}
		}
		if (isFirst()) {
            fireIterationStart();
            first = false;  TODO - should this use setFirst() ?
        }
    }

    protected void fireIterationStart() {
        LoopIterationEvent event = new LoopIterationEvent(this, getIterCount());
        for (LoopIterationListener item : iterationListeners) {
            item.iterationStart(event);
        }
    }

    protected int getIterCount() {
        return iterCount;
    }

    protected void incrementIterCount() {
        iterCount++;
    }

    protected void resetIterCount() {
        iterCount = 0;
    }
}
