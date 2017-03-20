(function() {
	
	var AddShowingController = function($state, $scope, showingDal, screenDal, movieDal) {
		var vm = this;
		
		vm.addShowing = function(screen, movie, dateTime) {
			var showingToAdd = {};
			showingToAdd.screen = screen;
			showingToAdd.movie = movie;
			showingToAdd.dateTime = dateTime;
			alert(JSON.stringify(showingToAdd));
			showingDal.saveShowing(showingToAdd).then(function(results) {
				vm.showingAddMessage = results;
				$state.go("getshowings");
			}, function(error) {
				alert(JSON.stringify(error));
				vm.error = true;
				vm.errorMessage = error;
			});
		};

		vm.init = function() {
			screenDal.getAllScreens().then(function(results) {
				$scope.screens = results;
			}, function(error) {
                vm.error = true;
                vm.errorMessage = error;
			});
			movieDal.getMovies().then(function(results) {
                $scope.movies = results;
            }, function(error) {
                vm.error = true;
                vm.errorMessage = error;
			});
		};

		vm.init();
	};
	angular.module("movieApp").controller("addShowingController", ["$state", "$scope", "showingDal", "screenDal", "movieDal", AddShowingController]);
}());