"use strict";

(function(){
    angular.module("movieApp").service("userDAL", ["dal", UserDAL]);

    function UserDAL (dal){

        this.getUsers = function(){
            return dal.http.GET("rest/user/json");
        };

        this.authAttempt = function(email, password) {
            return dal.http.GET("rest/user/json/login/" + email + "/" + password);
        }

        this.saveUser = function(userToSave){
            return dal.http.POST("rest/user/json", userToSave);
        };

        this.updateUser = function(userToUpdate){
            return dal.http.PUT("rest/user/json/" + userToUpdate.email, userToUpdate);
        };

        this.deleteUser = function(userToDelete){
            return dal.http.DELETE("rest/user/json/", userToDelete);
        };
    }
}());