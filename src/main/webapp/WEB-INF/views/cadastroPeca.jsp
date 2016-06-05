
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::Peca::.</title>
</head>
<body>

	<h3>Pecas</h3>

	<form action="novaPeca" method="post">
		<div>
			<table>
				<c:forEach items="${pecas}" step="1" var="peca">
					<tr>
						<td>${peca.descricao}</td>
						<td>  R$ ${peca.valor}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<h3>Nova Peca</h3>
			<label>Descrição peca</label> 
			<input type="text" name="descricao"/>
			<label>Valor peca</label> 
			<input type="text" name="valor"/>
			<input type="submit" value="adicionar" />
		</div>
	</form>

</body>
</html>