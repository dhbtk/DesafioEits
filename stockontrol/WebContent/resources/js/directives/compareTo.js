Stockontrol.directive('compareTo', function()
{
	return {
		require: 'ngModel',
		scope: {
			otherValue: '=compareTo'
		},
		link: function(scope, element, attributes, ngModel)
		{
			ngModel.$validators.compareTo = function(modelValue)
			{
				console.log('modelValue: ' + modelValue);
				console.log('otherValue: ' + scope.otherValue);
				return modelValue == scope.otherValue;
			}

			scope.$watch('otherValue',function() { ngModel.$validate(); });
		}
	};
});
