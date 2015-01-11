/**
 * Created by clarencenpy on 3/1/15.
 */
'use strict';

angular.module('JavalyApp')
  .controller('addQuestionFormController', function($scope, $http, $mdToast, ENV) {
    $scope.editorOptions = {
      lineWrapping : true,
      smartIndent: false,
      indentWithTabs: true,
      theme: 'neat',
      mode: 'text/x-java'
    }

    $scope.question = {
      title: '',
      init: '',
      helpermethod: ''
    }


    $scope.updatePreview = function() {
      var testcode = ''
      for(var i=0; i<$scope.testcases.length; i++){
        testcode += 'System.out.println(' + $scope.question.methodname + '(' + $scope.testcases[i].input + '));\n'
      }

      $scope.preview =
        'public class ' + $scope.question.classname + ' {\n'
      + 'public static void main(String[] args) {\n\n'
      + '//Init Code\n'
      +  $scope.question.init + '\n\n'
      + '//Test Code\n'
      + testcode + '\n}\n\n'
      + '//Helper Methods\n\n'
      + $scope.question.helpermethod + '\n\n'
      + '// Inject user submitted method\n'
      + '/* public static ' + $scope.question.methodname + '() ...*/\n\n'
      + '}'
    }

    $scope.testcases = [{
      input: '',
      output: ''
    }]

    $scope.add = function () {
      $scope.testcases.push({
        input: '',
        output: ''
      })
    }

    $scope.remove = function (index) {
      $scope.testcases.splice(index, 1)
    }

    //Pre-populate for test purposes
    //$scope.question.title = 'Add even numbers'
    //$scope.question.description = 'Write a method named `evenSum` that prompts the user for many integers and print the total even sum and maximum of the even numbers. You may assume that the user types at least one non-negative even integer. \n```\nhow many integers? 4\nnext integer? 2\nnext integer? 9\nnext integer? 18\nnext integer? 4\neven sum = 24\neven max = 18\n```'
    //$scope.question.classname = 'Tester'
    //$scope.question.methodname = 'evenSum'
    //$scope.testcases[0].input = 1
    //$scope.testcases[0].output = 2
    //$scope.testcases.push({input: 1, output: 2})

    $scope.submit = function () {
      var formData = {
        title: $scope.question.title,
        description: $scope.question.description,
        className: $scope.question.classname,
        methodName: $scope.question.methodname,
        mainMethodCode: $scope.question.init,
        helperMethodCode: $scope.question.helpermethod
      }

      for(var i= 0; i<$scope.testcases.length; i++) {
        formData['tc' + (i+1) + 'input'] = $scope.testcases[i].input
        formData['tc' + (i+1) + 'output'] = $scope.testcases[i].output
      }

      $http({
        method: 'POST',
        url: ENV.apiEndpoint + 'question/add',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        transformRequest: function(obj) {
          var str = []
          for(var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]))
          return str.join("&")
        },
        data: formData
      }).success(function (data, status, headers, config) {
        $mdToast.show($mdToast.simple().content('Question added'));
      }).error(function (data, status, headers, config) {
        console.log('Fail status: ' + status)
        $mdToast.show($mdToast.simple().content('Error'));
      })
    }

  })
