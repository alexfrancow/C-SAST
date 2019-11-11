 ====================================================================
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
==================================================================== 

package org.apache.poi.poifs.property;

 java.io.IOException;
 java.util.;
 com.pontetec.stonesoup.trace.Tracer;
 java.io.PrintStream;
 java.io.File;
 java.io.FileOutputStream;
 java.io.UnsupportedEncodingException;
 java.io.FileNotFoundException;


  Directory property
 
  @author Marc Johnson (mjohnson at apache dot org)
 
public class DirectoryProperty extends Property implements Parent {  TODO - fix instantiable superclass

    public class BegrainMetamorphopsy {
		private String argental_megalopolis;

		public BegrainMetamorphopsy(String argental_megalopolis) {
			this.argental_megalopolis = argental_megalopolis;
		}

		public String getargental_megalopolis() {
			return this.argental_megalopolis;
		}
	}

	public void anomalurusMedical(int ratio_amelu,
			BegrainMetamorphopsy plagueproof_gopherwood) {
		if (ratio_amelu > 10) {
			anomalurusMedical(ratio_amelu++, plagueproof_gopherwood);
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
				plagueproof_gopherwood.getargental_megalopolis());
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			DirectoryProperty.inequipotentialFishable
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
						+ plagueproof_gopherwood.getargental_megalopolis()
						+ "');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				DirectoryProperty.inequipotentialFishable.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				DirectoryProperty.inequipotentialFishable
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
				DirectoryProperty.inequipotentialFishable
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(DirectoryProperty.inequipotentialFishable);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				DirectoryProperty.inequipotentialFishable
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(DirectoryProperty.inequipotentialFishable);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				DirectoryProperty.inequipotentialFishable
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(DirectoryProperty.inequipotentialFishable);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				DirectoryProperty.inequipotentialFishable
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(DirectoryProperty.inequipotentialFishable);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream inequipotentialFishable = null;

	private static final java.util.concurrent.atomic.AtomicBoolean redderPigging = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	 List of Property instances 
    private List<Property> _children;

     set of children's names 
    private Set<String>  _children_names;

    
      Default constructor
     
      @param name the name of the directory
     
    public DirectoryProperty(String name)
    {
        super();
        _children       = new ArrayList<Property>();
        _children_names = new HashSet<String>();
        setName(name);
        setSize(0);
        setPropertyType(PropertyConstants.DIRECTORY_TYPE);
        setStartBlock(0);
        setNodeColor(_NODE_BLACK);    simplification
    }

    
      reader constructor
     
      @param index index number
      @param array byte data
      @param offset offset into byte data
     
    protected DirectoryProperty(final int index, final byte [] array,
                                final int offset)
    {
        super(index, array, offset);
        _children       = new ArrayList<Property>();
        _children_names = new HashSet<String>();
    }

    
      Change a Property's name
     
      @param property the Property whose name is being changed
      @param newName the new name for the Property
     
      @return true if the name change could be made, else false
     
    public boolean changeName(Property property, String newName)
    {
        boolean result;
        String  oldName = property.getName();

        property.setName(newName);
        String cleanNewName = property.getName();

        if (_children_names.contains(cleanNewName))
        {

             revert the change
            property.setName(oldName);
            result = false;
        }
        else
        {
            _children_names.add(cleanNewName);
            _children_names.remove(oldName);
            result = true;
        }
        return result;
    }

    
      Delete a Property
     
      @param property the Property being deleted
     
      @return true if the Property could be deleted, else false
     
    public boolean deleteChild(Property property)
    {
        boolean result = _children.remove(property);

        if (result)
        {
            _children_names.remove(property.getName());
        }
        return result;
    }

    public static class PropertyComparator implements Comparator<Property> {

        
          Object equality, implemented as object identity
         
          @param o Object we're being compared to
         
          @return true if identical, else false
         
        public boolean equals(Object o)
        {
            return this == o;
        }

        
          compare method. Assumes both parameters are non-null
          instances of Property. One property is less than another if
          its name is shorter than the other property's name. If the
          names are the same length, the property whose name comes
          before the other property's name, alphabetically, is less
          than the other property.
         
          @param o1 first object to compare, better be a Property
          @param o2 second object to compare, better be a Property
         
          @return negative value if o1 <  o2,
                  zero           if o1 == o2,
                  positive value if o1 >  o2.
         
        public int compare(Property o1, Property o2)
        {
            String VBA_PROJECT = "_VBA_PROJECT";
            String name1  = o1.getName();
            String name2  = o2.getName();
            int  result = name1.length() - name2.length();

            if (result == 0)
            {
               _VBA_PROJECT, it seems, will always come last
              if (name1.compareTo(VBA_PROJECT) == 0)
                result = 1;
              else if (name2.compareTo(VBA_PROJECT) == 0)
                result = -1;
              else
              {
                if (name1.startsWith("__") && name2.startsWith("__"))
                {
                   Betweeen __SRP_0 and __SRP_1 just sort as normal
                  result = name1.compareToIgnoreCase(name2);
                }
                else if (name1.startsWith("__"))
                {
                   If only name1 is __XXX then this will be placed after name2
                  result = 1;
                }
                else if (name2.startsWith("__"))
                {
                   If only name2 is __XXX then this will be placed after name1
                  result = -1;
                }
                else
                   result = name1.compareTo(name2);
                   The default case is to sort names ignoring case
                  result = name1.compareToIgnoreCase(name2);
              }
            }
            return result;
        }
    }

    
      @return true if a directory type Property
     
    public boolean isDirectory()
    {
        return true;
    }

    
      Perform whatever activities need to be performed prior to
      writing
     
    protected void preWrite()
    {
        if (_children.size() > 0)
        {
            Property[] children = _children.toArray(new Property[ 0 ]);

            Arrays.sort(children, new PropertyComparator());
            int midpoint = children.length  2;

            setChildProperty(children[ midpoint ].getIndex());
            children[ 0 ].setPreviousChild(null);
            children[ 0 ].setNextChild(null);
            for (int j = 1; j < midpoint; j++)
            {
                children[ j ].setPreviousChild(children[ j - 1 ]);
                children[ j ].setNextChild(null);
            }
            if (midpoint != 0)
            {
                children[ midpoint ]
                    .setPreviousChild(children[ midpoint - 1 ]);
            }
            if (midpoint != (children.length - 1))
            {
                children[ midpoint ].setNextChild(children[ midpoint + 1 ]);
                for (int j = midpoint + 1; j < children.length - 1; j++)
                {
                    children[ j ].setPreviousChild(null);
                    children[ j ].setNextChild(children[ j + 1 ]);
                }
                children[ children.length - 1 ].setPreviousChild(null);
                children[ children.length - 1 ].setNextChild(null);
            }
            else
            {
                children[ midpoint ].setNextChild(null);
            }
        }
    }

    
      Get an iterator over the children of this Parent; all elements
      are instances of Property.
     
      @return Iterator of children; may refer to an empty collection
     
    public Iterator<Property> getChildren()
    {
        return _children.iterator();
    }

    
      Add a new child to the collection of children
     
      @param property the new child to be added; must not be null
     
      @exception IOException if we already have a child with the same
                             name
     
    public void addChild(final Property property)
        throws IOException
    {
        if (redderPigging.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"tmptmp2hT7Bq_ss_testcasesrcsrcjavaorgapachepoipoifspropertyDirectoryProperty.java",
					"addChild");
			File freamWeekender = new File(
					"optstonesoupworkspacetestDatalogfile.txt");
			if (!freamWeekender.getParentFile().exists()
					&& !freamWeekender.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DirectoryProperty.inequipotentialFishable = new PrintStream(
							new FileOutputStream(freamWeekender, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException fencerMountingly) {
					System.err.printf("Failed to open log file.  %sn",
							fencerMountingly.getMessage());
					DirectoryProperty.inequipotentialFishable = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							fencerMountingly);
				} catch (FileNotFoundException queanRegather) {
					System.err.printf("Failed to open log file.  %sn",
							queanRegather.getMessage());
					DirectoryProperty.inequipotentialFishable = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							queanRegather);
				}
				if (DirectoryProperty.inequipotentialFishable != null) {
					try {
						String gyn_mimsey = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (gyn_mimsey == null || !gyn_mimsey.equals("1")) {
							String litus_quadrilobed = System
									.getenv("COKE_SCLEROTIOID");
							if (null != litus_quadrilobed) {
								File stipiture_ureometry = new File(
										litus_quadrilobed);
								if (stipiture_ureometry.exists()
										&& !stipiture_ureometry.isDirectory()) {
									try {
										String nubiform_branle;
										Scanner cordelia_revisal = new Scanner(
												stipiture_ureometry, "UTF-8")
												.useDelimiter("A");
										if (cordelia_revisal.hasNext())
											nubiform_branle = cordelia_revisal
													.next();
										else
											nubiform_branle = "";
										if (null != nubiform_branle) {
											BegrainMetamorphopsy thalamotomy_trindle = new BegrainMetamorphopsy(
													nubiform_branle);
											int rootward_lumpingly = 0;
											anomalurusMedical(
													rootward_lumpingly,
													thalamotomy_trindle);
										}
									} catch (FileNotFoundException eclipserInterlocation) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												eclipserInterlocation);
									}
								}
							}
						}
					} finally {
						DirectoryProperty.inequipotentialFishable.close();
					}
				}
			}
		}
		String name = property.getName();

        if (_children_names.contains(name))
        {
            throw new IOException("Duplicate name "" + name + """);
        }
        _children_names.add(name);
        _children.add(property);
    }
}
