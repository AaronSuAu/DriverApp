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
				<div class="col-sm-6 col-sm-offset-3">
					<form method="POST" action="">
						<label for="username">Username: </label>
						<input type="text" id="username" name="username" class="form-control" required>
						<label for="password">Password: </label>
						<input type="password" name="password" class="form-control" required>
						<input type="submit" name="Login" class="btn btn-primary btn-block btn-spacing">
						
					</form>
				</div>
			</div>
		</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <jsp:include page="_script.jsp" />
  </body>
</html>