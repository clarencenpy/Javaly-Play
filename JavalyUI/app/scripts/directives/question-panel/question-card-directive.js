/**
 * Created by clarencenpy on 31/12/14.
 */

angular
  .module('JavalyApp')
  .directive('questionCard', function() {
    return {
      restrict: 'EA',
      templateUrl: "scripts/components/question-panel/question-card.html",
      replace: true,
      controller: function($scope) {
        $scope.editorOptions = {
          lineWrapping : true,
          lineNumbers: true,
          smartIndent: false,
          indentWithTabs: true,
          theme: 'neat',
          mode: 'text/x-java',
          autofocus: true
        }
      }



    }
  })
