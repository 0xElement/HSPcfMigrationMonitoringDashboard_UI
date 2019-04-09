/**
 * 
 */
package com.homedepot.hs.monitoring.dto;

import java.util.List;

/**
 * @author syntel
 *
 */
public class AppDataDTO {
	
	private List<DonutChartDTO> donutList;
	
	private List<List<String>> linearList;
	
	public void setDonutList(List<DonutChartDTO> donutList) {
		this.donutList = donutList;
	}
	
	public List<DonutChartDTO> getDonutList() {
		return donutList;
	}
	
	public void setLinearList(List<List<String>> linearList) {
		this.linearList = linearList;
	}
	
	public List<List<String>> getLinearList() {
		return linearList;
	}

}
