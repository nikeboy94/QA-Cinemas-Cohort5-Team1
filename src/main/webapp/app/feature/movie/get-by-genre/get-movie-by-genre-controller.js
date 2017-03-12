(function () {

    var GetMovieByGenreController = function(movieDal) {

        var vm = this;

        vm.searchGenre = function(genre) {
            movieDal.getMoviesByGenre(genre).then(function (results) {
                console.log(results);
                vm.movies = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('movieApp').controller('getMovieByGenreController', ['movieDal', GetMovieByGenreController])

}());
