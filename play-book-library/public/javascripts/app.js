angular.module('books', ['bookService', '$strap.directives', 'authenticationService', 'angular-growl']).config(
    function(growlProvider, $httpProvider) {
        growlProvider.globalTimeToLive(2000);
        growlProvider.messagesKey("my-messages");
        growlProvider.messageTextKey("messagetext");
        growlProvider.messageSeverityKey("severity-level");
    }
) ;
