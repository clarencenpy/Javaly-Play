'use strict';

angular
  .module('JavalyApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ui.router',
    'ngSanitize',
    'ngTouch',
    'ngMaterial',
    'ui.codemirror',
    'angular-flippy',
    'yaru22.md',
    'angular-loading-bar'
  ])

  .config(function ($stateProvider) {
    $stateProvider
      .state('listQuestions', {
        url: '/questions',
        template: '<question-list></question-list>'
      })
      .state('practice', {
        url: '/practice/:id',
        template: '<question-panel></question-panel>'
      })
      .state('addQuestion', {
        url: '/addquestion',
        templateUrl: 'views/partial-add-question-view.html'
      })
  })

  //theming
  //refer to https://material.angularjs.org/#/Theming/03_configuring_a_theme
  .config(function($mdThemingProvider) {
    $mdThemingProvider
      .theme('default')
      //.primaryColor('yellow', {
      //  'default': '400',
      //  'hue-1': '100',
      //  'hue-2': '600',
      //  'hue-3': 'A100'
      //})
      //.accentColor('orange', {
      //  'default': '400',
      //  'hue-1': '100',
      //  'hue-2': '600',
      //  'hue-3': 'A100'
      //})

  })

