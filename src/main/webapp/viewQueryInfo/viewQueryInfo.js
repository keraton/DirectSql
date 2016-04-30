'use strict';

angular.module('myApp.viewQueryInfo', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/queryInfo', {
    templateUrl: 'viewQueryInfo/viewQueryInfo.html',
    controller: 'QueryInfoCtrl'
  });
}])

.controller('QueryInfoCtrl', ['$scope','$http',"$routeParams", "$location",
                         function($scope, $http, $routeParams, $location) {

   var getQueryInfo = function (response) {
       if (response.data != null) {
           $scope.queryInfo = response.data;
       }
    };

   var getRowQueries = function (response) {
      if (response.data != null) {
          $scope.queryCollection = response.data;
      }
   };

   // Select Query by location
   $scope.selectQueryByLocation = function(selectedQuery) {
       $location.path("queryInfo").search({"selectedQuery" : selectedQuery});
   }

   // Go to Query
   $scope.goToQuery = function () {
       $location.path("queries").search({"selectedQuery" : $scope.selectedQuery});
   }

    // init
    $scope.queryCollection = [];
    $http.get("/list")
                        .then(getRowQueries);

    if ($routeParams.selectedQuery) {
        $scope.selectedQuery = $routeParams.selectedQuery;
        $http.get("/search?name=" + $routeParams.selectedQuery)
            .then(getQueryInfo);

    }
    else {
        $location.path("queries");
    }
}]);