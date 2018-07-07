<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Usuário</title>
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
	<p><a href="/appcrudmvc">Voltar</a></p>
	</div>
</body>
</html>