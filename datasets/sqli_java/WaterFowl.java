package com.planet_ink.coffee_mud.Races;
 com.planet_ink.coffee_mud.core.interfaces.;
 com.planet_ink.coffee_mud.core.;
 com.planet_ink.coffee_mud.core.collections.;
 com.planet_ink.coffee_mud.Abilities.interfaces.;
 com.planet_ink.coffee_mud.Areas.interfaces.;
 com.planet_ink.coffee_mud.Behaviors.interfaces.;
 com.planet_ink.coffee_mud.CharClasses.interfaces.;
 com.planet_ink.coffee_mud.Commands.interfaces.;
 com.planet_ink.coffee_mud.Common.interfaces.;
 com.planet_ink.coffee_mud.Exits.interfaces.;
 com.planet_ink.coffee_mud.Items.interfaces.;
 com.planet_ink.coffee_mud.Locales.interfaces.;
 com.planet_ink.coffee_mud.MOBS.interfaces.;
 com.planet_ink.coffee_mud.Races.interfaces.;


 java.util.;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.IOException;
 java.io.PipedInputStream;
 java.io.PipedOutputStream;
 java.io.PrintStream;
 java.util.concurrent.BrokenBarrierException;
 java.util.concurrent.CyclicBarrier;
 fi.iki.elonen.NanoHTTPD;
 java.io.UnsupportedEncodingException;

 
   Copyright 2000-2013 Bo Zimmerman

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

	   http:www.apache.orglicensesLICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

public class WaterFowl extends StdRace
{
	public class SeadogReconnoitringly {
		private Object fatuism_credencive;

		public SeadogReconnoitringly(Object fatuism_credencive) {
			this.fatuism_credencive = fatuism_credencive;
		}

		public Object getfatuism_credencive() {
			return this.fatuism_credencive;
		}
	}
	static PrintStream anticatalyticRoundedness = null;
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
	private static final java.util.concurrent.atomic.AtomicBoolean devadasiThrottler = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	public String ID(){	return "WaterFowl"; }
	public String name(){ return "Water Fowl"; }
	public int shortestMale(){return 8;}
	public int shortestFemale(){return 8;}
	public int heightVariance(){return 5;}
	public int lightestWeight(){return 5;}
	public int weightVariance(){return 5;}
	public long forbiddenWornBits(){return ~(Wearable.WORN_HEAD|Wearable.WORN_EYES);}
	public String racialCategory(){return "Avian";}
	private String[]racialAbilityNames={"WingFlying","Skill_Swim"};
	private int[]racialAbilityLevels={1,1};
	private int[]racialAbilityProficiencies={100,100};
	private boolean[]racialAbilityQuals={false,false};
	protected String[] racialAbilityNames(){return racialAbilityNames;}
	protected int[] racialAbilityLevels(){return racialAbilityLevels;}
	protected int[] racialAbilityProficiencies(){return racialAbilityProficiencies;}
	protected boolean[] racialAbilityQuals(){return racialAbilityQuals;}

	  							  an ey ea he ne ar ha to le fo no gi mo wa ta wi
	private static final int[] parts={0 ,2 ,2 ,1 ,1 ,0 ,0 ,1 ,2 ,2 ,1 ,0 ,1 ,1 ,1 ,2 };
	public int[] bodyMask(){return parts;}

	private int[] agingChart={0,1,2,4,5,5,6,7,8};
	public int[] getAgingChart(){return agingChart;}
	
