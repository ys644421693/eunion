/**
 * Created by ys on 2016/9/9.
 */
app.factory('dataCheck', [function () {
    return function (data) {

        if (!data.name) {
            return "Name can not be empty.";
        }
        if (!data.mail) {
            return "Email address format not correct or empty.";
        }
        if (!data.subject) {
            return "Subject can not be empty.";
        }
        if (!data.message) {
            return "Content can not be empty.";
        }
        return true;
    };
}]);

app.factory('sendRequest', ['$resource', function ($resource) {
    return $resource('/:SPACE/:CHANNEL/',
        {SPACE: '@space', CHANNEL: '@channel'},
        {
            update: {method: 'PUT', isArray: false},
            add: {method: 'POST', isArray: false}
        }
    );
}]);