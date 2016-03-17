function serializeObject(o)
{
	var s = '';
	var i = 0;
	for(var k in o)
	{
		if(i != 0)
		{
			s = s + '&';
		}
		s = s + encodeURIComponent(k) + '=' + encodeURIComponent(o[k]);
		if(i == 0)
		{
			i++;
		}
	}
	return s;
}

angular.module('StockontrolLogin',['ngMaterial']).controller('LoginController', function($scope, $http, $mdToast, $window)
{
	$scope.model = {user: {}, loading: false};

	$scope.handleLogIn = function()
	{
		$scope.model.loading = true;
		console.log(serializeObject($scope.model.user));
		var config = {
			headers: {'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'}
		};
		$http.post('/authenticate', serializeObject($scope.model.user), config)
				.success(function(data)
				{
					console.log(data);
					$window.location.href = "/";
					$scope.model.loading = false;
				})
				.error(function(data, status)
				{
					if(status == "401")
					{
						$mdToast.showSimple('Email ou senha incorretos. Por favor tente novamente.');
					}
					else if(status == "403")
					{
						$mdToast.showSimple('Suas credenciais foram desativadas.');
					}
					else
					{
						$mdToast.showSimple('Houve um erro desconhecido. Por favor tente novamente. (' + status + ')');
					}
					$scope.model.loading = false;
				});
	};
});
