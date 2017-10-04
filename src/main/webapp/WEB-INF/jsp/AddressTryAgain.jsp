<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
   	<jsp:include page="_header.jsp" />
  </head>
  <body>
		<div class="container">
      <div class="row">
        <div class="col-md-12">
        	  <% String result = request.getAttribute("reason").toString(); 
        	  	 String token = request.getAttribute("token").toString();
        	  %>
          <h1><%= result %></h1>
          <h1>Please click the button to go back</h1>
          <a class="btn btn-danger" href="/AssignValidationClient/DriverLink1?token=<%= token%>">Go Back</a>
        </div>
      </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
  </body>
</html>