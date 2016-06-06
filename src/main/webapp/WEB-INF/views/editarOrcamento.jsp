<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Orçamento</title>
</head>
<body>

	<h2>Editar Orcamento</h2>


	<div>
		<label>Número Orçamento</label> <input type="text"
			name="num_orcamento" value="${orcamento.id_orcamento}" disabled />
	</div>
	
	<div>
		<label>Status</label> <input type="text"
			name="statusOrcamento" value="${orcamento.statusOrcamento}" disabled />
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
	
	

	<form action="manterOrcamento">

		<div>
			<div>
				<input type="text" name="codOrcamento"
					value="${orcamento.id_orcamento}" hidden />
			</div>
			<div>
				<label>Peças</label> <select name="descricao">
					<c:forEach items="${pecas}" step="1" var="peca">
						<option value="${peca.descricao}">${peca.descricao}|R$
							${peca.valor}</option>
					</c:forEach>
				</select> <label>Quantidade</label> <input type="text" name="quantidade"
					value="Quantidade" /> <input type=submit value="Incluir" />
			</div>

			<table>
				<th>Descricao</th>
				<c:forEach items="${pecasInclusas}" step="1" var="pecasInclusa">
					<tr>
						<td>${pecasInclusa.descricao}</td>
						<td></td>
						<td>R$ ${pecasInclusa.valor}</td>
						<td></td>
						<td>Qtd ${pecasInclusa.quantidade}</td>
					</tr>
				</c:forEach>
			</table>
			<br> <br> <label>Total</label><br>
						${orcamento.valorTotal}
		</div>
	</form>

	<form action = "finalizarOrcamento">

		<div>
			<label>Observacao</label><br>
			<textarea rows="8" cols="80" name="observacao">
			${orcamento.observacao}
		</textarea>
		</div>

		<div>
		<input type="submit" value="Finalizar"/>
		</div>

	</form>



</body>
</html>