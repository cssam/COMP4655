<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
		  xmlns:c="http://java.sun.com/jsp/jstl/core"
		  xmlns:fn="http://java.sun.com/jsp/jstl/functions"
		 version="2.0">
<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>EJB Calc Client</title>
<style type="text/css">
		table { border: 3px outset black; }
		td { border: 1px dotted black; }
		tr {border: 1px dotted black; }
		th {border: 1px thick black;}

	</style>
</head>
<body>

<form method="post" id="one" action="${pageContext.request.contextPath}/calcApp">
<table>
<c:if test="${(empty param.firstName) }">
<tr><td></td>
<td colspan="2"><font color="red">Please enter number1</font></td></tr>
<c:set var="parseError"  scope="request" value="Number1" />
</c:if>
<tr>
<td>Number1:</td>
<td><input type="text" form="one"  name="number1" size="10"/></td>
<td>No space and 10 digits only</td>
</tr>

<c:if test="${(empty param.number2) }">
<tr><td></td>
<td colspan="2"><font color="red">Please enter number2</font></td></tr>
<c:set var="parseError"  scope="request" value="Number2" />
</c:if>
<c:if test="${(param.operation =='divide') and (param.number2 =='0') }">
<tr><td></td>
<td colspan="2"><font color="red">In division number2 should not be 0</font></td></tr>
<c:set var="parseError"  scope="request" value="Number2" />
</c:if>
<tr>
<td>Number 2:</td>
<td><input type="text" form="one"  name="number2" size="10"/></td>
<td>No space and max 10 digits only</td>
</tr>
<tr>
<td>Operation</td>
<td>
	+<input type="radio" Form="one" name="operation" value="add"/>
	-<input type="radio" Form="one" name="operation" value="sub"/>
	*<input type="radio" Form="one" name="operation" value="mul"/>
	/<input type="radio" Form="one" name="operation" value="divide"/>
</td>
</tr>


<tr>
	<td><input type="submit" form="one" name="submit" value="Calculate"/></td>
</tr>
<tr>
		<c:if test="${fn:length( addResponseCode.code ) > 0}" >
			<c:set var="code" value=" Result Code: ${addResponseCode.code} "/>
			<c:set var="desc" value=" Description: ${addResponseCode.desc} "/>
			<c:out value="${code}"/>
			<c:out value="${desc}"/>
		</c:if>
</tr>

</table>
</form>
</body>
</html>
</jsp:root>