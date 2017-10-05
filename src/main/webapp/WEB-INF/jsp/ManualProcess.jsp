<%@page import="model.FeePayments"%>
<%@page import="model.RenewalNotices"%>
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
        <div class="col-md-6 col-md-offset-3">
        <% String error = (String)request.getAttribute("error"); 
        	if(error != null){ %>
        	<h5><%=error %></h5>
        <%}%>
        <% RenewalNotices notice = (RenewalNotices)request.getAttribute("notice");
        	FeePayments payment = (FeePayments)request.getAttribute("payment");
 	     %>
        <% String status = notice.getStatus(); %> 
          <form action="/AssignValidationClient/manuallyUpdate" method="POST" style="margin-top: 10px;">
           <%--  <label for="licenseNumber">License Number:</label>
            <p id="licenseNumber"> <%=notice.getLicid() %></p> --%>
            <label for="address">Address</label>
            <input type="text" name="address" id="address" class="form-control" value=<%=notice.getAddress() %>>
            <label for="email">Email</label>
            <input type="email" name="email" id='email' class="form-control" value=<%=notice.getContact_email() %>>
            <label for="status">Status</label>
            <select class="form-control" id="status" name="status">
              <option value="new" <%if(status.equals("new")){ %> selected <%} %>>New</option>
              <option value="validated" <%if(status.equals("validated")){ %> selected <%} %>>Validated</option>
              <option value="rejected" <%if(status.equals("rejected")){ %> selected <%} %>>Rejected</option>
              <option value="accepted" <%if(status.equals("accepted")){ %> selected <%} %>>Accepted</option>
              <option value="failed" <%if(status.equals("failed")){ %> selected <%} %>>Failed</option>
              <option value="underReview" <%if(status.equals("underReview")){ %> selected <%} %>>UnderReview</option>

            </select>
            <label for="reviewResult">Review Result</label>
            <input type="text" name="reviewResult" id="reviewResult" class="form-control" value=<%=notice.getReview_result() %>>
            <label for="amount" >Amount:</label>
            <input type="number" name="amount" id="amount" class="form-control" value=<%=payment.getAmount() %>>
            <input type="text" name="nid" hidden value=<%=notice.getNid() %>>
            <input type="submit" name="" value="Update" class="btn btn-primary btn-spacing">
            <a href="/AssignValidationClient/renewalNoticeList" class="btn btn-danger btn-spacing">Go Back</a>
          </form>
        </div>
      </div>  
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
  </body>
</html>