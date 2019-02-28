<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.demo.service.*, com.demo.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="service" class="com.demo.service.ProductService"></jsp:useBean>
<%
	int id = Integer.parseInt(request.getParameter("id"));

	service.deleteProduct(id);
	
	response.sendRedirect("index.jsp");
%>
</body>
</html>