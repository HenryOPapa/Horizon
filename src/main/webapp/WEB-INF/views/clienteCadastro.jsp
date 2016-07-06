
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
	<div
		class="text-center col-sm-6 col-sm-offset-3 panel panel-default center">
		<div class=" panel-heading">
			<label>Novo Cliente</label>
		</div>
		<div class="panel-body">
			<form action="adicionaCliente" method="post">

				<h2>Dados Cliente</h2>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd"></label>
					<div class="col-sm-10">
						<input type="nome" class="form-control" id="nome"
							placeholder="Nome do Cliente">
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"></label>
						<div class="col-sm-10">
							<input type="nascimento" class="form-control" id="nascimento"
								placeholder="Ano de nascimento">
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Password:</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="pwd"
									placeholder="Enter password">
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2" for="pwd">Password:</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="pwd"
										placeholder="Enter password">
								</div>

								<div class="text-center">
									<label>Idade</label> <input type="text" name="idade" />
								</div>

								<div class="text-center">
									<label>CPF</label> <input type="text" name="cpf" />
								</div>

								<div class="text-center">
									<label>RG</label> <input type="text" name="rg" />
								</div>

								<h2>Endereço</h2>
								<div>
									<label>Endereço</label> <input type="text" name="endereco" />
								</div>

								<div>
									<label>Cidade</label> <input type="text" name="cidade" />
								</div>

								<div>
									<label>Estado</label> <input type="text" name="estado" />
								</div>


								<h2>Contato</h2>
								<div>
									<label>Telefone</label> <input type="text" name="telefone" />
								</div>
								<div>
									<label>Email</label> <input type="text" name="email" />
								</div>


								<input type="submit" value="Adicionar" />
			</form>
		</div>
	</div>


</body>
</html>