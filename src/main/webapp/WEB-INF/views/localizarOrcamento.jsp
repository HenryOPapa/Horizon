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
		<table border="5px" bordercolor="black" align="center">
		<tr>
			<th>Código</th>
			<th>Relato</th>
<!-- 			<th>Id Cliente</th> -->
			<th>Especialidade</th> 
			<th>Equipamento</th>
			<th>Status</th>
			<th colspan="3">Ações</th>
		</tr>

		<c:forEach items="${orcamentos}" step="1" var="orcamento">

			<tr>
				<td>${orcamento.id_orcamento}</td>
				<td>${orcamento.relato}</td>
				<td>${orcamento.especialidade}</td>
				<td>${orcamento.equipamentoDanificado}</td>
				<td>${orcamento.statusOrcamento}</td>
								
				<td><a href="manterOrcamento?codOrcamento=${orcamento.id_orcamento}">Editar</a></td>
				<td><a href="reprovarOrcamento?codOrcamento=${orcamento.id_orcamento}">Reprovar</a></td>
				<td><a href="aprovarOrcamento?codOrcamento=${orcamento.id_orcamento}">Aprovar</a></td>
			</tr>

		</c:forEach>
	</table>

</body>
</html>