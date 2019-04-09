/**
 * 
 */
package com.homedepot.hs.monitoring.dto;

import java.util.List;
import java.util.Map;

/**
 * @author JBC8560
 *
 */
public class ResponseTo<T> {
	
	private int statusCode;
	private String statusDesc;
	
	private T responseData;
	private List<T> responseDataList;
	
	private Map<T, T> responseMap;
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	/**
	 * @param statusDesc the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	/**
	 * @return the responseData
	 */
	public T getResponseData() {
		return responseData;
	}
	/**
	 * @param responseData the responseData to set
	 */
	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}
	/**
	 * @return the responseDataList
	 */
	public List<T> getResponseDataList() {
		return responseDataList;
	}
	/**
	 * @param responseDataList the responseDataList to set
	 */
	public void setResponseDataList(List<T> responseDataList) {
		this.responseDataList = responseDataList;
	}
	/**
	 * @return the responseMap
	 */
	public Map<T, T> getResponseMap() {
		return responseMap;
	}
	/**
	 * @param responseMap the responseMap to set
	 */
	public void setResponseMap(Map<T, T> responseMap) {
		this.responseMap = responseMap;
	}
	
	

}
