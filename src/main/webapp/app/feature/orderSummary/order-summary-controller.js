(function() {
	
	var OrderSummaryController = function($rootScope, ticketDal) {
		
		var vm = this;
		
		vm.getByOrderId = function(ticketOrderId) {
			ticketDal.getTicketByOrderId(ticketOrderId).then(function(results) {
				vm.tickets = results;
				sumTotal();
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});
		};
		
		vm.getByUser = function(email) {
			ticketDal.getTickets(email).then(function(results) {
				vm.tickets = results;
				sumTotal();
				getOrders();
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});
		};
		
		var getUserOrders = function() {
			var email = $rootScope.globals.currentUser.email;
			vm.getByUser(email);
		};
		
		var sumTotal = function(orderId) {
			var total = 0;
			angular.forEach(vm.tickets, function(value, key) {
				if (value.orderId == orderId) {
					total += value.price;
				}
			});
			return total;
		};
		
		var getOrders = function() {
			vm.orderInfo = [];
			var orderIds = [];
			angular.forEach(vm.tickets, function(value, key) {
				if (containsObject(value.orderId, orderIds) == false) {
					var price = sumTotal(value.orderId);
					console.log(price);
					vm.orderInfo.push({"orderId": value.orderId, "title": value.showing.movie.title, 
						"dateTime": value.showing.dateTime, "price": price});
					orderIds.push(value.orderId);
				};
			});
		}
		
		var containsObject = function(obj, list) {
		    var i;
		    for (i = 0; i < list.length; i++) {
		        if (list[i] === obj) {
		            return true;
		        }
		    }
		    return false;
		}
		
		getUserOrders();
	};
	
	angular.module("movieApp").controller("orderSummaryController", ["$rootScope", "ticketDal", OrderSummaryController])
}());