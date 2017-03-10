(function() {
	
	var UpdateShowingController = function($state, showingDal) {
		
		var vm = this;
		
		vm.updateShowing = function(showingToUpdate) {
			console.log("This is the value of showing to update " + showingToUpdate);
			var showingToJson = JSON.stringify(showingToUpdate);
			console.log(showingToJson);
			
			showingDal.updateShowing(showingToUpdate).then(function(results) {
				vm.showingAddMessage = results;
				$state.go("getshowings");
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});
		};
	};
	angular.module("movieApp").controller("updateShowingController", ["$state", "showingDal", UpdateShowingController]);
}());