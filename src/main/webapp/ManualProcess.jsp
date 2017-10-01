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
          <form style="margin-top: 10px;">
            <label for="licenseNumber">License Number:</label>
            <p id="licenseNumber"> License Number</p>
            <label for="address">Address</label>
            <input type="text" name="address" id="address" class="form-control">
            <label for="email">Email</label>
            <input type="email" name="email" id='email' class="form-control">
            <label for="status">Status</label>
            <select class="form-control" id="status" name="status">
              <option value="new">New</option>
              <option value="validated">Validated</option>
              <option value="Rejected">Rejected</option>
              <option value="Accepted">Accepted</option>
              <option value="failed">Failed</option>
              <option value="underReview">underReview</option>

            </select>
            <label for="reviewResult">Review Result</label>
            <input type="text" name="reviewResult" id="reviewResult" class="form-control">
            <label for="amount" >Amount:</label>
            <input type="number" name="amount" id="amount" class="form-control">
            <input type="submit" name="" value="Update" class="btn btn-primary btn-spacing">
            <a href="" class="btn btn-danger btn-spacing">Go Back</a>
          </form>
        </div>
      </div>  
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
  </body>
</html>