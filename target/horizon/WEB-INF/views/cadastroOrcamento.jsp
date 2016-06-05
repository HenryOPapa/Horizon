<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="localizaCliente" method="post">
		<label>Localizar</label> <input type="text" name="cpf" value="CPF" />
		<input type="submit" value="Localizar" />

	</form>
	<form action="cadastraOrcamento" method="post">
		<div>
			<label>Nome Cliente</label> <input type="text" name="nome"
				value="${orcamento.cliente.nome}" />
		</div>

		<div>
			<label>Equipamento</label> <select name="equipamentoDanificado">
				<option value="default">Escolha um equipamento</option>
				<c:forEach items="${equipamentos}" var="equipamento">
					<option value="${equipamento.marca}| ${equipamento.modelo}">${equipamento.marca}||
						${equipamento.modelo}</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<label>Categoria</label> <select name="categoriaOrcamento">
				<option value="default">Escolha uma especialidade</option>
				<c:forEach items="${especialidades}" var="especialidades">
					<option value="${especialidades.descricao}">${especialidades.descricao}</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<label>Relato</label><br>
			<textarea rows="4" cols="40" name="relato"></textarea>
		</div>
		<input type="submit" value="Finalizar">
	</form>


</body>
</html>