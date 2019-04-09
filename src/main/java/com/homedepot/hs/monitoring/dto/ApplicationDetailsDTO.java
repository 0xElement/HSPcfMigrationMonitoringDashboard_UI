/**
 * 
 */
package com.homedepot.hs.monitoring.dto;

import java.util.Date;

/**
 * @author JBC8560
 *
 */
public class ApplicationDetailsDTO {
	
	private Integer status; 
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String time, url, email_sent_st;
	
	private Integer latency;
	
	private String app_name;
	
	private Date dateTime;
	
	private Long ID;
	
	private String dashboardName;
	
	public String getDashboardName() {
		return dashboardName;
	}

	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}

	private Long id;
	
	private String alertFlag;
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getAlertFlag() {
		return alertFlag;
	}

	public void setAlertFlag(String alertFlag) {
		this.alertFlag = alertFlag;
	}

	private int latencyRowCount;
	
	private int latencyWarnning;
	
	private int latencyError;
	
	private int errorthreshold;
	
	private int errorwarnningthreshold;
	
	private String parent;
	
	private String child;
	
	private String directory;
	
	private String schedule;
	
	private int jobinterval;
	
	private int fileCount;
	
	private String errorStatus;
	
	private String app_nm;
	
	private String type;
	
	private int paymentCount;
	
	private int paymentAmount;
	
	private int errorCount;
	
	private String responceString;
	
	private String alertChannels;
	
	public String getAlertChannels() {
		return alertChannels;
	}

	public void setAlertChannels(String alertChannels) {
		this.alertChannels = alertChannels;
	}

	public String getResponceString() {
		return responceString;
	}

	public void setResponceString(String responceString) {
		this.responceString = responceString;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(int paymentCount) {
		this.paymentCount = paymentCount;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getJobinterval() {
		return jobinterval;
	}

	public void setJobinterval(int jobinterval) {
		this.jobinterval = jobinterval;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public int getLatencyWarnning() {
		return latencyWarnning;
	}

	public void setLatencyWarnning(int latencyWarnning) {
		this.latencyWarnning = latencyWarnning;
	}

	public int getLatencyError() {
		return latencyError;
	}

	public void setLatencyError(int latencyError) {
		this.latencyError = latencyError;
	}

	public int getErrorthreshold() {
		return errorthreshold;
	}

	public void setErrorthreshold(int errorthreshold) {
		this.errorthreshold = errorthreshold;
	}

	public int getErrorwarnningthreshold() {
		return errorwarnningthreshold;
	}

	public void setErrorwarnningthreshold(int errorwarnningthreshold) {
		this.errorwarnningthreshold = errorwarnningthreshold;
	}

	public void setLatencyRowCount(int latencyRowCount) {
		this.latencyRowCount = latencyRowCount;
	}
	
	public int getLatencyRowCount() {
		return latencyRowCount;
	}
	
	
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the email_sent_st
	 */
	public String getEmail_sent_st() {
		return email_sent_st;
	}

	/**
	 * @param email_sent_st the email_sent_st to set
	 */
	public void setEmail_sent_st(String email_sent_st) {
		this.email_sent_st = email_sent_st;
	}

	/**
	 * @return the latency
	 */
	public Integer getLatency() {
		return latency;
	}

	/**
	 * @param latency the latency to set
	 */
	public void setLatency(Integer latency) {
		this.latency = latency;
	}

	/**
	 * @return the app_name
	 */
	public String getApp_name() {
		return app_name;
	}

	/**
	 * @param app_name the app_name to set
	 */
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	
	/**
	 * @return the app_nm
	 */
	public String getApp_nm() {
		return app_nm;
	}

	/**
	 * @param app_nm the app_nm to set
	 */
	public void setApp_nm(String app_nm) {
		this.app_nm = app_nm;
	}
	
	
	
}
