
<%@ page import="java.sql.*" %>
<style>
.results {
    position: absolute;
    top: 35px;
    left: 0;
    right: 0;
    z-index: 10;
    padding: 0;
    margin: 0;
    border-width: 1px;
    border-style: solid;
    border-color: #cbcfe2 #c8cee7 #c4c7d7;
    border-radius: 3px;
    background-color: #fdfdfd;
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #fdfdfd), color-stop(100%, #eceef4));
    background-image: -webkit-linear-gradient(top, #fdfdfd, #eceef4);
    background-image: -moz-linear-gradient(top, #fdfdfd, #eceef4);
    background-image: -ms-linear-gradient(top, #fdfdfd, #eceef4);
    background-image: -o-linear-gradient(top, #fdfdfd, #eceef4);
    background-image: linear-gradient(top, #fdfdfd, #eceef4);
    -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    -ms-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    -o-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}
</style> 
<%
	String name = request.getParameter("searchedValue").toString();
	String buffer="<div class=results>";
	String URL = "jdbc:mysql://localhost/fiimdb";
	String USERNAME = "fiimdb";
	String PASSWORD = "fiimdb";
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement st = connection.createStatement();
        
        ResultSet rs = st.executeQuery("select * from movie m where name like '" + name + "%'");
		while(rs.next()) {
			buffer = buffer + rs.getString("name") + "<br>";
		}
		buffer=buffer+"</div>";  
		response.getWriter().println(buffer);
	} catch (Exception e) {
		System.out.println(e);
	}
%>