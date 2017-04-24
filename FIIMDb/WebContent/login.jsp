<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="eu.ubis.fiimdb.captchas.CaptchasDotNet" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/site.css">

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="http://mymaplist.com/js/vendor/TweenLite.min.js"></script>

<%
// Construct the captchas object (Default Values)
CaptchasDotNet captchas = new eu.ubis.fiimdb.captchas.CaptchasDotNet(
  request.getSession(true),     // Ensure session
  "demo",                       // client
  "secret"                      // secret
  );
%>

</head>

<body>
	<div class="container">
	    <div class="row vertical-offset-50">
	    	<div class="col-md-4 col-md-offset-4">
	    		<div class="panel panel-default">
				  	<div class="panel-heading">
				    	<h3 class="panel-title">Please sign in</h3>
				 	</div>
				  	<div class="panel-body">
				  	
				  	
				  	<% if("false".equals(request.getParameter("correct"))) { %>
				  	<form action="UserServlet?action=captcha" method="post">
						<div class="form-group">
		          			<input name="captchaMessage" placeHolder="type the word" size="16" />
		          			<%= captchas.image() %>
		          		</div>
		          		<input type="submit" value="Submit" />
				    </form>
				    <% } %>
				    
				    <% if("true".equals(request.getParameter("correct"))) {%>
				    
				    	<form accept-charset="UTF-8" action="j_security_check" method="POST">
		                    <fieldset>
					    	  	<div class="form-group">
					    		    <input class="form-control" placeholder="Username" name="j_username" type="text">
					    		</div>
					    		<div class="form-group">
					    			<input class="form-control" placeholder="Password" name="j_password" type="password" value="">
					    		</div>
					    						    		
					    		<div class="checkbox">
					    	    	<label>
					    	    		<input name="remember" type="checkbox" value="Remember Me"> Remember Me
					    	    	</label>
					    	    </div>
					    		<div class="form-group">
					    			<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
					    		</div>
					    		<% 
									if("true".equals(request.getParameter("flag"))) {
								 %>
								 	<div class="span12">
										<div class="alert alert-danger">
										 	Username or Password is incorrect!
										</div>
								    </div>
								<%} %>
					    	</fieldset>
			      		</form>
			      		<% } %>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<style type="text/css">
.vertical-offset-50 {
  min-height: 100%;  /* Fallback for browsers do NOT support vh unit */
  min-height: 100vh; /* These two lines are counted as one :-)       */

  display: flex;
  align-items: center;
}
</style>
</html>