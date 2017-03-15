(function() {

    var AddTicketController = function(ticketDal, Auth, $state, movieDal, showingDal ) {
        var vm = this;

        vm.ticketArray = [];

        this.addTicket = function(ticket, qty) {
            Auth.setTicketQuantity(qty);
           // $state.go('dashboard');
            var myId = '1';
            ticketDal.getPrice(myId).then(function(results) {
                ticket.price = results.price;
            }), function(error) {
                vm.error = true;
                vm.errorMessage = error;
            };

            ticket.orderId=new Date().getTime()
            for (var i = 0; i < qty; i++) {
                vm.ticketArray.push(ticket);
            }
            console.log(vm.ticketArray);
            Auth.addOrder(vm.ticketArray);

        };

        vm.init = function(){

            movieDal.getMovies().then(function(results){
                vm.movieList=results;
            }), function(error) {
                vm.error = true;
                vm.errorMessage = error;
            }
        };
        vm.init();

        vm.getShowingsById = function(movieId) {
            showingDal.getShowingByMovie(movieId).then(function(results){
                vm.movieShowingList=results;
            }), function(error){
                alert(error);
                vm.error = true;
                vm.errorMessage = error;
            }
        };

        /*
        vm.addTicket = function(ticketToAdd) {

            ticketDal.addTicket(ticketToAdd).then(function (results) {
                vm.ticketAddMessage = results;
            }, function (error) {
                vm.error=true;
                vm.errorMessage = error;

            });


        }; */
    };

    angular.module('movieApp').controller('addTicketController', ['ticketDal', 'Auth', '$state', 'movieDal', 'showingDal', AddTicketController]);

}());