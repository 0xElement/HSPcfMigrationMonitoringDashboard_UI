/**
 * 
 */
package com.homedepot.hs.monitoring.dto;

/**
 * @author syntel
 *
 */
public class DonutChartDTO {

	private String key;
	
	private int y;
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
}
