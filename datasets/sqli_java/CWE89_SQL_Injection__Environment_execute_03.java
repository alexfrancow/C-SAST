 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__Environment_execute_03.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-03.tmpl.java


 @description
 CWE: 89 SQL Injection
 BadSource: Environment Read data from an environment variable
 GoodSource: A hardcoded string
 Sinks: execute
    GoodSink: Use prepared statement and execute (properly)
    BadSink : data concatenated into SQL statement used in execute(), which could result in SQL Injection
 Flow Variant: 03 Control flow: if(5==5) and if(5!=5)

 






 java.sql.;

 java.util.logging.Level;

public class CWE89_SQL_Injection__Environment_execute_03 extends AbstractTestCase
{
    public void bad() throws Throwable
    {
        String data;
        if (5==5)
        {
             get environment variable ADD 
             POTENTIAL FLAW: Read data from an environment variable 
            data = System.getenv("ADD");
        }
        else
        {
             INCIDENTAL: CWE 561 Dead Code, the code below will never run
              but ensure data is inititialized before the Sink to avoid compiler errors 
            data = null;
        }

        if (5==5)
        {
            Connection dbConnection = null;
            Statement sqlStatement = null;
            try
            {
                dbConnection = IO.getDBConnection();
                sqlStatement = dbConnection.createStatement();
                 POTENTIAL FLAW: data concatenated into SQL statement used in execute(), which could result in SQL Injection 
                Boolean result = sqlStatement.execute("insert into users (status) values ('updated') where name='"+data+"'");
                if(result)
                {
                    IO.writeLine("Name, " + data + ", updated successfully");
                }
                else
                {
                    IO.writeLine("Unable to update records for user: " + data);
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
            }
            finally
            {
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
    }

     goodG2B1() - use goodsource and badsink by changing first 5==5 to 5!=5 
    private void goodG2B1() throws Throwable
    {
        String data;
        if (5!=5)
        {
             INCIDENTAL: CWE 561 Dead Code, the code below will never run
              but ensure data is inititialized before the Sink to avoid compiler errors 
            data = null;
        }
        else
        {

             FIX: Use a hardcoded string 
            data = "foo";

        }

        if (5==5)
        {
            Connection dbConnection = null;
            Statement sqlStatement = null;
            try
            {
                dbConnection = IO.getDBConnection();
                sqlStatement = dbConnection.createStatement();
                 POTENTIAL FLAW: data concatenated into SQL statement used in execute(), which could result in SQL Injection 
                Boolean result = sqlStatement.execute("insert into users (status) values ('updated') where name='"+data+"'");
                if(result)
                {
                    IO.writeLine("Name, " + data + ", updated successfully");
                }
                else
                {
                    IO.writeLine("Unable to update records for user: " + data);
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
            }
            finally
            {
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
    }

     goodG2B2() - use goodsource and badsink by reversing statements in first if 
    private void goodG2B2() throws Throwable
    {
        String data;
        if (5==5)
        {
             FIX: Use a hardcoded string 
            data = "foo";
        }
        else
        {
             INCIDENTAL: CWE 561 Dead Code, the code below will never run
              but ensure data is inititialized before the Sink to avoid compiler errors 
            data = null;
        }

        if (5==5)
        {
            Connection dbConnection = null;
            Statement sqlStatement = null;
            try
            {
                dbConnection = IO.getDBConnection();
                sqlStatement = dbConnection.createStatement();
                 POTENTIAL FLAW: data concatenated into SQL statement used in execute(), which could result in SQL Injection 
                Boolean result = sqlStatement.execute("insert into users (status) values ('updated') where name='"+data+"'");
                if(result)
                {
                    IO.writeLine("Name, " + data + ", updated successfully");
                }
                else
                {
                    IO.writeLine("Unable to update records for user: " + data);
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
            }
            finally
            {
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
    }

     goodB2G1() - use badsource and goodsink by changing second 5==5 to 5!=5 
    private void goodB2G1() throws Throwable
    {
        String data;
        if (5==5)
        {
             get environment variable ADD 
             POTENTIAL FLAW: Read data from an environment variable 
            data = System.getenv("ADD");
        }
        else
        {
             INCIDENTAL: CWE 561 Dead Code, the code below will never run
              but ensure data is inititialized before the Sink to avoid compiler errors 
            data = null;
        }

        if (5!=5)
        {
             INCIDENTAL: CWE 561 Dead Code, the code below will never run 
            IO.writeLine("Benign, fixed string");
        }
        else
        {

            Connection dbConnection = null;
            PreparedStatement sqlStatement = null;

            try
            {
                 FIX: Use prepared statement and execute (properly) 
                dbConnection = IO.getDBConnection();
                sqlStatement = dbConnection.prepareStatement("insert into users (status) values ('updated') where name=?");
                sqlStatement.setString(1, data);

                Boolean result = sqlStatement.execute();

                if (result)
                {
                    IO.writeLine("Name, " + data + ", updated successfully");
                }
                else
                {
                    IO.writeLine("Unable to update records for user: " + data);
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
            }
            finally
            {
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

     goodB2G2() - use badsource and goodsink by reversing statements in second if  
    private void goodB2G2() throws Throwable
    {
        String data;
        if (5==5)
        {
             get environment variable ADD 
             POTENTIAL FLAW: Read data from an environment variable 
            data = System.getenv("ADD");
        }
        else
        {
             INCIDENTAL: CWE 561 Dead Code, the code below will never run
              but ensure data is inititialized before the Sink to avoid compiler errors 
            data = null;
        }

        if (5==5)
        {
            Connection dbConnection = null;
            PreparedStatement sqlStatement = null;
            try
            {
                 FIX: Use prepared statement and execute (properly) 
                dbConnection = IO.getDBConnection();
                sqlStatement = dbConnection.prepareStatement("insert into users (status) values ('updated') where name=?");
                sqlStatement.setString(1, data);
                Boolean result = sqlStatement.execute();
                if (result)
                {
                    IO.writeLine("Name, " + data + ", updated successfully");
                }
                else
                {
                    IO.writeLine("Unable to update records for user: " + data);
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
            }
            finally
            {
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

    public void good() throws Throwable
    {
        goodG2B1();
        goodG2B2();
        goodB2G1();
        goodB2G2();
    }

     Below is the main(). It is only used when building this testcase on
      its own for testing or for building a binary to use in testing binary
      analysis tools. It is not used when compiling all the testcases as one
      application, which is how source code analysis tools are tested.
     
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}
