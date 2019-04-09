package com.homedepot.hs.monitoring.dto;

import java.math.BigDecimal;


public class ValetParameterDTO {

	private BigDecimal availability;
	
	private BigDecimal latency;
	
	private BigDecimal errors;
	
	private BigDecimal volume;
	
	private String applicationId;
	/**
	 * @return the availability
	 */
	public BigDecimal getAvailability() {
		return availability;
	}
	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(BigDecimal availability) {
		this.availability = availability;
	}
	/**
	 * @return the latency
	 */
	public BigDecimal getLatency() {
		return latency;
	}
	/**
	 * @param latency the latency to set
	 */
	public void setLatency(BigDecimal latency) {
		this.latency = latency;
	}
	/**
	 * @return the errors
	 */
	public BigDecimal getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(BigDecimal errors) {
		this.errors = errors;
	}
	/**
	 * @return the volume
	 */
	public BigDecimal getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
}
