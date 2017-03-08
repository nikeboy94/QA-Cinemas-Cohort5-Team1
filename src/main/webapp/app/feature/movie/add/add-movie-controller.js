(function() {

    var AddMovieController =  function($state, movieDal) {
        var vm = this;

        vm.addMovie = function(movieToAdd) {
            movieDal.saveMovie(movieToAdd).then(function (results) {
                vm.movieAddMessage  = results;
                $state.go('getmovie');
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('movieApp').controller('addMovieController', ['$state','movieDal',AddMovieController]);
}());