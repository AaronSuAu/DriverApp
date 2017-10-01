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
          <form>
            <label for="amount">Amount</label>
            <p id="amount">$</p>
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