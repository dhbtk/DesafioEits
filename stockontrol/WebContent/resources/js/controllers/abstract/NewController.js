Stockontrol.controller('NewController', function($controller, $scope, $mdDialog)
{
	$controller('FormController', {$scope: $scope});

	$scope.cancel = function()
	{
		$mdDialog.cancel();
	};
});
