
@description This abstract class is the base for the other 
AbstractTestCaseServlet classes.



 note on servlets - when a new Servlet is created, it must be listed in the web.xml
  file in order to run.  The easy way to do this is in Eclipse:
    - right click on testcases in package explorer
    - select New->Servlet
    - Check the box that says use an existing Servlet class or JSP
    - Click browse and select the new Servlet class you have created
    - Click OK, Next, then Finish  
    - You should now be able to test the servlet by clicking Run in eclipse  
 

 java.io.IOException;
 javax.servlet.http.;

public abstract class AbstractTestCaseServletBase extends HttpServlet 
{
    private static final long serialVersionUID = 1L;  needed since Servlets are serializable 

     from a static method like main(), there is not an easy way to get the current
      classes's name.  We do a trick here to make it work so that we don't have
      to edit the main for each test case or use a string member to contain the class
      name
     
    public static void mainFromParent(String[] args)
        throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
    	
    	 	There is no such method for servlets testcases
    	 	This is just a temp-small modification to make servlets compile and run
    	 

        StackTraceElement stackTraceElements[] = Thread.currentThread().getStackTrace();
    
        String myClassName = stackTraceElements[stackTraceElements.length -1].getClassName();
    
        System.out.println("Cannot run Servlet-like testcases by its main method!");
        System.out.println("Please, run it on a Server!");
    
    	 Commented, so the testcases can compile
		 Class<?> myClass = Class.forName(myClassName);
         AbstractTestCase myObject = (AbstractTestCase) myClass.newInstance();
         myObject.runTest(myClassName);
    }

     this method runs a test on its own, creating a full HTML document
      it is called from doGet and doPost in this file
     
    public void runTestSolo(HttpServletRequest request, HttpServletResponse response)
        throws IOException 
    {  
        response.getWriter().println("<html><body>");
        
        runTest(request, response);

        response.getWriter().println("<body><html>");   
    }
     
     this method runs the tests, but assumes that the html document has already
      been started.  It is called by runTestSolo and by ServletMain
     
    abstract public void runTest(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    {
        try 
        {
            runTestSolo(request, response);
        } 
        catch (Throwable throwableException) 
        {
            IO.writeLine("Caught a throwable from runTest()");

            IO.writeLine("<br>Throwable's message = " + throwableException.getMessage());

            StackTraceElement stackTraceElements[] = throwableException.getStackTrace();

            IO.writeLine("Stack trace below");

            for (StackTraceElement stackTraceElement : stackTraceElements) 
            {
                IO.writeLine(stackTraceElement.toString());
            } 
        } 
    }
} 
