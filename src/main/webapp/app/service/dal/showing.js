"use strict";

(function() {
	
	angular.module("movieApp").service("showingDal", ["dal", ShowingDal]);
	
	function ShowingDal(dal) {
		
		this.getAllShowings = function() {
			return dal.http.GET("rest/showing/json");
		};
	}
}());