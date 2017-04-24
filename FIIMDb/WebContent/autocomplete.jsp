<%@page import="java.sql.*"%>
<html>
<head>
<script type="text/javascript">
function showData(value){ 
	xmlHttp=GetXmlHttpObject()
	var url="auto.jsp";
	url=url+"?searchedValue="+value;
	xmlHttp.onreadystatechange=stateChanged 
	xmlHttp.open("GET",url,true)
	xmlHttp.send(null)
}
function stateChanged() { 
	if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){ 
    	var showdata = xmlHttp.responseText; 
    	document.getElementById("mydiv").innerHTML= showdata;
    } 
}
function GetXmlHttpObject(){
	var xmlHttp=null;
	try {
		xmlHttp=new XMLHttpRequest();
	 } catch (e) {
	try  {
		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e)  {
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	}
	return xmlHttp;
}
</script>
</head>
<body>

<input  type="text" name="searchedValue" id="searchedValue" onkeyup="showData(this.value);"><br>
<div id="mydiv"></div>

</body>
</html>