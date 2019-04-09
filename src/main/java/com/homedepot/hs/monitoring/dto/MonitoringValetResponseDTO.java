/**
 * 
 */
package com.homedepot.hs.monitoring.dto;

/**
 * @author syntel
 *
 */
public class MonitoringValetResponseDTO {
	
	private String applicationName;
	
	private int latencyThresholdPercentage;
	
	private int availabilityPercentage;
	
	private int errorPercentage;
	
	private int latencyCount;
	
	private int availabilityCount;
	
	private int errorCount;
	
	private int totalRowCount;
	
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	
	public int getTotalRowCount() {
		return totalRowCount;
	}

	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * @return the latencyThresholdPercentage
	 */
	public int getLatencyThresholdPercentage() {
		return latencyThresholdPercentage;
	}

	/**
	 * @param latencyThresholdPercentage the latencyThresholdPercentage to set
	 */
	public void setLatencyThresholdPercentage(int latencyThresholdPercentage) {
		this.latencyThresholdPercentage = latencyThresholdPercentage;
	}

	/**
	 * @return the availabilityPercentage
	 */
	public int getAvailabilityPercentage() {
		return availabilityPercentage;
	}

	/**
	 * @param availabilityPercentage the availabilityPercentage to set
	 */
	public void setAvailabilityPercentage(int availabilityPercentage) {
		this.availabilityPercentage = availabilityPercentage;
	}

	/**
	 * @return the errorPercentage
	 */
	public int getErrorPercentage() {
		return errorPercentage;
	}

	/**
	 * @param errorPercentage the errorPercentage to set
	 */
	public void setErrorPercentage(int errorPercentage) {
		this.errorPercentage = errorPercentage;
	}

	/**
	 * @return the latencyCount
	 */
	public int getLatencyCount() {
		return latencyCount;
	}

	/**
	 * @param latencyCount the latencyCount to set
	 */
	public void setLatencyCount(int latencyCount) {
		this.latencyCount = latencyCount;
	}

	/**
	 * @return the availabilityCount
	 */
	public int getAvailabilityCount() {
		return availabilityCount;
	}

	/**
	 * @param availabilityCount the availabilityCount to set
	 */
	public void setAvailabilityCount(int availabilityCount) {
		this.availabilityCount = availabilityCount;
	}

	/**
	 * @return the errorCount
	 */
	public int getErrorCount() {
		return errorCount;
	}

	/**
	 * @param errorCount the errorCount to set
	 */
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	
}
