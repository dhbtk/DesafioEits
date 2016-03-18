Stockontrol.controller('CategoriesController',function($controller, $scope, $mdToast, $mdSidenav, $mdDialog)
{
	// Herdando do controller base
	$controller('BaseController', {$scope: $scope});

	/** Localização do cabeçalho **/
	$scope.header.location = '/resources/views/categories/categories-header.html';

	/*
	 * Modelo
	 */
	angular.extend($scope.model, {
		filters: ['text'],
		filterData: {
			text: null,
		},
		category: null,
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

	$scope.fetchFunction = productService.listAllCategoriesByFilters;
});
