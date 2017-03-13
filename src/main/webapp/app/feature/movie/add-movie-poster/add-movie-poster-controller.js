(function() {

    var AddMoviePosterController =  function($state, movieDal) {
        var vm = this;

        vm.addMoviePoster = function() {
            var movieToAdd = document.getElementById('file').files[0];
            movieDal.saveMoviePoster(movieToAdd).then(function (results) {
                vm.movieAddMessage  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        };
    };

    angular.module('movieApp').controller('addMoviePosterController', ['$state','movieDal',AddMoviePosterController]);
}());
