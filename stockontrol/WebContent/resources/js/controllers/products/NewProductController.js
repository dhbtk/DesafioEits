Stockontrol.controller('NewProductController', function($controller, $scope)
{
	$controller('NewController', {$scope: $scope});
	$scope.product = new Product
	$scope.entity = $scope.product;
	$scope.saveFunction = productService.insertProduct;
	$scope.successMessage = function(product)
	{
		return 'Produto "' + product.name + '" criado';
	};
	$scope.categories = [];

	productService.listAllCategoriesByFilters(null, null, function(data)
	{
		$scope.categories = data.content;
	});
});
