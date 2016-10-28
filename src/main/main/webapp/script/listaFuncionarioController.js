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

		var params = $scope.formData;

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
	

}]);