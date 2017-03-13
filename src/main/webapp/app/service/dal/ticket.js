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

    }
}());
