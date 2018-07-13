<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir Usuário</title>

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
		<form action="inserirUsuario" id="forminsereusuario" method="post" name="forminsereusuario" enctype="multipart/form-data">
			<p>Inserir usuário</p> 
			Nome: <input name="nome" type="text" required><form:errors path="usuario.nome" /><br>
			Login: <input name="login" type="text" required><form:errors path="usuario.login"/><br>
			E-mail: <input name="email" type="text" required><form:errors path="usuario.email" /><br>
			Senha: <input name="senha" type="password" required><form:errors path="usuario.senha" /><br>
			Confirma Senha: <input name="confirmasenha" type="password" required><form:errors path="usuario.senha" /><br>
			Papel: <select name="papel">
				<option value="usuario" selected>Usuário Comum</option>
				<option value="admin">Administrador</option>
			</select><br>
			Imagem: <input name="imagem" type="file"/>
			<p>
				<input name="botaoInserir" type="submit" value="Inserir" />
			</p>
		</form>
	</div>
	<div>
	<p><a href="/appcrudmvc/principal">Voltar</a></p>
	</div>
</body>
</html>