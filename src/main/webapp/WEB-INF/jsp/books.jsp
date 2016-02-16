<!doctype html>
<html lang="en" ng-app="bookApp">
<head>
    <meta charset="utf-8">
    <title>Service App</title>
    <link rel="stylesheet" href="resources/css/app.css"/>
    <link rel="stylesheet" href="resources/css/toastr.css"/>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"/>
    <script src="resources/js/lib/angular/angular.js"></script>
    <script src="resources/js/app.js"></script>
    <script src="resources/js/services.js"></script>
    <script src="resources/js/filters.js"></script>
    <script src="resources/js/directives.js"></script>
    <script src="resources/js/lib/angular/jquery-1.11.1.js"></script>
    <script src="resources/js/lib/angular/toastr.js"></script>
    <script src="resources/js/captcha.js"></script>

</head>
<body>

<div id="wrapper" ng-app="bookApp" ng-controller="BookController">
    <div style="text-align: center"><h3>Book List </h3></div>

    <table style="width: 100%">
        <tr>
            <td>
                <button type="button" class="btn btn-primary" ng-click="openAddOrUpdateModal(book)"
                        style="text-align: left">Add
                    New Book
                </button>
            </td>

            <td>
                <div style="text-align: right">
                    <input type="text" ng-model="BookController" placeholder="Search...">
                </div>
            </td>
        </tr>
    </table>
    <div class="alert alert-info" ng-show="bookList.length == 0">
        No book found
    </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th style="text-align: center;">Book Name</th>
            <th style="text-align: center;">Author</th>
            <th style="text-align: center;">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr class="filter" ng-repeat="book in bookList | filter:BookController ">
            <td>{{book.bookName}}</td>
            <td>{{book.author}}</td>
            <td style="width:150px;text-align: center;">

                <button class="btn btn-mini btn-success" style="text-align: left" ng-click="editBook(book)">Edit
                </button>

                <button class="btn btn-mini btn-danger" style="text-align: right" confirmed-click="deleteBook(book.id)"
                        ng-confirm-click="Are you sure ?">Remove
                </button>
            </td>
        </tr>
        </tbody>
    </table>

    <modal-dialog show='addOrUpdateModal' width=400px' height='50%'>

        <h4 ng-hide="editMode">Add New Book</h4>
        <h4 ng-show="editMode">Update Book</h4>
        <hr>
        <div class="container">
            <form name="registerBook" class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="inputName">Book Name :</label>
                    <div class="col-sm-10">
                        <input type="text" id="inputName" ng-model="book.bookName" placeholder="Book Name" required
                               min="2">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="inputAuhor">Author Name :</label>
                    <div class="col-sm-10">
                        <input class="span3" type="text" id="inputAuhor" ng-model="book.author"
                               placeholder="Author Name" required min="2">
                    </div>
                </div>
                <div class="form-group">
                    <captcha class='control-label col-sm-2' field1="{{field1}}" operator="{{operator}}"
                             field2="{{field2}}"></captcha>
                    <div class="col-sm-10">
                        <input class="span3" type="text" ng-model="book.resultado" placeholder="Verify Captcha"
                               required min="2">
                    </div>
                    <div class="control-group">
                        <hr/>
                        <div class="controls">
                            <button type="button" class="btn btn-primary" ng-hide="editMode"
                                    ng-disabled="!registerBook.$valid"
                                    ng-click="createBook(book)">Create Book
                            </button>
                            <button type="button" class="btn btn-primary" ng-show="editMode"
                                    ng-disabled="!registerBook.$valid"
                                    ng-click="updateBook(book)">Update Book
                            </button>
                            <button type="button" class="btn" ng-click="resetBookForm()">Cancel</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </modal-dialog>
    <div ng-view></div>
</div>
</body>
</html>
