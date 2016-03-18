Stockontrol.directive('validateUserEmail', function($q)
{
	return {
		require: 'ngModel',
		link: function(scope, el, attrs, controller)
		{
			controller.$asyncValidators.validateUserEmail = function(val)
			{
				console.log('val: ', val);
				if(controller.$isEmpty(val))
				{
					return $q.when();
				}
				console.log('val: ', val);
				var def = $q.defer();

				userService.findByEmail(val, function(user)
				{
					console.log('User: ', user);
					if(user === null)
					{
						def.resolve();
					}
					else
					{
						def.reject();
					}
				});
				return def.promise;
			};
		},
	};
});
