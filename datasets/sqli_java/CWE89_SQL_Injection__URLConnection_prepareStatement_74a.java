 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__URLConnection_prepareStatement_74a.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-74a.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: URLConnection Read data from a web server with URLConnection
  GoodSource: A hardcoded string
  Sinks: prepareStatement
     GoodSink: Use prepared statement and execute (properly)
     BadSink : data concatenated into SQL statement used in prepareStatement() call, which could result in SQL Injection
  Flow Variant: 74 Data flow: data passed in a HashMap from one method to another in different source files in the same package
 
  



 java.util.HashMap;



 java.io.BufferedReader;
 java.io.InputStreamReader;
 java.io.IOException;
 java.net.URL;
 java.net.URLConnection;

 java.util.logging.Level;

public class CWE89_SQL_Injection__URLConnection_prepareStatement_74a extends AbstractTestCase
{
    public void bad() throws Throwable
    {
        String data;

        data = "";  Initialize data 

         read input from URLConnection 
        {
            URLConnection urlConnection = (new URL("http:www.example.org")).openConnection();
            BufferedReader readerBuffered = null;
            InputStreamReader readerInputStream = null;

            try
            {
                readerInputStream = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                 POTENTIAL FLAW: Read data from a web server with URLConnection 
                 This will be reading the first "line" of the response body,
                  which could be very long if there are no newlines in the HTML 
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                 clean up stream reading objects 
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

        HashMap<Integer,String> dataHashMap = new HashMap<Integer,String>();
        dataHashMap.put(0, data);
        dataHashMap.put(1, data);
        dataHashMap.put(2, data);
        (new CWE89_SQL_Injection__URLConnection_prepareStatement_74b()).badSink(dataHashMap  );
    }

    public void good() throws Throwable
    {
        goodG2B();
        goodB2G();
    }

     goodG2B() - use GoodSource and BadSink 
    private void goodG2B() throws Throwable
    {
        String data;

         FIX: Use a hardcoded string 
        data = "foo";

        HashMap<Integer,String> dataHashMap = new HashMap<Integer,String>();
        dataHashMap.put(0, data);
        dataHashMap.put(1, data);
        dataHashMap.put(2, data);
        (new CWE89_SQL_Injection__URLConnection_prepareStatement_74b()).goodG2BSink(dataHashMap  );
    }

     goodB2G() - use BadSource and GoodSink 
    private void goodB2G() throws Throwable
    {
        String data;

        data = "";  Initialize data 

         read input from URLConnection 
        {
            URLConnection urlConnection = (new URL("http:www.example.org")).openConnection();
            BufferedReader readerBuffered = null;
            InputStreamReader readerInputStream = null;

            try
            {
                readerInputStream = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                 POTENTIAL FLAW: Read data from a web server with URLConnection 
                 This will be reading the first "line" of the response body,
                  which could be very long if there are no newlines in the HTML 
                data = readerBuffered.readLine();
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
            finally
            {
                 clean up stream reading objects 
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

        HashMap<Integer,String> dataHashMap = new HashMap<Integer,String>();
        dataHashMap.put(0, data);
        dataHashMap.put(1, data);
        dataHashMap.put(2, data);
        (new CWE89_SQL_Injection__URLConnection_prepareStatement_74b()).goodB2GSink(dataHashMap  );
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
