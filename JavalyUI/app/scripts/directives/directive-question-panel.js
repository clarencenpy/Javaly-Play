/**
 * Created by clarencenpy on 4/1/15.
 */
'use strict'

angular
  .module('JavalyApp')
  .directive('questionPanel', function() {
    return {
      restrict: 'EA',
      replace: true,
      templateUrl: "views/directive-question-panel.html",
      controller: function($scope, $http, $stateParams) {
        $scope.editorOptions = {
          lineWrapping : true,
          lineNumbers: true,
          smartIndent: false,
          indentWithTabs: true,
          theme: 'neat',
          mode: 'text/x-java',
          autofocus: true
        }

        $http.get('http://localhost:9000/question/' + $stateParams.id).success(function(data) {
          $scope.question = data;
        })


        $http.get('http://localhost:9000/fakeresult').success(function(data) {
          $scope.results = data;
        })

        $scope.code = ''

        $scope.submit = function() {
          var formData = {
            code: $scope.code,
            id: $scope.question.id
          }

          $http({
            method: 'POST',
            url: 'http://localhost:9000/question/submit',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
              var str = []
              for(var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]))
              return str.join("&")
            },
            data: formData
          }).success(function (data) {
            $scope.results = data
          }).error(function (data, status) {
            console.log('Fail status: ' + status)
          })
        }

      }
    }
  })
