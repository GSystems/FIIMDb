<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="eu.ubis.fiimdb.model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FIIMDb</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/site.css">

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:useBean id="movieBean" class="eu.ubis.fiimdb.controller.MovieBean" scope="request"></jsp:useBean>

	<% String user = request.getRemoteUser(); %>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="navbar-brand">
					<a href="#"> Java Awesome Training Logo &copy; FII Practic 2017</a>
				</div>
			</div>
			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="movies.jsp">Home</a>
				</ul>
			</div>
				
	 		<div class="nav navbar-nav navbar-right">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
							guest
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" >
						<li>
						 	<form action="<%=response.encodeURL("UserServlet?action=login") %>" method="post">
	           					<button type="submit" class="btn btn-default center-block">Login</button>
	           				</form>
           				</li>
						<li>
       						<form action="<%=response.encodeURL("UserServlet?action=register") %>" method="post">
       							<button type="submit" class="btn btn-default center-block">Register</button>
   							</form>
   						</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	
	<div class="container">
	    <div class="row vertical-offset-50">
	    	<div class="col-md-4 col-md-offset-4">
	    		<div class="panel panel-default">
				  	<div class="panel-heading">
				    	<h3 class="panel-title">Register</h3>
				 	</div>
				  	<div class="panel-body">
				    	<form accept-charset="UTF-8" action="UserServlet?action=insertNewUser" method="post">
	                    <fieldset>
				    	  	<div class="form-group">
				    		    <input class="form-control" placeholder="Username" name="r_username" type="text">
				    		</div>
				    		<div class="form-group">
				    			<input class="form-control" placeholder="Password" name="r_password" type="password" value="">
				    		</div>
				    		<div class="form-group">
				    			<input class="form-control" placeholder="First Name" name="r_firstname" type="text">
				    		</div>
				    		<div class="form-group">
				    			<input class="form-control" placeholder="Last Name" name="r_lastname" type="text">
				    		</div>
				    		<div class="form-group">
				    			<input class="form-control" placeholder="Email" name="r_email" type="text">
				    		</div>

				    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Register">
							 
						 	<% if ("1".equals(request.getParameter("errorId"))) { %>
							 	<div class="span12">
									<div class="alert alert-danger">
								 		username already exists in database
									</div>
							    </div>
				     		<% } %>
				     		
				     		<% if ("2".equals(request.getParameter("errorId"))) { %>
							 	<div class="span12">
									<div class="alert alert-danger">
								 		email address already exists in database
									</div>
							    </div>
				     		<% } %>
				    	</fieldset>
				      	</form>
				    </div>
				</div>
			</div>
		</div>
	</div>
		