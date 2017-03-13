"use strict";

(function(){
    angular.module("movieApp").service("seatDAL", ["dal", SeatDAL]);

    function SeatDAL (dal){
        this.getSeat = function(){
            return dal.http.GET("rest/seat/json");
        };

        this.saveSeat = function(seatToSave){
            return dal.http.POST("rest/seat/json", seatToSave);
        };


        this.deleteSeat = function(seatToDelete){
            return dal.http.DELETE("rest/seat/json/", seatToDelete);
        };
    }
}());