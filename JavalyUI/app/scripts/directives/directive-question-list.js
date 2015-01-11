/**
 * Created by clarencenpy on 9/1/15.
 */
'use strict'

angular
  .module('JavalyApp')
  .directive('questionList', function() {
    return {
      restrict: 'EA',
      replace: true,
      templateUrl: "views/directive-question-list.html",
      controller: function($scope, $http, ENV) {
        $http.get(ENV.apiEndpoint + 'question/all').success(function (data) {
          $scope.questions = data
        })
      }
    }
  })
