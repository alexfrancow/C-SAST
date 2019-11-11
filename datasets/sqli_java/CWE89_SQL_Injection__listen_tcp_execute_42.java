 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__listen_tcp_execute_42.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-42.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: listen_tcp Read data using a listening tcp connection
  GoodSource: A hardcoded string
  Sinks: execute
     GoodSink: Use prepared statement and execute (properly)
     BadSink : data concatenated into SQL statement used in execute(), which could result in SQL Injection
  Flow Variant: 42 Data flow: data returned from one method to another in the same class
 
  






 java.io.BufferedReader;
 java.io.InputStreamReader;
 java.io.IOException;
 java.net.Socket;
 java.net.ServerSocket;

 java.util.logging.Level;

 java.sql.;


public class CWE89_SQL_Injection__listen_tcp_execute_42 extends AbstractTestCase
{
    private String badSource() throws Throwable
    {
        String data;

        data = "";  Initialize data 

         Read data using a listening tcp connection 
        {
            ServerSocket listener = null;
            Socket socket = null;
            BufferedReader readerBuffered = null;
            InputStreamReader readerInputStream = null;

             Read data using a listening tcp connection 
            try
            {
                listener = new ServerSocket(39543);
                socket = listener.accept();

                 read input from socket 

                readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                 POTENTIAL FLAW: Read data using a listening tcp connection 
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                 Close stream reading objects 
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

                 Close socket objects 
                try
                {
                    if (socket != null)
                    {
                        socket.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing Socket", exceptIO);
                }

                try
                {
                    if (listener != null)
                    {
                        listener.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing ServerSocket", exceptIO);
                }
            }
        }

        return data;
    }

    public void bad() throws Throwable
    {
        String data = badSource();

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
    private String goodG2BSource() throws Throwable
    {
        String data;

         FIX: Use a hardcoded string 
        data = "foo";

        return data;
    }

    private void goodG2B() throws Throwable
    {
        String data = goodG2BSource();

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

     goodB2G() - use badsource and goodsink 
    private String goodB2GSource() throws Throwable
    {
        String data;

        data = "";  Initialize data 

         Read data using a listening tcp connection 
        {
            ServerSocket listener = null;
            Socket socket = null;
            BufferedReader readerBuffered = null;
            InputStreamReader readerInputStream = null;

             Read data using a listening tcp connection 
            try
            {
                listener = new ServerSocket(39543);
                socket = listener.accept();

                 read input from socket 

                readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                 POTENTIAL FLAW: Read data using a listening tcp connection 
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                 Close stream reading objects 
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

                 Close socket objects 
                try
                {
                    if (socket != null)
                    {
                        socket.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing Socket", exceptIO);
                }

                try
                {
                    if (listener != null)
                    {
                        listener.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing ServerSocket", exceptIO);
                }
            }
        }

        return data;
    }

    private void goodB2G() throws Throwable
    {
        String data = goodB2GSource();

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

    public void good() throws Throwable
    {
        goodG2B();
        goodB2G();
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
