var app = angular.module('app', []);

app.config(function($httpProvider) {
	//Send all requests, even OPTIONS, with credentials
	$httpProvider.defaults.withCredentials = true;
});

app.controller('indexctrl', indexctrl);
indexctrl.$inject = [ '$scope', '$http' ];
function indexctrl($scope, $http) {
	$scope.signup = function() {
		var isUsing2FA = false;
		var role = "1";

		var req = {
			method : 'POST',
			url : 'http://192.168.0.6:9000/user/registration',
			headers : {
				//'Authorization' : 'Basic'+btoa("user:11f8637c-1f79-4048-940c-4303ec75fd14"),
				'Content-Type' : 'application/json'
			},
			data : {
				"firstName" : $scope.firstname,
				"lastName" : $scope.lastname,
				"password" : $scope.password1,
				"matchingPassword" : $scope.password2,
				"email" : $scope.emailid,
				"isUsing2FA" : false,
				"role" : role
			}
		}

		$http(req).then(function(response) {
			$scope.response = response.data;
			console.log(response);
		});
	}
}