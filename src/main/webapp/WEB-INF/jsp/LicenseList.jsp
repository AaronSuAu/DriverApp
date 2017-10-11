<%@page import="java.util.ArrayList"%>
<%@page import="model.LicenseNotice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
   	<jsp:include page="_header.jsp" />
  </head>
  <body>
	<jsp:include page="_nav.jsp" />
  <div class="container">
    <div class="row">
      <div class="col-sm-3">
        <form action="/AssignValidationClient/expiry" method="POST">
          <div class="input-group">
            <input type="number" class="form-control" placeholder="Enter due date" name="days">
             <span class="input-group-btn">
                  <button type="submit" class="btn btn-default">Filter</button>
             </span>
          </div>
        </form>
        <%String error = (String)request.getAttribute("error");
						   if(error != null){   %>
						   <h4> <%=error %></h4>
						   <%} %>
      </div>
      <div class="col-sm-6 text-center">
          <h1>Liceses</h1> 
      </div>
      <div class="col-sm-12">
        <table class="table">
          <thead>
            <tr>
              <th>Driver Name</th>
              <th>Address</th>
              <th>Email</th>
              <th>License Number</th>
              <th>License Class</th>
              <th>Expiry Date</th>
              <th>Status</th>
              <th>Operation</th>
            </tr>
          </thead>
         
          <tbody>  
          	<%ArrayList<LicenseNotice> licenses = (ArrayList)request.getAttribute("licenses");
          	if(licenses != null){
         	for(LicenseNotice license: licenses){
         		if(license.getStatus() == null){
        	 %> 	
        	 <tr>
	              <td><%=license.getDriver_name() %></td>
	              <td><%=license.getAddress() %></td>
	              <td><%=license.getEmail() %></td>
	              <td><%=license.getLicense_number() %></td>
	              <td><%=license.getLicense_class() %></td>
	              <td><%=license.getExpiry_date() %></td>
	   				<td>No Notice Generated</td>
	   				<td><a href=<%="/AssignValidationClient/generateNotice?licid="+license.getLicid() %> class="btn btn-primary">Generate Notice</a></td>
            </tr> 
        	 
        	 <%} else if(!license.getStatus().equals("archived")){%>
        	 	<tr>
              <td><%=license.getDriver_name() %></td>
              <td><%=license.getAddress() %></td>
              <td><%=license.getEmail() %></td>
              <td><%=license.getLicense_number() %></td>
              <td><%=license.getLicense_class() %></td>
              <td><%=license.getExpiry_date() %></td>
             	 <td><%=license.getStatus() %></td>
             	 <td><button class="btn btn-block">Generate Notice</button></td>
             	</tr>
              <%}}} %>	
          </tbody>
        </table>
      </div>
    </div>
  </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
  </body>
</html>