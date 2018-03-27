<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
	<title>Hello</title>
	</head>
	<body bgcolor="white">
	<img src="duke.waving.gif">
	<h2>My name is Duke. What is yours?</h2>

	<form method="GET">
		<input type="text" name="username" size="25">
		<input type="submit" value="Submit">
		<input type="reset" value="Reset">
	</form>
	
	<jsp:useBean id="userNameBean" class="ca.bcit.lab.beans.User" scope="request" />
	<jsp:setProperty name="userNameBean" property="name" value="${param.username}" />
	
	<c:if test="${fn:length(userNameBean.name) 	> 0}">	
		<%@include file="response.jsp" %>
	</c:if>
</body>
</html>