(function() {
	
	var DeleteShowingController = function($state, showingDal) {
		
		var vm = this;
		
		vm.deleteShowing = function(showingToDelete) {
			showingDal.removeShowing(showingToDelete).then(function(results) {
				vm.showingAddMessage = results;
				$state.go("getshowings");
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});
		};
	};
	angular.module("movieApp").controller("deleteShowingController", ["$state", "showingDal", DeleteShowingController]);
}());