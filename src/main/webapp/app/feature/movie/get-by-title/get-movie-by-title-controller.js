(function() {

    var GetByTitleController =  function(movieDal) {
        var vm = this;

        vm.getByTitle = function(title) {
            console.log("This is the value of movie to delete " + title);
            console.log(title);

            movieDal.getMoviesByTitle(title).then(function (results) {
                vm.movies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }
    };

    angular.module('movieApp').controller('getByTitleController', ['movieDal', GetByTitleController]);
}());