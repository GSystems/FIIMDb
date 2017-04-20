<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="eu.ubis.fiimdb.model.Movie"%>

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
	
				<% if("admin".equals(user)) { %>
					<li class="active"><a href="movie-insert.jsp">Insert Movie</a>
				<% } %>
				</ul>
			</div>
				
	 		<div class="nav navbar-nav navbar-right">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
						<% if(user == null) { %>
							guest
						<% } else { %>
							<%= user %>
						<% } %>
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" >
						<li>
							<% if(user == null) { %>
						 	<form action="<%=response.encodeURL("UserServlet?action=login") %>" method="post">
	           					<button type="submit" class="btn btn-default center-block">Login</button>
	           				</form>
           				</li>
						<li>
       						<form action="<%=response.encodeURL("UserServlet?action=register") %>" method="post">
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
		<fieldset>
			<legend>Search Form</legend>

			<form action="SearchServlet" method="POST">
				<div class="col-sm-8">
					<div class="form-group">
						<% if (request.getParameter("searchedValue") == null) { %>
							<input type="text" class="form-control" placeholder="Search..." name="searchedValue">
						<%} else { %>
							<input type="text" class="form-control" value="<%=request.getParameter("searchedValue") %>"  name="searchedValue">
						<% } %>
					</div>
				</div>

				<div class="col-sm-4">
					<button type="submit" class="btn btn-primary">Search</button>
				</div>

				<div class="form-group col-sm-8">
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="name" checked="<%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("name") == true ? true : false%>"/> By Name
						</label>
					</div>
					
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="genre" <%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("genre") == true ? "checked" : ""%>/> By Genre
						</label>
					</div>
					
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="year" <%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("year") == true ? "checked" : ""%>/> By Release Year
						</label>
					</div>
					
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="description" <%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("description") == true ? "checked" : ""%>/> By Description
						</label>
					</div>
					
				</div>
			</form>
		</fieldset>
		<div class="movie-container">
			<ul class="list-group">
			
				<%
					movieBean.getAllMovies();
					for (Movie movie : movieBean.getMovies()) {
				%>

				<li class="list-group-item">
					<div class="row">
						<img src="images/poster-unavailable.jpg" alt="Poster unavailable"
							class="img-thumbnail rounded float-left col-sm-2" />
						
						<div class="col-sm-10">
							<h3><%=movie.getName()%></h3>
	
							Release date:
							<%=movie.getReleaseDate()%><br /> Director:
							<%=movie.getDirector()%><br /> Rating:
							<%=movie.getRating()%><br /> Genre: 
							<%=movie.getGenre()%> <br />
							<p>
								Storyline:
								<%=movie.getDescription()%>
							</p>
							<form method="post" action="MovieDetails">
								<button type="submit" class="btn btn-primary" name="detailsButton" value="<%=movie.getId()%>">Details</button>
							</form>
							<% if("admin".equals(user)) { %>
							<form method="post" action="MovieDelete">
								<button type="submit" class="btn btn-primary" name="deleteMovie" value="<%=movie.getId()%>">Delete</button>
							</form>
							<form method="post" action="MovieUpdate">
								<button type="submit" class="btn btn-primary" name="updateMovie" value="<%=movie.getId()%>">Update</button>
							</form>
							<% } %>
						</div>
					</div>
				</li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
</body>
</html>