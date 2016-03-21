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

	/*
	 * Métodos
	 */

	/**
	 *
	 * Listagem
	 *
	 */

	/*
	 * Inicialização
	 */

	$scope.fetchFunction = productService.listAllProductsByFilters;
});
