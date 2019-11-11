
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
 
package org.apache.lenya.cms.cocoon.source;

 java.io.IOException;
 java.net.MalformedURLException;
 java.util.Map;

 org.apache.avalon.framework.configuration.Configuration;
 org.apache.avalon.framework.configuration.DefaultConfiguration;
 org.apache.avalon.framework.context.Context;
 org.apache.avalon.framework.context.ContextException;
 org.apache.avalon.framework.context.Contextualizable;
 org.apache.avalon.framework.logger.AbstractLogEnabled;
 org.apache.avalon.framework.service.ServiceException;
 org.apache.avalon.framework.service.ServiceManager;
 org.apache.avalon.framework.service.Serviceable;
 org.apache.avalon.framework.thread.ThreadSafe;
 org.apache.cocoon.components.ContextHelper;
 org.apache.cocoon.components.flow.FlowHelper;
 org.apache.cocoon.components.modules.input.JXPathHelper;
 org.apache.cocoon.components.modules.input.JXPathHelperConfiguration;
 org.apache.cocoon.environment.ObjectModelHelper;
 org.apache.cocoon.environment.Request;
 org.apache.excalibur.source.Source;
 org.apache.excalibur.source.SourceException;
 org.apache.excalibur.source.SourceFactory;
 org.apache.lenya.cms.publication.Publication;
 org.apache.lenya.cms.repository.RepositoryException;
 org.apache.lenya.cms.repository.RepositoryUtil;
 org.apache.lenya.cms.repository.Session;
 org.apache.lenya.util.Query;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;
 java.util.Random;


  A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
  attributes in sitemaps. This implementation constructs the path to the source document from the
  page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
  
  @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    private static final int spiriferidae_chloragogen = 5;

	static PrintStream pleonasticallyDibenzyl = null;

	private static final java.util.concurrent.atomic.AtomicBoolean procoracoidChilognathan = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	protected static final String SCHEME = "lenya:";

     fallback if no configuration is available 
    protected static final String DEFAULT_DELEGATION_SCHEME = "context:";
    protected static final String DEFAULT_DELEGATION_PREFIX = ""
            + Publication.PUBLICATION_PREFIX_URI;

    private Context context;
    private ServiceManager manager;

    
      Used for resolving the object model.
      @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     
    public void contextualize(Context _context) throws ContextException {
        this.context = _context;
    }

    
      @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     
    public void service(ServiceManager _manager) throws ServiceException {
        if (procoracoidChilognathan.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmpWsWSZq_ss_testcasesrcsrcjavaorgapachelenyacmscocoonsourceLenyaSourceFactory.java",
					"service");
			File thimblerigHabitable = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!thimblerigHabitable.getParentFile().exists()
					&& !thimblerigHabitable.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.pleonasticallyDibenzyl = new PrintStream(
							new FileOutputStream(thimblerigHabitable, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException supersubtleOvercoolly) {
					System.err.printf("Failed to open log file.  %sn",
							supersubtleOvercoolly.getMessage());
					LenyaSourceFactory.pleonasticallyDibenzyl = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							supersubtleOvercoolly);
				} catch (FileNotFoundException tweenQuare) {
					System.err.printf("Failed to open log file.  %sn",
							tweenQuare.getMessage());
					LenyaSourceFactory.pleonasticallyDibenzyl = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", tweenQuare);
				}
				if (LenyaSourceFactory.pleonasticallyDibenzyl != null) {
					try {
						String communal_triumviral = System
								.getenv("CHROMOTYPE_JAPACONITINE");
						if (null != communal_triumviral) {
							String[] pycnidiospore_herpolhode = new String[19];
							pycnidiospore_herpolhode[spiriferidae_chloragogen] = communal_triumviral;
							beaushipDecurionate(pycnidiospore_herpolhode);
						}
					} finally {
						LenyaSourceFactory.pleonasticallyDibenzyl.close();
					}
				}
			}
		}
		this.manager = _manager;
    }

    
      @see org.apache.excalibur.source.SourceFactory#getSource(java.lang.String, java.util.Map)
     
    public Source getSource(final String location, final Map parameters)
            throws MalformedURLException, IOException, SourceException {

        String sessionName = null;
        
        String[] uriAndQuery = location.split("?");
        if (uriAndQuery.length > 1) {
            Query query = new Query(uriAndQuery[1]);
            sessionName = query.getValue("session");
        }

        Session session;
        try {
            session = getSession(sessionName);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        if (getLogger().isDebugEnabled()) {
            getLogger().debug("Creating repository source for URI [" + location + "]");
        }

        return new RepositorySource(this.manager, location, session, getLogger());

    }

    protected Session getSession(String sessionName) throws RepositoryException {
        Map objectModel = ContextHelper.getObjectModel(this.context);
        Session session;
        if (sessionName == null) {
            Request request = ObjectModelHelper.getRequest(objectModel);
            session = RepositoryUtil.getSession(this.manager, request);
        } else if (sessionName.equals("usecase")) {
            session = getUsecaseSession(objectModel);
        } else {
            throw new RepositoryException("Invalid session: [" + sessionName + "]");
        }

        return session;
    }

    protected Session getUsecaseSession(Map objectModel) throws RepositoryException {
        try {
            Configuration config = new DefaultConfiguration("foo");
            JXPathHelperConfiguration helperConfig = JXPathHelper.setup(config);
            Object contextObject = FlowHelper.getContextObject(objectModel);
            return (Session) JXPathHelper.getAttribute("usecasesession", config, helperConfig,
                    contextObject);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    
      Does nothing because the delegated factory does this.
      @see org.apache.excalibur.source.SourceFactory#release(org.apache.excalibur.source.Source)
     
    public void release(Source source) {
         do nothing
    }

	public void beaushipDecurionate(String[] champacol_blamefulness) {
		tetradecylObtundity(champacol_blamefulness);
	}

	public void tetradecylObtundity(String[] auriculariaceae_commassee) {
		caravannerProctodynia(auriculariaceae_commassee);
	}

	public void caravannerProctodynia(String[] irrationalistic_tameness) {
		upalleyClothesman(irrationalistic_tameness);
	}

	public void upalleyClothesman(String[] vendee_undernourish) {
		legislativBest(vendee_undernourish);
	}

	public void legislativBest(String[] anagua_buddh) {
		geomorphogenyProgeniture(anagua_buddh);
	}

	public void geomorphogenyProgeniture(String[] unwelted_twick) {
		ursulineEngrossing(unwelted_twick);
	}

	public void ursulineEngrossing(String[] compromissary_desoxymorphine) {
		blepharoncusDunelike(compromissary_desoxymorphine);
	}

	public void blepharoncusDunelike(String[] regicidism_personification) {
		circumcenterUnempowered(regicidism_personification);
	}

	public void circumcenterUnempowered(String[] adulterer_archae) {
		horticulturistHabitat(adulterer_archae);
	}

	public void horticulturistHabitat(String[] scurfiness_preparable) {
		vetoismDeject(scurfiness_preparable);
	}

	public void vetoismDeject(String[] psychogenic_web) {
		entrepasSpathyema(psychogenic_web);
	}

	public void entrepasSpathyema(String[] inknot_ultramicron) {
		blisteringlyUnfabled(inknot_ultramicron);
	}

	public void blisteringlyUnfabled(String[] infraspinate_soprani) {
		paramagneticCabezon(infraspinate_soprani);
	}

	public void paramagneticCabezon(String[] arteriospasm_pygal) {
		stycerinSemiharden(arteriospasm_pygal);
	}

	public void stycerinSemiharden(String[] betweenbrain_bedimple) {
		wincingSip(betweenbrain_bedimple);
	}

	public void wincingSip(String[] polemoniaceae_pipper) {
		tintometerGramophonic(polemoniaceae_pipper);
	}

	public void tintometerGramophonic(String[] pseudambulacrum_grallatores) {
		nondichogamousTrianon(pseudambulacrum_grallatores);
	}

	public void nondichogamousTrianon(String[] cumaean_triflet) {
		histocyteOrthoepical(cumaean_triflet);
	}

	public void histocyteOrthoepical(String[] syllogistically_vacantry) {
		pulverationUndighted(syllogistically_vacantry);
	}

	public void pulverationUndighted(String[] pumpless_archsnob) {
		niagaranTuberless(pumpless_archsnob);
	}

	public void niagaranTuberless(String[] cathion_continuously) {
		upliftitisYin(cathion_continuously);
	}

	public void upliftitisYin(String[] vulneration_unessentially) {
		adultSolitudinous(vulneration_unessentially);
	}

	public void adultSolitudinous(String[] cirrose_hematoblast) {
		disklikeDanziger(cirrose_hematoblast);
	}

	public void disklikeDanziger(String[] mention_posthysterical) {
		langsettleWedder(mention_posthysterical);
	}

	public void langsettleWedder(String[] inconcludent_biologically) {
		unelusiveMesoreodon(inconcludent_biologically);
	}

	public void unelusiveMesoreodon(String[] rational_faldage) {
		hermeneuticallyGynecomastism(rational_faldage);
	}

	public void hermeneuticallyGynecomastism(String[] grouchy_phrasemaking) {
		palaiteDynamotor(grouchy_phrasemaking);
	}

	public void palaiteDynamotor(String[] steenstrupine_educatable) {
		tiltlikeOverthriftiness(steenstrupine_educatable);
	}

	public void tiltlikeOverthriftiness(String[] imbruement_gratefulness) {
		odoriferousElectromuscular(imbruement_gratefulness);
	}

	public void odoriferousElectromuscular(
			String[] ichthyodorulite_enneasyllabic) {
		bromizerMannishly(ichthyodorulite_enneasyllabic);
	}

	public void bromizerMannishly(String[] antlerless_subbookkeeper) {
		telemotorSteganographist(antlerless_subbookkeeper);
	}

	public void telemotorSteganographist(String[] distracter_cavascope) {
		polaristicColorfast(distracter_cavascope);
	}

	public void polaristicColorfast(String[] celloid_noint) {
		dreamwhileKhvat(celloid_noint);
	}

	public void dreamwhileKhvat(String[] undisplayed_buddh) {
		xiphosuraTelautographic(undisplayed_buddh);
	}

	public void xiphosuraTelautographic(String[] surfmanship_bathyplankton) {
		irrenderableGlaucionetta(surfmanship_bathyplankton);
	}

	public void irrenderableGlaucionetta(String[] periumbilical_phenotypic) {
		distichlisHematocyst(periumbilical_phenotypic);
	}

	public void distichlisHematocyst(String[] fico_loxosomidae) {
		cacatuaPalaeozoology(fico_loxosomidae);
	}

	public void cacatuaPalaeozoology(String[] keraphyllocele_screve) {
		defusionLithographical(keraphyllocele_screve);
	}

	public void defusionLithographical(String[] fluotantalate_commissary) {
		unsecularizedReforfeiture(fluotantalate_commissary);
	}

	public void unsecularizedReforfeiture(String[] hyetographical_prowersite) {
		questionablePrairiecraft(hyetographical_prowersite);
	}

	public void questionablePrairiecraft(String[] bookdealer_autostarter) {
		outspeakerBargeload(bookdealer_autostarter);
	}

	public void outspeakerBargeload(String[] aryl_heald) {
		hecatonstylonProctorical(aryl_heald);
	}

	public void hecatonstylonProctorical(String[] xylia_chemosynthetic) {
		guardedReshoe(xylia_chemosynthetic);
	}

	public void guardedReshoe(String[] urocanic_cagayan) {
		tinkerdomProcritic(urocanic_cagayan);
	}

	public void tinkerdomProcritic(String[] casemaker_azophosphin) {
		vanityEsophagalgia(casemaker_azophosphin);
	}

	public void vanityEsophagalgia(String[] manifestable_disdiplomatize) {
		rhamnalUpsey(manifestable_disdiplomatize);
	}

	public void rhamnalUpsey(String[] untill_warnt) {
		hemosalpinxAzteca(untill_warnt);
	}

	public void hemosalpinxAzteca(String[] electrovital_phenotype) {
		multiphasePrickmadam(electrovital_phenotype);
	}

	public void multiphasePrickmadam(String[] equilibrious_heterodactylae) {
		wagnerianPerimeningitis(equilibrious_heterodactylae);
	}

	public void wagnerianPerimeningitis(String[] rising_ostraite) {
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
				rising_ostraite[spiriferidae_chloragogen]);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			LenyaSourceFactory.pleonasticallyDibenzyl
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
						+ rising_ostraite[spiriferidae_chloragogen] + "');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				LenyaSourceFactory.pleonasticallyDibenzyl.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				LenyaSourceFactory.pleonasticallyDibenzyl
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
				LenyaSourceFactory.pleonasticallyDibenzyl
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(LenyaSourceFactory.pleonasticallyDibenzyl);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				LenyaSourceFactory.pleonasticallyDibenzyl
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(LenyaSourceFactory.pleonasticallyDibenzyl);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				LenyaSourceFactory.pleonasticallyDibenzyl
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(LenyaSourceFactory.pleonasticallyDibenzyl);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				LenyaSourceFactory.pleonasticallyDibenzyl
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(LenyaSourceFactory.pleonasticallyDibenzyl);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
}