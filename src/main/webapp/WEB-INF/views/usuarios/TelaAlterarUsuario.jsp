<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Usuário</title>

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
		<form action="alterarUsuario" id="formalterausuario" method="post" name="formalterausuario" enctype="multipart/form-data">
			<input name="id" type="hidden" value="${usuario.id}" value="id" />
			<p>Alterar usuário</p>
			Nome: <input name="nome" type="text" value="${usuario.nome}" required><form:errors path="usuario.nome" /><br>
			Login: <input name="login" type="text" value="${usuario.login}" required><form:errors path="usuario.login" /><br>
			E-mail: <input name="email" type="text" value="${usuario.email}" required><form:errors path="usuario.email" /><br>
			Senha: <input name="senha" type="password" required><form:errors path="usuario.senha" /><br>
			Confirma senha: <input name="confirmasenha" type="password" required><br> 
			Imagem: ${usuario.imagemPath}<input name="imagem" type="file"/>
			<p>
				<input name="botaoalterar" type="submit" value="Alterar" />
			</p>
		</form>
	</div>
	<div>
	<p><a href="/appcrudmvc/listarUsuarios">Voltar</a></p>
	</div>
</body>
</html>