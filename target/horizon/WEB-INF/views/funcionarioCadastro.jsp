<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::Funcionário::.</title>
</head>
<body>

	<h3>Adicionar Funcionário</h3>

	<form action="adicionaFuncionario" method="post">

		<h2>Dados Funcionário</h2>
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
			Endereco: <input type="text" name="endereco" />
		</div>

		<div>
			Cidade: <input type="text" name="cidade" />
		</div>

		
<!-- 		<div> -->
<!-- 			<select name="tipoEndereco"> -->
<!-- 				<option name="RESIDENCIAL">RESIDENCIAL</option> -->
<!-- 				<option name="COMERCIAL">COMERCIAL</option> -->
<!-- 			</select> -->
<!-- 		</div> -->


		<h2>Contato</h2>
		<div>
			Telefone: <input type="text" name="telefone" />
		</div>
		<div>
			Email: <input type="text" name="email" />
		</div>
		
		Nivel de Acesso: 
		<div>
			<select name="nivelAcesso">
				<option name="BAIXO">BAIXO</option>
				<option name="MEDIO">MEDIO</option>
				<option name="TOTAL">TOTAL</option>
			</select>
		</div>


		<input type="submit" value="Adicionar" />


	</form>

</body>
</html>