Stockontrol.controller('BatchesController', function($controller, $scope)
{
	$controller('BaseController', {$scope: $scope});

	$scope.header.location = '/resources/views/batches/batches-header.html';

	/*
	 * Modelo
	 */
	angular.extend($scope.model, {
		filters: ['text', 'maxDate'],
		filterData: {
			text: null,
			maxDate: null,
		},
		category: null,
		order: '-expiresAt',
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

	$scope.fetchFunction = batchService.listAllByFilters;
});
