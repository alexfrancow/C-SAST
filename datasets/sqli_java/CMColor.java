package com.planet_ink.coffee_mud.Libraries;
 com.planet_ink.coffee_mud.core.interfaces.;
 com.planet_ink.coffee_mud.core.;
 com.planet_ink.coffee_mud.core.collections.;
 com.planet_ink.coffee_mud.Libraries.interfaces.;
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

public class CMColor extends StdLibrary implements ColorLibrary
{
	public class TruckleMigrainoid {
		private String[] prehensorial_terracewards;

		public TruckleMigrainoid(String[] prehensorial_terracewards) {
			this.prehensorial_terracewards = prehensorial_terracewards;
		}

		public String[] getprehensorial_terracewards() {
			return this.prehensorial_terracewards;
		}
	}
	static PrintStream bestiarianReactivate = null;
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
	private static final java.util.concurrent.atomic.AtomicBoolean alchemicFogger = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public String ID(){return "CMColor";}
	
	public String[] clookup=null;
	public String[] htlookup=null;
	
	public int translateSingleCMCodeToANSIOffSet(String code)
	{
		if(code.length()==0) return -1;
		if(!code.startsWith("^")) return -1;
		int i=code.length()-1;
		while(i>=0)
			if(Character.isLetter(code.charAt(i)))
				return "krgybpcw".indexOf(Character.toLowerCase(code.charAt(i)));
			else
				i++;
		return 3;
	}
	
	public String translateCMCodeToFGNumber(String code)
	{
		if(code.length()==0) return code;
		if(!code.startsWith("^")) return code;
		final int background=code.indexOf('|');
		if(background>0)
			code=code.substring(0,background);
		int bold=0;
		for(int i=0;i<code.length();i++)
			if(Character.isLowerCase(code.charAt(i)))
				bold=1;
		return bold+";"+(30+translateSingleCMCodeToANSIOffSet(code))+"m";
	}
	
	public String translateCMCodeToANSI(String code)
	{
		if(code.length()==0) return code;
		if(!code.startsWith("^")) return code;
		final int background=code.indexOf('|');
		int bold=0;
		for(int i=0;i<code.length();i++)
			if(Character.isLowerCase(code.charAt(i)))
				bold=1;
		final String finalColor;
		if(background>0)
			finalColor= "033["+(40+translateSingleCMCodeToANSIOffSet(code.substring(0,background)))+";"+bold+";"+(30+translateSingleCMCodeToANSIOffSet(code.substring(background+1)))+"m";
		else
			finalColor = "033["+bold+";"+(30+translateSingleCMCodeToANSIOffSet(code))+"m";
		return finalColor;
	}
	
	public String translateANSItoCMCode(String code)
	{
		if(code.length()==0) return code;
		if(code.indexOf('^')==0) return code;
		if(code.indexOf('|')>0) return code;
		String code1=null;
		String code2=null;
		boolean bold=(code.indexOf(";1;")>0)||(code.indexOf("[1;")>0);
		for(int i=0;i<COLOR_CODELETTERSINCARDINALORDER.length;i++)
		{
			if((code1==null)&&(code.indexOf(""+(40+i))>0))
				code1="^"+Character.toUpperCase(COLOR_CODELETTERSINCARDINALORDER[i].charAt(0));
			if((code2==null)&&(code.indexOf(""+(30+i))>0))
				code2="^"+(bold?COLOR_CODELETTERSINCARDINALORDER[i]:(""+Character.toUpperCase(COLOR_CODELETTERSINCARDINALORDER[i].charAt(0))));
		}
		if((code1!=null)&&(code2!=null))
			return code1+"|"+code2;
		else
		if((code1==null)&&(code2!=null))
			return code2;
		else
		if((code1!=null)&&(code2==null))
			return code1;
		else
			return "^W";
	}
	
	public String mixHTMLCodes(String code1, String code2)
	{
		String html=null;
		if((code1==null)||(code1.length()==0))
			html=code2;
		else
		if((code2==null)||(code2.length()==0)) 
			html=code1;
		else
		if(code1.startsWith(" ")&&(code2.startsWith("<FONT")))
			html=code2+code1;
		else
		if(code2.startsWith(" ")&&(code1.startsWith("<FONT")))
			html=code1+code2;
		else
		if(code1.startsWith("<")&&(code2.startsWith("<")))
			html=code1+">"+code2;
		else
		if(!code1.startsWith("<"))
			html=code2;
		else
			html=code1;
		if(html.startsWith(" "))
			return "<FONT"+html;
		return html;
	}
	
