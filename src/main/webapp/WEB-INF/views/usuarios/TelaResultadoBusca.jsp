<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultado da busca de usuário</title>
</head>
<body>
	<p>${mensagem}</p>
	<c:if test="${not empty usuario.id}">
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>E-mail</th>
			</tr>
			<tr>
				<td>${usuario.id}</td>
				<td>${usuario.nome}</td>
				<td>${usuario.email}</td>
			</tr>
		</table>
	</c:if>
	<div>
		<p>
			<a href="/appcrudmvc">Voltar</a>
		</p>
	</div>
</body>
</html>