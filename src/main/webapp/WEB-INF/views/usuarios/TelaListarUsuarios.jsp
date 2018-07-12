<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:url value="/resources/css" var="cssPath" />
<c:url value="/resources/js" var="jsPath" />

<!-- Bootstrap -->
<link href="${cssPath }/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${cssPath }/starter-template.css" rel="stylesheet">
<script src="${jsPath }/bootstrap-filestyle.min.js"></script>

<title>Lista de usuários</title>
</head>
<body>
	<p>${mensagem}</p>
	<table class="table table-bordered table-striped table-hover">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>E-mail</th>
			<th>Detalhes</th>
			<security:authorize access="hasRole('ROLE_ADMIN')"><th>Alterar</th></security:authorize>
			<security:authorize access="hasRole('ROLE_ADMIN')"><th>Remover</th></security:authorize>
		</tr>

		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td>${usuario.id}</td>
				<td>${usuario.nome}</td>
				<td>${usuario.email}</td>
				<td><a href="detalharUsuario/${usuario.id}">detalhes</a></td>
				<security:authorize access="hasRole('ROLE_ADMIN')"><td><a href="formularioAlterarUsuario?id=${usuario.id}">alterar</a></td></security:authorize>
				<security:authorize access="hasRole('ROLE_ADMIN')"><td><a href="removerUsuario/${usuario.id}">Remover</a></td></security:authorize>
			</tr>
		</c:forEach>
	</table>
	<div>
		<p>
			<a href="/appcrudmvc/principal">Voltar</a>
		</p>
	</div>
</body>
</html>