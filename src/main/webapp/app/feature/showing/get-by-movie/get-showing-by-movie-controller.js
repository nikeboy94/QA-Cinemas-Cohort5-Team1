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

			vm.today = new Date();
			vm.tomorrow = new Date(new Date().getTime()+ 24*60*60*1000);


		}
	};
	angular.module("movieApp").controller("getShowingByMovieController", ["showingDal", GetShowingByMovieController]);
}());