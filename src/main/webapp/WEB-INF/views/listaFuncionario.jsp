<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Funcionários</title>
</head>
<body>

	<table border="5px" align="center" bordercolor="black">
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
			<th>Ações</th>
		</tr>

		<c:forEach items="${funcionarios}" var="funcionario">
			<tr>
				<td>${funcionario.id}</td>
				<td>${funcionario.nome}</td>
				<td>${funcionario.idade}</td>
				<td>${funcionario.cpf}</td>
				<td>${funcionario.rg}</td>
				<td>${funcionario.endereco}</td>
				<td>${funcionario.cidade}</td>
				<td>${funcionario.tipoEndereco}</td>
				<td>${funcionario.telefone}</td>
				<td>${funcionario.email}</td>
				<td><a href="removeFuncionario?id=${funcionario.id}">Remover</a></td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>