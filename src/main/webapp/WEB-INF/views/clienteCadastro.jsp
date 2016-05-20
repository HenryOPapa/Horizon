
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::Cliente::.</title>
</head>
<body>

	<h3>Adicionar Cliente</h3>
	
	<form action="adicionaCliente" method="post">
		
			<h2>Dados Cliente</h2>
			<div>
				Nome: <input type="text" name="nome" />
			</div>

			<div>
				Idade: <input type="text" name="idade" />
			</div>

			<div>
				CPF: <input type="text" name="cpf" />
			</div>

			<div>
				RG: <input type="text" name="rg" />
			</div>
	
			<h2>Endereço</h2>
			<div>
				Endereço: <input type="text" name="endereco" />
			</div>

			<div>
				Cidade: <input type="text" name="cidade" />
			</div>
			
			<div>
				Estado: <input type="text" name="estado" />
			</div>

		
			<h2>Contato</h2>
			<div>
				Telefone: <input type="text" name="telefone" />
			</div>
			<div>
				Email: <input type="text" name="email" />
			</div>
		

		<input type="submit" value="Adicionar" />


	</form>

</body>
</html>