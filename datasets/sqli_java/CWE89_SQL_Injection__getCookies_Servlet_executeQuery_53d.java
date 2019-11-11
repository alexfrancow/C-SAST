 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__getCookies_Servlet_executeQuery_53d.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-53d.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: getCookies_Servlet Read data from the first cookie using getCookies()
  GoodSource: A hardcoded string
  Sinks: executeQuery
     GoodSink: Use prepared statement and executeQuery (properly)
     BadSink : data concatenated into SQL statement used in executeQuery(), which could result in SQL Injection
  Flow Variant: 53 Data flow: data passed as an argument from one method through two others to a fourth; all four functions are in different classes in the same package
 
  




 javax.servlet.http.;

 java.sql.;

 java.util.logging.Level;

public class CWE89_SQL_Injection__getCookies_Servlet_executeQuery_53d
{
    public void badSink(String data , HttpServletRequest request, HttpServletResponse response) throws Throwable
    {

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

     goodG2B() - use goodsource and badsink 
    public void goodG2BSink(String data , HttpServletRequest request, HttpServletResponse response) throws Throwable
    {

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

     goodB2G() - use badsource and goodsink 
    public void goodB2GSink(String data , HttpServletRequest request, HttpServletResponse response) throws Throwable
    {

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
