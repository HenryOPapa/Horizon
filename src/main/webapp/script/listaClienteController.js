app.controller('ListaClienteController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'listaClientes/';
	$scope.showAtualizaCliente = false;


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	$scope.atualizaCliente = function (row){
		$scope.screenData.clienteSelecionado = row;
		$scope.showAtualizaCliente = true;		
		
	}
	
	$scope.alterarDados = function(){
		
		var params = $scope.consolidateParams();
		
		$http.post($scope.path + 'atualizarCliente',  params).then(
				function(response) {
					if (response.status === 200) {
						sucessoAtualizacao.style.display = "block";
						setTimeout(function(){			
							sucessoAtualizacao.style.display = "none";
							location.reload();
						},5000);
					}else{
						erroAtualizacao.style.display = "block";
						setTimeout(function(){
							erroAtualizacao.style.display = "none";
												
						},5000);

					}
				}
		);
		
	}
	
	$scope.excluirCliente = function(row){
		var params = {
				'id_cliente' : row.id_cliente,			
			}
		
		$http.post($scope.path + 'excluirCliente', params).then(
				function(response) {
					if (response.status === 200) {
						sucessoExcluir.style.display = "block";
						setTimeout(function(){			
							sucessoExcluir.style.display = "none";
							location.reload();
						},5000);
					}else{
						erroExcluir.style.display = "block";
						setTimeout(function(){			
							erroExcluir.style.display = "none";
							},5000);
					}
				}
		);
		
	}
	
	/**
	 * Trata o objeto de envio para o backend
	 * @return {Object} Objeto que sera enviado para o java
	 */
	$scope.consolidateParams = function() {
		if($scope.formData.cep == undefined)$scope.formData.cep = $scope.screenData.clienteSelecionado.cep;
		if($scope.formData.cidade == undefined)$scope.formData.cidade = $scope.screenData.clienteSelecionado.cidade;
		if($scope.formData.email == undefined)$scope.formData.email = $scope.screenData.clienteSelecionado.email;
		if($scope.formData.estado == undefined)$scope.formData.estado = $scope.screenData.clienteSelecionado.estado;
		if($scope.formData.logradouro == undefined)$scope.formData.logradouro = $scope.screenData.clienteSelecionado.logradouro;
		if($scope.formData.telefone == undefined)$scope.formData.telefone = $scope.screenData.clienteSelecionado.telefone;

		var params = {
				'id_cliente' : $scope.screenData.clienteSelecionado.id_cliente,
				'cep' : $scope.formData.cep,
				'nome' : $scope.screenData.clienteSelecionado.nome,
				'cidade' : $scope.formData.cidade,
				'cpf' : $scope.screenData.clienteSelecionado.cpf,
				'dataNascimento' : $scope.screenData.clienteSelecionado.dataNascimento,
				'email' : $scope.formData.email,
				'estado' :  $scope.formData.estado,
				'logradouro' : $scope.formData.logradouro,
				'telefone' :  $scope.formData.telefone,
				'orcamentos' : $scope.screenData.clienteSelecionado.orcamentos,
				'ordemDeServicos' : $scope.screenData.clienteSelecionado.ordemDeServicos,
				'equipamentos' : $scope.screenData.clienteSelecionado.equipamentos
			}
		return params;
	}


}]);