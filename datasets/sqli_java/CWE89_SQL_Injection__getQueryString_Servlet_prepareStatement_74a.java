 TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__getQueryString_Servlet_prepareStatement_74a.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-74a.tmpl.java


  @description
  CWE: 89 SQL Injection
  BadSource: getQueryString_Servlet Parse id param out of the URL query string (without using getParameter())
  GoodSource: A hardcoded string
  Sinks: prepareStatement
     GoodSink: Use prepared statement and execute (properly)
     BadSink : data concatenated into SQL statement used in prepareStatement() call, which could result in SQL Injection
  Flow Variant: 74 Data flow: data passed in a HashMap from one method to another in different source files in the same package
 
  



 java.util.HashMap;

 javax.servlet.http.;

 java.util.StringTokenizer;

public class CWE89_SQL_Injection__getQueryString_Servlet_prepareStatement_74a extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        data = "";  initialize data in case id is not in query string 

         POTENTIAL FLAW: Parse id param out of the URL querystring (without using getParameter()) 
        {
            StringTokenizer tokenizer = new StringTokenizer(request.getQueryString(), "&");
            while (tokenizer.hasMoreTokens())
            {
                String token = tokenizer.nextToken();  a token will be like "id=foo" 
                if(token.startsWith("id="))  check if we have the "id" parameter" 
                {
                    data = token.substring(3);  set data to "foo" 
                    break;  exit while loop 
                }
            }
        }

        HashMap<Integer,String> dataHashMap = new HashMap<Integer,String>();
        dataHashMap.put(0, data);
        dataHashMap.put(1, data);
        dataHashMap.put(2, data);
        (new CWE89_SQL_Injection__getQueryString_Servlet_prepareStatement_74b()).badSink(dataHashMap , request, response );
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B(request, response);
        goodB2G(request, response);
    }

     goodG2B() - use GoodSource and BadSink 
    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

         FIX: Use a hardcoded string 
        data = "foo";

        HashMap<Integer,String> dataHashMap = new HashMap<Integer,String>();
        dataHashMap.put(0, data);
        dataHashMap.put(1, data);
        dataHashMap.put(2, data);
        (new CWE89_SQL_Injection__getQueryString_Servlet_prepareStatement_74b()).goodG2BSink(dataHashMap , request, response );
    }

     goodB2G() - use BadSource and GoodSink 
    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        data = "";  initialize data in case id is not in query string 

         POTENTIAL FLAW: Parse id param out of the URL querystring (without using getParameter()) 
        {
            StringTokenizer tokenizer = new StringTokenizer(request.getQueryString(), "&");
            while (tokenizer.hasMoreTokens())
            {
                String token = tokenizer.nextToken();  a token will be like "id=foo" 
                if(token.startsWith("id="))  check if we have the "id" parameter" 
                {
                    data = token.substring(3);  set data to "foo" 
                    break;  exit while loop 
                }
            }
        }

        HashMap<Integer,String> dataHashMap = new HashMap<Integer,String>();
        dataHashMap.put(0, data);
        dataHashMap.put(1, data);
        dataHashMap.put(2, data);
        (new CWE89_SQL_Injection__getQueryString_Servlet_prepareStatement_74b()).goodB2GSink(dataHashMap , request, response );
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
