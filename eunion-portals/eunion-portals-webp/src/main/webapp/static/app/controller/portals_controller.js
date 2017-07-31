/**
 * Created by ys on 2016/9/9.
 */
app.controller('GreetingController', ['$scope','dataCheck','sendRequest', function($scope,dataCheck,sendRequest) {
    $scope.formData={};
    $scope.submitMsg=function(){
        var result = dataCheck($scope.formData);
        if(result ==true ){
            sendRequest.add({SPACE:"info",CHANNEL:"sendMail"},$scope.formData,function(data){
                console.log(data);
                alert(data.msg);
            });
        }else{
            alert(result);
        }

    }
}]);