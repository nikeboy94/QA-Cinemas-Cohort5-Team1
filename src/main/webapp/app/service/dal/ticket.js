"use strict";

(function () {

    angular.module("movieApp").service("ticketDal", ["dal", ticketDal]);

    function ticketDal (dal) {

        this.addTicket = function (ticketToAdd) {
            return dal.http.POST("rest/ticket/json", ticketToAdd);
        };

        this.getTickets = function (userEmail) {
            return dal.http.GET("rest/ticket/json/" + userEmail);
        };

        this.updateTicket = function (ticketIdToUpdate, updatedTicket) {
            return dal.http.PUT("rest/ticket/json/" + ticketIdToUpdate, updatedTicket);
        };

        this.getAvailableTickets = function (showingId) {
            return dal.http.GET("rest/ticket/json/tickets/" + showingId);
        };
      
        this.deleteTicket = function (ticketIdToDelete) {
            return dal.http.DELETE("rest/ticket/json/" + ticketIdToDelete);
        };

        this.getPrice = function(showingId, ticketType) {
            return dal.http.GET("rest/ticket/json/price/" + showingId +"/" + ticketType);
        };

        this.addOrder = function(orderToAdd) {
            return dal.http.POST("rest/ticket/json/order", orderToAdd);
        };

        this.getBookedSeatsForShowing = function(showingId) {
            return dal.http.GET("rest/ticket/json/seats/" + showingId);
        };
        
        this.getTicketByOrderId = function(orderId) {
        	return dal.http.GET("rest/ticket/json/order/" + orderId);
        };

    }
}());
