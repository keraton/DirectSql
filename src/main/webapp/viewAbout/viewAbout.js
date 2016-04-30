'use strict';

angular.module('myApp.viewAbout', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/help', {
    templateUrl: 'viewAbout/viewAbout.html',
    controller: 'AboutCtrl'
  });
}])

.controller('AboutCtrl', [function() {
}]);