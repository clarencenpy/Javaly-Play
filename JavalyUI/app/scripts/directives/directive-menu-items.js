/**
 * Created by clarencenpy on 16/1/15.
 */

'use strict'

angular
  .module('JavalyApp')
  .directive('menuItems', function() {
    return {
      restrict: 'EA',
      replace: true,
      templateUrl: "views/directive-menu-items.html"
    }
  })
