Stockontrol.directive('isAfter', function()
{
	return {
		restrict: 'A',
		require: 'ngModel',
		scope: {
			otherDate: '=isAfter'
		},
		link: function(scope, element, attributes, ngModel)
		{
			ngModel.$validators.isAfter = function(date)
			{
				return moment(date).isAfter(scope.otherDate, 'day');
			}

			scope.$watch('otherDate', function() { ngModel.$validate(); });
		}
	};
});
