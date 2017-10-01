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
            <tr>
              <td>Address</td>
              <td>john@example.com</td>
              <td>Status</td>
              <td>Review Result</td>
              <td><a href="/xxx?id=nid" class="btn btn-primary">Manual Process</a></td>
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