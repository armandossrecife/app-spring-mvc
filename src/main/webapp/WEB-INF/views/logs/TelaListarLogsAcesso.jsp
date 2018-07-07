<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de logs dos usuários</title>
</head>
<body>
	<p>${mensagem}</p>
	<table>
		<tr>
			<td>Id</td>
			<td>Descrição</td>
			<td>E-mail</td>
			<td>Data</td>
		</tr>

		<c:forEach items="${logs}" var="log">
			<tr>
				<td>${log.id}</td>
				<td>${log.descricao}</td>
				<td>${log.email}</td>
				<td>${log.data}</td>
			</tr>
		</c:forEach>
	</table>

	<div>
		<p>
			<a href="/appcrudmvc">Voltar</a>
		</p>
	</div>
</body>
</html>