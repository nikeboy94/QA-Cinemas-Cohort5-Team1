(function() {

    var SearchResultsController =  function($rootScope,$state, movieDal) {
        var vm = this;

        function searchMovies() {

            movieDal.searchMovie($rootScope.globals.searchedTitle).then(function (results) {
                vm.movies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        searchMovies();

        vm.goToMovie = function(theTitle) {
            $rootScope.globals.movieTitle = theTitle;
            $state.go('getmoviebytitle');
        };

    };

    angular.module('movieApp').controller('searchResultsController', ['$rootScope','$state', 'movieDal', SearchResultsController]);

}());