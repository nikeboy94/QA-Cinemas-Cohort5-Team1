(function() {

    var GetMovieController =  function($rootScope, $state, movieDal, Auth) {
        var vm = this;


        function init() {

            movieDal.getCurrentMovies().then(function (results) {
                vm.currentMovies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });

            movieDal.getComingSoonMovies().then(function (results) {
                vm.comingSoonMovies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });

        }
        init();

        vm.replace = function(movie) {
            $('#modalImg').attr('src', movie.posterUrl);
            $('#modalTitle').text(movie.title);
            $('#modalDescription').text(movie.description);
            $rootScope.globals.movieTitle = movie.title;
            Auth.setCredentials();
            movieDal.movieTitle = movie.title;
            movieDal.movieId = movie.movieId;
        };


        vm.goToMovie = function() {
            $('#myModal').modal('toggle');
            $state.go('getmoviebytitle');
        };

    };

    angular.module('movieApp').controller('getMovieController', ['$rootScope','$state','movieDal', 'Auth', GetMovieController]);
}());