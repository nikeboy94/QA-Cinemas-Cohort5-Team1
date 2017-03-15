"use strict";

(function () {

    angular.module("movieApp").service("dal", ["$http", "$q", "$log", Dal]);

    function Dal ($http, $q, $log) {
        this.http = (function serviceCaller() {
            return {

                GET: function (apiPath) {
                    var deferred = $q.defer();
                    $http.get(apiPath).then(function (result) {
                        deferred.resolve(result.data);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                },

                POST_MSK: function () {
                    var deferred = $q.defer();
                    $http(
                        {
                            method: "post",
                            url: "https://pi-test.sagepay.com/api/v1/merchant-session-keys",
                            headers: {
                                "Accept": "application/json, text/plain, */*",
                                "Content-Type": "application/json;charset=UTF-8",
                                "Authorization": "Basic aEpZeHN3N0hMYmo0MGNCOHVkRVM4Q0RSRkxodUo4RzU0TzZyRHBVWHZFNmhZRHJyaWE6bzJpSFNyRnliWU1acG1XT1FNdWhzWFA1MlY0ZkJ0cHVTRHNocktEU1dzQlkxT2lONmh3ZDlLYjEyejRqNVVzNXU="
                            },
                            data: {
                                vendorName: "sandbox"
                            }
                        }
                    ).then(function (results) {
                        deferred.resolve(results.data);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                },

                POST_CARD: function (merchantSessionKey, card) {
                    var authorisation = "Bearer " + merchantSessionKey;
                    var deferred = $q.defer();
                    $http(
                        {
                            method: "post",
                            url: "https://pi-test.sagepay.com/api/v1/card-identifiers",
                            headers: {
                                "Accept": "application/json, text/plain, */*",
                                "Content-Type": "application/json;charset=UTF-8",
                                "Authorization": authorisation
                            },
                            data: {
                                cardDetails: {
                                    cardholderName: card.cardholderName,
                                    cardNumber: card.cardNumber,
                                    expiryDate: card.expiryDate,
                                    securityCode: card.securityCode
                                }
                            }
                        }
                    ).then(function (results) {
                        deferred.resolve(results.data);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                },

                POST_PAYMENT: function (merchantSessionKey, cardIdentifier, amount) {
                    var authorisation = "Bearer " + merchantSessionKey;
                    var deferred = $q.defer();
                    $http(
                        {
                            method: "post",
                            url: "https://pi-test.sagepay.com/api/v1/transactions",
                            headers: {
                                "Accept": "application/json, text/plain, */*",
                                "Content-Type": "application/json;charset=UTF-8",
                                "Authorization": "Basic aEpZeHN3N0hMYmo0MGNCOHVkRVM4Q0RSRkxodUo4RzU0TzZyRHBVWHZFNmhZRHJyaWE6bzJpSFNyRnliWU1acG1XT1FNdWhzWFA1MlY0ZkJ0cHVTRHNocktEU1dzQlkxT2lONmh3ZDlLYjEyejRqNVVzNXU="
                            },
                            data: {
                                transactionType: "Payment",
                                paymentMethod: {
                                    card: {
                                        merchantSessionKey: merchantSessionKey,
                                        cardIdentifier: cardIdentifier,
                                        save: "false"
                                    }
                                },
                                vendorTxCode: "4563456345634564562327289929dfg",
                                amount: amount,
                                currency: "GBP",
                                description: "demo transaction",
                                customerFirstName: "John",
                                customerLastName: "Smith",
                                billingAddress: {
                                    address1: "88",
                                    city: "Glasgow",
                                    postalCode: "412",
                                    country: "GB"
                                }
                            }
                        }
                    ).then(function (results) {
                        deferred.resolve(results.data);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                },

                POST: function (apiPath, itemToSave) {
                    var deferred = $q.defer();
                    $http(
                        {
                            method: "post",
                            url: apiPath,
                            headers: {
                                "Accept": "application/json, text/plain, */*",
                                "Content-Type": "application/json;charset=UTF-8"
                            },
                            data: JSON.stringify(itemToSave)
                        }
                    ).then(function (results) {
                        deferred.resolve(results.data);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                },

                POST_IMAGE: function (apiPath, itemToSave) {
                    var deferred = $q.defer();
                    $http(
                        {
                            method: "post",
                            url: apiPath,
                            headers: {
                                "Accept": "application/json, text/plain, */*",
                                "Content-Type": "multipart/form-data"
                            },
                            data: {
                                image: itemToSave
                            },
                            transformRequest: function (data, headersGetter) {
                                var formData = new FormData();
                                angular.forEach(data, function (value, key) {
                                    formData.append(key, value);
                                });

                                var headers = headersGetter();
                                delete headers['Content-Type'];

                                return formData;
                            }
                        }
                    ).then(function (results) {
                        deferred.resolve(results.data);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                },

                PUT: function (apiPath, itemToSave) {
                    var deferred = $q.defer();
                    $http(
                        {
                            method: "put",
                            url: apiPath,
                            headers: {
                                "Accept": "application/json, text/plain, */*",
                                "Content-Type": "application/json;charset=UTF-8"
                            },
                            data: JSON.stringify(itemToSave)
                        }
                    ).then(function (results) {
                        deferred.resolve(results);
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                },

                DELETE: function (apiPath) {
                    var deferred = $q.defer();
                    $http.delete(apiPath).then(function () {
                        deferred.resolve();
                    }, function (e) {
                        deferred.reject(e);
                    });
                    return deferred.promise;
                }
            }
        })();
        $log.debug("DAL Instantiated");
    }
}());