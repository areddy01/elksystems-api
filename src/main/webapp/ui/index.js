var app=angular.module('app', ['ui.router','ngRoute']);

app.config([ '$routeProvider', '$locationProvider','$httpProvider',
    function($routeProvider, $locationProvider,$httpProvider) {
        $routeProvider.when('/home', {
            templateUrl : 'home.html',
            controller : 'HomeController'
        });
        $routeProvider.when('/', {
            templateUrl : 'login.html',
            controller : 'LoginController'
        }).otherwise({
            redirectTo : 'index.html'
        });
        $httpProvider.defaults.headers.common = {};
        $httpProvider.defaults.headers.post = {};
        $httpProvider.defaults.headers.put = {};
        $httpProvider.defaults.headers.patch = {};

        //$locationProvider.html5Mode(true); //Remove the '#' from URL.
    }
]);

app.controller("LoginController", function($scope, $location,$http) {

    $scope.login=true;
    $scope.signIn=false;

    $scope.loginpagectrl=function () {
        $scope.login=false;
        $scope.signIn=true;
    }

    $scope.signupctrl=function () {
        $scope.login=true;
        $scope.signIn=false;
    }

    $scope.signup=function(){
        var firstName=document.getElementById("firstname").value;
        var lastName=document.getElementById("lastname").value;
        var password1=document.getElementById("passwordsignup").value;
        var password2=document.getElementById("passwordsignup_confirm").value;
        var email=document.getElementById("email").value;;
        var isUsing2FA=false;
        var role="1";

        var req = {
            method: 'POST',
            url: 'http://localhost:5000/user/registration',
            headers : {			
				'Content-Type' : 'application/json'
			},
            data : {
                "firstName" : firstName,
                "lastName" : lastName,
                "password" : password1,
                "matchingPassword" : password2,
                "email" : email,
                "isUsing2FA" : false,
                "role" : role
            }
        }

        $http(req).then(function(response) {
            $location.path("/home" );
        });
    }
});

app.controller("HomeController", function($scope, $location) {

});


app.controller('indexctrl',indexctrl);
indexctrl.$inject=['$scope','$http','$window'];
function indexctrl($scope,$http,$window){

}
