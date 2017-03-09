(function() {
	
	var AddShowingController = function($state, showingDal) {
		var vm = this;
		
		vm.addShowing = function(showingToAdd) {
			console.log("This is the value of showing to add " + showingToAdd);
			console.log(showingToAdd);
			
			var showingToJson = JSON.stringify(showingToAdd);
			console.log(showingToJson);
			
			showingDal.saveShowing(showingToAdd).then(function(results) {
				vm.showingAddMessage = results;
				$state.go("getshowings");
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});
		};
	};
	angular.module("movieApp").controller("addShowingController", ["$state", "showingDal", AddShowingController]);
}());