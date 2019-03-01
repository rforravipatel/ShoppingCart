<%@page import="java.util.ArrayList"%>
<%@page import="com.demo.service.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.demo.service.*, com.demo.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="CSS/indexcss.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:useBean id="service" class="com.demo.service.ProductService" />
<a href="insert.html">
	<img alt="Insert" id= "img_1" src="Images/insert.png">
</a>
<%
	
	ArrayList<Product> list = service.viewAll();
	if(list != null && list.size()>0){
	
%>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Seller</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		
<%
	for(Product p:list){
%>		

	<tr>
		<td><%=p.getId() %></td>
		<td><%=p.getName() %></td>
		<td><%=p.getPrice() %></td>
		<td><%=p.getSeller() %></td>
		<td><a href=<%="editProduct.jsp?id=" +p.getId() %>><img id="img_3" src="Images/edit.png" ></a></td>  
        <td><a href=<%="deleteProduct.jsp?id=" +p.getId() %>><img id="img_2" src="Images/delete.png" ></a></td> 
	</tr>
<%
	}
}
%>	

	</table>
</body>
</html>