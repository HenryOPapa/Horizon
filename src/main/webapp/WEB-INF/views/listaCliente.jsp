<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Clientes</title>
</head>
<h:body>
	<h:form>

		<table border="5px" bordercolor="black" align="center">
			<tr>
				<th>Código</th>
				<th>Nome</th>
				<th>Idade</th>
				<th>CPF</th>
				<th>RG</th>
				<th>Endereco</th>
				<th>Cidade</th>
				<th>Tipo Endereço</th>
				<th>Telefone</th>
				<th>Email</th>
				<th colspan="3">Ações</th>
			</tr>

			<c:forEach items="${clientes}" step="1" var="cliente">

				<tr>
					<td>${cliente.id_cliente}</td>
					<td>${cliente.nome}</td>
					<td>${cliente.dataNascimento}</td>
					<td>${cliente.cpf}</td>
					<td>${cliente.rg}</td>
					<td>${cliente.endereco}</td>
					<td>${cliente.cidade}</td>
					<td>${cliente.estado}</td>
					<td>${cliente.telefone}</td>
					<td>${cliente.email}</td>

					<td><a href="removeCliente?id_cliente=${cliente.id_cliente}">Remover</a></td>
					<td><a
						href="alterarDadosCliente?id_cliente=${cliente.id_cliente}">Atualizar</a></td>
					<td><a
						href="adicionarEquipamentoAoCliente?id_cliente=${cliente.id_cliente}">Adicionar
							Máquina</a></td>
				</tr>

			</c:forEach>
		</table>

	</h:form>
</h:body>
</html>