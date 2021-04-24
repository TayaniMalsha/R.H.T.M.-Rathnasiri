<%@page import="com.Customer"%>
<%@page import="com.SystemUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/customer.js"></script>

</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-6">
		
			<h1>Customer Management</h1>
		
			<form action="customer.jsp" name="formCustomer" method="post" id="formCustomer">
				Name:
				<input id="name" name="name" type="text" class="form-control form-control-sm">
				<br>
				Email:
				<input id="email" name="email" type="text" class="form-control form-control-sm">
				<br>
				Address:
				<input id="address" name="address" type="text" class="form-control form-control-sm">
				<br>
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<input type="hidden" id="hiddenIDSave" name="hiddenIDSave" value="">
			
			</form>
			
			<div id="alertSuccess" class="atert alert-success"></div>
			<div id="alertError" class="atert alert-danger"></div>
			
			<br>
			
			<div id="divUsersGrid">
				<%
					Customer obj = new Customer();
					out.print(obj.readCustomer());
				%>
			</div>
			
		</div>
	</div>
</div>

</body>
</html>