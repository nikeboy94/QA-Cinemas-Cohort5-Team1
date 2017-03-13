(function() {

    var DeleteMovieController =  function($state, movieDal) {
        var vm = this;

        vm.deleteMovie = function(movieToDeleteId) {
            movieDal.deleteMovie(movieToDeleteId).then(function (results) {
                vm.movieAddMessage  = results;
                $state.go('getmovie');
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('movieApp').controller('deleteMovieController', ['$state','movieDal',DeleteMovieController]);
}());