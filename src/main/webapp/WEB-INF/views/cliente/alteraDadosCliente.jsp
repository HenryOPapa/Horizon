
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::Cliente::.</title>
</head>
<body>

	<h3>Atualizar Cliente</h3>
	
	<form action="atualizarCliente" method="post">
			<h2>Dados Cliente</h2>
			<div disabled>
				ID Cliente: <input type="text" name="id_cliente" value="${cliente.id_cliente}"/>
			</div>
			
			<div>
				Nome: <input type="text" name="nome" value="${cliente.nome}"/>
			</div>

			<div>
				Idade: <input type="text" name="idade" value="${cliente.dataNascimento}"/>
			</div>

			<div>
				CPF: <input type="text" name="cpf" value="${cliente.cpf}"/>
			</div>

			<div>
				RG: <input type="text" name="rg" value="${cliente.rg}"/>
			</div>
	
			<h2>Endereço</h2>
			<div>
				RUA: <input type="text" name="endereco" value="${cliente.endereco}"/>
			</div>

			<div>
				Cidade: <input type="text" name="cidade" value="${cliente.cidade}"/>
			</div>
			
			<div>
				Estado: <input type="text" name="estado" value="${cliente.estado}"/>
			</div>
			


			<div>
				<select name="tipoEndereco">
					<option name="RESIDENCIAL">RESIDENCIAL</option>
					<option name="COMERCIAL">COMERCIAL</option>
				</select>
			</div>

		
			<h2>Contato</h2>
			<div>
				Telefone: <input type="text" name="telefone" value="${cliente.telefone}"/>
			</div>
			<div>
				Email: <input type="text" name="email" value="${cliente.email}"/>
			</div>
		

		<input type="submit" value="Atualizar" />


	</form>

</body>
</html>