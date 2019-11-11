
Description: Tainted data spliced into a SQL query leads to a SQL injection issue.
Keywords: Port Java Size0 Complex0 WebServer SQLInject
InvalidParam: "name=' or ''='"
ValidParam: hi

Copyright 2005 Fortify Software.

Permission is hereby granted, without written agreement or royalty fee, to
use, copy, modify, and distribute this software and its documentation for
any purpose, provided that the above copyright notice and the following
three paragraphs appear in all copies of this software.

IN NO EVENT SHALL FORTIFY SOFTWARE BE LIABLE TO ANY PARTY FOR DIRECT,
INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE
USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF FORTIFY SOFTWARE HAS
BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMANGE.

FORTIFY SOFTWARE SPECIFICALLY DISCLAIMS ANY WARRANTIES INCLUDING, BUT NOT
LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE, AND NON-INFRINGEMENT.

THE SOFTWARE IS PROVIDED ON AN "AS-IS" BASIS AND FORTIFY SOFTWARE HAS NO
OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR
MODIFICATIONS.


 java.io.;
 javax.servlet.;
 javax.servlet.http.;
 javax.naming.;
 javax.sql.;
 java.sql.;

public class Sql1_bad extends HttpServlet
{
	private DataSource _ds;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
    	throws ServletException, IOException
    {
        res.setContentType("texthtml");
        ServletOutputStream out = res.getOutputStream();
        out.println("<HTML><HEAD><TITLE>Test<TITLE><HEAD><BODY><blockquote><pre>");

		String name = req.getParameter("name");
		if(name == null)
			name = "hi";

		Connection conn = getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement("select val from mytab where name = '" + name + "'");
			ResultSet rs = stmt.executeQuery();
			out.println("results:");
			while(rs.next())
				out.println(rs.getString(1));
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			close(conn);
		}

        out.println("<pre><blockquote><BODY><HTML>");
        out.close();
    }

	public void close(Connection conn)
		throws ServletException
	{
		try {
			conn.close();
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

    public Connection getConnection()
		throws ServletException
    {
		try {
			if(_ds == null) {
				String dsname = getInitParameter("data-source");
				Context ctx = new InitialContext();
				_ds = (DataSource)ctx.lookup("java:compenv" + dsname);
			}
			return _ds.getConnection();
		} catch(Exception e) {
			throw new ServletException(e);
		} 
	}
}

