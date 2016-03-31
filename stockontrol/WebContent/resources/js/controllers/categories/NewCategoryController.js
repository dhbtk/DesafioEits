Stockontrol.controller('NewCategoryController', function($controller, $scope)
{
	$controller('NewController', {$scope: $scope});
	$scope.category = new Category();
	$scope.entity = 'category';
	$scope.saveFunction = productService.insertCategory;
	$scope.successMessage = function(category)
	{
		return 'Categoria "' + category.name + '" criada';
	};
});
