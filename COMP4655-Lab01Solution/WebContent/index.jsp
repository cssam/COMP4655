<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Hello</title></head>
<body bgcolor="white">
	<img src="duke.waving.gif"> 

	<h2>Hello, my name is Duke. What's yours?</h2>
	<form method="get">
		<input type="text" name="userName" size="25">
		<p></p>
		<input type="submit" value="Submit">
	</form>
	
	<jsp:useBean id="usernameBean" class="com.bcit.comp4655.beans.User" scope="request"  />
	<jsp:setProperty name="usernameBean" property="userName" value="${param.userName}" />
	<c:if test="${!empty usernameBean.userName}">
		<p><c:out value="${usernameBean.userName}" /> </p>
	</c:if> 
</body>
</html>
