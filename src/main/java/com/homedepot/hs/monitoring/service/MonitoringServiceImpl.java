package com.homedepot.hs.monitoring.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.homedepot.hs.monitoring.constants.MonitorConstants;
import com.homedepot.hs.monitoring.dao.MonitoringServiceDAOImpl;
import com.homedepot.hs.monitoring.dto.ApplicationDetailsDTO;
import com.homedepot.hs.monitoring.dto.HomeServiceDTO;
import com.homedepot.hs.monitoring.dto.MonitoringResponseDTO;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MonitoringServiceImpl {
	
	@Autowired
    private Environment env;
	
	@Autowired
	private MonitoringServiceDAOImpl monitoringServiceDAOImpl;	
	
	
	
	/**
	 * @param lastUpdatedData
	 * @return
	 */
	public MonitoringResponseDTO getLastHourDataForTree(String dashboardName){
		
		
		MonitoringResponseDTO monitoringResponseDTO = new MonitoringResponseDTO();
		String rootNode="";
		LinkedHashMap<String, MonitoringResponseDTO> map = new LinkedHashMap<>();
		LinkedHashMap<String, MonitoringResponseDTO> map1 = new LinkedHashMap<>();
		LocalDateTime currentDateTime = null;
		LocalDateTime beforeDateTime = null;

		List<Object> inputList = new ArrayList<>();
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MonitorConstants.DB_DT_FORMAT);
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		
		try{
			
			currentDateTime = LocalDateTime.now(MonitorConstants.TIME_ZONE_EST);
			
			int endTime = 0;
			
			if(null != env.getProperty("monitor.app.available.endtime")) {
				endTime = Integer.valueOf(env.getProperty("monitor.app.available.endtime"));
			}
			
			if(endTime != 0 && currentDateTime.getHour() >= Integer.valueOf(env.getProperty("monitor.app.available.endtime"))) {
				currentDateTime = LocalDate.now(MonitorConstants.TIME_ZONE_EST).atTime(Integer.valueOf(env.getProperty("monitor.app.available.endtime")), 0);
			}
			
			beforeDateTime = currentDateTime.minusHours(1);
			
			inputList.add(beforeDateTime.format(formatter));
			inputList.add(currentDateTime.format(formatter));
			
			System.out.println("Before database call selectSample()");
			List<ApplicationDetailsDTO> loadHourDataForTree=null;
			
			if (dashboardName.equals("FileMonitoring")) {
				//loadHourDataForTree= monitoringServiceDAOImpl.loadForFTPTree(dashboardName);
			} else if (dashboardName.equals("DailySales")) {
				//loadHourDataForTree= monitoringServiceDAOImpl.loadDataForDailySalesTree();
			}
			else {
			
				// Hour base Data
				loadHourDataForTree = 
						monitoringServiceDAOImpl.loadHourDataForTree(beforeDateTime.format(formatter), currentDateTime.format(formatter), dashboardName);
					
				if(null == loadHourDataForTree || loadHourDataForTree.isEmpty()) {
			    	String queryForObject = 
			    			monitoringServiceDAOImpl.getLastUpdatedTime();
			    	
			    	if(StringUtils.isNotBlank(queryForObject)) {
			    		
			    		currentDateTime = LocalDateTime.parse(queryForObject, ft);
			    		
			    		beforeDateTime = currentDateTime.minusHours(1);
			    		loadHourDataForTree = 
								monitoringServiceDAOImpl.loadHourDataForTree(beforeDateTime.format(formatter), currentDateTime.format(formatter), dashboardName);
			    		
			    	}
			    }
			}
			
			MonitoringResponseDTO rootDTO = new MonitoringResponseDTO();
			
			 if(null != loadHourDataForTree && !loadHourDataForTree.isEmpty()) {
				 
				 monitoringResponseDTO.setLastUpdatedDate(currentDateTime.format(formatter));
				 monitoringResponseDTO.setLastUpdateDateTime(currentDateTime.format(DateTimeFormatter.ofPattern(MonitorConstants.DT_FORMAT)));
				
				 
				 MonitoringResponseDTO monitorDto = null;
				 
				 if (!dashboardName.equals("FileMonitoring") && !dashboardName.equals("DailySales") && !dashboardName.equals("HRSApps") && !dashboardName.equals("Tickets")) {
				 
					//Group the records based on application name
					 Map<String, List<ApplicationDetailsDTO>> appMap = 
							 loadHourDataForTree.stream().collect(Collectors.groupingBy(p -> p.getApp_name(), Collectors.toList()));
					 
					 for(Map.Entry<String, List<ApplicationDetailsDTO>> mapIt: appMap.entrySet()) {
						 monitorDto = new MonitoringResponseDTO();
						 monitorDto.setAppName(mapIt.getKey());
						 calculateSD(mapIt.getValue(),monitorDto);
						 map1.put(mapIt.getKey(), monitorDto);
					 }
				 }
				 
				 if (dashboardName.equals("Tickets")) {
					
					 monitoringResponseDTO.setDashboardName("Tickests");
				 }
				 
				 
				 for (ApplicationDetailsDTO row : loadHourDataForTree) {
				 String String_TwoH="0";
	        	 String String_FourH="0";
	        	 String String_FiveH="0";
	     
		        	 if (row.getResponceString()!=null) {
		        		 StringTokenizer st = new StringTokenizer(row.getResponceString(),",");
		        		 
				        	 while (st.hasMoreTokens()) {
				        		 String token = st.nextToken();
				        		 
				        		 if(token.startsWith("200")) {
				        			 String_TwoH=token;
				        		 } 
				        		 
				        		 if(token.startsWith("400")) {
				        			 String_FourH=token;
				        		 } 
				        		 
				        		 if(token.startsWith("500")) {
				        			 String_FiveH=token;
				        		 } 
				        	 }
				        	 
				         if (String_TwoH==null )  	 String_TwoH="0";
				         if (String_FourH==null )  	 String_FourH="0";
				         if (String_FiveH==null )  	 String_FiveH="0";
				        	
			        	 monitorDto.setTwoHundredResponce(String_TwoH);
			        	 monitorDto.setFourHundredReaponce(String_FourH);
			        	 monitorDto.setFiveHundredReaponce(String_FiveH);
			     	 }
		   				 monitorDto = new MonitoringResponseDTO();
						 if(row.getStatus() != null && row.getStatus() != 200) {
							 monitorDto.setErrorCount(1);
						 }
						 
						 monitorDto.setAppName(row.getApp_name());
						 monitorDto.setRowCount(1);
						 monitorDto.setParent(row.getParent());
						 monitorDto.setChild(row.getChild());
						 
						 if (dashboardName.equals("FileMonitoring")) {
							
							 monitorDto.setStatus(row.getErrorStatus());
							 monitorDto.setFileCount(row.getFileCount());
							 monitorDto.setFolderName(row.getDirectory());
							 monitorDto.setUrl("FTPS");
							 if(monitorDto.getChild() == null) {
								 ApplicationDetailsDTO ftps_status = monitoringServiceDAOImpl.loadDataForFTPS(monitorDto.getAppName());
								 monitorDto.setStatus(ftps_status.getErrorStatus());
								 monitorDto.setFileCount(ftps_status.getFileCount());
							 }
						 } else if (dashboardName.equals("DailySales")) {							 
							
							 monitorDto.setStatus("success");
							 monitorDto.setType(row.getType());
							 monitorDto.setPaymentCount(row.getPaymentCount());
							 monitorDto.setPaymentAmount(row.getPaymentAmount());
							 monitorDto.setUrl("DailySales");
							 if(monitorDto.getChild() == null) {
								 ApplicationDetailsDTO sales_status = monitoringServiceDAOImpl.loadDataForDailySales(monitorDto.getAppName());
								 monitorDto.setPaymentAmount(sales_status.getPaymentAmount());
								 monitorDto.setPaymentCount(sales_status.getPaymentCount());
							 }
						 } else if (dashboardName.equals("HRSApps") && !row.getUrl().equals("Dummy Node")) {	
							 monitorDto.setStatus("success");
							 monitorDto.setDashboardName(dashboardName);
							 monitorDto.setLatencyAvg(row.getLatency().doubleValue());
							 
						 } else if (dashboardName.equals("Tickets")) {	
							 
							
							 
							 if (row.getApp_name().contains("INBOX")) {
								 if (row.getLatency() >0) {
									 monitorDto.setStatus("error");
								 } else {
									 monitorDto.setStatus("success");
								 }
							 } else {
								 monitorDto.setStatus("success");
							 }
							 monitorDto.setDashboardName(dashboardName);
							 monitorDto.setLatencyAvg(row.getLatency().doubleValue());
							 
							 if (monitorDto.getAppName().equals("Tickets")) {
								 System.out.println(monitorDto.getLatencyAvg());
							 }
							 
						 }	
						 else 
						 {
							 if (!row.getUrl().equals("Dummy Node")){
								 MonitoringResponseDTO dto= map1.get(row.getApp_name());
								 monitorDto.setLatencyAvg(dto.getLatencyAvg());
							 }
							 monitorDto.setTotalLatency(row.getLatency());
							 monitorDto.setLatencyWarnning(row.getLatencyWarnning());
							 monitorDto.setLatencyError(row.getLatencyError());
							 monitorDto.setErrorthreshold(row.getErrorthreshold());
							 monitorDto.setErrorwarnningthreshold(row.getErrorwarnningthreshold());
							
							 monitorDto.setUrl(row.getUrl());
							 monitorDto.setTwoHundredResponce(String_TwoH);
				        	 monitorDto.setFourHundredReaponce(String_FourH);
				        	 monitorDto.setFiveHundredReaponce(String_FiveH);
							
						 }
						 
						if (monitorDto.getParent().equals("ROOT")) {
							rootDTO = monitorDto;
						}
						else {
							 map.put(monitorDto.getAppName(), monitorDto);
						}						
						 
					 }
				
			 }
			 
			 map.put(rootDTO.getAppName(), rootDTO);
			 if (dashboardName.equals("FileMonitoring")) {
				 prepareTreeDataForFTP(map, monitoringResponseDTO, rootNode);
			} else if (dashboardName.equals("DailySales")) {
				 prepareTreeDataForDailySales(map, monitoringResponseDTO, rootNode);
			} else if (dashboardName.equals("POUpdate")) {
				prepareTreePOUpdate(map, monitoringResponseDTO, rootNode);
			}
			 
			else {
				 prepareTreeDataNew(map, monitoringResponseDTO, rootNode);
			}
			 
			 monitoringResponseDTO= monitoringServiceDAOImpl.getAppStatus(monitoringResponseDTO);
			 
			 if (monitoringResponseDTO.getAppName().equals("Tickets")) {
				// if (monitoringResponseDTO.getTicketStatus() !=null && monitoringResponseDTO.getTicketStatus().equals("images/Red.png")) {
				 monitoringResponseDTO = monitoringServiceDAOImpl.updateTicketDeails(monitoringResponseDTO);
					 System.out.println(monitoringResponseDTO.getLatencyAvg());
				
				// }
					 
					 
			 }
			
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return monitoringResponseDTO;
		
	}
	
	
	/**
	 * Method to calculate Standard Deviation
	 * @param appList
	 * @return
	 */
	public void calculateSD(List<ApplicationDetailsDTO> appList, MonitoringResponseDTO appDto) {
		double sum = appList.stream().mapToInt(obj->obj.getLatency()).sum(); // sum of all latency values
		double mean = BigDecimal.valueOf(sum).divide(BigDecimal.valueOf(appList.size()), 2, BigDecimal.ROUND_CEILING).doubleValue(); // mean of latency
		double standardDeviation = 0.0;
		double avg = sum/appList.size();
		double sd =0;
		//Iteration to calculate standardDeviation val
		for(ApplicationDetailsDTO row: appList) {
			standardDeviation += Math.pow(row.getLatency() - mean, 2);
			if(row.getStatus() != null && row.getStatus() != 200) {
				appDto.setErrorCount(appDto.getErrorCount() + 1);
			}
		}
		MathContext ct = new MathContext(2, RoundingMode.CEILING);
		sd =BigDecimal.valueOf(Math.sqrt(standardDeviation/appList.size())).round(ct).doubleValue();
		double avgFinal=0;
		double totalSum=0;
		int count=0;
		for(ApplicationDetailsDTO row: appList) {
			if(!(row.getLatency() > sd+avg)) {
				totalSum +=row.getLatency();
				count+=1;
			}
		}
		
		avgFinal= round(totalSum/count,0);
		
		
		appDto.setLatencyAvg(avgFinal); // Assigning standard deviation to latency average property.
		appDto.setRowCount(count); // List of rows 
		appDto.setTotalLatency(new Double(totalSum).longValue()); // Sum of latency of all rows for an application
	  }
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public void prepareTreeDataForFTP(LinkedHashMap<String, MonitoringResponseDTO> map, MonitoringResponseDTO monitoringResponseDTO, String rootNode) {
		ArrayList<MonitoringResponseDTO> arrayList = new ArrayList<>(map.values());

		ArrayList<MonitoringResponseDTO> apigeeList = new ArrayList<>();
	
		ArrayList<MonitoringResponseDTO> leadList= new ArrayList<>();
	
		boolean errorStr = Boolean.FALSE;
		boolean warnStr = Boolean.FALSE;
		
		
		
		for(MonitoringResponseDTO dto: arrayList) {
			
			//double errorPerSec = dto.getErrorCount()/dto.getRowCount();  
			dto.setErrPerSec((double)dto.getErrorCount());
		
			
			
			if(dto.getParent()!= null && dto.getParent().equals("ROOT")) {
				monitoringResponseDTO.setAppName(dto.getAppName());
				monitoringResponseDTO.setStatus(dto.getStatus());
				
				String child[]=getChildNodes(dto.getChild());
				
				for(int i=0; i<child.length; i++) {
					if(child[i] != null) {
						MonitoringResponseDTO childDTO = new MonitoringResponseDTO();
						childDTO = getNode(map, child[i]);						
						childDTO.setChildren(populateChild(map,child[i]));
						MonitoringResponseDTO dtoNew= getStatus(childDTO);
						apigeeList.add(getStatus(dtoNew));
						
						if ((dtoNew.getStatus()!=null && dtoNew.getStatus().equals(("error")))) {
							monitoringResponseDTO.setStatus("error");
						} else if (monitoringResponseDTO.getStatus()!=null && !monitoringResponseDTO.getStatus().equals("error") &&dtoNew.getStatus()!=null &&  dtoNew.getStatus().equals(("warning"))) {
							monitoringResponseDTO.setStatus("warning");
							
						} else
						{
							monitoringResponseDTO.setStatus("success");
					}
					}
					
				}
		
			}
			
		}
		
		monitoringResponseDTO.setChildren(apigeeList);
		getStatus(monitoringResponseDTO);
	
	}
	
	public void prepareTreePOUpdate(LinkedHashMap<String, MonitoringResponseDTO> map, MonitoringResponseDTO monitoringResponseDTO, String rootNode) {
		
		ArrayList<MonitoringResponseDTO> arrayList = new ArrayList<>(map.values());

		ArrayList<MonitoringResponseDTO> apigeeList = new ArrayList<>();
	
		ArrayList<MonitoringResponseDTO> leadList= new ArrayList<>();
	
		boolean errorStr = Boolean.FALSE;
		boolean warnStr = Boolean.FALSE;
	
		
		for(MonitoringResponseDTO dto: arrayList) {
			
			//double errorPerSec = dto.getErrorCount()/dto.getRowCount();  
			dto.setErrPerSec((double)dto.getErrorCount());
		
			
			if((dto.getUrl()!=null && !dto.getUrl().equals("Dummy Node")) && dto != null) {
				dto.setStatus("success");
				dto.setLtTreshholdSt("success");
			
				
				double avg = dto.getTotalLatency()/(dto.getRowCount());
				dto.setLatencyAvg(avg);
				
				
			}
			
			if(dto.getParent()!= null && dto.getParent().equals("ROOT")) {
				monitoringResponseDTO.setAppName(dto.getAppName());
				monitoringResponseDTO.setStatus("success");
				
				String child[]=getChildNodes(dto.getChild());
				
				for(int i=0; i<child.length; i++) {
					if(child[i] != null) {
						MonitoringResponseDTO childDTO = new MonitoringResponseDTO();
						childDTO = getNode(map, child[i]);						
						childDTO.setChildren(populateChild(map,child[i]));
						apigeeList.add(getStatus(childDTO));
					}
					
				}
		
			}
			
		}
		
		monitoringResponseDTO.setChildren(apigeeList);

	
	}
		
	public void prepareTreeDataNew(LinkedHashMap<String, MonitoringResponseDTO> map, MonitoringResponseDTO monitoringResponseDTO, String rootNode) {
	
		ArrayList<MonitoringResponseDTO> arrayList = new ArrayList<>(map.values());

		ArrayList<MonitoringResponseDTO> apigeeList = new ArrayList<>();
	
		ArrayList<MonitoringResponseDTO> leadList= new ArrayList<>();
	
		boolean errorStr = Boolean.FALSE;
		boolean warnStr = Boolean.FALSE;
		
		int tr_st_err_cnt = 0;
		int tr_st_wrn_cnt = 0;
		int tr_lt_err_cnt = 0;
		int tr_lt_wrn_cnt = 0;
		
		for(MonitoringResponseDTO dto: arrayList) {
			
			//double errorPerSec = dto.getErrorCount()/dto.getRowCount();  
			dto.setErrPerSec((double)dto.getErrorCount());
		
			tr_st_err_cnt = dto.getErrorthreshold();
			tr_st_wrn_cnt = dto.getErrorwarnningthreshold();
			tr_lt_err_cnt = dto.getLatencyError();
			tr_lt_wrn_cnt = dto.getLatencyWarnning();
			
			 if (dto.getAppName()!=null && dto.getAppName().equals("HDI_Integrations")) {
				 String status = monitoringServiceDAOImpl.getHDIStatus(); 
				 
				 if (status!=null && Integer.parseInt(status)==200) {
					 dto.setStatus("error");
					 dto.setLtTreshholdSt("error");
				 }else if(status!=null && Integer.parseInt(status)==100) {
					 dto.setStatus("warning");
					 dto.setLtTreshholdSt("warning");
				 } else {
					 dto.setStatus("success");
				 }
				
			 }
			
			if((dto.getUrl()!=null && !dto.getUrl().equals("Dummy Node")&& !dto.getUrl().equals("db")) && dto != null) {
				if(tr_st_err_cnt <= dto.getErrPerSec()) {
					dto.setStatus("error");
					errorStr =Boolean.TRUE;
				} else if(tr_st_wrn_cnt <= dto.getErrPerSec()) {
					dto.setStatus("warning");
					warnStr = Boolean.TRUE;
				}  else {
					dto.setStatus("success");
				}
				/*
				double avg = dto.getTotalLatency()/(dto.getRowCount());
				dto.setLatencyAvg(avg);
				*/
				if(tr_lt_err_cnt <= dto.getLatencyAvg()|| dto.getLatencyAvg()==0) {
					dto.setLtTreshholdSt("error");
					errorStr =Boolean.TRUE;
				} else if(tr_lt_wrn_cnt <= dto.getLatencyAvg()) {
					dto.setLtTreshholdSt("warning");
					warnStr = Boolean.TRUE;
				}  else {
					dto.setLtTreshholdSt("success");
				}
			}
			
			if(dto.getParent()!= null && dto.getParent().equals("ROOT")) {
				monitoringResponseDTO.setAppName(dto.getAppName());
				
				if(errorStr) {
					monitoringResponseDTO.setStatus("error");
				} else if(warnStr){
					monitoringResponseDTO.setStatus("warning");
				} else {
					monitoringResponseDTO.setStatus("success");
				}
				
				 if (dto.getAppName().equals("Tickets")) {
					 monitoringResponseDTO.setLatencyAvg(dto.getLatencyAvg());
					 monitoringResponseDTO.setStatus("error");
				 }
				 
				String child[]=getChildNodes(dto.getChild());
				
				for(int i=0; i<child.length; i++) {
					if(child[i] != null) {
						MonitoringResponseDTO childDTO = new MonitoringResponseDTO();
						childDTO = getNode(map, child[i]);						
						childDTO.setChildren(populateChild(map,child[i]));
						apigeeList.add(getStatus(childDTO));
					}
					
				}
		
			}
			
		}
		
		monitoringResponseDTO.setChildren(apigeeList);

	
	}
	
	public MonitoringResponseDTO getNode(LinkedHashMap<String, MonitoringResponseDTO> map, String node) {
		MonitoringResponseDTO dtoNode = null;
		
		ArrayList<MonitoringResponseDTO> arrayList = new ArrayList<>(map.values());
		
		for(MonitoringResponseDTO dto : arrayList) {
			if(dto.getAppName().equals(node)) {
				dtoNode = dto;
			}
		}
		
		if (dtoNode == null) {
			dtoNode = new MonitoringResponseDTO();
			dtoNode.setAppName(node);
		}
		
		return dtoNode;
		
	}
	
	
	public ArrayList<MonitoringResponseDTO> populateChild(LinkedHashMap<String, MonitoringResponseDTO> map, String parent) {
		ArrayList<MonitoringResponseDTO> arrayList = new ArrayList<>(map.values());
		ArrayList<MonitoringResponseDTO> apigeeList= new ArrayList<>();
		Hashtable<String, MonitoringResponseDTO> hashtable = new Hashtable<String, MonitoringResponseDTO>();
		
			for(MonitoringResponseDTO dto: arrayList) {
			int index = -1;
			
			
			if(dto.getChild()!=null) {
				index=dto.getChild().indexOf("-");
			}
			
			if(dto.getParent() != null && dto.getParent().equals(parent)) {

				//recursively get additional children
				if(dto.getChild() != null) {
					String subChild[] = getChildNodes(dto.getChild());
					for(int i = 0; i < subChild.length; i++) {
						if(subChild[i] != null) {
//							dto = getNode(map, subChild[i]);
							dto.setChildren(populateChild(map, dto.getAppName()));
							hashtable.put(dto.getAppName(), getStatus(dto));
						}
					}
				}
				if(hashtable.get(dto.getAppName())==null) {
					hashtable.put(dto.getAppName(), dto);
				}
					
			} 
			
			else if ((dto.getChild()!= null && index > 0 &&  dto.getChild().substring(0,index).equals(parent))){				
				MonitoringResponseDTO dtoParent= new MonitoringResponseDTO();
				MonitoringResponseDTO dtoParenttemp= new MonitoringResponseDTO();
				ArrayList<MonitoringResponseDTO> subList;
				dtoParent.setAppName(dto.getParent());
				subList= new ArrayList<>();
				subList.add(dto);
				dtoParent.setChildren(subList);
				dtoParent.setStatus(dto.getStatus());
				dtoParent.setLtTreshholdSt(dto.getLtTreshholdSt());
				
				
				
				if(hashtable.get(dtoParent.getAppName())==null) {
					hashtable.put(dtoParent.getAppName(), dtoParent);
				}
				else
				{
					dtoParenttemp=hashtable.get(dtoParent.getAppName());
					List<MonitoringResponseDTO> dtotemp=dtoParenttemp.getChildren();
					dtotemp.add(dto);
					dtoParenttemp.setChildren(dtotemp);
					hashtable.put(dtoParent.getAppName(), dtoParenttemp);
				}
				
				
			}
					
		}
		
		Enumeration names = hashtable.keys();
		
		while(names.hasMoreElements()) {
			apigeeList.add((MonitoringResponseDTO)hashtable.get(((String)names.nextElement())));
		}
		
		return apigeeList;
	}
	
	public MonitoringResponseDTO getStatus(MonitoringResponseDTO dto) {
		
		if(dto.getChildren()!=null && dto.getChildren().size() >0) {
			
			List<MonitoringResponseDTO> temp = dto.getChildren();
			
			for(MonitoringResponseDTO dtotemp : temp) {			
				
				if(dto.getLtTreshholdSt() == null) {
					dto.setLtTreshholdSt("success");
				}
				if(dto.getStatus() == null) {
					dto.setStatus("success");
				}
				
				if (dtotemp.getStatus() != null && dtotemp.getStatus().equals("error"))	{
					dto.setStatus("error");
				}
				if (dtotemp.getLtTreshholdSt() != null && dtotemp.getLtTreshholdSt().equals("error")) {
					dto.setLtTreshholdSt("error");
				}
				if (dtotemp.getStatus() != null & dto.getStatus()!=null && dtotemp.getStatus().equals("warning") && !dto.getStatus().equals("error")) {
					dto.setStatus("warning");
				}
				if (dtotemp.getLtTreshholdSt() != null & dto.getLtTreshholdSt()!=null &&  dtotemp.getLtTreshholdSt().equals("warning") && !dto.getLtTreshholdSt().equals("error")) {
					dto.setLtTreshholdSt("warning");
				}
				if (dto.getStatus() == null) {
					dto.setStatus("success");
				}
				if (dto.getLtTreshholdSt() == null) {
					dto.setLtTreshholdSt("success");
				}	
			}
			
		}
		return dto;
	}
	
public MonitoringResponseDTO getTotals(MonitoringResponseDTO dto) {
		
		if(dto.getChildren()!=null && dto.getChildren().size() >0) {
			
			List<MonitoringResponseDTO> temp = dto.getChildren();
			
			int totalSales = 0;
			int totalCount = 0;
			
			for(MonitoringResponseDTO dtotemp : temp) {			
				totalSales += dtotemp.getPaymentAmount();
				totalCount += dtotemp.getPaymentCount();
				
			}
			dto.setPaymentAmount(totalSales);
			dto.setPaymentCount(totalCount);
			
		}
		return dto;
	}
	
	public ArrayList<MonitoringResponseDTO> getList(ArrayList<MonitoringResponseDTO> arrayList, String node){
		ArrayList<MonitoringResponseDTO> nodeList= null;
		
		for(MonitoringResponseDTO dto: arrayList) {
			if (dto.getAppName().equals(node)) {
				nodeList= new ArrayList(dto.getChildren());
			}
		}
		return nodeList;
	}
	
	public String[] getChildNodes(String childString) {
		
		StringTokenizer  token= new StringTokenizer(childString,",");
		String children[]=new String[10];
		int i=0;
		while(token.hasMoreTokens()) {
			children[i]=token.nextToken();
			i++;
		}
		
		return children;
		
	}

	/**
	 * @param homeServiceDTO
	 * @return
	 */
	public int addStatus(HomeServiceDTO homeServiceDTO) throws DataAccessException{
		return monitoringServiceDAOImpl.addStatus(homeServiceDTO);
	}
	
	public List<ApplicationDetailsDTO> prepareFTPSTreeDataNew(List<ApplicationDetailsDTO> dtoList) {
        
        List<ApplicationDetailsDTO> appDTOList = new ArrayList<>();
        return monitoringServiceDAOImpl.loadFTPDataForTree();
    
  }


	private  Date cvtToGmt( Date date ){
	    TimeZone tz = TimeZone.getDefault();
	    Date ret = new Date( date.getTime() - tz.getRawOffset() );

	    // if we are now in DST, back off by the delta.  Note that we are checking the GMT date, this is the KEY.
	    if ( tz.inDaylightTime( ret )){
	        Date dstDate = new Date( ret.getTime() - tz.getDSTSavings() );

	        // check to make sure we have not crossed back into standard time
	        // this happens when we are on the cusp of DST (7pm the day before the change for PDT)
	        if ( tz.inDaylightTime( dstDate )){
	            ret = dstDate;
	        }
	     }
	     return ret;
	}
	
	public void prepareTreeDataForDailySales(LinkedHashMap<String, MonitoringResponseDTO> map, MonitoringResponseDTO monitoringResponseDTO, String rootNode) {
		ArrayList<MonitoringResponseDTO> arrayList = new ArrayList<>(map.values());

		ArrayList<MonitoringResponseDTO> apigeeList = new ArrayList<>();
	
		ArrayList<MonitoringResponseDTO> leadList= new ArrayList<>();
	
		boolean errorStr = Boolean.FALSE;
		boolean warnStr = Boolean.FALSE;
		
		
		
		for(MonitoringResponseDTO dto: arrayList) {
			
			//double errorPerSec = dto.getErrorCount()/dto.getRowCount();  
			dto.setErrPerSec((double)dto.getErrorCount());
		
			
			
			if(dto.getParent()!= null && dto.getParent().equals("ROOT")) {
				monitoringResponseDTO.setAppName(dto.getAppName());
				monitoringResponseDTO.setStatus(dto.getStatus());
				monitoringResponseDTO.setUrl(dto.getUrl());
				
				String child[]=getChildNodes(dto.getChild());
				
				for(int i=0; i<child.length; i++) {
					if(child[i] != null) {
						MonitoringResponseDTO childDTO = new MonitoringResponseDTO();
						childDTO = getNode(map, child[i]);						
						childDTO.setChildren(populateChild(map,child[i]));
						MonitoringResponseDTO dtoNew= getStatus(childDTO);
						apigeeList.add(getTotals(dtoNew));
						
						if ((dtoNew.getStatus()!=null && dtoNew.getStatus().equals(("error")))) {
							monitoringResponseDTO.setStatus("error");
						} else if (monitoringResponseDTO.getStatus()!=null && !monitoringResponseDTO.getStatus().equals("error") &&dtoNew.getStatus()!=null &&  dtoNew.getStatus().equals(("warning"))) {
							monitoringResponseDTO.setStatus("warning");
							
						} else
						{
							monitoringResponseDTO.setStatus("success");
					}
					}
					
				}
		
			}
			
		}
		
		monitoringResponseDTO.setChildren(apigeeList);
		getTotals(monitoringResponseDTO);
	
	}

}
