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
        <div class="col-md-12 text-center">
          <h1>Please update your information</h1>
        </div>
        <div class="col-md-6 col-md-offset-3">
          <form>
            <label for="address">Address</label>
            <input type="text" name="address" class="form-control" required>
            <label for="email">Email</label>
            <input type="email" name="email" required class="form-control">
            <label for="expiryDate">Expiry Date</label>
            <p id="expiryDate"> expiryDate</p>
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