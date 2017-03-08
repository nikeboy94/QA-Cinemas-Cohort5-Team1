(function() {

    var DeleteMovieController =  function($state, movieDal) {
        var vm = this;

        vm.deleteMovie = function(movieToDelete) {
            console.log("This is the value of movie to delete " + movieToDelete);
            console.log(movieToDelete);
            var movieToJson = JSON.stringify(movieToDelete);
            console.log(movieToJson);
            movieDal.deleteMovie(movieToDelete).then(function (results) {
                vm.movieAddMessage  = results;
                $state.go('getmovie');
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('cinema').controller('deleteMovieController', ['$state','movieDal',DeleteMovieController]);
}());