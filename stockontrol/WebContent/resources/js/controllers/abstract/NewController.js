Stockontrol.controller('NewController', function($controller, $scope, $mdDialog, $mdToast)
{
	$controller('FormController', {$scope: $scope});

	$scope.cancel = function()
	{
		$mdDialog.cancel();
	};
});
