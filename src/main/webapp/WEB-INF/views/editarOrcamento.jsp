<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Orçamento</title>
</head>
<body>
	<form action="">
		<h2>Editar Orcamento</h2>
	</form>
	<div>
		<label>Número Orçamento</label> <input type="text" name="id_orcamento"
			value="${orcamento.id_orcamento}" disabled />
	</div>

	<div>
		<label>Nome Cliente</label> <input type="text" name="nome"
			value="${cliente.nome}" disabled />
	</div>

	<div>
		<label>Relato Problema</label> <input type="text" name="relato"
			value="${orcamento.relato}" disabled />
	</div>

	<div>
		<label>Equipamento</label> <input type="text"
			name="equipamentoDanificado"
			value="${orcamento.equipamentoDanificado}" disabled />
	</div>

	<div>
		<label>Especialidade</label> <input type="text" name=""
			value="${orcamento.especialidade}" disabled />
	</div>

	<div>
		<label>Peças</label> <select name="pec">
			<c:forEach items="${pecas}" var="peca">
				<option value="${peca.descricao}"></option>
			</c:forEach>
		</select>
	</div>

	<div>
		<label>Observacao</label><br>
		<textarea rows="8" cols="20" name="observacao">
		</textarea>
	</div>

	<div></div>





</body>
</html>