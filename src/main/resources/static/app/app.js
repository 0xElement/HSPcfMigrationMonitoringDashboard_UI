angular.module('mainMdl', ['ngRoute','nvd3',  'ui.bootstrap','blockUI','dialogs.main'])
	.config(function ($routeProvider) {
	    $routeProvider
	        /*.when('/ICONX', {
	            templateUrl: 'app/core/monitor_dashboard/monitor_dashboard.html',
	            controller: 'iconxCtrl as ctrl'
	        }).when('/HDI', {
	        	 templateUrl: 'app/core/hde_dashboard/monitor_dashboard.html',
		            controller: 'hdeCtrl as ctrl'
	        }).when('/LeadMgmt', {
	        	 templateUrl: 'app/core/lead_dashboard/monitor_dashboard.html',
		            controller: 'leadCtrl as ctrl'
	        }).when('/POUpdate', {
	        	 templateUrl: 'app/core/poupdate_dashboard/monitor_dashboard.html',
		            controller: 'poupdateCtrl as ctrl'
	        }).when('/HRSApps', {
	        	 templateUrl: 'app/core/hrs_dashboard/monitor_dashboard.html',
		            controller: 'hrsCtrl as ctrl'
	        }).when('/ALTC', {
	        	 templateUrl: 'app/core/altc_dashboard/monitor_dashboard.html',
		            controller: 'altcCtrl as ctrl'
	        }).when('/EWRApp', {
	        	 templateUrl: 'app/core/ewr_dashboard/monitor_dashboard.html',
		            controller: 'ewrCtrl as ctrl'        		
	        }).when('/RenoWalk', {
	            templateUrl: 'app/core/renowalk_dashboard/monitor_dashboard.html',
	            controller: 'renoCtrl as ctrl'
	        }).when('/RapidPass', {
	            templateUrl: 'app/core/rapidpass_dashboard/monitor_dashboard.html',
	            controller: 'rapidCtrl as ctrl'
	        }).when('/DailySales', {
	            templateUrl: 'app/core/sales_dashboard/monitor_dashboard.html',
	            controller: 'salesCtrl as ctrl'
	        }).when('/FTPS', {
	            templateUrl: 'app/core/ftps_dashboard/monitor_dashboard.html',
	            controller: 'ftpsCtrl as ctrl'
	        }).when('/HDConnect', {
	            templateUrl: 'app/core/hdconnect_dashboard/monitor_dashboard.html',
	            controller: 'hdconnectCtrl as ctrl'   
	        }).when('/ServerDetails_ICONX', {
	            templateUrl: 'app/core/server_dashboard/monitor_dashboard_ICONX.html',
	            controller: 'serverCtrl as ctrl' 
	        }).when('/ServerDetails_RapidPass', {
	            templateUrl: 'app/core/server_dashboard/monitor_dashboard_RapidPass.html',
	            controller: 'serverCtrl as ctrl'
	        }).when('/Tickets', {
	            templateUrl: 'app/core/ticket_dashboard/monitor_dashboard.html',
	            controller: 'ticketCtrl as ctrl'
	        }).when('/ServerDetails_RenoWalk', {
	            templateUrl: 'app/core/server_dashboard/monitor_dashboard_RenoWalk.html',
	            controller: 'serverCtrl as ctrl' 
	        }).when('/ServerDetails_HDConnect', {
	            templateUrl: 'app/core/server_dashboard/monitor_dashboard_HDConnect.html',
	        }).when('/ServerDetails_HDE', {
	            templateUrl: 'app/core/server_dashboard/monitor_dashboard_HDI.html',
	        }).when('/ServerDetails_EWR', {
	            templateUrl: 'app/core/server_dashboard/monitor_dashboard_EWR.html',
	        }).when('/ServerDetails_ALTC', {
	            templateUrl: 'app/core/server_dashboard/monitor_dashboard_ALTC.html',
	        }).when('/ServerDetails_HRSApps', {
	            templateUrl: 'app/core/server_dashboard/monitor_dashboard_HRSApps.html',
	        })*/
	    .when('/IconxMobWS', {
            templateUrl: 'app/core/iconxmobws_dashboard/iconxmobws_dashboard.html',
            
            controller: 'iconxmobwsCtrl as ctrl'
        })
         .when('/ServerDetails_IconxMobWS', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_ICONXMOB.html',
            controller: 'iconxmobwsCtrl as ctrl'
        })
         .when('/IconxPayments', {
            templateUrl: 'app/core/iconxpayments_dashboard/iconxpayments_dashboard.html',
            controller: 'iconxpaymentsCtrl as ctrl'
        })
        
         .when('/ServerDetails_IconxPayments', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_IconxPayments.html',
            controller: 'iconxpaymentsCtrl as ctrl'
        })
        .when('/Iconx', {
            templateUrl: 'app/core/iconx_dashboard/iconx_dashboard.html',
            controller: 'ICONXCtrl as ctrl'
        })
        
        .when('/ServerDetails_Iconx', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_Iconx.html',
            controller: 'ICONXCtrl as ctrl'
        })
        .when('/DocumentService', {
            templateUrl: 'app/core/documentservice_dashboard/documentservice_dashboard.html',
            controller: 'DocumentServiceCtrl as ctrl'
        })
        
         .when('/ServerDetails_DocumentService', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_DocumentService.html',
            controller: 'DocumentServiceCtrl as ctrl'
        })
        .when('/Smhdemasterdataservice', {
            templateUrl: 'app/core/smhdemasterdataservice_dashboard/smhdemasterdataservice_dashboard.html',
            controller: 'smhdemasterdataserviceCtrl as ctrl'
        })
        
        .when('/ServerDetails_Smhdemasterdataservice', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_Smhdemasterdataservice.html',
            controller: 'smhdemasterdataserviceCtrl as ctrl'
        })
        .when('/SSSHWebservices', {
            templateUrl: 'app/core/ssshWebservices_dashboard/ssshWebservices_dashboard.html',
            controller: 'ssshWebservicesCtrl as ctrl'
        })
        .when('/ServerDetails_SSSHWebservices', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_SSSHWebservices.html',
            controller: 'ssshWebservicesCtrl as ctrl'
        })
        
         .when('/HsHdeEmailService', {
            templateUrl: 'app/core/hsHdeEmailService_dashboard/hsHdeEmailService_dashboard.html',
            controller: 'hsHdeEmailServiceCtrl as ctrl'
        })
        
        
        .when('/ServerDetails_HsHdeEmailService', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_HsHdeEmailService.html',
            controller: 'hsHdeEmailServiceCtrl as ctrl'
        })
        .when('/AppointmentSchedulerService', {
            templateUrl: 'app/core/appointmentSchedulerService_dashboard/appointmentSchedulerService_dashboard.html',
            controller: 'appointmentSchedulerServiceCtrl as ctrl'
        })
        .when('/ServerDetails_AppointmentSchedulerService', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_AppointmentSchedulerService.html',
            controller: 'appointmentSchedulerServiceCtrl as ctrl'
        })
        
         .when('/ICONWEB', {
            templateUrl: 'app/core/iconweb_dashboard/iconweb_dashboard.html',
            controller: 'iconwebCtrl as ctrl'
        })
        .when('/ServerDetails_ICONWEB', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_ICONWEB.html',
            controller: 'iconwebCtrl as ctrl'
        })
        
        .when('/ICONXDOCWSTC7', {
            templateUrl: 'app/core/iconxdocws_dashboard/iconxdocws_dashboard.html',
            controller: 'ICONXDOCWSTC7Ctrl as ctrl'
        })
        
        .when('/ServerDetails_ICONXDOCWSTC7', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_ICONXDOCWSTC7.html',
            controller: 'ICONXDOCWSTC7Ctrl as ctrl'
        })
         .when('/HDIPCIWeb', {
            templateUrl: 'app/core/hdiPciWeb_dashboard/hdiPciWeb_dashboard.html',
            controller: 'HDIPCIWebCtrl as ctrl'
        })
         .when('/ServerDetails_HDIPCIWeb', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_HDIPCIWeb.html',
            controller: 'HDIPCIWebCtrl as ctrl'
        })
         .when('/PurgedOrderRetrieval', {
            templateUrl: 'app/core/purgedOrderRetrieval_dashboard/purgedOrderRetrieval_dashboard.html',
            controller: 'purgedOrderRetrievalCtrl as ctrl'
        })
         .when('/ServerDetails_PurgedOrderRetrieval', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_PurgedOrderRetrieval.html',
            controller: 'purgedOrderRetrievalCtrl as ctrl'
        })
         .when('/TACZDocumentViewerWeb', {
            templateUrl: 'app/core/taczDocumentViewerWeb_dashboard/taczDocumentViewerWeb_dashboard.html',
            controller: 'taczDocumentViewerWebCtrl as ctrl'
        })
         .when('/ServerDetails_TACZDocumentViewerWeb', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_TACZDocumentViewerWeb.html',
            controller: 'taczDocumentViewerWebCtrl as ctrl'
        })
        .when('/MyInstall', {
            templateUrl: 'app/core/myinstall_dashboard/myinstall_dashboard.html',
            controller: 'myinstallCtrl as ctrl'
        })
         .when('/ServerDetails_MyInstall', {
            templateUrl:  'app/core/server_dashboard/monitor_dashboard_MyInstall.html',
            controller: 'myinstallCtrl as ctrl'
        })
	    /*.otherwise({
	            redirectTo: '/IconxMobWS'
	        });*/
	})
	.config(['blockUIConfig',function (blockUIConfig) {
		blockUIConfig.template = '<div class=\"block-ui-overlay\"></div><div class=\"block-ui-message-container\" aria-live=\"assertive\" aria-atomic=\"true\"><div ng-class=\"$_blockUiMessageClass\"><img src="images/loading_orange.gif"/></div></div>';
	}])
	.controller('locationsCtrl', function($rootScope, $scope, $location){
		
		$scope.loadMainPage = function() {
			//$location.path('/');
			$rootScope.$broadcast('homeImageClicked');
		}
	})
    .directive('collapsibleTree', ['$window', '$filter', function($window, $filter) {

        function link(scope, elem, attrs) {
             // ************** Generate the tree diagram	 *****************
        	var newheight = 2400;
        	root = scope.data[0];
            
          /*  if (root.dashBoardName ==='HDCONNECT') newheight =1500;
            if (root.dashBoardName ==='Tickets') newheight =2200;
            if (root.dashBoardName ==='LEAD') newheight =1700;
            if (root.dashBoardName ==='EWR') newheight =1500;
            if (root.dashBoardName ==='ALTC') newheight =1500;
            if (root.dashBoardName ==='HDI') newheight =1700;
            if (root.dashBoardName ==='POUPDATE') newheight =1700;
            if (root.dashBoardName ==='RAPIDPASS') newheight =1600;
            if (root.dashBoardName ==='RENOWALK') newheight =1900;
            if (root.dashBoardName ==='ICONX') newheight =2100;
            if (root.dashBoardName ==='HRS') newheight =2000;*/
            if (root.dashBoardName ==='IconxMobWS') newheight =1500;
            
            var margin = {
            		top: 90,
                    right: 0,
                    bottom: 40,
                    left: 0
                },
                width = $window.innerWidth - margin.right - margin.left+800,
                height = newheight - margin.top - margin.bottom;
               
            
            console.log("inner width ="+$window.innerWidth);
            console.log("Tree width = "+width);
            
            var i = 0,
                duration = 750,
                root;
            
            var tree = d3.layout.tree()
                .size([height, width]);

            var diagonal = d3.svg.diagonal()
                .projection(function(d) {
                    return [d.x, d.y];
                });
            
            var svg = d3.select(elem[0]).append("svg")
                .attr("width", width + margin.right + margin.left)
                .attr("height", height - margin.top - margin.bottom - 600)
                .append("g")
                .attr("transform", "translate(" + -300 + "," + margin.top + ")");
          

            
            
            root.x0 = height / 2;
            root.y0 = 0;

            update(root);

            d3.select(self.frameElement).style("height", "400px");

            function update(source) {
            	
                // Compute the new tree layout.
                var nodes = tree.nodes(root).reverse(),
                    links = tree.links(nodes);

                // Normalize for fixed-depth.
                nodes.forEach(function(d) {
                    d.y = d.depth * 230;
                });

                // Update the nodes…
                var node = svg.selectAll("g.node")
                    .data(nodes, function(d) {
                        return d.id || (d.id = ++i);
                    });
                
                // Enter any new nodes at the parent's previous position.
                var nodeEnter = node.enter().append("g")
                    .attr("class", "node")
                    .attr("transform", function(d) {
                        return "translate(" + source.x0 + "," + source.y0 + ")";
                    })
                    .on("click", click);

                nodeEnter.append("circle")
                    .attr("r", 75)
                    //.style("fill", "#fff")
                    //.style("stroke", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c");
                	.style("stroke", d => (d.status == "error" || d.ltTreshholdSt == "error") ? "red" : (d.status == "warning" || d.ltTreshholdSt == "warning") ? "#ff9900" : "#2ca02c");

                /* nodeEnter.append("text")
    			  .attr("x", function(d) { return d.children || d._children ? 25 : -25; })
    			  .attr("dy", ".35em")
    			  .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
    			  .text(function(d) { return "<span>d.category</span><span>d.errorStatus</span>" })
    			  .style("fill-opacity", 1e-6);*/

                nodeEnter.append("text")
                    .attr("x", 200)
                    .attr("dy", ".35em")
                    .attr("text-anchor", "middle")
                    .style("fill", "#fff")
                    .append('tspan')
                    .attr('x', 1)
                    .attr('font-size', "1.4em")
                    .attr('dy', d => !d.children && !d._children ? -20 : 0)
                    .text(d => d.appName);

                nodeEnter.append("text")
                    .attr("x", 200)
                    .attr("dy", ".35em")
                    .attr("text-anchor", "middle")
                    .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                    .append('tspan')
                    .attr('x', 1)
                    .attr('font-size', "1.3em")
                    .attr('dy', 20)
                    .text(d => (d.url === "DailySales") ? "$ " + d.paymentAmount.toLocaleString("en-US") : (d.appName!="POCOUNT"  && d.dashboardName!="HRSApps" && d.appName!="AMT" && d.url != "FTPS" && d.url != "POUpdate" && d.errPerSec !== null && d.url !== "Dummy Node" && d.dashboardName !=="Tickets") ? d.errPerSec + " errors/hour" : "");

                nodeEnter.append("text")
                    .attr("x", 200)
                    .attr("dy", ".35em")
                    .attr("text-anchor", "middle")
                    .style("fill", d => d.ltTreshholdSt == "error" ? "red" : d.ltTreshholdSt == "warning" ? "#ff9900" : "#2ca02c")
                    .append('tspan')
                    .attr('x', 1)
                    .attr('font-size', "1.3em")
                    .attr('dy', 40)
                    .text(d =>  (d.url === "DailySales") ? "# " + d.paymentCount : (d.url != "FTPS" && d.dashboardName!="HRSApps" && d.dashboardName!="HRSApps" && d.url != "POUpdate" && d.url != "AMT" &&  d.url != "POCOUNT" && d.latencyAvg == null && d.url != null &&d.url != 'Dummy Node') ? "No Data" : (d.latencyAvg == 0 && d.dashboardName!="HRSApps" && d.dashboardName!="Tickets") ? "No Connection" : (d.appName!="AMT" && d.appName!="POCOUNT" && d.dashboardName!="HRSApps" && d.latencyAvg !== null &&  d.url != "POUpdate" && d.url !== "Dummy Node" && d.dashboardName !=="Tickets" && d.appName!="Tickets" ) ? d.latencyAvg + " ms" : "");
                
                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.ltTreshholdSt == "error" ? "red" : d.ltTreshholdSt == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.7em")
                .attr('dy', 40)
                .text(d =>  (d.dashboardName === "Tickets" || d.appName=="Tickets" ) ?  d.latencyAvg :"" );
       
                
                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.3em")
                .attr('dy', 20)
                .text(d => (d.url == "FTPS" && d.fileCount !== null && d.folderName !=null) ? d.fileCount + " Files" : "");
                
                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.3em")
                .attr('dy', 20)
                .text(d => (d.url == "FTPS" && d.fileCount !== null && d.folderName !=null) ? d.fileCount + " Files" : "");

                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.3em")
                .attr('dy', 20)
                .text(d => (d.url == "POUpdate" && d.url != 'Dummy Node') ? d.twoHundredResponce:"" );
                
                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.3em")
                .attr('dy', 40)
                .text(d => (d.url == "POUpdate" && d.url != 'Dummy Node') ? d.fourHundredReaponce:"" );
                
                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.3em")
                .attr('dy', 60)
                .text(d => (d.url == "POUpdate" && d.url != 'Dummy Node') ? d.fiveHundredReaponce:"" );
                
                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.3em")
                .attr('dy', 20)
                .text(d => (d.appName=="POCOUNT" && d.latencyAvg!=null ) ? d.latencyAvg:"" );
                
                
                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.7em")
                .attr('dy', 20)
                .text(d => (d.dashboardName=="HRSApps" && d.latencyAvg!=null ) ?(d.appName=="FUP" || d.appName=="REO-Target" || d.appName=="Total-WIP" ||  d.appName=="B2B-Actual"|| d.appName=="B2B-Target"||  d.appName=="Product-Actual"|| d.appName=="Product-Target"|| d.appName=="Inv->60days" || d.appName=="FUP-WTD" ||  d.appName=="CC-Sales" ||  d.appName=="Daily-Product-Sales"  ||d.appName=="Approval-WTD" || d.appName=="REO-Actual") ?  "$ "+d.latencyAvg.toLocaleString("en-US")+".00":d.latencyAvg.toLocaleString("en-US") : "" );
                
                nodeEnter.append("text")
                .attr("x", 200)
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "#ff9900" : "#2ca02c")
                .append('tspan')
                .attr('x', 1)
                .attr('font-size', "1.3em")
                .attr('dy', 20)
                .text(d => (d.appName=="AMT" && d.latencyAvg!=null ) ? "$"+d.latencyAvg+".00":"" );
             
           

                // Transition nodes to their new position.
                var nodeUpdate = node.transition()
                    .duration(duration)
                    .attr("transform", function(d) {
                        return "translate(" + d.x + "," + d.y + ")";
                    });

                nodeUpdate.select("circle")
                    .attr("r", 75)
                    .style("stroke-width", 15)
                    //.style("fill", d => d.status == "error" ? "red" : d.status == "warning" ? "rgb(255, 255, 26)" : "green"); //AS per Status
                    .style("fill", function(d) 
                    { return "#333333"; }); //Black
                    //{ return "#fff"; }); //White

                nodeUpdate.select("text")
                    .style("fill-opacity", 1);

                // Transition exiting nodes to the parent's new position.
                var nodeExit = node.exit().transition()
                    .duration(duration)
                    .attr("transform", function(d) {
                        return "translate(" + source.x + "," + source.y + ")";
                    })
                    .remove();

                nodeExit.select("circle")
                    .attr("r", 1e-6);

                nodeExit.select("text")
                    .style("fill-opacity", 1e-6);

                // Update the links…
                var link = svg.selectAll("path.link")
                    .data(links, function(d) {
                        return d.target.id;
                    });

                // Enter any new links at the parent's previous position.
                link.enter().insert("path", "g")
                    .attr("class", "link")
                    .style("stroke-width", 5)
                    .attr("d", function(d) {
                        var o = {
                            x: source.x0,
                            y: source.y0
                        };
                        return diagonal({
                            source: o,
                            target: o
                        });
                    })
                    .style("stroke", d => (d.target.status == "error" || d.target.ltTreshholdSt == "error") ? "red" : (d.target.status == "warning" || d.target.ltTreshholdSt == "warning") ? "#ff9900" : "#2ca02c");
                
                // Transition links to their new position.
                link.transition()
                    .duration(duration)
                    .attr("d", diagonal);

                // Transition exiting nodes to the parent's new position.
                link.exit().transition()
                    .duration(duration)
                    .attr("d", function(d) {
                        var o = {
                            x: source.x,
                            y: source.y
                        };
                        return diagonal({
                            source: o,
                            target: o
                        });
                    })
                    .remove();


                // Declare the links…
                // Enter the links.
                link.enter().insert("path", "g")
                    .attr("class", "link")
                    .style("stroke-width", 5)
                    //.style("stroke", d => d.target.status == "error" ? "red" : d.target.status == "warning" ? "#ff9900" : "#2ca02c")
                    .style("stroke", d => (d.target.status == "error" || d.target.ltTreshholdSt == "error") ? "red" : (d.target.status == "warning" || d.target.ltTreshholdSt == "warning") ? "#ff9900" : "#2ca02c")
                    .attr("d", diagonal);

                // Stash the old positions for transition.
                nodes.forEach(function(d) {
                    d.x0 = d.x;
                    d.y0 = d.y;
                });
            }

            // Toggle children on click.
            function click(d) {
            	if(d.appName=='HDI_Integrations'){
            		window.open('/#/FTPS', '_blank'); 
            	} else if (!d.children && !d._children) {
                    console.log(d);
                    scope.$emit('child directive change grid data', {
                        data: {
                            name: d.appName,
                            level: d.level
                        }
                    });
                } else {
                    scope.$emit('child directive collapse grid data', {
                        data: {
                            name: d.appName,
                            level: d.level,
                            collapse: true
                        }
                    });
                }


                if (d.children) {
                    d._children = d.children;
                    d.children = null;
                } else {
                    d.children = d._children;
                    d._children = null;
                }
                update(d);
            }

        };

        return {
            restrict: 'E', // Directive Scope is Element,
            scope: {
                data: '='
            },
            link: link
        };

    }]);