'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.viewQueries',
  'myApp.viewQueryInfo',
  'myApp.viewDetail',
  'myApp.viewAbout',
  'myApp.version'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/queries'});
}]);
