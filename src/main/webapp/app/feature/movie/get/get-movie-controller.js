(function() {

    var GetMovieController =  function(movieDal) {
        var vm = this;

        function init() {
            movieDal.getMovies().then(function (results) {
                vm.movies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        init();

        vm.replace = function(movie) {
            console.log("omar pls");
            alert("omar pls");
            $('#modalImg').attr('src', movie.title);
        };

    };

    angular.module('movieApp').controller('getMovieController', ['movieDal', GetMovieController]);
}());