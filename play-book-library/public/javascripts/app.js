angular.module('books', ['bookService', '$strap.directives', 'authenticationService', 'angular-growl']).config(
   initConfig

) ;




function initConfig(growlProvider, $routeProvider) {

    growlConfig(growlProvider) ;
    routingConfig($routeProvider);

}

function growlConfig(growlProvider) {
    growlProvider.globalTimeToLive(2000);
    growlProvider.messagesKey("my-messages");
    growlProvider.messageTextKey("messagetext");
    growlProvider.messageSeverityKey("severity-level");
}

function routingConfig(routeProvider){

}