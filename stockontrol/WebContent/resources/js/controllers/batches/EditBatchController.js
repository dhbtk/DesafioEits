Stockontrol.controller('EditBatchController', function($controller, $scope, $mdToast, $mdDialog, batch)
{
	$controller('FormController', {$scope: $scope}); // Para setForm()

	$scope.batch = batch;
	$scope.quantity = batch.quantity;

	$scope.save = function()
	{
		if($scope.form.$valid)
		{

		}
	};
});
