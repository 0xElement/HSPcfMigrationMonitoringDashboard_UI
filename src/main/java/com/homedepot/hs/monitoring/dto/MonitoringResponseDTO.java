/**
 * 
 */
package com.homedepot.hs.monitoring.dto;

import java.util.List;

/**
 * @author syntel
 *
 */
public class MonitoringResponseDTO {

	private int errorCount;
	

	public String getInstall2GoTickets() {
		return install2GoTickets;
	}

	public void setInstall2GoTickets(String install2GoTickets) {
		this.install2GoTickets = install2GoTickets;
	}

	public String getThunderheadTickets() {
		return thunderheadTickets;
	}

	public void setThunderheadTickets(String thunderheadTickets) {
		this.thunderheadTickets = thunderheadTickets;
	}

	public String getSibelSecurityTickets() {
		return sibelSecurityTickets;
	}

	public void setSibelSecurityTickets(String sibelSecurityTickets) {
		this.sibelSecurityTickets = sibelSecurityTickets;
	}

	public String getSibelInfraTickets() {
		return sibelInfraTickets;
	}

	public void setSibelInfraTickets(String sibelInfraTickets) {
		this.sibelInfraTickets = sibelInfraTickets;
	}

	public String getHsSupportTickets() {
		return hsSupportTickets;
	}

	public void setHsSupportTickets(String hsSupportTickets) {
		this.hsSupportTickets = hsSupportTickets;
	}

	public String getSibelAppTickets() {
		return sibelAppTickets;
	}

	public void setSibelAppTickets(String sibelAppTickets) {
		this.sibelAppTickets = sibelAppTickets;
	}

	public String getSibelAppTransferTickets() {
		return sibelAppTransferTickets;
	}

	public void setSibelAppTransferTickets(String sibelAppTransferTickets) {
		this.sibelAppTransferTickets = sibelAppTransferTickets;
	}

	private int fileCount;
	
	private String folderName;
	
	private String type;
	
	private String install2GoTickets,thunderheadTickets,sibelSecurityTickets,sibelInfraTickets,hsSupportTickets,sibelAppTickets,sibelAppTransferTickets,hdConnectTickets;
	
	
	
	public String getHdConnectTickets() {
		return hdConnectTickets;
	}

	public void setHdConnectTickets(String hdConnectTickets) {
		this.hdConnectTickets = hdConnectTickets;
	}

