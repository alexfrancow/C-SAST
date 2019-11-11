 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__database_executeQuery_52a.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-52a.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: database Read data from a database
  GoodSource: A hardcoded string
  Sinks: executeQuery
     GoodSink: Use prepared statement and executeQuery (properly)
     BadSink : data concatenated into SQL statement used in executeQuery(), which could result in SQL Injection
  Flow Variant: 52 Data flow: data passed as an argument from one method to another to another in three different classes in the same package
 
  






 java.sql.Connection;
 java.sql.PreparedStatement;
 java.sql.ResultSet;
 java.sql.SQLException;

 java.util.logging.Level;

public class CWE89_SQL_Injection__database_executeQuery_52a extends AbstractTestCase
{
    public void bad() throws Throwable
    {
        String data;

        data = "";  Initialize data 

         Read data from a database 
        {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try
            {
                 setup the connection 
                connection = IO.getDBConnection();

                 prepare and execute a (hardcoded) query 
                preparedStatement = connection.prepareStatement("select name from users where id=0");
                resultSet = preparedStatement.executeQuery();

                 POTENTIAL FLAW: Read data from a database query resultset 
                data = resultSet.getString(1);
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error with SQL statement", exceptSql);
            }
            finally
            {
                 Close database objects 
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
                    if (preparedStatement != null)
                    {
                        preparedStatement.close();
                    }
                }
                catch (SQLException exceptSql)
                {
                    IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql);
                }

                try
                {
                    if (connection != null)
                    {
                        connection.close();
                    }
                }
                catch (SQLException exceptSql)
                {
                    IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql);
                }
            }
        }

        (new CWE89_SQL_Injection__database_executeQuery_52b()).badSink(data );
    }

    public void good() throws Throwable
    {
        goodG2B();
        goodB2G();
    }

     goodG2B() - use goodsource and badsink 
    private void goodG2B() throws Throwable
    {
        String data;

         FIX: Use a hardcoded string 
        data = "foo";

        (new CWE89_SQL_Injection__database_executeQuery_52b()).goodG2BSink(data );
    }

     goodB2G() - use badsource and goodsink 
    private void goodB2G() throws Throwable
    {
        String data;

        data = "";  Initialize data 

         Read data from a database 
        {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try
            {
                 setup the connection 
                connection = IO.getDBConnection();

                 prepare and execute a (hardcoded) query 
                preparedStatement = connection.prepareStatement("select name from users where id=0");
                resultSet = preparedStatement.executeQuery();

                 POTENTIAL FLAW: Read data from a database query resultset 
                data = resultSet.getString(1);
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error with SQL statement", exceptSql);
            }
            finally
            {
                 Close database objects 
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
                    if (preparedStatement != null)
                    {
                        preparedStatement.close();
                    }
                }
                catch (SQLException exceptSql)
                {
                    IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql);
                }

                try
                {
                    if (connection != null)
                    {
                        connection.close();
                    }
                }
                catch (SQLException exceptSql)
                {
                    IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql);
                }
            }
        }

        (new CWE89_SQL_Injection__database_executeQuery_52b()).goodB2GSink(data );
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
