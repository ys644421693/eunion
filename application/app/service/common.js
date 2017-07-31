/**
 * Created by ys on 2016/9/9.
 */
app.factory('dataCheck', [function () {
    return function (data) {

        if (!data.name) {
            return "名称不能为空！";
        }
        if (!data.mail) {
            return "邮件格式不正确或不能为空！";
        }
        if (!data.subject) {
            return "主题不能为空！";
        }
        if (!data.message) {
            return "内容不能为空！";
        }
        return true;
    };
}]);

app.factory('sendRequest', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/:SPACE/:CHANNEL',
        {SPACE: '@space', CHANNEL: '@channel'},
        {
            update: {method: 'PUT', isArray: false},
            add: {method: 'POST', isArray: false}
        }
    );
}]);