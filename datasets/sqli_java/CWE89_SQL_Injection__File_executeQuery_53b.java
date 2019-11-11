 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__File_executeQuery_53b.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-53b.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: File Read data from file (named c:data.txt)
  GoodSource: A hardcoded string
  Sinks: executeQuery
     GoodSink: Use prepared statement and executeQuery (properly)
     BadSink : data concatenated into SQL statement used in executeQuery(), which could result in SQL Injection
  Flow Variant: 53 Data flow: data passed as an argument from one method through two others to a fourth; all four functions are in different classes in the same package
 
  






public class CWE89_SQL_Injection__File_executeQuery_53b
{
    public void badSink(String data ) throws Throwable
    {
        (new CWE89_SQL_Injection__File_executeQuery_53c()).badSink(data );
    }

     goodG2B() - use goodsource and badsink 
    public void goodG2BSink(String data ) throws Throwable
    {
        (new CWE89_SQL_Injection__File_executeQuery_53c()).goodG2BSink(data );
    }

     goodB2G() - use badsource and goodsink 
    public void goodB2GSink(String data ) throws Throwable
    {
        (new CWE89_SQL_Injection__File_executeQuery_53c()).goodB2GSink(data );
    }
}
