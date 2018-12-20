<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Lista de pedidos">

	<section id="index-section" class="container middle">
	
		<h1>Lista de pedidos atuais</h1>
		
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Valor</th>
				<th>Data Pedido</th>
				<th>Títulos</th>
			</tr>
			<c:forEach items="${pedidos }" var="pedidos">
				<tr>
					<td>${pedidos.id }</td>
					<td>${pedidos.valor }</td>
					<td><fmt:formatDate value="${pedidos.data.time }" pattern="dd/MM/yy"/></td>
					<td>${pedidos.todosTitulos }</td>
				</tr>
			</c:forEach>
		</table>
	</section>

</tags:pageTemplate>
