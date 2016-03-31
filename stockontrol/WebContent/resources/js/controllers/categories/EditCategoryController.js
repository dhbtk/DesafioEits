Stockontrol.controller('EditCategoryController', function($controller, $scope)
{
	$controller('EditController', {$scope: $scope});
	$scope.entity = 'category';
	$scope.saveFunction = productService.updateCategory;
	$scope.successMessage = function(category)
	{
		return 'Categoria "' + category.name + '" alterada';
	};
});
