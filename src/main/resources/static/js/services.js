angular.module('demo', [])
    .controller('ExpensesServiceController',

        function($scope, $http) {

            $scope.model = {
                selected: {}
            };
            $scope.getExpenses = function() {
                $http.get('http://localhost:8080/api/expenses/')
                    .then(function (response) {
                        $scope.expenses = response.data;
                    },
                    function(response){
                        //code in case of error
                    }
                    );
            }

            $scope.deleteExpense = function(id) {
                $http.delete('http://localhost:8080/api/expenses/' + id)
                    .then(function (response) {
                        $scope.getExpenses();

                        },
                        function(response){
                            //code in case of error
                        }
                    );
            }

            $scope.addExpense = function(id) {
                $http.post('http://localhost:8080/api/expenses/',
                    {
                        description : $scope.description,
                        value : $scope.value,
                        date: new Date($scope.date)
                    }
                    ).then(function (response) {
                        $scope.getExpenses();
                    },
                    function(response){
                        //code in case of error
                    }
                );
            }

            $scope.editExpense = function(id) {
                $http.put('http://localhost:8080/api/expenses/' + id,
                    {
                        id : $scope.model.selected.id,
                        description : $scope.model.selected.description,
                        value : $scope.model.selected.value,
                        date:  new Date($scope.model.selected.date)
                    }
                ).then(function (response) {
                    $scope.reset();
                    $scope.getExpenses();
                    },
                    function(response){
                        //code in case of error
                    }
                );
            }

            // gets the template to ng-include for a table row / item
            $scope.getTemplate = function (expense) {
                if (expense.id === $scope.model.selected.id) return 'edit';
                else return 'display';
            };

            $scope.editExpenseTemplate = function (expense) {
                $scope.model.selected = angular.copy(expense);
            };

            $scope.reset = function () {
                $scope.model.selected = {};
            };
        });
