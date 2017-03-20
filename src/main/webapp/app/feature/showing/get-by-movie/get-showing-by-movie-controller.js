(function() {
	
	var GetShowingByMovieController = function(Auth, showingDal, ticketDal) {
		
		var vm = this;
        vm.globalAdultPrice = 0;
        vm.globalChildPrice = 0;
        vm.tempAdultTickets = 0;
        vm.tempChildTickets = 0;
        vm.totalPrice = 0;
		
		vm.getByMovie = function(movieId) {
			showingDal.getShowingByMovie(movieId).then(function(results) {
				vm.showings = results;
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});

			vm.today = new Date();
			vm.tomorrow = new Date(new Date().getTime()+ 24*60*60*1000);

            vm.replace = function(showing) {
                $('#modalShowing').text(showing.showingId);
                $('#modalTime').text(showing.dateTime);
                $('#hiddenShowingId').val(showing.showingId);
                $('#hiddenShowingId').trigger('input');
                Auth.setShowingId(showing.showingId);
                vm.updateGlobalPrices(showing.showingId);
            };


		}


        vm.updateAdultQty = function (qty) {
            if (qty < 0) {
                return;
            }

            vm.tempAdultTickets = qty;
            vm.updatePrice();
        };

        vm.updateChildQty = function (qty) {
            if (qty < 0) {
                return;
            }

            vm.tempChildTickets = qty;
            vm.updatePrice();
        };

        vm.updatePrice = function () {
            vm.totalPrice = parseFloat(vm.tempAdultTickets * vm.globalAdultPrice) + parseFloat(vm.tempChildTickets * vm.globalChildPrice);
        };


        vm.updateGlobalPrices = function (showingId) {
            ticketDal.getPrice(showingId, 'ADULT').then(function (result) {
                vm.globalAdultPrice = result.price;
                vm.updatePrice();
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });

            ticketDal.getPrice(showingId, 'CHILD').then(function (result) {
                vm.globalChildPrice = result.price;
                vm.updatePrice();
            }, function (error) {
                vm.error = true;
                vm.errorMessage = error;
            });
        }


	};
	angular.module("movieApp").controller("getShowingByMovieController", ["Auth", "showingDal", "ticketDal", GetShowingByMovieController]);
}());