/**
 * Created by clarencenpy on 2/1/15.
 */

angular
  .module('JavalyApp')
  .directive('questionResult', function() {
    return {
      restrict: 'EA',
      replace: true,
      templateUrl: "scripts/components/question-panel/question-result.html",
      controller: function($scope, $http) {
        $http.get('http://localhost:9000/fakeresult').success(function(data) {
          $scope.results = data;
        })
      }
    }
  })
