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
        <% RenewalNotices rNotices = (RenewalNotices)request.getAttribute("renewalNotices");
        	   FeePayments fPayments = (FeePayments)request.getAttribute("feePayments");
        	   if(rNotices.getStatus().toLowerCase().equals("rejected")){
        		   %>
        		   	<h3>Rejected. Reason: <%= rNotices.getReview_result() %></h3>
        		   	<h3>You can only renew for one year.</h3>
        		   <% 
        	   }else if(rNotices.getStatus().toLowerCase().equals("accepted")){
        		   %>
        		   <h3>Your request for extension has been approved.</h3>
        		   <% 
        	   }else{
        		   %>
        		   <h3>Please pay the money to finsh the renewal process.</h3> 
        		   <% 
        	   }
        %>
          <form method="POST" action="/AssignValidationClient/DriverPayment">
            <label for="amount">Amount</label>
            <p id="amount">$ <%= fPayments.getAmount() %></p>
            <input type="hidden" name="confirm" value="1">
            <input type="submit" name="" value="Pay" class="btn btn-primary">
          </form>
        </div>
      </div>  
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
  </body>
</html>