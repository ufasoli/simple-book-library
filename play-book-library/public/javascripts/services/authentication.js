angular.module("authenticationService", ['ngResource']).
    factory('User', function($resource){
        return $resource("http\\://localhost\\:9000/user" );
    })
  ;


