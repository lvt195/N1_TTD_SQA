<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Trang chủ chính admin</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
	<jsp:include page="/menuadmin.jsp"/>
	<div style="width: 100%;">
            <img style="width: 100%;margin-top:101px; min-width: 60%" src="img/VARVJEUAWP.jpg" alt="Description of your image">
	</div>
        <jsp:include page="/footer.jsp"/>
</body>
</html>