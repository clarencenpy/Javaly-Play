"use strict";angular.module("JavalyApp",["ngAnimate","ngCookies","ngResource","ngRoute","ui.router","ngSanitize","ngTouch","ngMaterial","ui.codemirror","angular-flippy","yaru22.md"]).config(["$stateProvider",function(a){a.state("practice",{url:"/practice",templateUrl:"views/partial-practice-view.html"}).state("addQuestion",{url:"/addquestion",templateUrl:"views/partial-add-question-view.html"})}]).config(["$mdThemingProvider",function(a){a.theme("default")}]),angular.module("JavalyApp").controller("sidebarCtrl",["$scope","$timeout","$mdSidenav",function(a,b,c){a.toggleLeft=function(){c("left").toggle()},a.toggleRight=function(){c("right").toggle()}}]).controller("leftCtrl",["$scope","$timeout","$mdSidenav",function(a,b,c){a.close=function(){c("left").close()}}]).controller("rightCtrl",["$scope","$timeout","$mdSidenav",function(a,b,c){a.close=function(){c("right").close()}}]),angular.module("JavalyApp").controller("addQuestionFormController",["$scope","$http",function(a,b){a.editorOptions={lineWrapping:!0,smartIndent:!1,indentWithTabs:!0,theme:"neat",mode:"text/x-java"},a.question={title:"Question title",init:"",helpermethod:""},a.updatePreview=function(){for(var b="",c=0;c<a.testcases.length;c++)b+="System.out.println("+a.question.methodname+"("+a.testcases[c].input+"));\n";a.preview="public class "+a.question.classname+" {\npublic static void main(String[] args) {\n\n//Init Code\n"+a.question.init+"\n\n//Test Code\n"+b+"\n}\n\n//Helper Methods\n\n"+a.question.helpermethod+"\n\n// Inject user submitted method\n/* public static "+a.question.methodname+"() ...*/\n\n}"},a.testcases=[{input:"",output:""}],a.add=function(){a.testcases.push({input:"",output:""})},a.remove=function(b){a.testcases.splice(b,1)},a.question.title="Add even numbers",a.question.description="Write a method named `evenSum` that prompts the user for many integers and print the total even sum and maximum of the even numbers. You may assume that the user types at least one non-negative even integer. \n```\nhow many integers? 4\nnext integer? 2\nnext integer? 9\nnext integer? 18\nnext integer? 4\neven sum = 24\neven max = 18\n```",a.question.classname="Tester",a.question.methodname="evenSum",a.testcases[0].input=1,a.testcases[0].output=2,a.testcases.push({input:1,output:2}),a.submit=function(){for(var c={title:a.question.title,description:a.question.description,className:a.question.classname,methodName:a.question.methodname,mainMethodCode:a.question.init,helperMethodCode:a.question.helpermethod},d=0;d<a.testcases.length;d++)c["tc"+(d+1)+"input"]=a.testcases[d].input,c["tc"+(d+1)+"output"]=a.testcases[d].output;b({method:"POST",url:"http://localhost:9000/question/add",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:function(a){var b=[];for(var c in a)b.push(encodeURIComponent(c)+"="+encodeURIComponent(a[c]));return b.join("&")},data:c}).success(function(){console.log("success!!")}).error(function(a,b){console.log("Fail status: "+b)})}}]),angular.module("JavalyApp").directive("questionPanel",function(){return{restrict:"EA",replace:!0,templateUrl:"views/question-panel.html",controller:["$scope","$http",function(a,b){a.editorOptions={lineWrapping:!0,lineNumbers:!0,smartIndent:!1,indentWithTabs:!0,theme:"neat",mode:"text/x-java",autofocus:!0},b.get("http://localhost:9000/question/2").success(function(b){a.question=b}),b.get("http://localhost:9000/fakeresult").success(function(b){a.results=b}),a.code="",a.submit=function(){var c={code:a.code,id:a.question.id};b({method:"POST",url:"http://localhost:9000/question/submit",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:function(a){var b=[];for(var c in a)b.push(encodeURIComponent(c)+"="+encodeURIComponent(a[c]));return b.join("&")},data:c}).success(function(b){a.results=b}).error(function(a,b){console.log("Fail status: "+b)})}}]}});