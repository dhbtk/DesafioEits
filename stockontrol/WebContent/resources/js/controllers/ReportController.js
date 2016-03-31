Stockontrol.controller('ReportController', function($controller, $scope)
{
	$controller('BaseController', {$scope: $scope});

	$scope.header.location = '/resources/views/report/report-header.html';

	angular.extend($scope.model, {
		filters: ['categoryId', 'name'],
		filterData: {
			categoryId: null,
			name: null,
		},
		product: null,
		order: 'name',
	});

	function loadProductBatches(product)
	{
		product.expiringBatches = [];
		product.expiredBatches = [];
		batchService.listAllExpiring(product.id, null, function(data)
		{
			product.expiringBatches = data.content;
		});
		batchService.listAllExpired(product.id, null, function(data)
		{
			product.expiredBatches = data.content;
		});
	}

	$scope.fetchFunctionCallback = function(data)
	{
		for(var i = 0; i < data.content.length; i++)
		{
			loadProductBatches(data.content[i]);
		}
	};

	$scope.fetchFunction = productService.listAllProductsByFilters;
});
