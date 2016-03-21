Stockontrol.controller('NewController', function($controller, $scope)
{
	$controller('FormController', {$scope: $scope});

	$scope.cancel = function()
	{
		$mdDialog.cancel();
	};
});
