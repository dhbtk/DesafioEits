Stockontrol.controller('BatchesController', function($controller, $scope, $mdDialog)
{
	$controller('BaseController', {$scope: $scope});

	$scope.header.location = '/resources/views/batches/batches-header.html';

	/*
	 * Modelo
	 */
	angular.extend($scope.model, {
		filters: ['productName', 'identifier', 'maxDate', 'productId'],
		filterData: {
			productName: null,
			identifier: null,
			maxDate: null,
			productId: null,
		},
		batch: null,
		order: '-expiresAt',
	});

	$scope.products = [];

	/*
	 * Métodos
	 */

	$scope.openNew = function()
	{
		$mdDialog.show({
			controller: 'NewBatchController',
			templateUrl: '/resources/views/batches/batches-new.html',
			scope: $scope.$new(),
			clickOutsideToClose: true
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

	$scope.fetchFunction = batchService.listAllByFilters;

	$scope.model.tasks++;
	productService.listAllProductsByFilters(null, null, {page: 0, size: 5000, property: 'name', direction: 'ASC'}, function(data) // FIXME!!!!!!
	{
		$scope.model.tasks--;
		$scope.products = data.content;
	});
});
