/**
 * 
 */
package com.homedepot.hs.monitoring.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homedepot.hs.monitoring.dao.MonitoringServiceDAOImpl;
import com.homedepot.hs.monitoring.dao.MonitoringValletDAOImpl;
import com.homedepot.hs.monitoring.dto.AppDataDTO;
import com.homedepot.hs.monitoring.dto.ApplicationResponseDTO;
import com.homedepot.hs.monitoring.dto.HomeServiceDTO;
import com.homedepot.hs.monitoring.dto.MonitoringResponseDTO;
import com.homedepot.hs.monitoring.dto.ResponseTo;
import com.homedepot.hs.monitoring.dto.ValetInputDTO;
import com.homedepot.hs.monitoring.service.MonitoringServiceImpl;

/**
 * @author syntel
 *
 */
@RestController
public class MonitoringController {
	
	Logger LOGGER = LoggerFactory.getLogger(MonitoringController.class);
	
	@Autowired
	MonitoringServiceDAOImpl monitoringServiceDAOImpl;
	
	@Autowired
	MonitoringValletDAOImpl monitoringValletDAOImpl;
	
	@Autowired
	MonitoringServiceImpl monitoringServiceImpl;
	
    /**
     * @param lastUpdatedData
     * @return
     */
    @RequestMapping("/getLastHourDataForTree")
    public ResponseTo<MonitoringResponseDTO> getLastHourDataForTree(@RequestParam(value="lastUpdatedData") String lastUpdatedData,
    		@RequestParam(value="dashboardName") String dashboardName){
    	
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("Start: MonitoringController - getLastHourDataForTree()");
		}
    	
    	System.out.println("getLastHourDataForTree start");
    	
    	ResponseTo<MonitoringResponseDTO> response = new ResponseTo<MonitoringResponseDTO>();
    	response.setStatusCode(-1);
    	try{
    		MonitoringResponseDTO searchProjects = monitoringServiceImpl.getLastHourDataForTree(dashboardName);
    		
    		if(null != searchProjects) {
    			response.setResponseData(searchProjects);	
    			response.setStatusCode(0);
    		} else {
    			response.setStatusDesc("No data found, please try later");
    		}
    		
    	}catch (Exception exception) {
    		System.out.println("searchApplications error");
    		LOGGER.error(exception.getMessage(), exception);
    		response.setStatusDesc("Error occured while retrieving data");
		}
    	
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("End: MonitoringController - getLastHourDataForTree()");
		}
    	
    	System.out.println("getLastHourDataForTree end");
    	
    	return response;
    }
    
    /**
     * @param appName
     * @return
     */
    @RequestMapping("/loadApplicationsData")
    public ResponseTo<ApplicationResponseDTO> loadApplicationsData(@RequestParam(value="appName") String appName, 
    		@RequestParam(value="lastUpdatedDate") String lastUpdatedDate){
    	
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("Start: MonitoringController - loadApplicationsData()");
		}
    	
    	ResponseTo<ApplicationResponseDTO> response = new ResponseTo<ApplicationResponseDTO>();
    	response.setStatusCode(-1);
    	try{
    		Map<String, AppDataDTO> applicationData = monitoringServiceDAOImpl.getApplicationData(appName, lastUpdatedDate);
    		
    		if(null != applicationData) {
    			
    			ApplicationResponseDTO applicationResponseDTO = new ApplicationResponseDTO();
    			applicationResponseDTO.setApplicationDataMap(applicationData);
    			
    			response.setResponseData(applicationResponseDTO);	
    			response.setStatusCode(0);
    		} else {
    			response.setStatusDesc("No data found, please try later");
    		}
    		
    	}catch (Exception exception) {
    		LOGGER.error(exception.getMessage(), exception);
    		response.setStatusDesc("Error occured while retrieving data for applications");
		}
    	
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("End: MonitoringController - loadApplicationsData()");
		}
    	return response;
    }
    
    
    /**
     * @param appName
     * @return
     */
    @RequestMapping("/getMonitoringValletInforamtion")
    public ResponseTo<ValetInputDTO> getMonitoringValletInforamtion(){
    	
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("Start: MonitoringController - getMonitoringValletInforamtion()");
		}
    	
    	ResponseTo<ValetInputDTO> response = new ResponseTo<ValetInputDTO>();
    	response.setStatusCode(-1);
    	try{
    		ValetInputDTO valletInformation = monitoringValletDAOImpl.getValletInformation();
    		
    		if(null != valletInformation) {
    			response.setResponseData(valletInformation);	
    			response.setStatusCode(0);
    		} else {
    			response.setStatusDesc("No data found, please try later");
    		}
    		
    	}catch (Exception exception) {
    		LOGGER.error(exception.getMessage(), exception);
    		response.setStatusDesc("Error occured while retrieving data for applications");
		}
    	
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("End: MonitoringController - getMonitoringValletInforamtion()");
		}
    	return response;
    }
    
    
    /**
     * @param lastUpdatedData
     * @return
     */
    @RequestMapping(value = "/addAppMonitoringStatus", method = RequestMethod.POST)
    public ResponseTo<String> addAppMonitoringStatus(@RequestBody HomeServiceDTO homeServiceDTO){
    	
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("Start: MonitoringController - addAppMonitoringStatus()");
		}
    	
    	System.out.println("getLastHourDataForTree start");
    	
    	ResponseTo<String> response = new ResponseTo<String>();
    	response.setStatusCode(-1);
    	try{
    		int addStatus = monitoringServiceImpl.addStatus(homeServiceDTO);
    		
    		if(addStatus == 1) {
    			response.setResponseData("Monitoring status added successfully");	
    			response.setStatusCode(0);
    			response.setStatusDesc("success");
    		} else {
    			response.setStatusDesc("Unable to add monitoring status");
    			response.setStatusDesc("failure");
    		}
    		
    	}catch (Exception exception) {
    		System.out.println("searchApplications error");
    		LOGGER.error(exception.getMessage(), exception);
    		response.setStatusDesc("Error occured while adding monitoring Status");
    		response.setStatusDesc("error");
		}
    	
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("End: MonitoringController - addAppMonitoringStatus()");
		}
    	
    	return response;
    }
}