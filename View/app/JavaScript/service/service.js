/**
 * Created by yangshuo on 15/11/23.
 */
var dataUrl="http://localhost:8089/data";
app.service('operationDataById',['$resource',function($resource){
    return $resource(dataUrl+'/:NAME/:ID',
        {NAME:"@name",ID:"@id"},
        {post:{method:"POST",params:{},isArray:false}},
        {put:{method:"PUT",params:{},isArray:false}},
        {get:{method:"GET",params:{},isArray:true}},
        {delete:{method:"DELETE",params:{},isArray:false}}
    );
}]);