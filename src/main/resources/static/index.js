angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/showroom';

    $scope.fillTable = function () {
        $http.get(contextPath + '/api/v1/cars')
            .then(function (response) {
                $scope.CarsList = response.data;
            });
    };

    // $scope.submitCreateNewProduct = function () {
    //     $http.post(contextPath + '/api/v1/cars', $scope.newCar)
    //         .then(function (response) {
    //             // $scope.BooksList.push(response.data);
    //             $scope.fillTable();
    //         });
    // };
    //
    // $scope.applyFilter = function () {
    //     $http({
    //         url: contextPath + '/api/v1/books',
    //         method: "GET",
    //         params: {book_title: $scope.bookFilter.title}
    //     }).then(function (response) {
    //         $scope.BooksList = response.data;
    //     });
    // }

    $scope.deleteProductById = function(productId) {
        console.log('deleteTest');
        $http({
            url: contextPath + '/api/v1/cars/' + productId,
            method: "DELETE"
        }).then(function (response) {
            $scope.fillTable();
        });
    }

    $scope.fillTable();
});