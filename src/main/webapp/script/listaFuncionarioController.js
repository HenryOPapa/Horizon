app.controller('ListaFuncionarioController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'listaFuncionarios/';


	$scope.init = function(){
		$scope.screenData = RETORNO;

	}


	$scope.cancelar = function(){
		location.reload();
	}
	
	$scope.atualizaFuncionario = function (row){
		$scope.screenData.funcionarioSelecionado = row;
		$scope.showAtualizaFuncionario = true;		
		
	}

	$scope.alterarDados = function(){

		var params = $scope.consolidateParams();

		$http.post($scope.path + 'atualizarFuncionario',  params).then(
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
	
	
	/**
	 * Trata o objeto de envio para o backend
	 * @return {Object} Objeto que sera enviado para o java
	 */
	$scope.consolidateParams = function() {
		if($scope.formData.cep == undefined)$scope.formData.cep = $scope.screenData.funcionarioSelecionado.cep;
		if($scope.formData.cidade == undefined)$scope.formData.cidade = $scope.screenData.funcionarioSelecionado.cidade;
		if($scope.formData.email == undefined)$scope.formData.email = $scope.screenData.funcionarioSelecionado.email;
		if($scope.formData.estado == undefined)$scope.formData.estado = $scope.screenData.funcionarioSelecionado.estado;
		if($scope.formData.logradouro == undefined)$scope.formData.logradouro = $scope.screenData.funcionarioSelecionado.logradouro;
		if($scope.formData.telefone == undefined)$scope.formData.telefone = $scope.screenData.funcionarioSelecionado.telefone;

		var params = {
				'id_cliente' : $scope.screenData.funcionarioSelecionado.id_cliente,
				'cep' : $scope.formData.cep,
				'nome' : $scope.screenData.funcionarioSelecionado.nome,
				'cidade' : $scope.formData.cidade,
				'cpf' : $scope.screenData.funcionarioSelecionado.cpf,
				'dataNascimento' : $scope.screenData.funcionarioSelecionado.dataNascimento,
				'email' : $scope.formData.email,
				'estado' :  $scope.formData.estado,
				'logradouro' : $scope.formData.logradouro,
				'telefone' :  $scope.formData.telefone,

			}
		return params;
	}




}]);