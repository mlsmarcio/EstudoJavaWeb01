<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<div class="container">
		
		<a href="logincomservlet.jsp" class="btn btn-link btn-md">
			<span class="glyphicon glyphicon-home sm"></span>
			Início
		</a>
		
		<%
			String dest = request.getParameter("destino");
			String tit = request.getParameter("titulo");
			if (dest != null && dest != ""){
				out.println("<a href='" + dest + 
					"' class='btn btn-link btn-md'> <span class='glyphicon glyphicon-chevron-left'></span> " + 
					tit + "</a>");
			}
		%>
      	
	</div>		
	<br/>
</body>
</html>