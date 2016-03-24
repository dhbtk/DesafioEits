Stockontrol.controller('EditBatchController', function($controller, $scope, $mdToast, $mdDialog)
{
	$controller('FormController', {$scope: $scope}); // Para setForm()

	$scope.quantity = $scope.batch.quantity;

	$scope.save = function()
	{
		if($scope.form.$valid)
		{
			batchService.registerOutgoingById($scope.batch.id, $scope.quantity, function()
			{
				$mdDialog.cancel();
				$mdToast.showSimple($scope.quantity + ' itens do lote "' + $scope.batch.identifier + '" removidos');
				$scope.fetchData();
			});
		}
	};

	$scope.cancel = function()
	{
		$mdDialog.cancel();
	};
});
