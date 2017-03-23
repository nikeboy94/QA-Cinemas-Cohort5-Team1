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
				vm.showingsStandard = [];
				vm.showingsDeluxe = [];
				filterShowings();
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});

			vm.today = new Date();
			vm.tomorrow = new Date(vm.today.getTime()+ 24*60*60*1000);
			vm.day3 = new Date(vm.today.getTime()+ 24*60*60*1000*2);
            vm.day4 = new Date(vm.today.getTime()+ 24*60*60*1000*3);
            vm.day5 = new Date(vm.today.getTime()+ 24*60*60*1000*4);
            vm.day6 = new Date(vm.today.getTime()+ 24*60*60*1000*5);
            vm.day7 = new Date(vm.today.getTime()+ 24*60*60*1000*6);

            vm.replace = function(showing) {
                $('#modalShowing').text(showing.showingId);
                $('#modalTime').text(vm.convertDate(showing.dateTime));
                $('#hiddenShowingId').val(showing.showingId);
                $('#hiddenShowingId').trigger('input');
                Auth.setShowingId(showing.showingId);
                Auth.setShowing(showing);
                vm.updateGlobalPrices(showing.showingId);
            };


		};


		vm.convertDate = function(dateString) {
            var d = new Date(dateString);

            var daysOfWeek = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
            var monthsOfYear = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

            var printDate = "";
            printDate += daysOfWeek[d.getDay()] + " ";
            printDate += d.getDate() + " ";
            printDate += monthsOfYear[d.getMonth()] + " ";
            printDate += d.getHours() + ":";

            printDate += d.getMinutes()<10 ? '0' : '';
            printDate += d.getMinutes() + " ";

            return printDate;
		};


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
                ticketDal.getAvailableTickets(showingId).then(function(result) {
                    vm.availableTickets = result.availableTickets;
                });
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
        };
        
        var filterShowings = function () {
        	angular.forEach(vm.showings, function(value, key) {
        		if (value.screen.screenId == 3) {
        			vm.showingsDeluxe.push(value);
        		} else {
        			vm.showingsStandard.push(value);
        		}
        	})
        }


	};
	angular.module("movieApp").controller("getShowingByMovieController", ["Auth", "showingDal", "ticketDal", GetShowingByMovieController]);
}());