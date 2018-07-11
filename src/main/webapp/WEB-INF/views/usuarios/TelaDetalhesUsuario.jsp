<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detalhes do Usuário</title>

<c:url value="/resources/css" var="cssPath" />
<c:url value="/resources/js" var="jsPath" />

<!-- Bootstrap -->
<link href="${cssPath }/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${cssPath }/starter-template.css" rel="stylesheet">
<script src="${jsPath }/bootstrap-filestyle.min.js"></script>

</head>
<body>
	<p>${mensagem}</p>
	<table class="table table-bordered table-striped table-hover">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Login</th>
			<th>E-mail</th>
			<th>Imagem</th>
		</tr>
		<tr>
			<td>${usuario.id}</td>
			<td>${usuario.nome}</td>
			<td>${usuario.login}</td>
			<td>${usuario.email}</td>
			<td>${usuario.imagemPath}</td>
		</tr>
	</table>
	<div>
		<p>
			<a href="/appcrudmvc/listarUsuarios">Voltar</a>
		</p>
	</div>
</body>
</html>