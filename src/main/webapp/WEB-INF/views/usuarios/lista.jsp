<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />

<tags:pageTemplate titulo="Lista de usuários">

	<section id="index-section" class="container middle">
	
		<h1><a href="${s:mvcUrl('UC#form').build()}">Novo Usuário<a></h1>		
		
		<h1>Lista de Usuários</h1>
		
		<h1 style="color: green;">${sucesso}</h1>
		
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
				<th></th>				
				
			</tr>
			<c:forEach items="${usuarios }" var="users">
				<tr>
					<td>${users.nome}</td>
					<td>${users.email }</td>
					<td>${users.todosRoles }</td>
					<td>
						<form:form action="${s:mvcUrl('UC#formAlterar').arg(0, users.nome).build()}">
								<input type="image" src="${contextPath }resources/imagens/adicionar.png" 
									alt="Alterar" title="Alterar" />
							</form:form>
					</td>										
				</tr>
			</c:forEach>
		</table>
	</section>

</tags:pageTemplate>