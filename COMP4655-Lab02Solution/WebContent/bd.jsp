<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
		  xmlns:c="http://java.sun.com/jsp/jstl/core"
		  xmlns:cf="http://bcit.ca/lab//calltag/customFunctions.tld"	
 version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>DOB Page</title>
</head>
<body>
	<form method="GET">
			Name: <input type="text" name="user" size="25" />
			Year of Birth: <input type="text" name="dob" size="4" />
			<input type="submit" value="Submit" />
			<input type="reset" value="Reset" />
		</form>
		<jsp:useBean xmlns:jsp="http://java.sun.com/JSP/Page" id="userBean" class="ca.bcit.lab.beans.User" scope="request" >
			<jsp:setProperty name="userBean" property="name" value="${param.username}" />
			<jsp:setProperty name="userBean" property="dob" value="${param.dob}" />
		</jsp:useBean>
		${cf:getAge( userBean)}
		
</body>
</html>
</jsp:root>