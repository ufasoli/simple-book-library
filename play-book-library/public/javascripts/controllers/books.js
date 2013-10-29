
function booksCtrl($scope, growl, Books, User, BorrowBook, ReturnBook){



    $scope.books = Books.query();

    $scope.resetSearch = function () {
        $scope.search.bookTitle = "";
    };


    $scope.borrow = function(bookId){
        BorrowBook.save({"bookId": bookId}, function(data){
            $scope.books = Books.query();
            growl.addInfoMessage("Book borrowed");
        });
    };

    $scope.return = function(bookId){
        ReturnBook.save({"bookId": bookId}, function(data){
            $scope.books = Books.query();
            growl.addInfoMessage("Book returned");
        });
    };




    // login methods and operation


    $scope.user = User.get();

    $scope.authenticate = function(){
        User.save({"username" : $scope.user.username}, function(){
        });
    }  ;


    $scope.modal = {
        "content": "Hello Modal",
        "saved": false
    }   ;

}