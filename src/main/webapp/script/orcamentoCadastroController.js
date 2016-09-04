app.controller('OrcamentoCadastroController', ['$scope', '$http' , function($scope, $http) {
	$scope.showLocalizaCliente = true;
	$scope.showFormularioOrcamento = false;
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'novoOrcamento/';


	$scope.init = function() {
		$scope.screenData = RETORNO;

	}

	$scope.localizarCliente = function() {
		var params = {
				'cpf': $scope.formData.cpf,
		}

		$http.post($scope.path + 'localizarCliente', params).then(
				function(response) {
					if (response.status === 200) {
						$scope.screenData.equipamentos = response.data.equipamentos;
						$scope.screenData.cliente = response.data.cliente;
						$scope.showLocalizaCliente = false;
						$scope.showFormularioOrcamento = true;
						
						clienteLocalizadoSucesso.style.display = "block";
						setTimeout(function(){
							clienteLocalizadoSucesso.style.display = "none";

						},5000);

					}else{
						clienteLocalizadoErro.style.display = "block";
						setTimeout(function(){
							clienteLocalizadoErro.style.display = "none";
						},5000);
					}
				}
		);
	}
	

	$scope.salvarOrcamento = function() {
		var params = {
				'relato': $scope.formData.relato,
				'observacao': $scope.formData.observacao,
				'idEspecialidade': $scope.formData.id_especialidade,
				'numSerieEquipamento': $scope.formData.equipamento
		}
		

		$http.post($scope.path + 'salvarOrcamento',params).then(
				function(response) {
					if (response.status === 200) {
						$scope.screenData.equipamentos = response.data.equipamentos;
						$scope.screenData.cliente = response.data.cliente;
						$scope.showLocalizaCliente = false;
						$scope.showFormularioOrcamento = true;
						
						sucessoSalvarOrcamento.style.display = "block";
						setTimeout(function(){
							sucessoSalvarOrcamento.style.display = "none";

						},5000);

					}else{
						erroSalvarOrcamento.style.display = "block";
						setTimeout(function(){
							erroSalvarOrcamento.style.display = "none";
						},5000);
					}
				}
		);
	}
	
/*	$scope.limpar = function() {
		$scope.formData.relato = "";
		$scope.formData.especialidade = "";
		$scope.formData.equipamento = "";
		$scope.formData.observacao = "";

	}
*/



}]);