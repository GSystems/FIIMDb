<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import ="eu.ubis.fiimdb.model.User" %>

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
	<jsp:useBean id="userBean" class="eu.ubis.fiimdb.controller.UserBean" scope="request"></jsp:useBean>
	
	<% String username = request.getRemoteUser(); %>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="navbar-brand">
					<a href="#"> Java Awesome Training Logo &copy; FII Practic 2017</a>
				</div>
			</div>
			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="movies.jsp?pageNumber=1">Home</a>
	
				<% if("admin".equals(username)) { %>
					<li class="active"><a href="movie-insert.jsp">Insert Movie</a>
				<% } %>
				</ul>
			</div>
				
	 		<div class="nav navbar-nav navbar-right">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
						<% if(username == null) { %>
							guest
						<% } else { %>
							<%= username %>
						<% } %>
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" >
						<li>
							<% if(username == null) { %>
						 	<form action="<%=response.encodeURL("UserServlet?action=login") %>" method="post">
	           					<button type="submit" class="btn btn-default center-block">Login</button>
	           				</form>
           				</li>
						<li>
       						<form action="<%=response.encodeURL("UserServlet?action=register") %>">
       							<button type="submit" class="btn btn-default center-block">Register</button>
   							</form>
   						</li>
        				<% } else  { %>
        				<li>
        					<form action="<%=response.encodeURL("UserServlet?action=userProfile") %>" method="post">
        						<button type="submit" class="btn btn-default center-block">Profile</button> 
        					</form>
        				</li>
         				<li>
							<form action="<%=response.encodeURL("UserServlet?action=logout") %>" method="post">
           						<button type="submit" class="btn btn-default center-block">Logout</button>
	           				</form>
       					</li>
      					<% } %>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	
	<div class="container">
		<h1> Edit Profile </h1>
		<hr>
		<div class="row">
				<% User user = userBean.getUser(); %>
			<div class="col-md-6">
				<h3> Personal Info </h3>
				<form class="form-horizontal" action="UserServlet?action=saveProfile" method="post">
					<% if(!"admin".equals(username)) { %>
					<div class="form-group">
						<label class="col-lg-3 control-label">Username:</label>
						<div class="col-lg-8">
							<input class="form-control" name="username" value="<%=user.getUsername() %>" type="text">
						</div>
					</div>
					<% } %>
					<div class="form-group">
						<label class="col-lg-3 control-label">First Name:</label>
						<div class="col-lg-8">
							<input class="form-control" name="firstname" value="<%=user.getFirstname() %>" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Last Name:</label>
						<div class="col-lg-8">
							<input class="form-control" name="lastname" value="<%=user.getLastname()%>" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">
							<input class="form-control" name="email" value="<%=user.getEmail()%>" type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label"></label>
						<div class="col-md-8">
							<input type="submit" class="btn btn-primary" value="Save Changes">
							<span></span>
							<input type="reset"  class="btn btn primary" value="Cancel">
						</div>
					</div>
				</form>
			</div>
			
			<div class="col-md-6">
				<h3>Reset Password</h3>
				<form class="form-horizontal" action="UserServlet?action=resetPassword" method="post">
					<div class="form-group">
						<label class="col-md-3 control-label">Old password:</label>
						<div class="col-md-8">
							<input class="form-control" name="oldpassword" type="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">New Password:</label>
						<div class="col-md-8">
							<input class="form-control" name="newpassword" type="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Confirm Password:</label>
						<div class="col-md-8">
							<input class="form-control" name="confirmpassword" type="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<input type="submit" class="btn btn-primary" value="Reset Password">
						</div>
					</div>
				</form>
			</div>
			
		</div>
	</div>