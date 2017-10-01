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
          <h4>Do you want to extend for another 5 years?</h4>
          <form>
            <div class="radio">
              <label><input type="radio" name="extend" value="true">Yes</label>
            </div>
            <div class="radio">
              <label><input type="radio" name="extend" value="false">No</label>
            </div>
            <input type="submit" name="" value="Next" class="btn btn-primary btn-spacing">
          </form>
        </div>
      </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
  </body>
</html>