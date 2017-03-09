"use strict";

(function () {

    angular.module("movieApp").service("ticketDal", ["dal", ticketDal]);

    function ticketDal (dal) {

        this.getTickets = function (userEmail) {
            return dal.http.GET("rest/ticket/json/" + userEmail);
        };

    }
}());
