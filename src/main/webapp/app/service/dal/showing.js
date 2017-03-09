"use strict";

(function() {
	
	angular.module("movieApp").service("showingDal", ["dal", ShowingDal]);
	
	function ShowingDal(dal) {
		
		this.getAllShowings = function() {
			return dal.http.GET("rest/showing/json");
		};
		this.saveShowing = function(showingToAdd) {
			return dal.http.POST("rest/showing/json", showingToAdd);
		};
	}
}());