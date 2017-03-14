(function() {

    var SearchResultsController =  function(movieDal) {
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

    };

    angular.module('movieApp').controller('searchResultsController', ['movieDal', SearchResultsController]);

}());