/**
 * This controller encapsulate actions with tasks list.
 * Created by loki on 06.12.2015.
 */

app.controller('ListController', function ($scope, $http) {
    // Empty & new task using to create and edit tasks.
    // To initialize it using empty task object.
    this.emptyTask = {id: null, title: null, description: null};
    $scope.newTask = angular.copy(this.emptyTask);
    $scope.editingTask = angular.copy(this.emptyTask);

    // Tasks list refreshing on controller loaded & on some data actions.
    $scope.refreshList = function() {
        $http.get('/api/tasks/')
            .success(function (data) {
                $scope.tasks = data;
            });
    };

    $scope.refreshList();

    // POST request to controller's saveTask function
    this.addTask = function (task) {
        $http.post('/api/tasks/save', task)
            .then(function successCallback() {
                $scope.refreshList();
            }, function errorCallback(response) {
                console.log("Something goes wrong: ", response);
                alert("Сохранение задачи прошло как-то так себе.");
            });
        $scope.newTask = angular.copy(this.emptyTask);
    };

    // POST request to controller's completeTask function
    this.completeTask = function(task) {
        task.completed = true;
        $http.post('/api/tasks/save', task).then(function successCallback() {
            $scope.refreshList();
        }, function errorCallback(response) {
            console.log("Something goes wrong: ", response);
            alert("Сохранение задачи прошло как-то так себе.");
        });
    };

    // Call to taskEdit modal.
    this.editTask  = function(task) {
        $scope.editingTask = task;
        $('#taskEditModal').modal('show');
    };

    // Save task & dismiss task modal.
    this.saveTask = function(task) {
        this.addTask(task);
        $('#taskEditModal').modal('hide');
    };
});
