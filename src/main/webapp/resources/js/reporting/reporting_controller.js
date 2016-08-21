'use strict';

application.controller('ReportingCtrl', function($scope, Bug, Author, Tag) {
    $scope.selectedTags = [];
    $scope.bug = {
        author: {
        }
    };

    $scope.fetchAllBugs = function(){
        Bug.query().$promise.then(function(data) {
            $scope.bugs= data.bugs;
        });
    };

    $scope.fetchAllAuthors = function() {
        Author.query().$promise.then(function(data) {
            $scope.authors= data.authors;
            $scope.bug.author = $scope.authors[0];
        });
    };

    $scope.fetchAllTags = function() {
        Tag.query().$promise.then(function(data) {
            $scope.tags= data.tags;
        });
    };

    $scope.fetchAllBugs();
    $scope.fetchAllAuthors();
    $scope.fetchAllTags();

    $scope.saveOrUpdate = function () {
        if ($scope.bug.bugId) {
            updateBug();
        } else {
            saveBug();
        }
    }

    $scope.edit = function(data) {
        $scope.bug = data;
    };

    var updateBug = function() {
        Bug.update($scope.bug).$promise.then(function(returnedData){
            alert(returnedData.bugDescription + " has been successfully updated.")
            $scope.bug.bugId = null;
            $scope.fetchAllBugs();
        },function (err) {
            alert("Request failed with a status " + err.status + " " + err.statusText);
        });
    };

    var saveBug = function() {
        Bug.save($scope.bug).$promise.then(function(returnedData){
            alert(returnedData.bugDescription + " has been successfully created.")
            $scope.fetchAllBugs();
        },function (err) {
            alert("Request failed with a status " + err.status + " " + err.statusText);
        });
    };
});

