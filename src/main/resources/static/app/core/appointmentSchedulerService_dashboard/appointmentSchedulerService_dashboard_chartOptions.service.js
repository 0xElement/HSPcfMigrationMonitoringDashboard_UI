(function () {
    angular.module('mainMdl')
        .service('appointmentSchedulerServiceChartOptionService', function () {
        	
        	
           this.getDonutChartOptions = function(){
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
            };
            
            
            this.getLineChartOptions = function(duration){
            	var lable='Milliseconds';
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
                            	}  else if(duration === 'month') {
                            		return d3.time.format('%m/%d')(new Date(d))
                            	}  else if(duration === 'error') {
                            		return d3.time.format('%m/%d')(new Date(d))
                            	}  
                           },
                            showMaxMin: false,
                            staggerLabels: false,
                            rotateLabels: 20
                        },
                        yAxis: {
	                            tickFormat: function(d) {
	                            	if(duration && duration === 'error') {
	                            		lable='Error Count';
		                        	}
	                                console.log("y valie is " + d);
	                                return d3.format('d')(d);
	                            },	                           
	                            axisLabel:duration === 'error'?'Error Count':'Millisecods',
	                            axisLabelDistance: -10
                        }
                    }
                };
            };
        });
})();