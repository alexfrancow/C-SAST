 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__getParameter_Servlet_executeQuery_74b.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-74b.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: getParameter_Servlet Read data from a querystring using getParameter()
  GoodSource: A hardcoded string
  Sinks: executeQuery
     GoodSink: Use prepared statement and executeQuery (properly)
     BadSink : data concatenated into SQL statement used in executeQuery(), which could result in SQL Injection
  Flow Variant: 74 Data flow: data passed in a HashMap from one method to another in different source files in the same package
 
  



 java.util.HashMap;

 javax.servlet.http.;

 java.sql.;

 java.util.logging.Level;

public class CWE89_SQL_Injection__getParameter_Servlet_executeQuery_74b
{
    public void badSink(HashMap<Integer,String> dataHashMap , HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = dataHashMap.get(2);

        Connection dbConnection = null;
        Statement sqlStatement = null;
        ResultSet resultSet = null;

        try
        {
            dbConnection = IO.getDBConnection();
            sqlStatement = dbConnection.createStatement();

             POTENTIAL FLAW: data concatenated into SQL statement used in executeQuery(), which could result in SQL Injection 
            resultSet = sqlStatement.executeQuery("select  from users where name='"+data+"'");

            IO.writeLine(resultSet.getRow());  Use ResultSet in some way 
        }
        catch (SQLException exceptSql)
        {
            IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
        }
        finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing ResultSet", exceptSql);
            }

            try
            {
                if (sqlStatement != null)
                {
                    sqlStatement.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing Statement", exceptSql);
            }

            try
            {
                if (dbConnection != null)
                {
                    dbConnection.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql);
            }
        }

    }

     goodG2B() - use GoodSource and BadSink 
    public void goodG2BSink(HashMap<Integer,String> dataHashMap , HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = dataHashMap.get(2);

        Connection dbConnection = null;
        Statement sqlStatement = null;
        ResultSet resultSet = null;

        try
        {
            dbConnection = IO.getDBConnection();
            sqlStatement = dbConnection.createStatement();

             POTENTIAL FLAW: data concatenated into SQL statement used in executeQuery(), which could result in SQL Injection 
            resultSet = sqlStatement.executeQuery("select  from users where name='"+data+"'");

            IO.writeLine(resultSet.getRow());  Use ResultSet in some way 
        }
        catch (SQLException exceptSql)
        {
            IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
        }
        finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing ResultSet", exceptSql);
            }

            try
            {
                if (sqlStatement != null)
                {
                    sqlStatement.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing Statement", exceptSql);
            }

            try
            {
                if (dbConnection != null)
                {
                    dbConnection.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql);
            }
        }

    }

     goodB2G() - use BadSource and GoodSink 
    public void goodB2GSink(HashMap<Integer,String> dataHashMap , HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = dataHashMap.get(2);

        Connection dbConnection = null;
        PreparedStatement sqlStatement = null;
        ResultSet resultSet = null;

        try
        {
             FIX: Use prepared statement and executeQuery (properly) 
            dbConnection = IO.getDBConnection();
            sqlStatement = dbConnection.prepareStatement("select  from users where name=?");
            sqlStatement.setString(1, data);

            resultSet = sqlStatement.executeQuery();

            IO.writeLine(resultSet.getRow());  Use ResultSet in some way 
        }
        catch (SQLException exceptSql)
        {
            IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
        }
        finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing ResultSet", exceptSql);
            }

            try
            {
                if (sqlStatement != null)
                {
                    sqlStatement.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql);
            }

            try
            {
                if (dbConnection != null)
                {
                    dbConnection.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql);
            }
        }

    }
}
