package badCodeComplexity;
 This software was developed at the National Institute of Standards and
  Technology by employees of the Federal Government in the course of their
  official duties. Pursuant to title 17 Section 105 of the United States
  Code this software is not subject to copyright protection and is in the
  public domain. NIST assumes no responsibility whatsoever for its use by
  other parties, and makes no guarantees, expressed or implied, about its
  quality, reliability, or any other characteristic.
  We would appreciate acknowledgement if the software is used.
  The SAMATE project website is: http:samate.nist.gov
 


  This servlet implements an SQL injection vulnerability
  Parameters:
    - name: source of the vulnerability
  Example:
    - url: http:server_addresspath_to_servletSQLInjection_089?name=' OR ''='
 

 java.io.IOException;
 java.sql.Connection;
 java.sql.PreparedStatement;
 java.sql.SQLException;
 java.util.logging.Logger;

 javax.naming.Context;
 javax.naming.InitialContext;
 javax.naming.NamingException;
 javax.servlet.ServletException;
 javax.servlet.ServletOutputStream;
 javax.servlet.http.HttpServlet;
 javax.servlet.http.HttpServletRequest;
 javax.servlet.http.HttpServletResponse;
 javax.sql.DataSource;

public class SQLInjection_scope_089 extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public SQLInjection_scope_089()
    {
        super();
    }

    public PreparedStatement function(  Connection conn, String name )
	{
    	PreparedStatement p = null;
    	try
    	{
			 BUG
    		 this unsanitized SQL request may result in SQL injection
	    	p = conn.prepareStatement ("SELECT  FROM users WHERE firstname LIKE '" +
	 		       name + "'");
    	}
    	catch (SQLException se)
    	{
    		final Logger logger = Logger.getAnonymousLogger();
			String exception = "Exception " + se;
			logger.warning( exception );
    	}
    	return p;
	}
    
     Method which will be called to handle HTTP GET requests
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)
    	throws ServletException, IOException
	{
		Connection conn = null;

		 Get the parameter "name" from the data provided by the user
		String name = req.getParameter ("name");

		 Initialize the output stream
		resp.setContentType ("texthtml");
		ServletOutputStream out = resp.getOutputStream ();
		out.println ("<HTML><BODY><blockquote><pre>");

		try
		{
			 Set the context factory to use to create the initial context
			System.setProperty (Context.INITIAL_CONTEXT_FACTORY, "your.ContextFactory");

			 Create the initial context and use it to lookup the data source
			InitialContext ic = new InitialContext ();
			DataSource dataSrc = (DataSource) ic.lookup ("java:compenvjdbc:mydb");

			 Create a connection to the SQL database from the data source
			conn = dataSrc.getConnection ();

			 Send an unsanitized SQL request to the database, which may result in SQL injection
			PreparedStatement p = function(conn, name);
			p.executeQuery ();
		}
		catch (NamingException e)
	    {
			out.println ("Naming exception");
	    }
	    catch (SQLException e)
	    {
	    	out.println ("SQL exception");
	    }
	    finally
	    {
	    	try
	    	{
	    		if (conn != null)
	    			conn.close ();
	    	}
	    	catch (SQLException se)
	    	{
	    		out.println("SQL Exception");
	    	}
	    }

	    out.println ("<pre><blockquote><body><html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
	}
}
