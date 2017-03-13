(function() {

    var AddMoviePosterController =  function($state, movieDal) {
        var vm = this;

        vm.uploadMoviePoster = function(movieToAdd) {
            movieDal.saveMoviePoster(movieToAdd).then(function (results) {
                vm.movieAddMessage  = results;
                $state.go('getmovie');
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('movieApp').controller('addMoviePosterController', ['$state','movieDal',AddMoviePosterController]);
}());