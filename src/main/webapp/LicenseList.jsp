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
        <form action="" method="">
          <div class="input-group">
            <input type="number" class="form-control" placeholder="Enter due date">
             <span class="input-group-btn">
                  <button type="submit" class="btn btn-default">Filter</button>
             </span>
          </div>
        </form>
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
            <tr>
              <td>John</td>
              <td>Doe</td>
              <td>john@example.com</td>
              <td>License Number</td>
              <td>License Class</td>
              <td>Expiry Date</td>
              <td>Status</td>
              <td><a href="/xxx?id=licid" class="btn btn-primary">Generate Notice</a></td>
            </tr>
            <tr>
              <td>Mary</td>
              <td>Moe</td>
              <td>mary@example.com</td>
            </tr>
            <tr>
              <td>July</td>
              <td>Dooley</td>
              <td>july@example.com</td>
            </tr>
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