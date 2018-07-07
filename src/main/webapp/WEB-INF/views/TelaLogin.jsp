<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tela Login</title>
</head>
<body>
<p>Informe os dados de e-mail e senha</p>
<form action="efetuarLogin" method="post"><br>
E-mail<input name="email" type="text" value="armando@ufpi.edu.br"><br>
Senha<input name="senha" type="password" value="armando"><br>
<button type="submit">Logar</button>
<button>Cancelar</button>
<p> ${mensagem}</p> 
</form>
</body>
</html>