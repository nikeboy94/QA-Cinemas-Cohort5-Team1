(function() {
	
	var DeleteShowingController = function($state, showingDal) {
		
		var vm = this;
		
		vm.deleteShowing = function(showingToDelete) {
			console.log("This is the value of showing to delete " + showingToDelete);
			var showingToJson = JSON.stringify(showingToDelete);
			console.log(showingToJson);
			
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