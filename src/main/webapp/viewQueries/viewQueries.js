'use strict';

angular.module('myApp.viewQueries', ['ngRoute','smart-table'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/queries', {
    templateUrl: 'viewQueries/viewQueries.html',
    controller: 'QueriesCtrl'
  });
}])

.controller('QueriesCtrl', ['$scope', '$http',"$routeParams", "$location",
    function($scope, $http, $routeParams, $location) {

    // Private Methods
    var getRowCollection = function (response) {
       $scope.hasDetail = response.data.hasDetail;
       if (response.data.rows != null && response.data.rows[0] != null) {
           $scope.rowCollection = response.data.rows;
           $scope.columns = Object.keys(response.data.rows[0]);
       }
    };

    var getRowQueries = function (response) {
       if (response.data != null) {
           $scope.queryCollection = response.data;

            if ($routeParams.selectedQuery == null) {
                    $scope.selectQueryByLocation($scope.queryCollection[0]);
            }
       }
    };

    var getQueryFilter = function () {
        return $scope.queryFilter.replace(/%/g,"__PERCENT__");
    }

    var getLimit = function () {
        if ($scope.limit) {
            return $scope.limit;
        }
        else {
            return 100;
        }
    }

    var getOffset = function () {
        if ($scope.offset) {
            return $scope.offset;
        }
        else {
            return 0;
        }
    }

    $scope.selectQuery = function(selectedQuery) {
         $scope.rowCollection = [];
         $scope.selectedQuery = selectedQuery;

         if ($routeParams.queryFilter) {
                 $scope.queryFilter = $routeParams.queryFilter.replace(/__PERCENT__/g,"%");
                 $http.get("/executeFilter?name="+ $scope.selectedQuery +
                                        "&queryFilter="+ getQueryFilter() +
                                        "&limit="+ getLimit() +
                                        "&offset="+ getOffset()
                                        )
                                       .then(getRowCollection);
         }
         else {
               $http.get("/executeFilter?name=" + $scope.selectedQuery +
                                                                            "&limit="+ getLimit() +
                                                                            "&offset="+ getOffset())
                   .then(getRowCollection);
         }
     };

     // Select Query by location
     $scope.selectQueryByLocation = function(selectedQuery) {
          $location.path("queries").search({"selectedQuery" : selectedQuery});
     }

     // Database query filter
     $scope.databaseQueryFilter = function() {
        if ($scope.queryFilter) {
             $location.path("queries").search({"selectedQuery" : $scope.selectedQuery,
                                                "queryFilter" : getQueryFilter()});
         }
         else {
            $location.path("queries").search({"selectedQuery" : $scope.selectedQuery});
         }
     };

     $scope.limitQuery = function() {
        $scope.selectQuery($scope.selectedQuery);
     }

     $scope.goToInfo = function () {
        $location.path("queryInfo").search({"selectedQuery" : $scope.selectedQuery});
     }

     $scope.goToDetail = function (map) {
        return "/#/detail"
                    + "?selectedQuery="  + $scope.selectedQuery
                    + "&map=" + angular.toJson(map);
     }

    // Init
    $scope.queryCollection = [];
    $http.get("/list").then(getRowQueries);

    if ($routeParams.selectedQuery) {
         $scope.selectQuery($routeParams.selectedQuery);
    }

}]);