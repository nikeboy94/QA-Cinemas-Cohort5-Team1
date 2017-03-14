(function() {

    var GetByTitleController =  function(movieDal) {
        var vm = this;

        function getByTitle() {
            movieDal.getMoviesByTitle(movieDal.movieTitle).then(function (results) {
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

                $state.go('getmovie');
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };


    };

    angular.module('movieApp').controller('getByTitleController', ['movieDal', GetByTitleController]);

}());