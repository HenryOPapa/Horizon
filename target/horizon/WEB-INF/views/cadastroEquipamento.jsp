
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::Cliente::.</title>
</head>
<body>

	<h3>Adicionar Cliente</h3>

	<form action="adicionaEquipamento?id_cliente=${cliente.id_cliente}" method="post">

		<h2>Dados Equipamento</h2>


		<div>
			Tipo do Equipamento: <input type="text" name="tipoEquipamento" />
		</div>
		<div>
			Marca: <input type="text" name="marca" />
		</div>
		<div>
			Modelo: <input type="text" name="modelo" />
		</div>
		<div>
			Número Série: <input type="text" name="numeroSerie" />
		</div>

		<input type="submit" value="Adicionar" />


	</form>

</body>
</html>