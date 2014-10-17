<%@ page isErrorPage="true"%>

<html>
 <body>
   <%= exception%>
  
   <br/>
   <h3> Here is the Stack Trace </h3>
   
   <pre>
   
   <%   java.io.PrintWriter myWriter = new java.io.PrintWriter(out);
        exception.printStackTrace(myWriter);
   %>
   
   </pre>
 </body>
</html>