<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buscar usuário</title>

<c:url value="/resources/css" var="cssPath" />
<c:url value="/resources/js" var="jsPath" />

<!-- Bootstrap -->
<link href="${cssPath }/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${cssPath }/starter-template.css" rel="stylesheet">
<script src="${jsPath }/bootstrap-filestyle.min.js"></script>

</head>
<body>
	<div>
		<form action="buscarUsuario" id="formbuscausuario" method="post" name="formbuscausuario">
			<p>Buscar usuário</p>
			<p>
				<select id="opcaotipo" name="opcaotipo">
					<option selected="selected" value="nome">Nome</option>
					<option value="email">E-mail</option>
					<option value="login">Login</option>
				</select>
			</p>

			<p>
				Conteúdo<input maxlength="50" name="conteudobusca" size="50" type="text"/>
			</p>
			<p>
				<input name="botaobuscar" type="submit" value="Buscar" />
			</p>
		</form>
	</div>
	<div>
	<p><a href="/appcrudmvc/principal">Voltar</a></p>
	</div>
</body>
</html>