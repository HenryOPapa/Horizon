app.controller('AprovarReprovarOrcamentoController', ['$scope', '$http', function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'aprovarReprovarOrcamento/';
	$scope.showListOrcamento = true;
	$scope.showOrcamento = false;

	
	
	$scope.init = function() {
		$scope.screenData = RETORNO;

	}
	
	

	$scope.detalharOrcamento = function(row) {
		var params = {
				'id_orcamento': row.id_orcamento,
		}

		$http.post($scope.path + 'detalharOrcamento', params).then(
				function(response) {
					if (response.status == 200) {
						$scope.screenData.orcamento = response.data.orcamento,
						$scope.screenData.cliente = response.data.cliente,
						$scope.screenData.equipamento = response.data.equipamento,
						$scope.screenData.itensOrcamento = response.data.itensOrcamento,
						$scope.showListOrcamento = false;
						$scope.showOrcamento = true;

					}else{
						erroDetalharOrcamento.style.display = "block";
						setTimeout(function(){
							erroDetalharOrcamento.style.display = "none";
						},5000);

					}
				}
		);
	}
	
	$scope.aprovarOrcamento = function(){
		var params = {
			'id_orcamento' : $scope.screenData.orcamento.id_orcamento,
		}
		
		$http.post($scope.path + 'aprovarOrcamento', params).then(
				function(response) {
					if (response.status == 200) {
						$scope.showListOrcamento = false;
						$scope.showOrcamento = true;
						location.reload();

					}else{
						erroDetalharOrcamento.style.display = "block";
						setTimeout(function(){
							erroDetalharOrcamento.style.display = "none";
						},5000);

					}
				}
		);
		
	}
	
	$scope.reprovarOrcamento = function(){
		var params = {
				'id_orcamento' : $scope.screenData.orcamento.id_orcamento,
			}
			
			$http.post($scope.path + 'reprovarOrcamento', params).then(
					function(response) {
						if (response.status == 200) {
//							reprovarOrcamentoSucesso.style.display = "block";
//							setTimeout(function(){
//								reprovarOrcamentoSucesso.style.display = "none";
//							},5000);
							location.reload();
						}
					}
			);	
		
	}
	
	$scope.cancelar = function(){
		$scope.showListOrcamento = true;
		$scope.showOrcamento = false;
	}

	

}]);