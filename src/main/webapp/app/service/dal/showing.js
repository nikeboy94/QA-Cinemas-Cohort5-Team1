"use strict";

(function() {
	
	angular.module("movieApp").service("showingDal", ["dal", ShowingDal]);
	
	function ShowingDal(dal) {
		
		this.getAllShowings = function() {
			return dal.http.GET("rest/showing/json");
		};
		this.getShowingByMovie = function(movieId) {
			return dal.http.GET("rest/showing/json/movie/" + movieId);
		};
		this.saveShowing = function(showingToAdd) {
			return dal.http.POST("rest/showing/json", showingToAdd);
		};
		this.removeShowing = function(showingToDelete) {
			return dal.http.DELETE("rest/showing/json/" + showingToDelete);
		};
		this.updateShowing = function(showingToUpdate) {
			return dal.http.PUT("rest/showing/json/" + showingToUpdate.showingId, showingToUpdate);
		};
	}
}());