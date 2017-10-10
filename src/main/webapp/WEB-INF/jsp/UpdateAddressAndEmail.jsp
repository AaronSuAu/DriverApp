<%@page import="model.CarLicenses"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.RenewalNotices"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
   	<jsp:include page="_header.jsp" />
    <style type="text/css">
      .form-group{
        display: none;
      }
    </style>
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
            <label for="email">Email</label>
            <p><%= rNotices.getContact_email() %> </p>
            <label for="address">Address</label>
            <p><%= rNotices.getAddress() %></p>
            <label for="expiryDate">Expiry Date</label>
            <p id="expiryDate"> <%= carLicenses.getExpiry_date() %></p>
            <label for="change">Do you want to update your address?</label>
            <div class="radio">
              <label><input type="radio" name="change" value="true">Yes</label>
            </div>
            <div class="radio">
              <label><input type="radio" name="change" value="false" checked>No</label>
            </div>
            <div class="form-group">    
               <label for="email">Email</label>        
            	   <input type="email" name="email" required class="form-control addressDetail" value="<%= rNotices.getContact_email() %>">
            </div>
            <div class="form-group">
              <label for="exampleInputEmail1">PreStreet</label> 
              <input type="text" class="form-control addressDetail" name="preStreet" id="preStreet" placeholder="preStreet"/>
            </div>
            <div class="form-group">
              <label for="exampleInputEmail1">StreetName</label> 
              <input type="text" class="form-control addressDetail" name="streetName" placeholder="streetName"/>
            </div>
            <div class="form-group">
              <label for="exampleInputEmail1">StreetType</label> 
              <input type="text" class="form-control addressDetail" name="streetType" placeholder="streetType"/>
            </div>
            <div class="form-group">
              <label for="exampleInputEmail1">Suburb</label> 
              <input type="text" class="form-control addressDetail" name="suburb" placeholder="suburb" />
            </div>
            <div class="form-group">
              <label for="exampleInputEmail1">State(only support NSW and ACT)</label> 
              <input type="text" class="form-control addressDetail" name="state" placeholder="state"/>
            </div>
            <input type="submit" name="" class="btn btn-primary btn-spacing" value="Next">
          </form>
        </div>
      </div>  
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
    <script type="text/javascript">
      $(function(){
        $("input[name='change']").change(function() {
          var change = $("input[name='change']:checked").val();
          console.log(change);
          if(change === 'true'){
            $(".form-group").show();
            $(".addressDetail").attr("required", true);
          }else{
            $(".form-group").hide();
            $(".addressDetail").attr("required", false);            
          }
        })
      });
    </script>
  </body>
</html>