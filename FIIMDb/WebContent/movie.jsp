<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "eu.ubis.fiimdb.model.Movie" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FIIMDb</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel= "stylesheet" href="css/site.css">

<script src ="js/jquery-3.1.1.min.js"> </script>
<script src ="js/bootstrap.min.js"> </script>
</head>
<body>
	<jsp:useBean id="movieBean" class="eu.ubis.fiimdb.controller.MovieBean" scope="request"></jsp:useBean>
	
	<div class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<div class="navbar-brand">
					<a href="#">Java Awesome Training Logo &copy; FII Practic 2017</a>
				</div>
			</div>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="movies.jsp">Home</a>
			</ul>
		</div>
	</div>
	
	<div class="container">
		<fieldset>
			<legend> Movie's Details </legend>
			
			<div class="movie-container">
				<ul class="list-group">				
					<%
						String sid = request.getParameter("id");
						int id = Integer.parseInt(sid);
						
						Movie movie = movieBean.getSelectedMovie(id-1);
					%>
					<li class="list-group-item">
						<div class="row">
							<img src="images/poster-unavailable.jpg" alt="Poster unavailable"
								class="img-thumbnail rounded float-left col-sm-2" />
								
							<div class="col-sm-10">
								<h3> <%=movie.getName()%> </h3>
								Release date:
								<%=movie.getReleaseDate() %> <br /> Director:
								<%=movie.getDirector() %> <br /> Rating:
								<%=movie.getRating() %> <br /> Genre:
								<%=movie.getGenre() %> <br />
								<p> Storyline:
									<%=movie.getDescription() %>
								</p>
							</div>
							
						</div>
					</li>
				</ul>
			</div>
			
		</fieldset>
	</div>
	
	
</body>
</html>