app.controller('EstoqueController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'estoque/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}

	$scope.atualizarEstoque = function() {
		var params = {
				'idPeca': $scope.formData.pecaSelecionada,
				'quantidade' :$scope.formData.quantidade,
				'notaFiscal' :$scope.formData.notaFiscal,
		}



		$http.post($scope.path + 'atualizarEstoque', params).then(
				function(response) {
					if (response.status === 200) {
						cadastroSucesso.style.display = "block";
						setTimeout(function(){
							cadastroSucesso.style.display = "none";
							$scope.screenData.estoque = response.data.estoque;

						},3000);

					}else{
						cadastroErro.style.display = "block";
						setTimeout(function(){
							cadastroErro.style.display = "none";
						},5000);
					}
				}
		);
	}




}]);