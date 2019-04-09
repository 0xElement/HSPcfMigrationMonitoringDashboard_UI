/**
 * 
 */
package com.homedepot.hs.monitoring.dto;

import java.util.Map;

/**
 * @author syntel
 *
 */
public class ApplicationResponseDTO {

	private Map<String, AppDataDTO> applicationDataMap;
	
	public void setApplicationDataMap(Map<String, AppDataDTO> applicationDataMap) {
		this.applicationDataMap = applicationDataMap;
	}
	
	public Map<String, AppDataDTO> getApplicationDataMap() {
		return applicationDataMap;
	}
}
