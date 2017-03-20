(function() {

    var GetByTitleController =  function($rootScope, $state, movieDal) {
        var vm = this;
        
        var savedTitle = $rootScope.globals.movieTitle;

        function getByTitle() {
            movieDal.getMoviesByTitle($rootScope.globals.movieTitle).then(function (results) {
                vm.movies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        getByTitle();


        vm.updateRating = function(movieToUpdateId, movieToUpdate) {

            movieDal.updateRating(movieToUpdateId, movieToUpdate).then(function (results) {
                vm.movieAddMessage  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
        
        var newCount = 0;
        var newRating = 0;
        vm.newMovie = {
            rating: newRating,
            count: newCount
        };

        vm.calculate = function(userRating) {
           var x = vm.movies[0].rating;
           var y = vm.movies[0].count;
           var z = (x * y);
           newCount = y + 1;
           newRating = ((z + userRating) / newCount );
           vm.newMovie.rating = newRating;
           vm.newMovie.count = newCount;
           vm.updateRating(vm.movies[0].movieId, vm.newMovie);
           movieDal.movieTitle = savedTitle;
           movieDal.getMoviesByTitle($rootScope.globals.movieTitle).then(function (results) {
                vm.movies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };


    };

    angular.module('movieApp').controller('getByTitleController', ['$rootScope', '$state', 'movieDal', GetByTitleController]);

}());