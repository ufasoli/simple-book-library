angular.module("bookService", ['ngResource']).
    factory('Books', function($resource){
        return $resource("http\\://localhost\\:9000/books");
    })
    .factory('UserBooks', function($resource){
        return $resource("http\\://localhost\\:9000/books")
    })
    .factory('BorrowBook', function($resource){
                return $resource("http\\://localhost\\:9000/books/:bookId/borrow",{ 'bookId': '@bookId'});

    })
    .factory('ReturnBook', function($resource){
            return $resource("http\\://localhost\\:9000/books/:bookId/return",{ 'bookId': '@bookId'})
     });


