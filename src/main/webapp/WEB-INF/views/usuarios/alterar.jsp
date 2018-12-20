<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />

<tags:pageTemplate titulo="Alterar usuário">

	<section id="index-section" class="container middle">


		<h1>Cadastro de Permissões para ${nome }</h1>

		<form:form action="${s:mvcUrl('UC#alterar').arg(0,usuario.nome ).arg(1,rolesList).build() }" method="POST">
			<div class="form-group">
				<p>Permissões:
					<form:checkboxes items = "${rolesList}" path = "rolesList" />
				</p>				
			</div>
			<button type="submit" class="btn btn-primary">Atualizar</button>
		</form:form>
	</section>

</tags:pageTemplate>