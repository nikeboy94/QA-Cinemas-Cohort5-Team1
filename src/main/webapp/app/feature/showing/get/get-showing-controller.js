(function() {
	
	var GetShowingController = function(showingDal) {
		
		var vm = this;
		
		function init() {
			showingDal.getAllShowings().then(function(results) {
				vm.showings = results;
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});
		}
		init();
	};
	angular.module("movieApp").controller("getShowingController", ["showingDal", GetShowingController]);
}());