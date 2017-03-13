(function() {

    var GetByTitleController =  function(movieDal) {
        var vm = this;

        function getByTitle() {

            //console.log("This is the value of movie to delete " + title);
            //console.log(title);


            movieDal.getMoviesByTitle(movieDal.movieTitle).then(function (results) {
                vm.movies  = results;
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }
        getByTitle();
    };

    angular.module('movieApp').controller('getByTitleController', ['movieDal', GetByTitleController]);

}());