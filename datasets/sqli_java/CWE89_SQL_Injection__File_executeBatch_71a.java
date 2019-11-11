 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__File_executeBatch_71a.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-71a.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: File Read data from file (named c:data.txt)
  GoodSource: A hardcoded string
  Sinks: executeBatch
     GoodSink: Use prepared statement and executeBatch (properly)
     BadSink : data concatenated into SQL statement used in executeBatch(), which could result in SQL Injection
  Flow Variant: 71 Data flow: data passed as an Object reference argument from one method to another in different classes in the same package
 
  






 java.io.BufferedReader;
 java.io.InputStreamReader;
 java.io.FileInputStream;
 java.io.File;
 java.io.IOException;

 java.util.logging.Level;

public class CWE89_SQL_Injection__File_executeBatch_71a extends AbstractTestCase
{
    public void bad() throws Throwable
    {
        String data;

        data = "";  Initialize data 
        {
            File file = new File("C:data.txt");
            FileInputStream streamFileInput = null;
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;

            try
            {
                 read string from file into data 
                streamFileInput = new FileInputStream(file);
                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                 POTENTIAL FLAW: Read data from a file 
                 This will be reading the first "line" of the file, which
                  could be very long if there are little or no newlines in the file 
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

                try
                {
                    if (streamFileInput != null)
                    {
                        streamFileInput.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                }
            }
        }

        (new CWE89_SQL_Injection__File_executeBatch_71b()).badSink((Object)data  );
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

        (new CWE89_SQL_Injection__File_executeBatch_71b()).goodG2BSink((Object)data  );
    }

     goodB2G() - use badsource and goodsink 
    private void goodB2G() throws Throwable
    {
        String data;

        data = "";  Initialize data 
        {
            File file = new File("C:data.txt");
            FileInputStream streamFileInput = null;
            InputStreamReader readerInputStream = null;
            BufferedReader readerBuffered = null;

            try
            {
                 read string from file into data 
                streamFileInput = new FileInputStream(file);
                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                 POTENTIAL FLAW: Read data from a file 
                 This will be reading the first "line" of the file, which
                  could be very long if there are little or no newlines in the file 
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

                try
                {
                    if (streamFileInput != null)
                    {
                        streamFileInput.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                }
            }
        }

        (new CWE89_SQL_Injection__File_executeBatch_71b()).goodB2GSink((Object)data  );
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
