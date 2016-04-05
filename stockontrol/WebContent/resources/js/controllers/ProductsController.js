Stockontrol.controller('ProductsController',function($controller, $scope, $mdToast, $mdSidenav, $mdDialog, identityService)
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

	function getProductCategory(product, callback)
	{
		productService.findCategory(product.categoryId, function(category)
		{
			product.category = category;
			callback(product);
		});
	};

	$scope.updateProductWrapper = function(product, callback)
	{
		getProductCategory(product, function(product)
		{
			productService.updateProduct(product, callback);
		});
	};

	$scope.insertProductWrapper = function(product, callback)
	{
		getProductCategory(product, function(product)
		{
			productService.insertProduct(product, callback);
		});
	};

	$scope.openEditProduct = function(id)
	{
		productService.findProduct(id, function(product)
		{
			$scope.product = product;
			$mdSidenav('rightPanel').open();
		});
	};

	$scope.destroy = function(product)
	{
		var dialog = $mdDialog.confirm()
				.title('Excluir produto')
				.textContent('Tem certeza que quer excluir o produto "' + product.name + '"? Esta ação não pode ser desfeita.')
				.ok('Excluir')
				.cancel('Cancelar');
		$mdDialog.show(dialog).then(function()
		{
			productService.deleteProduct(product.id, {
				callback: function()
				{
					$mdSidenav('rightPanel').close();
					$mdToast.show(
							$mdToast.simple()
									.textContent('Produto "' + product.name + '" excluído')
									.position('bottom')
									.hideDelay(3000));
					$scope.fetchData();
				},
				errorHandler: function(ex, msg)
				{
					console.log(ex);
					console.log(msg);
				}
			});
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
