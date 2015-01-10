/**
 * Created by clarencenpy on 31/12/14.
 */
'use strict';

angular.module('JavalyApp')
  .controller('sidebarCtrl', function($scope, $timeout, $mdSidenav) {
    $scope.toggleLeft = function() {
      $mdSidenav('left').toggle()
    }
    $scope.toggleRight = function() {
      $mdSidenav('right').toggle()
    }
  })
  .controller('leftCtrl', function($scope, $timeout, $mdSidenav) {
    $scope.close = function() {
      $mdSidenav('left').close()
    }
  })
  .controller('rightCtrl', function($scope, $timeout, $mdSidenav) {
    $scope.close = function() {
      $mdSidenav('right').close()
    }
  })
