<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
<head>
<title>测试页面</title>
</head>
<body>
	<table border="1">
		<c:forEach items="${list }" var="u">
			<tr>
			<td>${u.id }1</td>
			<td>${u.name }2</td>
			<td>${u.remark }3</td>
			</tr>
		
		</c:forEach>
	</table>
</body>
</html>
