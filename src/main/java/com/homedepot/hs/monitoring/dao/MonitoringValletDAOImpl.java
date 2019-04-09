/**
 * 
 */
package com.homedepot.hs.monitoring.dao;

import com.homedepot.hs.monitoring.constants.MonitorConstants;
import com.homedepot.hs.monitoring.constants.MonitorSqlConstatns;
import com.homedepot.hs.monitoring.dto.ApplicationDetailsDTO;
import com.homedepot.hs.monitoring.dto.MonitoringResponseDTO;
import com.homedepot.hs.monitoring.dto.ValetInputDTO;
import com.homedepot.hs.monitoring.dto.ValetParameterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author syntel
 *
 */
@Repository
public class MonitoringValletDAOImpl {
	
	Logger LOGGER = LoggerFactory.getLogger(MonitoringValletDAOImpl.class);

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Autowired
    private Environment env;
	
	/**
	 * @param lastUpdatedData
	 * @return
	 */
	public ValetInputDTO getValletInformation() throws Exception{
		
		
		LOGGER.debug("Enter: MonitoringValletDAOImpl - getValletInformation()");
		
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		ApplicationDetailsDTO appDetails = null;
		Map<String, MonitoringResponseDTO> map = new HashMap<>();
		//List<MonitoringValetResponseDTO> valtList = new ArrayList<>();
		
		ValetInputDTO valetInputDTO = new ValetInputDTO();
		
		try{
			
			
			Date d1 = new Date(System.currentTimeMillis());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d1);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			
			Date d2 = cal.getTime();
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			List<Object> inputList = new ArrayList<>();
			inputList.add(format.format(d2));
			inputList.add(format.format(d1));
			
			List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_TREE_DATA, inputList.toArray());
			
			 if(null != queryForList && !queryForList.isEmpty()) {
			    	for (Map<String, Object> row : queryForList) {
			    		 appDetails = new ApplicationDetailsDTO();
			    		if(null != row.get("app_name")) {
			    			appDetails.setApp_name(row.get("app_name").toString());
			    		}
			    		
			    		if(null != row.get("url")) {
			    			appDetails.setUrl(row.get("url").toString());
			    		}
			    		
			    		if(null != row.get("time")) {
			    			appDetails.setTime(row.get("time").toString());
			    		}
			    		
			    		if(null != row.get("status")) {
			    			appDetails.setStatus(Integer.valueOf(row.get("status").toString()));
			    		}
			    		
			    		if(null != row.get("latency")) {
			    			appDetails.setLatency(Integer.valueOf(row.get("latency").toString()));
			    		}
			    		
			    		list.add(appDetails);
			    	}
			    	
			    	MonitoringResponseDTO monitorDto = null;
			    	
			    	int latencyThreshHold = Integer.valueOf(env.getProperty("monitor.app.valet.latencyThreshHold"));
			    	
			    	for (ApplicationDetailsDTO row : list) {
			    		if(map.get(row.getApp_name()) != null) {
			    			if(row.getStatus() != null && row.getStatus() != MonitorConstants.STATUS_SUCCESS) {
			    				map.get(row.getApp_name()).setErrorCount(map.get(row.getApp_name()).getErrorCount()+1);
			    			}else {
			    				map.get(row.getApp_name()).setAvailabilityCount(map.get(row.getApp_name()).getAvailabilityCount()+1);
			    			}
			    			
			    			if(row.getLatency() <= latencyThreshHold) {
			    				map.get(row.getApp_name()).setLatencyTresholdCount(map.get(row.getApp_name()).getLatencyTresholdCount()+1);
			    			}
			    			map.get(row.getApp_name()).setRowCount(map.get(row.getApp_name()).getRowCount()+1);
			    		} else {
			    			monitorDto = new MonitoringResponseDTO();
			    			if(row.getStatus() != null && row.getStatus() != MonitorConstants.STATUS_SUCCESS) {
			    				monitorDto.setErrorCount(1);
			    			} else {
			    				monitorDto.setAvailabilityCount(1);
			    			}
			    			
			    			if(row.getLatency() <= latencyThreshHold) {
			    				monitorDto.setLatencyTresholdCount(1);
			    			}
			    			monitorDto.setAppName(row.getApp_name());
			    			monitorDto.setRowCount(1);
			    			map.put(row.getApp_name(), monitorDto);
			    			
			    		}
			    		
			    	}
			    	
			    	
			    	ArrayList<MonitoringResponseDTO> arrayList = new ArrayList<>(map.values());
			    	
			    	
			    	
			    	List<ValetParameterDTO> valetParameters = new ArrayList<>();
			    	ValetParameterDTO valetParameterDTO = null;
			    	
			    	
			    	//MonitoringValetResponseDTO valetDto = null;
			    	float percentageVal;
			    	float rowCount;
			    	for(MonitoringResponseDTO dto: arrayList) {
			    		valetParameterDTO = new ValetParameterDTO();
			    		
			    		rowCount = Integer.valueOf(dto.getRowCount()).floatValue();

			    		percentageVal = Integer.valueOf(dto.getLatencyTresholdCount()).floatValue() / rowCount;
			    		valetParameterDTO.setLatency(BigDecimal.valueOf(percentageVal).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING));
			    		
			    		percentageVal = Integer.valueOf(dto.getAvailabilityCount()).floatValue() / rowCount;
			    		valetParameterDTO.setAvailability(BigDecimal.valueOf(percentageVal).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING));
			    		
			    		percentageVal = Integer.valueOf(dto.getErrorCount()).floatValue() / rowCount;
			    		valetParameterDTO.setErrors(BigDecimal.valueOf(percentageVal).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING));
			    		
			    		valetParameterDTO.setVolume(BigDecimal.valueOf(dto.getRowCount()));
			    		
			    		valetParameters.add(valetParameterDTO);
			    		
			    		/*valetDto = new  MonitoringValetResponseDTO();
			    		valetDto.setLatencyThresholdPercentage((int)(percentageVal * 100));
			    		valetDto.setAvailabilityPercentage((int)(percentageVal * 100));
			    		valetDto.setErrorPercentage((int)(percentageVal * 100));
			    		valetDto.setApplicationName(dto.getAppName());
			    		valetDto.setLatencyCount(dto.getLatencyTresholdCount());
			    		valetDto.setAvailabilityCount(dto.getAvailabilityCount());
			    		valetDto.setErrorCount(dto.getErrorCount());
			    		valetDto.setTotalRowCount(dto.getRowCount());
			    		valtList.add(valetDto);*/
			    		
			    	}
			    	
			    	
			    	
			    	valetInputDTO.setValetParameters(valetParameters);
			    	RestTemplate restTemplate = new RestTemplate();
			    	String url = env.getProperty("monitor.app.valet.url");
			    	ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, valetInputDTO, String.class);
			    	LOGGER.debug("Response: "+ postForEntity.getBody());
			    }
		}catch(Exception e){
			throw e;
		}
		LOGGER.debug("Exit: MonitoringValletDAOImpl - getValletInformation()");
		return valetInputDTO;
		
	}
	
}
