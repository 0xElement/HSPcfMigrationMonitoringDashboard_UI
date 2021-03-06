(function () {
    angular.module('mainMdl')
        .service('leadMainService', function ($http) {
           this.getTreeData = function(lastUpdatedData){
                return $http.get("/getLastHourDataForTree?dashboardName=LeadMgmt&lastUpdatedData="+lastUpdatedData)
                .success(function(data){
                    return data;
                }).error(function(err){
                    return err;
                });
            };
            
            this.getApplicationData = function(appName, lastUpdatedDate){
                return $http.get("/loadApplicationsData?appName=" + appName+"&lastUpdatedDate="+lastUpdatedDate)
                .success(function(data){
                    return data;
                }).error(function(err){
                    return err;
                });
            };
        });
})();