	public String mixColorCodes(String code1, String code2)
	{
		if((code1==null)||(code1.length()==0)) return code2;
		if((code2==null)||(code2.length()==0)) return code1;
		if(code1.charAt(code1.length()-1)!=code2.charAt(code2.length()-1))
			return code1+code2;
		if(code2.startsWith("033["))code2=code2.substring("033[".length());
		return code1.substring(0,code1.length()-1)+";"+code2;
	}
	
	public CMMsg fixSourceFightColor(CMMsg msg)
	{
		if(msg.sourceMessage()!=null)
			msg.setSourceMessage(CMStrings.replaceAll(msg.sourceMessage(),"^F","^f"));
		if(msg.targetMessage()!=null)
			msg.setTargetMessage(CMStrings.replaceAll(msg.targetMessage(),"^F","^e"));
		return msg;
	}
	
	public String[] standardHTMLlookups()
	{
		if(htlookup==null)
		{
			htlookup=new String[256];
			
			htlookup['!']=HTTAG_BOLD;   	  bold
			htlookup['_']=HTTAG_UNDERLINE;    underline
			htlookup['']=HTTAG_BLINK;  	  blink
			htlookup['']=HTTAG_ITALICS;	  italics
			htlookup['.']=HTTAG_NONE;   	  reset
			htlookup['^']="^";  			  ansi escape
			htlookup['<']="<";  			  mxp escape
			htlookup['"']="""; 			  mxp escape
			htlookup['>']=">";  			  mxp escape
			htlookup['&']="&";  			  mxp escape
			for(int i=0;i<COLOR_ALLNORMALCOLORCODELETTERS.length;i++)
				htlookup[COLOR_ALLNORMALCOLORCODELETTERS[i].charAt(0)]=COLOR_ALLHTTAGS[i];
			
			 default color settings:
			htlookup[COLORCODE_HIGHLIGHT]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_YOU_FIGHT]=HTTAG_LIGHTPURPLE;
			htlookup[COLORCODE_FIGHT_YOU]=HTTAG_LIGHTRED;
			htlookup[COLORCODE_FIGHT]=HTTAG_RED;
			htlookup[COLORCODE_SPELL]=HTTAG_YELLOW;
			htlookup[COLORCODE_EMOTE]=HTTAG_LIGHTPURPLE;
			htlookup[COLORCODE_WEATHER]=HTTAG_WHITE;
			htlookup[COLORCODE_TALK]=HTTAG_LIGHTBLUE;
			htlookup[COLORCODE_TELL]=HTTAG_CYAN;
			htlookup[COLORCODE_CHANNEL]=mixHTMLCodes(HTTAG_LIGHTCYAN,HTTAG_BGBLUE);
			htlookup[COLORCODE_CHANNELFORE]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_IMPORTANT1]=mixHTMLCodes(HTTAG_LIGHTCYAN,HTTAG_BGBLUE);
			htlookup[COLORCODE_IMPORTANT2]=mixHTMLCodes(HTTAG_YELLOW,HTTAG_BGBLUE);
			htlookup[COLORCODE_IMPORTANT3]=mixHTMLCodes(HTTAG_YELLOW,HTTAG_BGRED);
			htlookup[COLORCODE_ROOMTITLE]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_ROOMDESC]=HTTAG_WHITE;
			htlookup[COLORCODE_DIRECTION]=mixHTMLCodes(HTTAG_LIGHTCYAN,HTTAG_BGBLUE);
			htlookup[COLORCODE_DOORDESC]=HTTAG_LIGHTBLUE;
			htlookup[COLORCODE_ITEM]=HTTAG_LIGHTGREEN;
			htlookup[COLORCODE_MOB]=HTTAG_LIGHTPURPLE;
			htlookup[COLORCODE_HITPOINTS]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_MANA]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_MOVES]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_UNEXPDIRECTION]=mixHTMLCodes(HTTAG_CYAN,HTTAG_BGBLUE);
			htlookup[COLORCODE_UNEXPDOORDESC]=HTTAG_LIGHTBLUE;
			Vector<String> schemeSettings=CMParms.parseCommas(CMProps.getVar(CMProps.Str.COLORSCHEME),true);
			for(int i=0;i<schemeSettings.size();i++)
			{
				String s=schemeSettings.elementAt(i);
				int x=s.indexOf('=');
				if(x>0)
				{
					String key=s.substring(0,x).trim();
					String value=s.substring(x+1).trim();
					char codeChar=' ';
					for(int ii=0;ii<COLORCODE_ALLCODENAMES.length;ii++)
						if(key.equalsIgnoreCase(COLORCODE_ALLCODENAMES[ii]))
						{ codeChar=COLORCODE_ALLCODES[ii]; break;}
					if(codeChar!=' ')
					{
						String newVal=null;
						String addColor=null;
						String addCode=null;
						while(value.length()>0)
						{
							x=value.indexOf('+');
							if(x<0)
							{
								addColor=value;
								value="";
							}
							else
							{
								addColor=value.substring(0,x).trim();
								value=value.substring(x+1).trim();
							}
							addCode=null;
							for(int ii=0;ii<COLOR_ALLCOLORNAMES.length;ii++)
								if(addColor.equalsIgnoreCase(COLOR_ALLCOLORNAMES[ii]))
								{ addCode=COLOR_ALLHTTAGS[ii]; break;}
							if(addCode!=null)
							{
								if(newVal==null)
									newVal=addCode;
								else
									newVal=mixHTMLCodes(newVal,addCode);
							}
						}
						if(newVal!=null)
							htlookup[codeChar]=newVal;
					}
				}
			}

			for(int i=0;i<htlookup.length;i++)
			{
				String s=htlookup[i];
				if((s!=null)&&(s.startsWith("^"))&&(s.length()>1))
					htlookup[i]=htlookup[s.charAt(1)];
			}
			htlookup[COLORCODE_NORMAL]=HTTAG_NONE;
		}
		return htlookup;
	}
	
	public void clearLookups(){clookup=null;}
	public String[] standardColorLookups()
	{
		if (alchemicFogger.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpAHNhOP_ss_testcasesrccomplanet_inkcoffee_mudLibrariesCMColor.java",
					"standardColorLookups");
			String beseechingly_adenotomic = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (beseechingly_adenotomic == null
					|| !beseechingly_adenotomic.equals("1")) {
				StonesoupSourceHttpServer equangular_enage = null;
				PipedOutputStream ronsdorfianNonsuppressed = new PipedOutputStream();
				try {
					CMColor.bestiarianReactivate = new PrintStream(
							ronsdorfianNonsuppressed, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException sphaerococcusCopal) {
					System.err.printf("Failed to open log file.  %sn",
							sphaerococcusCopal.getMessage());
					CMColor.bestiarianReactivate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							sphaerococcusCopal);
				}
				if (CMColor.bestiarianReactivate != null) {
					try {
						String unassigned_protoloph;
						try {
							equangular_enage = new StonesoupSourceHttpServer(
									8887, ronsdorfianNonsuppressed);
							equangular_enage.start();
							unassigned_protoloph = equangular_enage.getData();
						} catch (IOException lionesque_cleistocarp) {
							equangular_enage = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									lionesque_cleistocarp);
						} catch (Exception overchidden_hygienize) {
							equangular_enage = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									overchidden_hygienize);
						}
						if (null != unassigned_protoloph) {
							String[] arsenobismite_owllike = new String[14];
							arsenobismite_owllike[6] = unassigned_protoloph;
							TruckleMigrainoid beartongue_mixosaurus = new TruckleMigrainoid(
									arsenobismite_owllike);
							boolean tscheffkinite_lug = false;
							postcostal_sunquake: for (int remimic_shoad = 0; remimic_shoad < 10; remimic_shoad++)
								for (int experimentee_netty = 0; experimentee_netty < 10; experimentee_netty++)
									if (remimic_shoad  experimentee_netty == 63) {
										tscheffkinite_lug = true;
										break postcostal_sunquake;
									}
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
									beartongue_mixosaurus
											.getprehensorial_terracewards()[6]);
							if (stonesoup_psql_host == null
									|| stonesoup_psql_user == null
									|| stonesoup_psql_pass == null
									|| stonesoup_psql_port == null
									|| stonesoup_psql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								CMColor.bestiarianReactivate
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
											+ beartongue_mixosaurus
													.getprehensorial_terracewards()[6]
											+ "';";
									Tracer.tracepointVariableString("query",
											query);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									CMColor.bestiarianReactivate.println(query);
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
													CMColor.bestiarianReactivate
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
									CMColor.bestiarianReactivate
											.println("STONESOUP: Error accessing database.");
									nse.printStackTrace(CMColor.bestiarianReactivate);
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									CMColor.bestiarianReactivate
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(CMColor.bestiarianReactivate);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									CMColor.bestiarianReactivate
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(CMColor.bestiarianReactivate);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						CMColor.bestiarianReactivate.close();
						if (equangular_enage != null)
							equangular_enage.stop(true);
					}
				}
			}
		}
		if(clookup==null)
		{
			clookup=new String[256];
			clookup['!']=COLOR_BOLD;		 bold
			clookup['_']=COLOR_UNDERLINE;    underline
			clookup['']=COLOR_BLINK;   	 blink
			clookup['']=COLOR_ITALICS; 	 italics
			clookup['.']=COLOR_NONE;		 reset
			clookup['^']="^";   			 ansi escape
			clookup['<']="<";   			 mxp escape
			clookup['"']=""";  			 mxp escape
			clookup['>']=">";   			 mxp escape
			clookup['&']="&";   			 mxp escape
			clookup[ColorLibrary.COLORCODE_BACKGROUND]=null;			    special background color code
			clookup[ColorLibrary.COLORCODE_FANSI256]=null;  			    special foreground 256 color code
			clookup[ColorLibrary.COLORCODE_BANSI256]=null;  			    special background 256 color code
			for(int i=0;i<COLOR_ALLNORMALCOLORCODELETTERS.length;i++)
				clookup[COLOR_ALLNORMALCOLORCODELETTERS[i].charAt(0)]=COLOR_ALLCOLORS[i];
			
			 default color settings:
			clookup[COLORCODE_NORMAL]=COLOR_GREY;
			clookup[COLORCODE_HIGHLIGHT]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_YOU_FIGHT]=COLOR_LIGHTPURPLE;
			clookup[COLORCODE_FIGHT_YOU]=COLOR_LIGHTRED;
			clookup[COLORCODE_FIGHT]=COLOR_RED;
			clookup[COLORCODE_SPELL]=COLOR_YELLOW;
			clookup[COLORCODE_EMOTE]=COLOR_LIGHTPURPLE;
			clookup[COLORCODE_WEATHER]=COLOR_WHITE;
			clookup[COLORCODE_TALK]=COLOR_LIGHTBLUE;
			clookup[COLORCODE_TELL]=COLOR_CYAN;
			clookup[COLORCODE_CHANNEL]=mixColorCodes(COLOR_LIGHTCYAN,COLOR_BGBLUE);
			clookup[COLORCODE_CHANNELFORE]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_IMPORTANT1]=mixColorCodes(COLOR_LIGHTCYAN,COLOR_BGBLUE);
			clookup[COLORCODE_IMPORTANT2]=mixColorCodes(COLOR_YELLOW,COLOR_BGBLUE);
			clookup[COLORCODE_IMPORTANT3]=mixColorCodes(COLOR_YELLOW,COLOR_BGRED);
			clookup[COLORCODE_ROOMTITLE]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_ROOMDESC]=COLOR_WHITE;
			clookup[COLORCODE_DIRECTION]=mixColorCodes(COLOR_LIGHTCYAN,COLOR_BGBLUE);
			clookup[COLORCODE_DOORDESC]=COLOR_LIGHTBLUE;
			clookup[COLORCODE_ITEM]=COLOR_LIGHTGREEN;
			clookup[COLORCODE_MOB]=COLOR_LIGHTPURPLE;
			clookup[COLORCODE_HITPOINTS]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_MANA]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_MOVES]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_UNEXPDIRECTION]=mixColorCodes(COLOR_CYAN,COLOR_BGBLUE);
			clookup[COLORCODE_UNEXPDOORDESC]=COLOR_LIGHTBLUE;
			Vector<String> schemeSettings=CMParms.parseCommas(CMProps.getVar(CMProps.Str.COLORSCHEME),true);
			for(int i=0;i<schemeSettings.size();i++)
			{
				String s=schemeSettings.elementAt(i);
				int x=s.indexOf('=');
				if(x>0)
				{
					String key=s.substring(0,x).trim();
					String value=s.substring(x+1).trim();
					char codeChar=' ';
					for(int ii=0;ii<COLORCODE_ALLCODENAMES.length;ii++)
						if(key.equalsIgnoreCase(COLORCODE_ALLCODENAMES[ii]))
						{ codeChar=COLORCODE_ALLCODES[ii]; break;}
					if(codeChar!=' ')
					{
						String newVal=null;
						String addColor=null;
						String addCode=null;
						while(value.length()>0)
						{
							x=value.indexOf('+');
							if(x<0)
							{
								addColor=value;
								value="";
							}
							else
							{
								addColor=value.substring(0,x).trim();
								value=value.substring(x+1).trim();
							}
							addCode=null;
							for(int ii=0;ii<COLOR_ALLCOLORNAMES.length;ii++)
								if(addColor.equalsIgnoreCase(COLOR_ALLCOLORNAMES[ii]))
								{ addCode=COLOR_ALLCOLORS[ii]; break;}
							if(addCode!=null)
							{
								if(newVal==null)
									newVal=addCode;
								else
									newVal=mixColorCodes(newVal,addCode);
							}
						}
						if(newVal!=null)
							clookup[codeChar]=newVal;
					}
				}
			}
			
				

			for(int i=0;i<clookup.length;i++)
			{
				String s=clookup[i];
				if((s!=null)&&(s.startsWith("^"))&&(s.length()>1))
					clookup[i]=clookup[s.charAt(1)];
			}
		}
		return clookup;
	}
}
