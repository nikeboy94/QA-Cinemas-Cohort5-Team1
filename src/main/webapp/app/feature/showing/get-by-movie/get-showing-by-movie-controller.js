(function() {
	
	var GetShowingByMovieController = function(showingDal) {
		
		var vm = this;
		
		vm.getByMovie = function(movieId) {
			showingDal.getShowingByMovie(movieId).then(function(results) {
				vm.showings = results;
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});
		}
	};
	angular.module("movieApp").controller("getShowingByMovieController", ["showingDal", GetShowingByMovieController]);
}());