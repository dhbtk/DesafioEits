Stockontrol.controller('NewBatchController', function($scope, $controller, $mdDialog)
{
	$controller('FormController', {$scope: $scope});
	$scope.batch = new Batch();
	$scope.entity = 'batch';
	$scope.saveFunction = batchService.insert;
	$scope.successMessage = function(batch)
	{
		return 'Lote "' + batch.identifier + '" adicionado';
	};

	$scope.cancel = function()
	{
		$mdDialog.cancel();
	};
});
