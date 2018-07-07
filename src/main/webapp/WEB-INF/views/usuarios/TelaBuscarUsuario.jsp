<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buscar usuário</title>
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
				Conteúdo<input maxlength="50" name="conteudobusca" size="50" type="text" required/>
			</p>
			<p>
				<input name="botaobuscar" type="submit" value="Buscar" />
			</p>
		</form>
	</div>
	<div>
	<p><a href="/appcrudmvc">Voltar</a></p>
	</div>
</body>
</html>