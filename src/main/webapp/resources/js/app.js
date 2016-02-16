'use strict';

var bookApp = {};

angular.module('bookApp', ['bookApp.filters', 'bookApp.services', 'bookApp.directives', 'udpCaptcha'])
    .controller('BookController', function ($scope, $http, $rootScope, $filter, dateFilter, $captcha) {
        $scope.book = {};
        $scope.editMode = false;
        $scope.addOrUpdateModal = false;
        $scope.generalModal = false;

        $scope.bookArrayFilter = function (book) {
            return (book.name.indexOf('it') != -1 && book.kind >= 40);
        };

        $scope.fetchBookList = function () {
            $http.get("books/records")
                .success(function (response) {
                    $scope.bookList = response.books;
                });
        };

        $scope.createBook = function (book) {
            if ($captcha.checkResult(book.resultado) == true) {
                $http({
                    url: "books/create", method: 'POST',
                    data: $.param($scope.book),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function () {
                    $scope.fetchBookList();
                    $scope.addOrUpdateModal = false;
                    toastr.info("Book created");
                }).error(function () {
                    toastr.error("Could not add a new book");
                    (!isValidBook(book))

                });
                return true;

            }

            else {
                alert("Please verify captcha");
                return false;
            }

        };

        $scope.updateBook = function (book) {
            if ($captcha.checkResult(book.resultado) == true) {
                $http({
                    url: "books/update", method: 'POST',
                    data: $.param($scope.book),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function () {
                    $scope.fetchBookList();
                    $scope.editMode = false;
                    $scope.addOrUpdateModal = false;
                    toastr.info("Book updated");
                }).error(function () {
                    toastr.error('Could not update the book');
                });
                return true;
            }

            else {
                alert("Please verify captcha");
                return false;
            }
        };

        $scope.editBook = function (book) {
            $scope.book = book;
            $scope.editMode = true;
            $scope.addOrUpdateModal = true;

        };

        $scope.deleteBook = function (id) {
            $http.delete("books/delete/?id=" + id).success(function () {
                toastr.info('Book deleted');
                $scope.fetchBookList();
            }).error(function () {
                toastr.error('Could not remove book');
            });
        };

        $scope.resetBookForm = function () {
            $scope.book = {};
            $scope.editMode = false;
            $scope.addOrUpdateModal = false;
            $scope.generalModal = false;

        };

        $scope.setError = function (message) {
            $scope.error = true;
            $scope.errorMessage = message;
            $scope.generalModal = true;
        };

        $scope.fetchBookList();

        $scope.predicate = 'book.id';

        var isEmpty = function (val) {
            return (val == 'undefined' || val == null || val.length <= 0) ? true : false;
        }

        $scope.openAddOrUpdateModal = function () {
            $scope.addOrUpdateModal = true;
            $scope.editMode = false
            $scope.book = {};
        };

        var isValidBook = function (book) {

            if (!$scope.createBook(book)) {
                return false;
            }

            if (isEmpty($scope.book.bookName)) {
                toastr.error('Please enter book name');
                return false;

            }
            if (isEmpty($scope.book.author)) {
                toastr.error('Please enter author name');
                return false;

            }

        };
    });