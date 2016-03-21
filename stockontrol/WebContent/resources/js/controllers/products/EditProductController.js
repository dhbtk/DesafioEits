Stockontrol.controller('EditProductController', function($controller, $scope)
{
	$controller('EditController', {$scope: $scope});
	$scope.entity = 'product';
	$scope.saveFunction = productService.saveProduct;
	$scope.successMessage = function(product)
	{
		return 'Produto "' + product.name + '" alterado';
	};
});
