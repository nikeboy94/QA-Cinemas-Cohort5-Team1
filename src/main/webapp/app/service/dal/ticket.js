"use strict";

(function () {

    angular.module("movieApp").service("ticketDal", ["dal", ticketDal]);

    function ticketDal (dal) {

        this.getTickets = function (userEmail) {
            return dal.http.GET("rest/ticket/json/" + userEmail);
        };

        this.updateTicket = function (ticketIdToUpdate, updatedTicket) {
            return dal.http.PUT("rest/ticket/json/" + ticketIdToUpdate, updatedTicket);
        };

        this.deleteTicket = function (ticketIdToDelete) {
            return dal.http.DELETE("rest/ticket/json/" + ticketIdToDelete);
        };

    }
}());
