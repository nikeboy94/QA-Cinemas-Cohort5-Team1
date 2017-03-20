(function() {

    var AddMovieController =  function($state, $scope, movieDal, dal) {
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

        vm.addMoviePoster = function() {
            var movieToAdd = document.getElementById('file').files[0];
            document.getElementById('posterUrl').value = "C:\\Users\\Administrator\\img\\" + movieToAdd.name;
            document.getElementById('posterUrl').disabled = 'disabled';
            movieDal.saveMoviePoster(movieToAdd).then(function (results) {
                vm.movieAddMessage  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('movieApp').controller('addMovieController', ['$state', '$scope', 'movieDal', 'dal', AddMovieController]);
}());


