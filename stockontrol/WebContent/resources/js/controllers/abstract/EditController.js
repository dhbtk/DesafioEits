Stockontrol.controller('EditController', function($controller, $scope)
{
	$controller('FormController', {$scope: $scope});

	$scope.cancel = function()
	{
		$mdSidenav('rightPanel').close();
	};
});
