 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__console_readLine_executeBatch_81_base.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-81_base.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: console_readLine Read data from the console using readLine()
  GoodSource: A hardcoded string
  Sinks: executeBatch
     GoodSink: Use prepared statement and executeBatch (properly)
     BadSink : data concatenated into SQL statement used in executeBatch(), which could result in SQL Injection
  Flow Variant: 81 Data flow: data passed in a parameter to an abstract method
 
  

package testcases.CWE89_SQL_Injection.s01;
 testcasesupport.;

 javax.servlet.http.;

public abstract class CWE89_SQL_Injection__console_readLine_executeBatch_81_base
{
    public abstract void action(String data ) throws Throwable;
}