	private int paymentCount;
	private String dashboardName;
	public String getDashboardName() {
		return dashboardName;
	}

	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}

	private int paymentAmount;
	private String responceString;
	public String getResponceString() {
		return responceString;
	}

	public void setResponceString(String responceString) {
		this.responceString = responceString;
	}

	private String iconXStatus;
	private String renoWalkStatus;
	private String rapidPassStatus;
	
	public String getTwoHundredResponce() {
		return twoHundredResponce;
	}

	public void setTwoHundredResponce(String twoHundredResponce) {
		this.twoHundredResponce = twoHundredResponce;
	}

	public String getFourHundredReaponce() {
		return fourHundredReaponce;
	}

	public void setFourHundredReaponce(String fourHundredReaponce) {
		this.fourHundredReaponce = fourHundredReaponce;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getFiveHundredReaponce() {
		return fiveHundredReaponce;
	}

	public void setFiveHundredReaponce(String fiveHundredReaponce) {
		this.fiveHundredReaponce = fiveHundredReaponce;
	}

	private int pipelineAmt;
	private int recSaleAmt;
	private int totalSalesAmt;
	private String ftpStatus;
	private String twoHundredResponce;
	private String fourHundredReaponce;
	private String fiveHundredReaponce;
	private String appStatus;
	private String hdeStatus;
	private String leadStatus;
	private String ewrAppStatus;
	private String ticketStatus;
	
	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getAltcStatus() {
		return altcStatus;
	}

	public void setAltcStatus(String altcStatus) {
		this.altcStatus = altcStatus;
	}

	private String altcStatus;
	public String getEwrAppStatus() {
		return ewrAppStatus;
	}

	public void setEwrAppStatus(String ewrAppStatus) {
		this.ewrAppStatus = ewrAppStatus;
	}

	public String getLeadStatus() {
		return leadStatus;
	}

	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}

	public String getHdeStatus() {
		return hdeStatus;
	}

	public void setHdeStatus(String hdeStatus) {
		this.hdeStatus = hdeStatus;
	}

	public String getFtpStatus() {
		return ftpStatus;
	}

	public void setFtpStatus(String ftpStatus) {
		this.ftpStatus = ftpStatus;
	}

	public int getPipelineAmt() {
		return pipelineAmt;
	}

	public void setPipelineAmt(int pipelineAmt) {
		this.pipelineAmt = pipelineAmt;
	}

	public int getRecSaleAmt() {
		return recSaleAmt;
	}

	public void setRecSaleAmt(int recSaleAmt) {
		this.recSaleAmt = recSaleAmt;
	}

	public int getTotalSalesAmt() {
		return totalSalesAmt;
	}

	public void setTotalSalesAmt(int totalSalesAmt) {
		this.totalSalesAmt = totalSalesAmt;
	}

	public String getRapidPassStatus() {
		return rapidPassStatus;
	}

	public void setRapidPassStatus(String rapidPassStatus) {
		this.rapidPassStatus = rapidPassStatus;
	}

	public String getRenoWalkStatus() { 
		return renoWalkStatus;
	}

	public void setRenoWalkStatus(String renoWalkStatus) {
		this.renoWalkStatus = renoWalkStatus;
	}

	public String getRenoPassStatus() {
		return renoPassStatus;
	}

	public void setRenoPassStatus(String renoPassStatus) {
		this.renoPassStatus = renoPassStatus;
	}

	public String getHdConnectStatus() {
		return hdConnectStatus;
	}

	public void setHdConnectStatus(String hdConnectStatus) {
		this.hdConnectStatus = hdConnectStatus;
	}

	private String renoPassStatus;
	private String hdConnectStatus;
	
	public String getIconXStatus() {
		return iconXStatus;
	}

	public void setIconXStatus(String iconXStatus) {
		this.iconXStatus = iconXStatus;
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

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	private Double latencyAvg;
	
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

	private long totalLatency;
	
	private String appName;
	
	private boolean error;
	
	private String status;
	
	private int rowCount;
	
	private int order;
	
	private Double errPerSec;
	
	private String ltTreshholdSt;
	
	private List<MonitoringResponseDTO> children;
	
	private String lastUpdatedDate;
	
	private int availabilityCount;
	
	private int latencyTresholdCount;
	
	private String dbStatus;
	
	private String lastUpdateDateTime;
	
	private int latencyWarnning;
	
	private int latencyError;
	
	private int errorthreshold;
	
	private int errorwarnningthreshold;
	 
	private String parent;
	
	private String child;
	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public void setDBStatus(String dbStatus) {
		this.dbStatus=dbStatus;
	}
	
	public String getDBStatus() {
		return dbStatus;
	}
	
	public void setLastUpdateDateTime(String lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}
	
	public String getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}
	
	
	public void setLatencyTresholdCount(int latencyTresholdCount) {
		this.latencyTresholdCount = latencyTresholdCount;
	}
	
	public int getLatencyTresholdCount() {
		return latencyTresholdCount;
	}
	
	public void setAvailabilityCount(int availabilityCount) {
		this.availabilityCount = availabilityCount;
	}
	
	public int getAvailabilityCount() {
		return availabilityCount;
	}
	
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
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

	/**
	 * @return the latencyAvg
	 */
	public Double getLatencyAvg() {
		return latencyAvg;
	}

	/**
	 * @param latencyAvg the latencyAvg to set
	 */
	public void setLatencyAvg(Double latencyAvg) {
		this.latencyAvg = latencyAvg;
	}

	/**
	 * @return the totalLatency
	 */
	public long getTotalLatency() {
		return totalLatency;
	}

	/**
	 * @param totalLatency the totalLatency to set
	 */
	public void setTotalLatency(long totalLatency) {
		this.totalLatency = totalLatency;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the errPerSec
	 */
	public Double getErrPerSec() {
		return errPerSec;
	}

	/**
	 * @param errPerSec the errPerSec to set
	 */
	public void setErrPerSec(Double errPerSec) {
		this.errPerSec = errPerSec;
	}

	/**
	 * @return the ltTreshholdSt
	 */
	public String getLtTreshholdSt() {
		return ltTreshholdSt;
	}

	/**
	 * @param ltTreshholdSt the ltTreshholdSt to set
	 */
	public void setLtTreshholdSt(String ltTreshholdSt) {
		this.ltTreshholdSt = ltTreshholdSt;
	}

	/**
	 * @return the children
	 */
	public List<MonitoringResponseDTO> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<MonitoringResponseDTO> children) {
		this.children = children;
	}
	
}
