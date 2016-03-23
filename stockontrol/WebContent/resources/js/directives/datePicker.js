Stockontrol.controller('DatePickerCtrl', ['$scope', '$mdDialog', 'currentDate', '$mdMedia', function($scope, $mdDialog, currentDate, $mdMedia) {
	var self = this;

	this.currentDate = currentDate;
	this.currentMoment = moment(self.currentDate);
	this.weekDays = moment.weekdaysMin();

	$scope.$mdMedia = $mdMedia;
	$scope.yearsOptions = [];
	for (var i = 1900; i <= (this.currentMoment.year() + 50); i++) {
		$scope.yearsOptions.push(i);
	}
	$scope.year = this.currentMoment.year();

	this.setYear = function() {
		self.currentMoment.year($scope.year);
	};

	this.selectDate = function(dom) {
		self.currentMoment.date(dom);
	};

	this.cancel = function() {
		$mdDialog.cancel();
	};

	this.confirm = function() {
		$mdDialog.hide(this.currentMoment.toDate());
	};

	this.getDaysInMonth = function() {
		var days = self.currentMoment.daysInMonth(),
			firstDay = moment(self.currentMoment).date(1).day();

		var arr = [];
		for (var i = 1; i <= (firstDay + days); i++)
			arr.push(i > firstDay ? (i - firstDay) : false);

		return arr;
	};

	this.nextMonth = function() {
		self.currentMoment.add(1, 'months');
		$scope.year = self.currentMoment.year();
	};

	this.prevMonth = function() {
		self.currentMoment.subtract(1, 'months');
		$scope.year = self.currentMoment.year();
	};
}]);

Stockontrol.factory("$mdDatePicker", ["$mdDialog", function($mdDialog) {
	var datePicker = function(targetEvent, currentDate) {
		if (!angular.isDate(currentDate)) currentDate = Date.now();

		return $mdDialog.show({
			controller: 'DatePickerCtrl',
			controllerAs: 'datepicker',
			templateUrl: "/resources/views/partials/modal-datepicker.html",
			targetEvent: targetEvent,
			locals: {
				currentDate: currentDate
			}
		});
	}

	return datePicker;
}]);

Stockontrol.directive("datePicker", ["$mdDatePicker", "$timeout", function($mdDatePicker, $timeout) {
	return {
		restrict: 'A',
		require: '?ngModel',
		scope: {
			noDatePicker: '='
		},
		link: function(scope, element, attrs, ngModel) {
			if (ngModel) {
				angular.element(element).on("click", function(ev) {
					ev.preventDefault();
					$mdDatePicker(ev, ngModel.$modelValue).then(function(selectedDate) {
						$timeout(function() {
							ngModel.$setViewValue(moment(selectedDate).format("DD/MM/YYYY"));
							ngModel.$render();
						});
					});
				});
			}
		}
	}
}]);
