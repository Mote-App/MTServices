<%@ page import="java.util.*, rain.*" errorPage="oops.jsp" %>

<%
	String contextPath = request.getContextPath();
	String baseLink = contextPath + "/VideoCatalogServlet?video_category=";


	RainForestService rainForestService  = (RainForestService) application.getAttribute(Constants.RAINFOREST_SERVICE_KEY);

	List<Category> categories = rainForestService.getVideoCategories();
	
%>


<body>
<table border="0" bgcolor="#FFFFCC" cellspacing="0" cellpadding="0" width="100%" >

	<tr>
		<td><a HREF="<%= contextPath %>/ShoppingCartServlet?command=view" TARGET="main"><img src="../images/cart.gif" ALT="View Your Shopping Cart" BORDER="0"></a></td>
	</tr>

	<tr><td>&nbsp;</td>
	</tr>


	
	<tr>
		<td>
		&nbsp;<font face="Verdana,Arial,Helvetica" size="1">
						<a href="../index.html" TARGET="_parent"><b>Home</b></a>
			</font><br>

<%
            for(Category category: categories) {
            
%>


		&nbsp;<font face="Verdana,Arial,Helvetica" size="1">
						<a href="<%= baseLink %><%= java.net.URLEncoder.encode(category.toString()) %>" TARGET="main"><%=category %></a>
					</font><br>
					
					
<%

            }
%>
	</tr>
	<tr>
		<td>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		</td>
	</tr>


</table>
</body>