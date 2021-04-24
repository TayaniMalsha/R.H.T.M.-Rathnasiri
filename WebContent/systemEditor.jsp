<%@page import="com.SystemUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/systemEditor.js"></script>

</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-6">
		
			<h1>System User Management</h1>
		
			<form action="systemEditor.jsp" name="formSystemUser" method="post" id="formSystemUser">
				UserName:
				<input id="username" name="username" type="text" class="form-control form-control-sm">
				<br>
				Email:
				<input id="email" name="email" type="text" class="form-control form-control-sm">
				<br>
				Password:
				<input id="password" name="password" type="password" class="form-control form-control-sm">
				<br>
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<input type="hidden" id="hiddenIDSave" name="hiddenIDSave" value="">
			
			</form>
			
			<div id="alertSuccess" class="atert alert-success"></div>
			<div id="alertError" class="atert alert-danger"></div>
			
			<br>
			
			<div id="divUsersGrid">
				<%
					SystemUser obj = new SystemUser();
					out.print(obj.readSystemUsers());
				%>
			</div>
			
		</div>
	</div>
</div>

</body>
</html>