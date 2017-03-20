(function() {
	
	var GetShowingByMovieController = function(Auth, showingDal) {
		
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

            vm.replace = function(showing) {
                $('#modalShowing').text(showing.showingId);
                $('#modalTime').text(showing.dateTime);
                $('#hiddenShowingId').val(showing.showingId);
                $('#hiddenShowingId').trigger('input');
                Auth.setShowingId(showing.showingId);
            };


		}


	};
	angular.module("movieApp").controller("getShowingByMovieController", ["Auth", "showingDal", GetShowingByMovieController]);
}());