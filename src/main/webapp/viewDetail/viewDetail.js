'use strict';

angular.module('myApp.viewDetail', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/detail', {
    templateUrl: 'viewDetail/viewDetail.html',
    controller: 'DetailCtrl'
  });
}])

.controller('DetailCtrl', ['$scope','$http',"$routeParams", "$location",
                         function($scope, $http, $routeParams, $location) {

    var detail = { "selectedQuery" : $routeParams.selectedQuery, "map" : JSON.parse($routeParams.map)}
    $scope.selectedQuery = $routeParams.selectedQuery;
    $scope.map = $routeParams.map;

    $scope.goToQuery = function () {
        $location.path("queries").search({"selectedQuery" : $scope.selectedQuery});
    };

    $http.post("/detail" , detail)
        .then(function (response) {
            $scope.data = response.data;
            $scope.keys = Object.keys(response.data);
        });
}]);