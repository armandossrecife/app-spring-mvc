<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bem vindo ao sistema X</title>

<c:url value="/resources/css" var="cssPath" />
<c:url value="/resources/js" var="jsPath" />

<!-- Bootstrap -->
<link href="${cssPath }/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${cssPath }/starter-template.css" rel="stylesheet">
<script src="${jsPath }/bootstrap-filestyle.min.js"></script>

</head>
<body>
	<a href="#"> <security:authentication property="principal"
			var="usuario" /> <c:if test="${not empty usuario.username}"> Usuário: ${usuario.username }</a>
	</c:if>
	<p>Agora você pode usufruir as funcionalidades do sistema X</p>
		<p>${mensagem}</p> <security:authorize access="hasRole('ROLE_ADMIN')">
			<p>
				<a href="formularioInserirUsuario">Inserir Usuario</a>
			</p>
			<p>
				<a href="listarLogsAcesso">Listar logs de acessos</a>
			</p>
		</security:authorize>
		<p>
			<a href="listarUsuarios">Listar Usuarios </a>
		</p>
		<p>
			<a href="formularioBuscarUsuario">Buscar Usuario </a>
		</p>
		<p>
			<a href="logout">Logout</a>
		</p>
</body>
</html>