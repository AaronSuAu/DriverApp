<%@page import="model.CarLicenses"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.RenewalNotices"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
   	<jsp:include page="_header.jsp" />
  </head>
  <body>
  		<% RenewalNotices rNotices= (RenewalNotices)request.getAttribute("renewalNotices"); 
  			CarLicenses carLicenses = (CarLicenses) request.getAttribute("carLicenses");
  		%>
		<div class="container">
      <div class="row">
        <div class="col-md-12 text-center">
          <h1>Please update your information</h1>
        </div>
        <div class="col-md-6 col-md-offset-3">
          <form method="POST" action="/AssignValidationClient/DriverUpdateAdd">
            <label for="address">Address</label>
            <input type="text" name="address" class="form-control" value="<%= rNotices.getAddress() %>" required>
            <label for="email">Email</label>
            <input type="email" name="email" required class="form-control" value="<%= rNotices.getContact_email() %>">
            <label for="expiryDate">Expiry Date</label>
            <p id="expiryDate"> <%= carLicenses.getExpiry_date() %></p>
            <input type="submit" name="" class="btn btn-primary btn-spacing" value="Next">
          </form>
        </div>
      </div>  
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
  </body>
</html>