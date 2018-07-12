<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:url value="/resources/css" var="cssPath" />
<c:url value="/resources/js" var="jsPath" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<!-- Bootstrap -->
<link href="${cssPath }/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${cssPath }/starter-template.css" rel="stylesheet">
<script src="${jsPath }/bootstrap-filestyle.min.js"></script>
</head>
<body>
        <h1>Bem Vindo a aplicação Exemplo Básico Spring MVC</h1>
        <p>Nessa aplicação vamos trabalhar os princípios de uma aplicação de controle de acesso com o framework Spring MVC</p>
        <p>As seguintes features estão disponíveis:</p>
        <ol>
        <li>Inserir usuário</li>
        <li>Listar usuários</li>
        <li>Buscar usuários</li>
        <li>Alterar usuário</li>
        <li>Remover usuário</li>
        <li>Validação de dados no lado servidor</li>
        <li>Validação de dados no lado cliente</li>
        <li>Manipulação de dados via ORM/JPA</li>
        <li>Controle de sessão do usuário via session</li> 
        <li>Inserção de arquivo via Multipart armazenado no filesystem do Servidor</li> 
        </ol>
        <p><a href="login">Login</a></p>
    </body>
</html>
