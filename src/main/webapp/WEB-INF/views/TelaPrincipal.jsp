<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bem vindo ao sistema de Gestão de Usuários</title>

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
		<a href="#"> <security:authentication property="principal"
				var="usuarioAutenticado" /> <c:if test="${not empty usuarioAutenticado.username}"> Usuário: ${usuario.username }</a>
		</c:if>
	</div>
	<div>
		<p>${mensagem}</p>
	</div>
	<div>
		<p>As seguintes opções estão disponíveis:</p>
	</div>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<div>
			<p>
				<a href="formularioInserirUsuario">Inserir Usuario</a>
			</p>
			<p>
				<a href="listarUsuarios">Listar Usuarios </a>
			</p>
			<p>
				<a href="formularioBuscarUsuario">Buscar Usuario </a>
			</p>
			<p>
				<a href="listarLogsAcesso">Listar logs de acessos</a>
			</p>
		</div>
	</security:authorize>
	<div>
		<p>
			<a href="meuperfil/${usuario.id}">Perfil</a>
		</p>
	</div>
	<p>
		<a href="logout">Logout</a>
	</p>
</body>
</html>