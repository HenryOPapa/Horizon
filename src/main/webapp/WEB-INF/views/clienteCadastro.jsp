
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>" /></script>
<script src="<c:url value="/js/jquery-2.1.3.min.js"/>" /></script>

<title>.::Cliente::.</title>
</head>
<body>
	<div class="text-center col-sm-6 col-sm-offset-3 panel panel-default center">
		<div class=" form-control panel-heading">
			<h3 class="panel-title">Novo Cliente</h3>
		</div>
		<div class="panel-body">
			<div class="panel panel-default">
				<div class=" panel-heading">Dados do Cliente</div>
				<div class="panel-body">
					<form action="adicionaCliente" method="post">

						<h2>Dados Cliente</h2>
						<div class="text-center">
							<input type="text" name="nome" />
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
				</div>
			</div>

		</div>
	</div>


</body>
</html>