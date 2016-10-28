app.controller('ItensDeServicoController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'itensDeServico/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}

	$scope.adicionaPeca = function() {
		var params = {
				'descricao': $scope.formData.descricao,
				'valor' :$scope.formData.valor,
				'quantidadeMinima' :$scope.formData.quantidadeMinima,
		}



		$http.post($scope.path + 'cadastrarPeca', params).then(
				function(response) {
					if (response.status === 200) {
						pecaCadastroSucesso.style.display = "block";
						setTimeout(function(){
							pecaCadastroSucesso.style.display = "none";
							location.reload();					

						},5000);

					}else{
						pecaCadastroErro.style.display = "block";
						setTimeout(function(){
							pecaCadastroErro.style.display = "none";
						},5000);
					}
				}
		);
	}
	
	
	$scope.adicionaServico = function() {
		var params = {
				'descricao': $scope.formData.descricaoServico,
				'valor' :$scope.formData.valorServico,
		}



		$http.post($scope.path + 'cadastrarServico', params).then(
				function(response) {
					if (response.status === 200) {
						servicoCadastroSucesso.style.display = "block";
						setTimeout(function(){
							servicoCadastroSucesso.style.display = "none";
							location.reload();					

						},5000);

					}else{
						servicoCadastroErro.style.display = "block";
						setTimeout(function(){
							servicoCadastroErro.style.display = "none";
						},5000);
					}
				}
		);
	}




}]);