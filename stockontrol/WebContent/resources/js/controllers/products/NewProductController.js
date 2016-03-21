Stockontrol.controller('NewProductController', function($controller, $scope)
{
	$controller('NewController', {$scope: $scope});
	$scope.product = new Product();
	$scope.entity = 'product';
	$scope.saveFunction = $scope.insertProductWrapper;
	$scope.successMessage = function(product)
	{
		return 'Produto "' + product.name + '" criado';
	};
});
