(function() {

    var SearchResultsController =  function($state, movieDal) {
        var vm = this;

        function searchMovies() {

            movieDal.searchMovie(movieDal.movieTitle).then(function (results) {
                vm.movies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        searchMovies();

        vm.goToMovie = function(theTitle) {
            movieDal.movieTitle = theTitle;
            $state.go('getmoviebytitle');
        };

    };

    angular.module('movieApp').controller('searchResultsController', ['$state', 'movieDal', SearchResultsController]);

}());