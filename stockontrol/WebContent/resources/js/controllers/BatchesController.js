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
		order: 'expiresAt',
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

	$scope.registerOutgoing = function(id)
	{
		batchService.find(id, function(batch)
		{
			$mdDialog.show({

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

	$scope.fetchFunction = batchService.listAllByFilters;

	productService.listAllProductsByFilters(null, null, null, function(data)
	{
		$scope.products = data.content.sort(function(a, b)
		{
			return a.name.toLocaleLowerCase().localeCompare(b.name.toLocaleLowerCase());
		});
	});
});
