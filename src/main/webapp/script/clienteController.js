app.controller('ClienteController', ['$scope', '$http' , function($scope, $http) {
	window.scope = $scope;
	$scope.screenData = [];
	$scope.formData = {};
	$scope.path = 'cadastroCliente/';


	$scope.cadastrarCliente = function() {
		var params = {
				'nome' :$scope.formData.nome,
				'dataNascimento' :$scope.formData.dataNascimento,
				'cpf' :$scope.formData.cpf,
				'cep' :$scope.formData.cep,
				'logradouro' :$scope.formData.logradouro,
				'String' :$scope.formData.String,
				'estado' :$scope.formData.estado,
				'telefone' :$scope.formData.telefone,
				'email' :$scope.formData.email,
		}
		

		$http.post($scope.path + 'cadastrarCliente', params).then(
				function(response) {
					if (response.status == 200) {
						
						sucessoCadastro.style.display = "block";
						setTimeout(function(){
							sucessoCadastro.style.display = "none";
							location.reload();
						},5000);
						
					}else{
						erroCadastro.style.display = "block";
						setTimeout(function(){
							erroCadastro.style.display = "none";
						},5000);

					}
				}
		);
	}
	
	
	$scope.cancelar = function(){
		location.reload();
	}




}]);