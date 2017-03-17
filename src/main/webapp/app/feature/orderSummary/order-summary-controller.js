(function() {
	
	var OrderSummaryController = function($rootScope, Auth, $state, ticketDal) {
		
		var vm = this;
		
		var getByOrder = function() {
			var order = $rootScope.globals.currentUser.order[0];
			var orderId = (order.orderId).toString();
			//var orderId = $rootScope.globals.currentUser.order[0].orderId;
			console.log(orderId);
			ticketDal.getTicketByOrderId(orderId).then(function(results) {
				vm.tickets = results;
				sumTotal();
				getOrders();
			}, function(error) {
				vm.error = true;
				vm.errorMessage = error;
			});
			/*console.log("in function");
			//vm.tickets = Auth.authService.getOrder();
			//vm.tickets = order;
			console.log(JSON.stringify(vm.tickets));
			sumTotal();
			getOrders();*/
			
		};
		
		var getByUser = function(email) {
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
			getByUser(email);
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
		
		var init = function() {
			if ($state.includes('ordersummary')) {
				console.log("order summary");
				getByOrder();
			} else if ($state.includes('allorderssummary')) {
				console.log("user orders summary");
				getUserOrders();
			};
		}
		
		init();
	};
	
	angular.module("movieApp").controller("orderSummaryController", ["$rootScope", "Auth", "$state", "ticketDal", OrderSummaryController])
}());