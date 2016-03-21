Stockontrol.controller('ProductsController',function($controller, $scope, $mdToast, $mdSidenav, $mdDialog)
{
	// Herdando do controller base
	$controller('BaseController', {$scope: $scope});

	/** Localização do cabeçalho **/
	$scope.header.location = '/resources/views/products/products-header.html';

	/*
	 * Modelo
	 */
	angular.extend($scope.model, {
		filters: ['categoryId', 'name'],
		filterData: {
			categoryId: null,
			name: null,
		},
		product: null,
		order: 'name',
	});

	$scope.categories = [];

	/*
	 * Métodos
	 */

	$scope.openNewProduct = function()
	{
		$mdDialog.show({
			controller: 'NewProductController',
			templateUrl: '/resources/views/products/products-new.html',
			scope: $scope.$new(),
			clickOutsideToClose: true
		});
	};

	$scope.getProductCategory = function(product, callback)
	{
		productService.findCategory(product.categoryId, function(category)
		{
			product.category = category;
			callback(product);
		});
	};

	$scope.updateProductWrapper = function(product, callback)
	{
		$scope.getProductCategory(product, function(product)
		{
			productService.updateProduct(product, callback);
		});
	};

	$scope.insertProductWrapper = function(product, callback)
	{
		$scope.getProductCategory(product, function(product)
		{
			productService.insertProduct(product, callback);
		});
	};

	$scope.openEditProduct = function(id)
	{
		$scope.model.tasks++;
		productService.findProduct(id, function(product)
		{
			$scope.model.tasks--;
			$scope.product = product;
			$mdSidenav('rightPanel').open();
		});
	};

	/**
	 *
	 * Listagem
	 *
	 */

	/*
	 * Inicialização
	 */

	$scope.fetchFunction = productService.listAllProductsByFilters;

	productService.listAllCategoriesByFilters(null, null, function(data)
	{
		$scope.categories = data.content;
	});
});
