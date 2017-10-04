<%@page import="model.RenewalNotices"%>
<%@page import="java.util.ArrayList"%>
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
      <div class="col-sm-12 text-center">
          <h1>Notices</h1>
      </div>
      <div class="col-sm-12">
        <table class="table">
          <thead>
            <tr>
              <th>Address</th>
              <th>Email</th>
              <th>Status</th>
              <th>Review Result</th>
              <th>Manual Process</th>
            </tr>
          </thead>
          <tbody>
          	<%ArrayList<RenewalNotices> notices = (ArrayList)request.getAttribute("notices");
         	for(RenewalNotices notice: notices){	
        	 %> 	
        	 <tr>
              <td><%=notice.getAddress() %></td>
              <td><%=notice.getContact_email() %></td>
              <td><%=notice.getStatus() %></td>
              <td><%=notice.getReview_result() %></td>
              <td><a href=<%="/AssignValidationClient/manual?nid="+notice.getNid() %> class="btn btn-primary">Update</a></td>
            </tr> 
        	 <%} %>
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