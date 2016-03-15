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

var Stockontrol = angular.module('Stockontrol',['ngMaterial','ui.router'])

Stockontrol.config(function($stateProvider)
{

});
