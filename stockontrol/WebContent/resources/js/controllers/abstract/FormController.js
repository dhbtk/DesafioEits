Stockontrol.controller('FormController', function($scope, $mdSidenav, $mdDialog, $mdToast)
{
	$scope.form = null;

	/** Função para a mensagem do toast **/
	$scope.successMessage = function(data) { return "saved" };

	/** Entidade a ser criada/salva **/
	$scope.entity = null;
	/** Função de salvamento **/
	$scope.saveFunction = null;

	$scope.setForm = function(form)
	{
		$scope.form = form;
	};

	$scope.save = function()
	{
		if($scope.form.$valid)
		{
			$scope.saveFunction($scope[$scope.entity], function(data)
			{
				$mdSidenav('rightPanel').close();
				$mdDialog.cancel();
				$mdToast.show(
						$mdToast.simple()
								.textContent($scope.successMessage(data))
								.position('bottom')
								.hideDelay(3000));
				$scope.fetchData();
			});
		}
	};

	$scope.create = $scope.save;
});
