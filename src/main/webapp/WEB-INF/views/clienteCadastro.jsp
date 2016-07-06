
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
<script src="<c:url value="/js/angular.min.js"/>" /></script>

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
					<div class="col-sm-6">
						<input type="nome" class="form-control" id="nome"
							placeholder="Nome do Cliente">
					</div>
				<div class="form-group">
					<div class="col-sm-6">
						<input type="dataNascimento" class="form-control" id="nascimento"
							placeholder="Ano de nascimento">
					</div>

						<div class="form-group">
							<div class="col-sm-6">
								<input type="cpf" class="form-control" id="cpf"
									placeholder="CPF">
							</div>

							<div class="form-group">
								<div class="col-sm-6">
									<input type="rg" class="form-control" id="rg" placeholder="RG">
								</div>
								
								<hr>

								<h2>Endereço</h2>

								<div class="form-group">
									<div class="col-sm-6">
										<input type="logradouro" class="form-control" id="logradouro"
											placeholder="Logradouro">
									</div>

									<div class="form-group">
										<div class="col-sm-6">
											<input type="cidade" class="form-control" id="cidade"
												placeholder="Cidade">
										</div>

										<div class="form-group">
											<div class="col-sm-6">
												<input type="cep" class="form-control" id="cep"
													placeholder="CEP">
											</div>
											
											<div class="form-group">
											<div class="col-sm-6">
												<input type="estado" class="form-control" id="estado"
													placeholder="Estado">
											</div>
											
											<hr>

											<h2>Contato</h2>
											
											<div class="form-group">
											<div class="col-sm-6">
												<input type="estado" class="form-control" id="estado"
													placeholder="Estado">
											</div>
											
											<div class="form-group">
											<div class="col-sm-6">
												<input type="telefone" class="form-control" id="telefone"
													placeholder="Telefone">
											</div>
											
											<div class="form-group">
											<div class="col-sm-12">
												<input type="email" class="form-control" id="email"
													placeholder="Email">
											</div>


											<input type="submit" value="Adicionar" />
			</form>
		</div>
	</div>


</body>
</html>