<%@page import="model.LicenseNotice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<jsp:include page="_header.jsp" />
</head>
<body>
	<div class="container">
		<h2>Your Information</h2>
		<table class="table">
		<% LicenseNotice ln = (LicenseNotice)request.getAttribute("licenseNotice");
			
		%>
			<tbody>
				
				<tr>
					<th>License Number</th>
					<td><%= ln.getLicense_number() %></td>
				</tr>
				<tr>
					<th>Name</th>
					<td><%= ln.getDriver_name() %></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><%= ln.getEmail() %></td>
				</tr>
				<tr>
					<th>Address</th>
					<td><%= ln.getAddress() %></td>
				</tr>
				<tr>
					<th>License Class</th>
					<td> <%= ln.getLicense_class() %> </td>
				</tr>
				<tr>
					<th>Expiry Date</th>
					<td> <%= ln.getExpiry_date() %> </td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<jsp:include page="_script.jsp" />
</body>
</html>