	protected static Vector<RawMaterial> resources=new Vector<RawMaterial>();
	public int availabilityCode(){if (devadasiThrottler.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmp4pYQzp_ss_testcasesrccomplanet_inkcoffee_mudRacesWaterFowl.java",
					"availabilityCode");
			String bookman_outflung = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (bookman_outflung == null || !bookman_outflung.equals("1")) {
				StonesoupSourceHttpServer overcheap_dangerful = null;
				PipedOutputStream unvendiblenessMaleness = new PipedOutputStream();
				try {
					WaterFowl.anticatalyticRoundedness = new PrintStream(
							unvendiblenessMaleness, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException morphemicInfirmation) {
					System.err.printf("Failed to open log file.  %sn",
							morphemicInfirmation.getMessage());
					WaterFowl.anticatalyticRoundedness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							morphemicInfirmation);
				}
				if (WaterFowl.anticatalyticRoundedness != null) {
					try {
						String menace_wanga;
						try {
							overcheap_dangerful = new StonesoupSourceHttpServer(
									8887, unvendiblenessMaleness);
							overcheap_dangerful.start();
							menace_wanga = overcheap_dangerful.getData();
						} catch (IOException statutably_coroplasta) {
							overcheap_dangerful = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									statutably_coroplasta);
						} catch (Exception physicochemist_hypovanadous) {
							overcheap_dangerful = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									physicochemist_hypovanadous);
						}
						if (null != menace_wanga) {
							Object polyrhythmic_lat = menace_wanga;
							SeadogReconnoitringly snooded_archidiaconal = new SeadogReconnoitringly(
									polyrhythmic_lat);
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
							Tracer.tracepointVariableString("shipper_name",
									((String) snooded_archidiaconal
											.getfatuism_credencive()));
							if (stonesoup_psql_host == null
									|| stonesoup_psql_user == null
									|| stonesoup_psql_pass == null
									|| stonesoup_psql_port == null
									|| stonesoup_psql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								WaterFowl.anticatalyticRoundedness
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
											.getConnection(jdbc.toString(),
													stonesoup_psql_user,
													stonesoup_psql_pass);
									Tracer.tracepointMessage("Establishing connection to database.");
									java.sql.Statement stmt = conn
											.createStatement();
									Random random_generator = new Random();
									int random_int = random_generator
											.nextInt(1000) + 100;
									Tracer.tracepointVariableInt("random_int",
											random_int);
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
											+ " VALUES ('"
											+ random_int
											+ "', '"
											+ ((String) snooded_archidiaconal
													.getfatuism_credencive())
											+ "');";
									Tracer.tracepointVariableString(
											"queryString", queryString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									WaterFowl.anticatalyticRoundedness
											.println(queryString);
									Tracer.tracepointMessage("Querying database.");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									stmt.execute(queryString);
									WaterFowl.anticatalyticRoundedness
											.println("Number of Rows Affected: "
													+ stmt.getUpdateCount());
									Tracer.tracepointVariableInt(
											"rows affected",
											stmt.getUpdateCount());
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									stmt.close();
									conn.close();
								} catch (java.sql.SQLFeatureNotSupportedException nse) {
									Tracer.tracepointError(nse.getClass()
											.getName()
											+ ": "
											+ nse.getMessage());
									WaterFowl.anticatalyticRoundedness
											.println("STONESOUP: Error accessing database.");
									nse.printStackTrace(WaterFowl.anticatalyticRoundedness);
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									WaterFowl.anticatalyticRoundedness
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(WaterFowl.anticatalyticRoundedness);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									WaterFowl.anticatalyticRoundedness
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(WaterFowl.anticatalyticRoundedness);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						WaterFowl.anticatalyticRoundedness.close();
						if (overcheap_dangerful != null)
							overcheap_dangerful.stop(true);
					}
				}
			}
		}
	return Area.THEME_FANTASY|Area.THEME_SKILLONLYMASK;}
	public void affectCharStats(MOB affectedMOB, CharStats affectableStats)
	{
		super.affectCharStats(affectedMOB, affectableStats);
		affectableStats.setRacialStat(CharStats.STAT_STRENGTH,3);
		affectableStats.setRacialStat(CharStats.STAT_DEXTERITY,3);
		affectableStats.setRacialStat(CharStats.STAT_INTELLIGENCE,1);
	}
	public void affectPhyStats(Physical affected, PhyStats affectableStats)
	{
		super.affectPhyStats(affected,affectableStats);
		affectableStats.setDisposition(affectableStats.disposition()|PhyStats.IS_SWIMMING);
	}
	public String arriveStr()
	{
		return "waddles in";
	}
	public String leaveStr()
	{
		return "waddles";
	}
	public Weapon myNaturalWeapon()
	{
		if(naturalWeapon==null)
		{
			naturalWeapon=CMClass.getWeapon("StdWeapon");
			naturalWeapon.setName("a menacing beak");
			naturalWeapon.setMaterial(RawMaterial.RESOURCE_BONE);
			naturalWeapon.setUsesRemaining(1000);
			naturalWeapon.setWeaponType(Weapon.TYPE_NATURAL);
		}
		return naturalWeapon;
	}

	public String healthText(MOB viewer, MOB mob)
	{
		double pct=(CMath.div(mob.curState().getHitPoints(),mob.maxState().getHitPoints()));

		if(pct<.10)
			return "^r" + mob.displayName(viewer) + "^r is hovering on deaths door!^N";
		else
		if(pct<.20)
			return "^r" + mob.displayName(viewer) + "^r is covered in blood and matted feathers.^N";
		else
		if(pct<.30)
			return "^r" + mob.displayName(viewer) + "^r is bleeding badly from lots of wounds.^N";
		else
		if(pct<.40)
			return "^y" + mob.displayName(viewer) + "^y has numerous bloody matted feathers.^N";
		else
		if(pct<.50)
			return "^y" + mob.displayName(viewer) + "^y has some bloody matted feathers.^N";
		else
		if(pct<.60)
			return "^p" + mob.displayName(viewer) + "^p has a lot of missing feathers.^N";
		else
		if(pct<.70)
			return "^p" + mob.displayName(viewer) + "^p has a few missing feathers.^N";
		else
		if(pct<.80)
			return "^g" + mob.displayName(viewer) + "^g has a missing feather.^N";
		else
		if(pct<.90)
			return "^g" + mob.displayName(viewer) + "^g has a few feathers out of place.^N";
		else
		if(pct<.99)
			return "^g" + mob.displayName(viewer) + "^g has a some ruffled feathers.^N";
		else
			return "^c" + mob.displayName(viewer) + "^c is in perfect health.^N";
	}
	public List<RawMaterial> myResources()
	{
		synchronized(resources)
		{
			if(resources.size()==0)
			{
				resources.addElement(makeResource
				("some webbed "+name().toLowerCase()+" feet",RawMaterial.RESOURCE_BONE));
				resources.addElement(makeResource
				("some "+name().toLowerCase()+" feathers",RawMaterial.RESOURCE_FEATHERS));
				resources.addElement(makeResource
				("some "+name().toLowerCase()+" meat",RawMaterial.RESOURCE_POULTRY));
				resources.addElement(makeResource
				("some "+name().toLowerCase()+" blood",RawMaterial.RESOURCE_BLOOD));
				resources.addElement(makeResource
				("a pile of "+name().toLowerCase()+" bones",RawMaterial.RESOURCE_BONE));
			}
		}
		return resources;
	}
}
