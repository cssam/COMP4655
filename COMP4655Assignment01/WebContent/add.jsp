<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form method="post" id="one">
<table>

<c:if test="${(param.submit =='Add') and (empty param.id) }">
<tr><td></td>
<td colspan="2"><font color="red">Please enter ID</font></td></tr>
	<c:set var="parseError"  scope="request" value="ID" />
</c:if>
<c:if test="${ (fn:length(param.id) ne 9) or !(fn:startsWith(param.id,'A0')) }">
<td colspan="2"><font color="red">Index number must start with A0 and 7 more numbers</font></td></tr>
	<c:set var="parseError"  scope="request" value="ID" />
</c:if>
<c:catch var="parsingError">
	<fmt:parseNumber var="i" integerOnly="true" type="number" value="${fn:substring(param.id, 2, 6)}" />
	<c:if test="${i != fn:substring(param.id, 2, 6)}" var="parseError" scope="request"  />
</c:catch>
<tr>
<td>ID:</td>
<td><input type="text" form="one"  name="id" size="9"/></td>
<td>(Use format A0{numberX7})</td>
</tr>

<c:if test="${(param.submit =='Add') and (empty param.firstName) }">
<tr><td></td>
<td colspan="2"><font color="red">Please enter your First Name</font></td></tr>
<c:set var="parseError"  scope="request" value="First Name" />
</c:if>
<tr>
<td>First Name:</td>
<td><input type="text" form="one"  name="firstName" size="20"/></td>
<td>No space and letters only</td>
</tr>

<c:if test="${(param.submit =='Add') and (empty param.lastName) }">
<tr><td></td>
<td colspan="2"><font color="red">Please enter your Last Name</font></td></tr>
<c:set var="parseError"  scope="request" value="Last Name" />
</c:if>
<tr>
<td>Last Name:</td>
<td><input type="text" form="one"  name="lastName" size="20"/></td>
<td>No space and max 20 letters only</td>
</tr>

<c:if test="${(param.submit =='Add') and (empty param.dob) or  (fn:length(param.dob) ne 10) }">
<tr><td></td>
<td colspan="2"><font color="red">Please enter your Date of Birth</font></td></tr>
<c:set var="parseError"  scope="request" value="Birth Date" />
</c:if>
<c:catch var="parsingError">
	<fmt:parseNumber var="b" integerOnly="true" type="number" value="${fn:substring(param.dob, 0, 3)}" />
	<c:if test="${b != fn:substring(param.dob, 0, 3)}" var="parseError" scope="request"  />
</c:catch>
<c:catch var="parsingError">
	<fmt:parseNumber var="b" integerOnly="true" type="number" value="${fn:substring(param.dob, 5, 6)}" />
	<c:if test="${b != fn:substring(param.dob, 5, 6)}" var="parseError" scope="request"  />
</c:catch>
<c:catch var="parsingError">
	<fmt:parseNumber var="b" integerOnly="true" type="number" value="${fn:substring(param.dob, 8, 9)}" />
	<c:if test="${b != fn:substring(param.dob, 8, 9)}" var="parseError" scope="request"  />
</c:catch>
<tr>
<td>Birth Date:</td>
<td><input type="text" form="one" name="dob" size="10"/></td>
<td>(Use format yyyy-mm-dd)</td>
</tr>
<tr><td colspan="2"><input type="submit" form="one" name="submit" value="Add"/></td></tr>
</table>
</form>