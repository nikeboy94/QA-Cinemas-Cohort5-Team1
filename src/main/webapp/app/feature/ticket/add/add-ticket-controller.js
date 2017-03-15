(function() {

    var AddTicketController = function(ticketDal, Auth, $state, movieDal, showingDal ) {
        var vm = this;

        this.ticketArray = [];

        this.addTicket = function(ticket, qty) {
            Auth.setTicketQuantity(qty);
           // $state.go('dashboard');

            ticket.orderId=new Date().getTime()
            for (var i = 0; i < qty; i++) {

                console.log(i);
                this.ticketArray.push(ticket);
                alert(JSON.stringify(vm.ticketArray));
            }

        };

        vm.init = function(){

            movieDal.getMovies().then(function(results){
                vm.movieList=results;
            }), function(error){
                vm.error = true;
                vm.errorMessage = error;
            }


        };
        vm.init();

        vm.getShowingsById = function(movieId) {
            alert(JSON.stringify(movieId));
            console.log(movieId);
            showingDal.getShowingByMovie(movieId).then(function(results){
                vm.movieShowingList=results;
                alert(JSON.stringify(vm.movieShowingList));
            }), function(error){
                alert(error);
                vm.error = true;
                vm.errorMessage = error;
            }
        };

        vm.getPriceByShowingId = function(){
            alert(JSON.stringify(vm.ticketArray));
            alert("text");
        };



        vm.addTicket = function(ticketToAdd) {

            ticketDal.addTicket(ticketToAdd).then(function (results) {
                vm.ticketAddMessage = results;
            }, function (error) {
                vm.error=true;
                vm.errorMessage = error;

            });


        };
    };

    angular.module('movieApp').controller('addTicketController', ['ticketDal', 'Auth', '$state', 'movieDal', 'showingDal', AddTicketController]);

}());