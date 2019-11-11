 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__console_readLine_execute_41.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-41.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: console_readLine Read data from the console using readLine()
  GoodSource: A hardcoded string
  Sinks: execute
     GoodSink: Use prepared statement and execute (properly)
     BadSink : data concatenated into SQL statement used in execute(), which could result in SQL Injection
  Flow Variant: 41 Data flow: data passed as an argument from one method to another in the same class
 
  






 java.io.BufferedReader;
 java.io.InputStreamReader;
 java.io.IOException;

 java.util.logging.Level;

 java.sql.;


public class CWE89_SQL_Injection__console_readLine_execute_41 extends AbstractTestCase
{
    private void badSink(String data ) throws Throwable
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

    public void bad() throws Throwable
    {
        String data;

        data = "";  Initialize data 

        {
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;

             read user input from console with readLine 
            try
            {
                readerInputStream = new InputStreamReader(System.in, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                 POTENTIAL FLAW: Read data from the console using readLine 
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                try
                {
                    if (readerBuffered != null)
                    {
                        readerBuffered.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                }

                try
                {
                    if (readerInputStream != null)
                    {
                        readerInputStream.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                }
            }
        }
         NOTE: Tools may report a flaw here because buffread and isr are not closed.  Unfortunately, closing those will close System.in, which will cause any future attempts to read from the console to fail and throw an exception 

        badSink(data  );
    }

    public void good() throws Throwable
    {
        goodG2B();
        goodB2G();
    }

    private void goodG2BSink(String data ) throws Throwable
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

     goodG2B() - use goodsource and badsink 
    private void goodG2B() throws Throwable
    {
        String data;

         FIX: Use a hardcoded string 
        data = "foo";

        goodG2BSink(data  );
    }

    private void goodB2GSink(String data ) throws Throwable
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

     goodB2G() - use badsource and goodsink 
    private void goodB2G() throws Throwable
    {
        String data;

        data = "";  Initialize data 

        {
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;

             read user input from console with readLine 
            try
            {
                readerInputStream = new InputStreamReader(System.in, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                 POTENTIAL FLAW: Read data from the console using readLine 
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                try
                {
                    if (readerBuffered != null)
                    {
                        readerBuffered.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                }

                try
                {
                    if (readerInputStream != null)
                    {
                        readerInputStream.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                }
            }
        }
         NOTE: Tools may report a flaw here because buffread and isr are not closed.  Unfortunately, closing those will close System.in, which will cause any future attempts to read from the console to fail and throw an exception 

        goodB2GSink(data  );
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
