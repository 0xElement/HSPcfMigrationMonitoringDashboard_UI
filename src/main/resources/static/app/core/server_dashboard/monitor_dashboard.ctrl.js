(function () {
angular
    .module('mainMdl').controller('salesCtrl', salesCtrl);
	
salesCtrl.$inject = ['$scope', '$document','$interval', '$timeout', '$window', 'dialogs', 'salesMainService', 'salesChartOptionService'];
    
	function salesCtrl($scope, $document, $interval, $timeout, $window, dialogs, mainService, chartOptionService) {
		console.log("salesCtrl");
        var ctrl = this;
        ctrl.hourDonutData = [];
        ctrl.dayDonutData = [];
        ctrl.weekDonutData = [];
        
        ctrl.cumulativeLineChartHourData = [];
        ctrl.cumulativeLineChartDayData = [];
        ctrl.cumulativeLineChartWeekData = [];
        
        ctrl.appSelected = false;
        ctrl.viewChange = true;
        
        ctrl.lastUpdatedDate = '';
        ctrl.lastUpdatedMonitoringMsg = '';

       /* ctrl.getDonutOptions = getDonutOptions;
        ctrl.getCumulativeLineChartOptions = getCumulativeLineChartOptions;*/
        ctrl.viewChangeFn = viewChangeFn;
        
        ctrl.selectedApp ="";
        ctrl.hourDonutDataTitle = "Success/Failures in Last Hour";
        ctrl.dayDonutDataTitle = "Success/Failures in Last 24 Hours";
        ctrl.weekDonutDataTitle = "Success/Failures in Last Week";
        
        ctrl.cumulativeLineChartHourDataTitle = "Latency Count in Last Hour";
        ctrl.cumulativeLineChartDayDataTitle = "Latency Count in Last 24 Hours";
        ctrl.cumulativeLineChartWeekDataTitle = "Latency Count in Last Week";
        
        ctrl.getHourDonutOptions = chartOptionService.getDonutChartOptions();
        ctrl.getDayDonutOptions = chartOptionService.getDonutChartOptions();
        ctrl.getWeekDonutOptions = chartOptionService.getDonutChartOptions();
        
        ctrl.getHourLineChartOptions = chartOptionService.getLineChartOptions('hour');
        ctrl.getDayLineChartOptions = chartOptionService.getLineChartOptions('day');
        ctrl.getWeekLineChartOptions = chartOptionService.getLineChartOptions('week');
        

       /* function getDonutOptions(duration) {
            return {
                chart: {
                    type: 'pieChart',
                    height: 400,
                    donut: true,
                    x: function(d) {
                        return d.key;
                    },
                    y: function(d) {
                        return d.y;
                    },
                    showLabels: true,

                    pie: {
                        startAngle: function(d) {
                            return d.startAngle / 2 - Math.PI / 2
                        },
                        endAngle: function(d) {
                            return d.endAngle / 2 - Math.PI / 2
                        }
                    },
                    duration: 500,
                    legend: {
                        margin: {
                            top: 5,
                            right: 140,
                            bottom: 5,
                            left: 0
                        }
                    },
                    color: function(d, i) {
                        console.log(d);
                        console.log(i);
                        if (d.key == 'Success') {
                            return "#2ca02c";
                        } else {
                            return "#d62728";
                        }
                    }
                }
            };
        }

        function getCumulativeLineChartOptions(duration) {
            return {
                chart: {
                    type: 'lineChart',
                    height: 500,
                    margin: {
                        top: 20,
                        right: 20,
                        bottom: 50,
                        left: 65
                    },
                    x: function(d) {
                        return new Date(d[0]);
                    },
                    y: function(d) {
                        console.log(d[1]);
                        return parseInt(d[1], 10);
                    },
                    average: function(d) {
                        return d.mean;
                    },

                    color: d3.scale.category10().range(),
                    duration: 300,
                    useInteractiveGuideline: true,
                    clipVoronoi: true,
                    isArea: function(d) {
                    	return true;
                    },

                    xAxis: {
                        axisLabel: 'Time',
                        tickFormat: function(d) {
                        	if(duration === 'hour') {
                        		return d3.time.format('%H:%M')(new Date(d))
                        	} else if(duration === 'day') {
                        		return d3.time.format('%H:%M')(new Date(d))
                        	} else if(duration === 'week') {
                        		return d3.time.format('%m/%d')(new Date(d))
                        	}  
                        	
                            
                        },
                        showMaxMin: false,
                        staggerLabels: false,
                        rotateLabels: 20
                    },
                    yAxis: {
                        axisLabel: 'Count',
                        tickFormat: function(d) {
                            console.log("y valie is " + d);
                            return d3.format('d')(d);
                        },
                        axisLabelDistance: -10
                    }
                }
            };
        }*/

        $scope.$on('child directive collapse grid data', function(event, args) {
            console.log('parent controller heard directive broadcast! again....');
            ctrl.appSelected = false;
            $scope.$apply();
        });
        
        $scope.$on('child directive change grid data', function(event, args) {
            console.log('parent controller heard directive broadcast!');
            console.log(args);
            ctrl.appSelected = false;
            ctrl.dataLoaded = false;

            mainService.getApplicationData(args.data.name, ctrl.lastUpdatedDate).then(function(response) {
            	
                ctrl.appSelected = true;
                console.log(response);
                ctrl.hourDonutData = [];
                ctrl.dayDonutData = [];
                ctrl.weekDonutData = [];
                
                ctrl.viewChange = true;
                
                ctrl.cumulativeLineChartHourData = [];
                ctrl.cumulativeLineChartDayData = [];
                ctrl.cumulativeLineChartWeekData = [];
                
                ctrl.selectedApp =args.data.name + " summary";
                
                ctrl.hourDonutData = response.data.responseData.applicationDataMap["hour"].donutList;
                ctrl.dayDonutData = response.data.responseData.applicationDataMap["day"].donutList;
                ctrl.weekDonutData = response.data.responseData.applicationDataMap["week"].donutList;
                console.log(response.data.responseData.applicationDataMap["week"].linearList);
                ctrl.cumulativeLineChartHourData = [{
                    key: "Latency",
                    mean: 100,
                    values: response.data.responseData.applicationDataMap["hour"].linearList
                }];
                
                ctrl.cumulativeLineChartDayData = [{
                    key: "Latency",
                    mean: 300,
                    values: response.data.responseData.applicationDataMap["day"].linearList
                }];
                
                ctrl.cumulativeLineChartWeekData = [{
                    key: "Latency",
                    mean: 1000,
                    values: response.data.responseData.applicationDataMap["week"].linearList
                }];
                
                $timeout(function() {
                	$window.scrollTo(0, angular.element($document[0].getElementById('donutId'))[0].offsetTop+500);
                }, 500);
            });
        });
        
        
        function viewChangeFn() {
        	var id = "donutId";
        	if(ctrl.viewChange) {
        		id= "latencyId";
        	}
        	ctrl.viewChange = (!ctrl.viewChange)
        	$timeout(function() {
            	$window.scrollTo(0, angular.element($document[0].getElementById(id))[0].offsetTop+500);
            }, 500);
        }

        function getData(lastUpdatedData) {
        	
        	ctrl.treeData = [];
            ctrl.lastUpdatedDate = '';
            ctrl.lastUpdatedMonitoringMsg ='';
            ctrl.appSelected = false;
            ctrl.viewChange = true;
        	
        	mainService.getTreeData(lastUpdatedData).then(function(response) {
                console.log(response);
                
                if(response.data.responseData && response.data.responseData.lastUpdateDateTime) {
                	ctrl.lastUpdatedMonitoringMsg = 'Status as of date: '+ response.data.responseData.lastUpdateDateTime +' ET';
                }
                

                if(response.data.responseData && response.data.responseData.children.length > 0) {
                	var appData = {};
            		appData.appName = 'Daily Sales';
            		appData.status =  response.data.responseData.status;
            		appData.ltTreshholdSt = response.data.responseData.ltTreshholdSt;
            		appData.errPerSec = null;
            		appData.latencyAvg = null;
            		appData.children = [];
            		appData.children.push(response.data.responseData);
                	ctrl.treeData[0] = appData;
                	if(response.data.responseData.lastUpdatedDate) {
                		ctrl.lastUpdatedDate = response.data.responseData.lastUpdatedDate; 
                	}
                } /*else {
                	// Check:: Not to display confirmation dialog if last updated data also not available. 
                	if(response.data.responseData.children.length === 0 && lastUpdatedData !== 'Y') {
                		var warningDlg1 = dialogs.confirm('Confirmation', 'Data for last one hour not available, Would you like to proceed?', {
                			size: 'md'
                		});
                		warningDlg1.result.then(function () {
                			getData('Y');
                		}, function () {
                		});
                	} else {
                		dialogs.notify('Information', 'Data is not available', {
                			size: 'md'
                		});
                	}
                	
                }*/
            });
        }
        getData('N');
        
        $scope.$on('homeImageClicked', function(event) {
        	 getData('N');
		});
    }
	
})();