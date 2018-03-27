
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Employee Admin Tool</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin.css" type="text/css" title="no title" charset="utf-8" />
		
	</head>
	<body>
		<div class="mdi">
			
			<div class="columns">
				<div class="column leftcol">
					<!--  Employee Tables  -->
					<jsp:include flush="true" page="employees.jsp"/> 
				</div>
				<div class="column rightcol">
					<!--   Add Pages -->
					<jsp:include flush="true" page="addEmployees.jsp" />
					<jsp:include flush="true" page="findEmployee.jsp" />
					<jsp:include flush="true" page="deleteEmployee.jsp" />
				</div>
				<div class="clear">&#160;</div>
			</div>
		</div>
		</div>
	</body>
</html>

<%
	
%>
