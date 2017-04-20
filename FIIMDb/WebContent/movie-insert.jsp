<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="eu.ubis.fiimdb.model.Genre"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FIIMDb - insert movie</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/site.css">

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="navbar-brand">
					<a href="movies.jsp"> Java Awesome Training Logo &copy; FII Practic 2017
					</a>
				</div>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="movies.jsp">Home</a></li>
					<% if(request.getRemoteUser().equals("admin")) { %>
					<li class="active"><a href="movie-insert.jsp">Insert Movie</a>
					<% } %>
			</div>
			
			
		<div class="nav navbar-nav navbar-right">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
				
				<%=request.getRemoteUser() %>
				
					<span class="caret"></span></button>
				<ul class="dropdown-menu" >
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
				</ul>
			</div>
		</div>	
	</nav>
	
		<jsp:useBean id="movieBean" class="eu.ubis.fiimdb.controller.MovieBean" scope="request"></jsp:useBean>
	
	<div class="container">
		<fieldset>
			<legend> Movie details</legend>
			<form method="post" action="MovieInsert">
				
				<div class="form-group">
					<label for="name"> Name: </label>
					<input type="text" name="name" class="form-control" id="name">
				</div>
				
				<div class="form-group">
					<label for="releaseDate"> Release Date: </label>
					<input type="text" name="releaseDate" class="form-control" id="releaseDate"> 
				</div>
				
				<div class="form-group">
					<label for="rating"> Rating: </label>
					<input type="number" name="rating" class="form-control" id="rating" value="0">
				</div>
				
				<div class="form-group">
					<label for="length"> Length: </label>
					<input type="number" name="length" class="form-control" id="length" value="0">
				</div>
				
				<div class="form-group">
					<label for="casting">Casting:</label> 
					<input type="text" name="casting" class="form-control" id="casting">
				</div>
				
				<div class="form-group">
					<label for="director"> Director: </label>
					<input type="text" name="director" class="form-control" id="director">
				</div>
				
				<div class="form-group">
					<label for="description"> Description: </label>
					<input type="text" name="description" class="form-control" id="description">
				</div>
				
				<div class="from-group">
					<label for="writer"> Writer: </label>
					<input type="text" name="writer" class="form-control" id="writer">
				</div>
				
				<div class="form-group">
					<label for="genres"> Genres: </label>
						<select name="genres" class="form-control" id="genres" multiple="multiple">
							<%
								for(Genre genre : movieBean.getGenres()) {
							%>
							<option value="<%=genre.getId()%> "><%=genre.getType()%></option>
							<%
								}
							%>
						</select>
				</div>
				<button type="submit" class="btn btn-primary"> Insert </button>
			</form>
		</fieldset>
	</div>

</body>
</html>