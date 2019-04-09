/**
 * 
 */
package com.homedepot.hs.monitoring.dao;


import com.homedepot.hs.monitoring.constants.MonitorSqlConstatns;
import com.homedepot.hs.monitoring.dto.AppDataDTO;
import com.homedepot.hs.monitoring.dto.ApplicationDetailsDTO;
import com.homedepot.hs.monitoring.dto.DonutChartDTO;
import com.homedepot.hs.monitoring.dto.HomeServiceDTO;
import com.homedepot.hs.monitoring.dto.MonitoringResponseDTO;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author syntel
 *
 */
@Repository
@Slf4j
public class MonitoringServiceDAOImpl{

	@Autowired
    JdbcTemplate jdbcTemplate;
	

 public List<ApplicationDetailsDTO> loadHourDataForTree(String beforeDate, String currentDate, String dashboardName) {
		
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		List<Map<String, Object>> queryForList=null;
		List<Object> inputList = new ArrayList<>();
		inputList.add(beforeDate);
		inputList.add(currentDate);
		inputList.add(dashboardName);
		
		if (dashboardName.equals("HDI")) {
			queryForList=jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_HDE_TREE_DATA, inputList.toArray());
		}
		else {
			queryForList=jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_TREE_DATA, inputList.toArray());
		}
		
		
		if(null != queryForList && !queryForList.isEmpty()) {
			list = getList(queryForList, Boolean.FALSE);
		}
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		if(queryForList == null) {
			list.add(dto);
		}
		
		return list;
	}
 
 	public String  calculateSD(List<ApplicationDetailsDTO> appList, MonitoringResponseDTO appDto) {
		double sum = appList.stream().mapToInt(obj->obj.getLatency()).sum(); // sum of all latency values
		double mean = BigDecimal.valueOf(sum).divide(BigDecimal.valueOf(appList.size()), 2, BigDecimal.ROUND_CEILING).doubleValue(); // mean of latency
		double standardDeviation = 0.0;
		double avg = sum/appList.size();
		double sd =0;
		String Status="images/Green.png";
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
		
		avgFinal= totalSum/count;
		
		if (avgFinal >5000) {
			Status ="images/Red.png";
		} else if (avgFinal >4000) {
			Status="images/Amber.png";
		}
		return Status;
 	}
		
	

	 public int  getLeadStatus() throws DataAccessException{
		 List<Map<String, Object>> queryForList=jdbcTemplate.queryForList("select * from app_status where status='images/Red.png' AND APP_NAME='LeadMgmt'"); 
		 return queryForList.size();
	 }
	 
	 public MonitoringResponseDTO getAppStatus(MonitoringResponseDTO monitoringResponseDTO) throws DataAccessException{
			
		
		 List<Map<String, Object>> queryForList=jdbcTemplate.queryForList("Select * from app_status"); 
		
		 
		for (Map<String, Object> row : queryForList) {
			
			
			if (null != row.get("app_name")) {
				System.out.println("App Name ---  "+row.get("app_name").toString());
				switch (row.get("app_name").toString()) {
				case "ICONX":
					monitoringResponseDTO.setIconXStatus(row.get("status").toString());
					break;	
				case "Reno":
					monitoringResponseDTO.setRenoWalkStatus(row.get("status").toString());
					break;	
				case "RapidPass":
					monitoringResponseDTO.setRapidPassStatus(row.get("status").toString());
					break;
				case "HDConnect":
					monitoringResponseDTO.setHdConnectStatus(row.get("status").toString());
					break;
				case "FTPStatus":
					monitoringResponseDTO.setFtpStatus(row.get("status").toString());
					break;
				case "HDE":
					monitoringResponseDTO.setHdeStatus(row.get("status").toString());
					break;
				case "LeadMgmt":
					monitoringResponseDTO.setLeadStatus(row.get("status").toString());
					break;
				case "ALTC":
					monitoringResponseDTO.setAltcStatus(row.get("status").toString());
					break;
				case "EWRApp":
					monitoringResponseDTO.setEwrAppStatus(row.get("status").toString());
					break;
				case "Tickets":
					monitoringResponseDTO.setTicketStatus(row.get("status").toString());
					break;
				case "Finance":
					monitoringResponseDTO.setPipelineAmt(Integer.parseInt(row.get("actual_sales").toString())); 
					monitoringResponseDTO.setRecSaleAmt(Integer.parseInt(row.get("exected_sales").toString())); 
					monitoringResponseDTO.setTotalSalesAmt(monitoringResponseDTO.getPipelineAmt()+monitoringResponseDTO.getRecSaleAmt());
					break;
				default:
					break;
			}
			
				
			}
		}
		
		return monitoringResponseDTO;
	}
 
 	public List<ApplicationDetailsDTO> loadDataForDailySalesTree() {
		
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		
				
		List<Map<String, Object>> queryForList = 
							jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_DAILY_SALES_TREE_DATA);
		
		if(null != queryForList && !queryForList.isEmpty()) {
			list = getList(queryForList, Boolean.FALSE);
		}
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		if(queryForList == null) {
			list.add(dto);
		}
		
		return list;
	}
 	
 	public ApplicationDetailsDTO loadDataForDailySales(String appName) {
		
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		
		List<String> inputList = new ArrayList<>();
		inputList.add(appName);
		
				
		List<Map<String, Object>> queryForList = 
							jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_DAILY_SALES, inputList.toArray());
		
		if(null != queryForList && !queryForList.isEmpty()) {
			for(Map<String, Object> row : queryForList) {
				if(row.get("PAYMT_CNT") != null) {
					dto.setPaymentCount((int)row.get("PAYMT_CNT"));
				}
				if(row.get("pymt_amt") != null) {
					dto.setPaymentAmount((int)row.get("pymt_amt"));
				}
			}
		}
		
		return dto;
	}
 	
 	
public MonitoringResponseDTO updateTicketDeails(MonitoringResponseDTO dto) {
 		
 		
		
		List<Map<String, Object>> queryForList = 
							jdbcTemplate.queryForList("select * from ticket_status");
		String responceString="";
		String responceStringNew;
		for (Map<String, Object> row : queryForList) {
			responceStringNew="";
			if (row.get("responce_string")!="") {
				responceString =(String)row.get("responce_string");
				StringTokenizer st1 =
			             new StringTokenizer(responceString, ",");
			        while (st1.hasMoreTokens()) {
			        	String token = st1.nextToken();
			        	String url ="https://homedepot.service-now.com/incident_list.do?sysparm_query=numberSTARTSWITH"
			        					+token
			        					+"&sysparm_first_row=1&sysparm_view=ess&sysparm_choice_query_raw=&sysparm_list_header_search=true";
			        	
			        	String newURL = "<a   target=\"_blank\" href="+"\"" + url + "\""+">"+token+"</a>";
			        	responceStringNew =responceStringNew+newURL +",";
			        }
			        if (responceStringNew.length()>0)
			        	responceStringNew = responceStringNew.substring(0,responceStringNew.length()-1);
			}
			
			if (row.get("app_name").equals("SIEBEL-APP")) {
				dto.setSibelAppTickets(responceStringNew);
			} else if (row.get("app_name").equals("TRANSFER")) {
				dto.setSibelAppTransferTickets(responceStringNew);
			} else if (row.get("app_name").equals("HDCONNECT-SES")) {
				dto.setHdConnectTickets(responceStringNew);
			} else if (row.get("app_name").equals("HS-SUPPORT")) {
				dto.setHsSupportTickets(responceStringNew);
			} else if (row.get("app_name").equals("SIEBEL-INFRA")) {
				dto.setSibelInfraTickets(responceStringNew);
			} else if (row.get("app_name").equals("SIEBEL-SECURITY")) {
				dto.setSibelSecurityTickets(responceStringNew);
			} else if (row.get("app_name").equals("THUNDERHEAD")) {
				dto.setThunderheadTickets(responceStringNew);
			}
			
		}
		
		
		
		return dto;
	}
	
 
 	public List<ApplicationDetailsDTO> loadFTPDataForTree() {
 		
 		List<ApplicationDetailsDTO> list = new ArrayList<>();
		
		
		List<Map<String, Object>> queryForList = 
							jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_FTPS_TREE_DATA);
		
		if(null != queryForList && !queryForList.isEmpty()) {
			list = getList(queryForList, Boolean.FALSE);
		}
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		if(queryForList == null) {
			list.add(dto);
		}
		
		return list;
	}
 	
public ApplicationDetailsDTO loadDataForFTPS(String appName) {
		
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		
		List<String> inputList = new ArrayList<>();
		inputList.add(appName);
		
				
		List<Map<String, Object>> queryForList = 
							jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_FTPS, inputList.toArray());
		
		if(null != queryForList && !queryForList.isEmpty()) {
			for(Map<String, Object> row : queryForList) {
				if(row.get("status") != null) {
					dto.setErrorStatus(row.get("status").toString());
				}
				if(row.get("file_count") != null) {
					dto.setFileCount((int)row.get("file_count"));
				}
			}
		}
		
		return dto;
	}
 
 	
	public List<ApplicationDetailsDTO> loadForFTPTree(String dashboardName) {
		
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		
		List<Object> inputList = new ArrayList<>();
		inputList.add(dashboardName);
		
		List<Map<String, Object>> queryForList = 
							jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_FTP_TREE_DATA, inputList.toArray());
		
		if(null != queryForList && !queryForList.isEmpty()) {
			list = getList(queryForList, Boolean.FALSE);
		}
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		if(queryForList == null) {
			list.add(dto);
		}
		
		return list;
	}
	
	public String getLastUpdatedTime() {
		return jdbcTemplate.queryForObject(MonitorSqlConstatns.SQL_SELECT_LAST_UPDATED_DATATIME, String.class);
	}
	
	
	/**
	 * @param homeServiceBean
	 * @return
	 * @throws DataAccessException
	 */
	public int addStatus(HomeServiceDTO homeServiceBean) throws DataAccessException{
		return jdbcTemplate.update("INSERT INTO HOMESERVICE_STATUS(status, time, url, app_name, latency) VALUES(?, ?, ?, ?, ?)",
				homeServiceBean.getStatus(), homeServiceBean.getTime(), homeServiceBean.getUrl(), homeServiceBean.getAppName(), homeServiceBean.getLatency());
	}
	
	public String getHDIStatus() {
		return jdbcTemplate.queryForObject("select max(status) from ftp_status", String.class);
	}
	
	/**
	 * Latency Count Calculation per 3 min
	 * 
	 * @param list
	 * @param d1
	 * @return
	 */
	public Map<Date, ApplicationDetailsDTO> getLatencyCountPerTime(List<ApplicationDetailsDTO> list, Date d1) {        
		int minutesCoount = 180;
        Date dt = new Date(d1.getTime()+ minutesCoount * 1000);
		Map<Date, ApplicationDetailsDTO> latencyMap = new HashMap<>();
		ApplicationDetailsDTO detailsDTO = null;
		if(list != null && !list.isEmpty()) {
			detailsDTO = new ApplicationDetailsDTO();
			detailsDTO.setLatency(0);
			latencyMap.put(dt, detailsDTO);
			
			for(ApplicationDetailsDTO data: list) {
				if(data.getDateTime().getTime() <= dt.getTime()) {
					detailsDTO = latencyMap.get(dt);
					detailsDTO.setLatency(detailsDTO.getLatency() + data.getLatency());
					detailsDTO.setLatencyRowCount(detailsDTO.getLatencyRowCount() + 1);
				} else {
					while(minutesCoount < 3600) {
						minutesCoount += 180;
						dt = new Date(d1.getTime() + minutesCoount * 1000);
						
						if(data.getDateTime().getTime() > dt.getTime()) {
							detailsDTO = new ApplicationDetailsDTO();
							detailsDTO.setLatency(0);
							latencyMap.put(dt, detailsDTO);
						} else {

							detailsDTO = latencyMap.get(dt);
							
							if(null == detailsDTO) {
								detailsDTO = new ApplicationDetailsDTO();
								detailsDTO.setLatency(0);
								latencyMap.put(dt, detailsDTO);
							}
							detailsDTO.setLatency(detailsDTO.getLatency() + data.getLatency());
							detailsDTO.setLatencyRowCount(detailsDTO.getLatencyRowCount() + 1);
							break;
						}
					}
				}
			}
			
			while(minutesCoount < 3600) {
				minutesCoount += 180;
				dt = new Date(d1.getTime() + minutesCoount * 1000);
				detailsDTO = new ApplicationDetailsDTO();
				detailsDTO.setLatency(0);
				latencyMap.put(dt, detailsDTO);
			}
			
		}
		return latencyMap;
         
  }
	
	/**
	 * Latency Count Calculation per hour
	 * 
	 * @param list
	 * @param d1
	 * @return
	 */
	public Map<Date, ApplicationDetailsDTO> getLatencyCountPerDay(List<ApplicationDetailsDTO> list, Date d1) {
		int minutesCoount = 3600;
        Date dt = new Date(d1.getTime()+ minutesCoount * 1000);
		Map<Date, ApplicationDetailsDTO> latencyMap = new HashMap<>();
		ApplicationDetailsDTO detailsDTO = null;
		if(list != null && !list.isEmpty()) {
			detailsDTO = new ApplicationDetailsDTO();
			detailsDTO.setLatency(0);
			latencyMap.put(dt, detailsDTO);
			
			for(ApplicationDetailsDTO data: list) {
				if(data.getDateTime().getTime() <= dt.getTime()) {
					detailsDTO = latencyMap.get(dt);
					detailsDTO.setLatency(detailsDTO.getLatency() + data.getLatency());
					detailsDTO.setLatencyRowCount(detailsDTO.getLatencyRowCount() + 1);
				} else {
					while(minutesCoount < 86400) {
						minutesCoount += 3600;
						dt = new Date(d1.getTime() + minutesCoount * 1000);
						
						if(data.getDateTime().getTime() > dt.getTime()) {
							detailsDTO = new ApplicationDetailsDTO();
							detailsDTO.setLatency(0);
							latencyMap.put(dt, detailsDTO);
						} else {

							detailsDTO = latencyMap.get(dt);
							
							if(null == detailsDTO) {
								detailsDTO = new ApplicationDetailsDTO();
								detailsDTO.setLatency(0);
								latencyMap.put(dt, detailsDTO);
							}
							detailsDTO.setLatency(detailsDTO.getLatency() + data.getLatency());
							detailsDTO.setLatencyRowCount(detailsDTO.getLatencyRowCount() + 1);
							break;
						}
					}
				}
			}
			
			while(minutesCoount < 86400) {
				minutesCoount += 180;
				dt = new Date(d1.getTime() + minutesCoount * 1000);
				detailsDTO = new ApplicationDetailsDTO();
				detailsDTO.setLatency(0);
				latencyMap.put(dt, detailsDTO);
			}
			
		}
		return latencyMap;
	}
	
	/**
	 * @param date
	 * @param days
	 * @return
	 */
	public Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	
	/**
	 * Latency Count Calculation per day
	 * 
	 * @param list
	 * @param d1
	 * @return
	 */
	
	public Map<Date, ApplicationDetailsDTO> getLatencyCountPerWeek(List<ApplicationDetailsDTO> list, Date date,int days) {
		
		Date convertFormat = convertFormat(date);
		Map<Date, ApplicationDetailsDTO> returnDaysmap = returnDaysmap(convertFormat, days);
		Date formatedDate = null;
		ApplicationDetailsDTO appData = null;
		if(list != null && !list.isEmpty()) {
			for(ApplicationDetailsDTO data: list) {
				
				formatedDate = convertFormat(data.getDateTime());
				appData = returnDaysmap.get(formatedDate);
				
				if(appData != null) {
					if(appData.getLatency()!=null) {
						appData.setLatency(appData.getLatency() + data.getLatency());
					}
					appData.setLatencyRowCount(appData.getLatencyRowCount() + 1);
				}
			}
		}
		return returnDaysmap;
	}
	
 public Map<Date, ApplicationDetailsDTO> getLatencyCountPerWeekTemp(List<ApplicationDetailsDTO> list, Date date,int days) {
		
		Date convertFormat = convertFormat(date);
		Map<Date, ApplicationDetailsDTO> returnDaysmap = returnDaysmap(convertFormat, days);
		Date formatedDate = null;
		ApplicationDetailsDTO appData = null;
		if(list != null && !list.isEmpty()) {
			for(ApplicationDetailsDTO data: list) {
				
				formatedDate = convertFormat(data.getDateTime());
				appData = returnDaysmap.get(formatedDate);
				
				if(appData != null) {
					appData.setLatencyRowCount(appData.getLatencyRowCount() + 1);
					System.out.println("Latency Count   ***** "+appData.getDateTime()+" "+ appData.getLatencyRowCount());
				}
				
			}
			
		}
		return returnDaysmap;
	}
	
	/**
	 * @param date
	 * @return
	 */
	public Map<Date, ApplicationDetailsDTO> returnDaysmap(Date date, int days) {
		Map<Date, ApplicationDetailsDTO> mapData = new TreeMap<>();
		
		ApplicationDetailsDTO applicationDetailsDTO = new ApplicationDetailsDTO(); 
		applicationDetailsDTO.setLatency(0);
		
		mapData.put(date, applicationDetailsDTO);
		Date d1 = null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int j = -1;
		for(int i =1; i<days; i++) {
	        cal.add(Calendar.DATE, j); //minus number would decrement the days
			d1 = cal.getTime();
			applicationDetailsDTO = new ApplicationDetailsDTO(); 
			applicationDetailsDTO.setLatency(0);
			mapData.put(d1, applicationDetailsDTO);
		}
		return mapData;
	}

	/**
	 * @param appName
	 * @param lastUpdatedDate
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, AppDataDTO> getApplicationData(String appName, String lastUpdatedDate) throws ParseException{
		
		Map<String, AppDataDTO> appDetails = new HashMap<>();
		
		
		// Hour base Data
		List<Object> inputList = new ArrayList<>();
		Date d1 = null;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(StringUtils.isNotBlank(lastUpdatedDate)) {
			d1 = format.parse(lastUpdatedDate);
		} else {
			d1 = new Date(System.currentTimeMillis());
		}
		
		Date d2 = new Date(d1.getTime()- 3600 * 1000);
		
		inputList.add(appName);
		inputList.add(format.format(d2));
		inputList.add(format.format(d1));
		
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_APP_DATA, inputList.toArray());
		
		List<ApplicationDetailsDTO> list = getList(queryForList, Boolean.TRUE);
		
		queryForList = null;
		
		// Constructing linear list per hour
		Collections.sort(list, new Comparator<ApplicationDetailsDTO>() {
			  public int compare(ApplicationDetailsDTO o1, ApplicationDetailsDTO o2) {
			      return o1.getDateTime().compareTo(o2.getDateTime());
			  }
		});
		
		Map<Date, ApplicationDetailsDTO> latencyCountPerTime = getLatencyCountPerTime(list, d2);
		
		List<List<String>> latencyCounList = new ArrayList<>();
		List<String> latencyCoutObj = null;
		long averageVal = 0;
		for(Map.Entry<Date, ApplicationDetailsDTO> entry: latencyCountPerTime.entrySet()) {
			latencyCoutObj = new ArrayList<>();
			latencyCoutObj.add(convertFormat1(entry.getKey()));
			
			if(entry.getValue().getLatency() != 0 && entry.getValue().getLatencyRowCount() != 0) {
				averageVal = entry.getValue().getLatency()/entry.getValue().getLatencyRowCount();
			} else {
				averageVal = 0;
			}
			
			latencyCoutObj.add(String.valueOf(averageVal));
			latencyCounList.add(latencyCoutObj);
			
		}
		latencyCountPerTime = null;
		latencyCoutObj = null;
		
		Collections.sort(latencyCounList, new Comparator<List<String>>() {
			  public int compare(List<String> o1, List<String> o2) {
			      //return Long.valueOf(o1.get(0).toString()).compareTo(Long.valueOf(o2.get(0).toString()));
				  return o1.get(0).compareTo(o2.get(0));
			  }
		});
		
		// Constructing donut list per hour
		List<DonutChartDTO> donutList = getDonutList(list);
		
		AppDataDTO appDataDTO = new AppDataDTO();
		appDataDTO.setDonutList(donutList);
		appDataDTO.setLinearList(latencyCounList);
		appDetails.put("hour", appDataDTO);
		
		latencyCounList = null;
		donutList = null;
		
		// Day base Data
		Date d3 = new Date(d1.getTime() - 3600 * 1000 * 24);
		inputList = new ArrayList<>();
		inputList.add(appName);
		inputList.add(format.format(d3));
		inputList.add(format.format(d1));
		
		queryForList = jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_APP_DATA, inputList.toArray());
		list = getList(queryForList, Boolean.TRUE);
		
		queryForList = null;
		
		// Constructing donut list per day
		donutList = getDonutList(list);
		
		// Constructing linear list per day
		Collections.sort(list, new Comparator<ApplicationDetailsDTO>() {
			  public int compare(ApplicationDetailsDTO o1, ApplicationDetailsDTO o2) {
			      return o1.getDateTime().compareTo(o2.getDateTime());
			  }
		});
		latencyCountPerTime = getLatencyCountPerDay(list, d3);
		latencyCounList = new ArrayList<>();
		for(Map.Entry<Date, ApplicationDetailsDTO> entry: latencyCountPerTime.entrySet()) {
			latencyCoutObj = new ArrayList<>();
			latencyCoutObj.add(convertFormat1(entry.getKey()));
			if(entry.getValue().getLatency() != 0 && entry.getValue().getLatencyRowCount() != 0) {
				averageVal = entry.getValue().getLatency()/entry.getValue().getLatencyRowCount();
			} else {
				averageVal = 0;
			}
			
			latencyCoutObj.add(String.valueOf(averageVal));
			latencyCounList.add(latencyCoutObj);
			
		}
		Collections.sort(latencyCounList, new Comparator<List<String>>() {
			  public int compare(List<String> o1, List<String> o2) {
			      //return Long.valueOf(o1.get(0).toString()).compareTo(Long.valueOf(o2.get(0).toString()));
				  return o1.get(0).compareTo(o2.get(0));
			  }
		});
		
		appDataDTO = new AppDataDTO();
		appDataDTO.setDonutList(donutList);
		appDataDTO.setLinearList(latencyCounList);
		appDetails.put("day", appDataDTO);
		
		latencyCountPerTime = null;
		latencyCounList = null;
		donutList = null;
		
		
		// Week base Data
		Date d4 = new Date(d1.getTime() - 3600 * 1000 * 24 * 7);
		inputList = new ArrayList<>();
		inputList.add(appName);
		inputList.add(format.format(d4));
		inputList.add(format.format(d1));
		queryForList = jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_APP_DATA, inputList.toArray());
		list = getList(queryForList, Boolean.TRUE);
		
		queryForList = null;
		
		// Constructing donut list per week
		donutList = getDonutList(list);
		
		// Constructing linear list per week
		Collections.sort(list, new Comparator<ApplicationDetailsDTO>() {
			public int compare(ApplicationDetailsDTO o1, ApplicationDetailsDTO o2) {
				return o1.getDateTime().compareTo(o2.getDateTime());
			}
		});
		latencyCountPerTime = getLatencyCountPerWeek(list, d1,7);
		latencyCounList = new ArrayList<>();
		for(Map.Entry<Date, ApplicationDetailsDTO> entry: latencyCountPerTime.entrySet()) {
			latencyCoutObj = new ArrayList<>();
			latencyCoutObj.add(convertFormat1(entry.getKey()));
			if(entry.getValue().getLatency() != 0 && entry.getValue().getLatencyRowCount() != 0) {
				averageVal = entry.getValue().getLatency()/entry.getValue().getLatencyRowCount();
			} else {
				averageVal = 0;
			}
			
			latencyCoutObj.add(String.valueOf(averageVal));
			latencyCounList.add(latencyCoutObj);
			
		}
		Collections.sort(latencyCounList, new Comparator<List<String>>() {
			public int compare(List<String> o1, List<String> o2) {
				//return Long.valueOf(o1.get(0).toString()).compareTo(Long.valueOf(o2.get(0).toString()));
				return o1.get(0).compareTo(o2.get(0));
			}
		});
		
		
		appDataDTO = new AppDataDTO();
		appDataDTO.setDonutList(donutList);
		appDataDTO.setLinearList(latencyCounList);
		appDetails.put("week", appDataDTO);
		
		
		// Month base Data
		Calendar calendar = Calendar.getInstance();
		int days = -30;
		calendar.add(Calendar.DAY_OF_MONTH, days);
		System.out.println("Date "+calendar.getTime());
		d4 = calendar.getTime();
		
		inputList = new ArrayList<>();
		inputList.add(appName);
		inputList.add(format.format(d4));
		inputList.add(format.format(d1));
		queryForList = jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_APP_DATA, inputList.toArray());
		list = getList(queryForList, Boolean.TRUE);
		
		queryForList = null;
		
		// Constructing donut list per week
		donutList = getDonutList(list);
		
		// Constructing linear list per week
		Collections.sort(list, new Comparator<ApplicationDetailsDTO>() {
			public int compare(ApplicationDetailsDTO o1, ApplicationDetailsDTO o2) {
				return o1.getDateTime().compareTo(o2.getDateTime());
			}
		});
		latencyCountPerTime = getLatencyCountPerWeek(list, d1,30);
		latencyCounList = new ArrayList<>();
		for(Map.Entry<Date, ApplicationDetailsDTO> entry: latencyCountPerTime.entrySet()) {
			latencyCoutObj = new ArrayList<>();
			latencyCoutObj.add(convertFormat1(entry.getKey()));
			if(entry.getValue().getLatency() != 0 && entry.getValue().getLatencyRowCount() != 0) {
				averageVal = entry.getValue().getLatency()/entry.getValue().getLatencyRowCount();
			} else {
				averageVal = 0;
			}
			
			latencyCoutObj.add(String.valueOf(averageVal));
			latencyCounList.add(latencyCoutObj);
			
		}
		Collections.sort(latencyCounList, new Comparator<List<String>>() {
			public int compare(List<String> o1, List<String> o2) {
				//return Long.valueOf(o1.get(0).toString()).compareTo(Long.valueOf(o2.get(0).toString()));
				return o1.get(0).compareTo(o2.get(0));
			}
		});
		
		
		appDataDTO = new AppDataDTO();
		appDataDTO.setDonutList(donutList);
		appDataDTO.setLinearList(latencyCounList);
		appDetails.put("month", appDataDTO);
		
		
		queryForList = jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_APP_ERROR, inputList.toArray());
		list = getList(queryForList, Boolean.TRUE);
		
		queryForList = null;
		
		// Constructing donut list per week
		donutList = getDonutList(list);
		
		// Constructing linear list per week
		Collections.sort(list, new Comparator<ApplicationDetailsDTO>() {
			public int compare(ApplicationDetailsDTO o1, ApplicationDetailsDTO o2) {
				return o1.getDateTime().compareTo(o2.getDateTime());
			}
		});
		latencyCountPerTime = getLatencyCountPerWeekTemp(list, d1,30);
		latencyCounList = new ArrayList<>();
		for(Map.Entry<Date, ApplicationDetailsDTO> entry: latencyCountPerTime.entrySet()) {
			latencyCoutObj = new ArrayList<>();
			latencyCoutObj.add(convertFormat1(entry.getKey()));
			if( entry.getValue().getLatencyRowCount() != 0) {
				averageVal = entry.getValue().getLatencyRowCount();
			} else {
				averageVal = 0;
			}
			
			latencyCoutObj.add(String.valueOf(averageVal));
			latencyCounList.add(latencyCoutObj);
			
		}
		Collections.sort(latencyCounList, new Comparator<List<String>>() {
			public int compare(List<String> o1, List<String> o2) {
				//return Long.valueOf(o1.get(0).toString()).compareTo(Long.valueOf(o2.get(0).toString()));
				return o1.get(0).compareTo(o2.get(0));
			}
		});
		
		
		appDataDTO = new AppDataDTO();
		//appDataDTO.setDonutList(donutList);
		appDataDTO.setLinearList(latencyCounList);
		appDetails.put("error", appDataDTO);
		
		latencyCountPerTime = null;
		latencyCounList = null;
		donutList = null;
		
		return appDetails;
	}
	
	/**
	 * @param list
	 * @return
	 */
	public List<DonutChartDTO> getDonutList(List<ApplicationDetailsDTO> list) {
		List<DonutChartDTO> donutList = new ArrayList<>();
		int successCount = 0;
		int failureCount = 0;
		
		if(null != list && !list.isEmpty()) {
			for(ApplicationDetailsDTO appDto: list) {
				if(appDto.getStatus() == 200) {
					successCount += 1;
				} else {
					failureCount += 1;
				}
			}
			DonutChartDTO chartDTO = new DonutChartDTO();
			chartDTO.setKey("Success");
			chartDTO.setY(successCount);
			donutList.add(chartDTO);
			
			chartDTO = new DonutChartDTO();
			chartDTO.setKey("Failure");
			chartDTO.setY(failureCount);
			donutList.add(chartDTO);
		}
		return donutList;
	}
	
	public List<ApplicationDetailsDTO> getFTPList(List<Map<String, Object>> queryForList, boolean fromApp) {
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		ApplicationDetailsDTO appDetails = null;
		if(null != queryForList && !queryForList.isEmpty()) {
			for (Map<String, Object> row : queryForList) {
				appDetails = new ApplicationDetailsDTO();
				
				if(null != row.get("app_nm")) {
					appDetails.setApp_name(row.get("app_nm").toString());
				}
				
				if(null != row.get("app_name")) {
					appDetails.setApp_name(row.get("app_name").toString());
				}
				
				if(null != row.get("url")) {
					appDetails.setUrl(row.get("url").toString());
				}
				
				if(null != row.get("time")) {
					appDetails.setTime(row.get("time").toString());
					
					if(fromApp) {
						appDetails.setDateTime(convertStringToDate(appDetails.getTime()));
					}
				}
				
				if(null != row.get("status")) {
					
						appDetails.setErrorStatus(row.get("status").toString());
					
				}
				
				if(null != row.get("latency")) {
					appDetails.setLatency(Integer.valueOf(row.get("latency").toString()));
				}
				
				if(null != row.get("LATENCY_ERROR")) {
					appDetails.setLatencyError(Integer.valueOf(row.get("LATENCY_ERROR").toString()));
				}
				
				if(null != row.get("LATENCY_WARNING")) {
					appDetails.setLatencyWarnning(Integer.valueOf(row.get("LATENCY_WARNING").toString()));
				}
				
				if(null != row.get("ERROR_THRESHHOLD")) {
					appDetails.setErrorthreshold(Integer.valueOf(row.get("ERROR_THRESHHOLD").toString()));
				}
				
				if(null != row.get("ERROR_WARNING_THRESHHOLD")) {
					appDetails.setErrorwarnningthreshold(Integer.valueOf(row.get("ERROR_WARNING_THRESHHOLD").toString()));
				}
				
				if(null != row.get("PARENT")) {
					appDetails.setParent(row.get("PARENT").toString());
				}
				
				if(null != row.get("CHILD")) {
					appDetails.setChild(row.get("CHILD").toString());
				}
				
				if(null != row.get("url")) {
					appDetails.setUrl(row.get("url").toString());
				}
				if(row.get("D_APP_NM")!=null && row.get("D_APP_NM").equals("FileMonitoring")) {
					appDetails.setUrl("FTPS");
				}
				
				if(null != row.get("directory")) {
					appDetails.setDirectory(row.get("directory").toString());
				}
				
				if(null != row.get("schedule")) {
					appDetails.setSchedule(row.get("schedule").toString());
				}
				
				if(null != row.get("jobinterval")) {
					appDetails.setJobinterval((int)row.get("jobinterval"));
				}
				
				if(null != row.get("file_count")) {
					appDetails.setFileCount((int)row.get("file_count"));
				}
				list.add(appDetails);
			}
			
		}
		
		return list;
	}
	
	/**
	 * @param queryForList
	 * @return
	 */
	public List<ApplicationDetailsDTO> getList(List<Map<String, Object>> queryForList, boolean fromApp) {
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		ApplicationDetailsDTO appDetails = null;
		if(null != queryForList && !queryForList.isEmpty()) {
			for (Map<String, Object> row : queryForList) {
				appDetails = new ApplicationDetailsDTO();
				
				if(null != row.get("app_nm")) {
					appDetails.setApp_name(row.get("app_nm").toString());
				}
				
				if(null != row.get("app_name")) {
					appDetails.setApp_name(row.get("app_name").toString());
				}
				
				if(null != row.get("D_APP_NM")) {
					appDetails.setDashboardName(row.get("D_APP_NM").toString());
				}
				
				if(null != row.get("url")) {
					appDetails.setUrl(row.get("url").toString());
				}
				
				if(null != row.get("time")) {
					appDetails.setTime(row.get("time").toString());
					
					if(fromApp) {
						appDetails.setDateTime(convertStringToDate(appDetails.getTime()));
					}
				}
				
				if(null != row.get("status")) {
					appDetails.setStatus(Integer.valueOf(row.get("status").toString()));
					
				}
				
				if(null != row.get("latency")) {
					appDetails.setLatency(Integer.valueOf(row.get("latency").toString()));
				}
				
				if(null != row.get("LATENCY_ERROR")) {
					appDetails.setLatencyError(Integer.valueOf(row.get("LATENCY_ERROR").toString()));
				}
				
				if(null != row.get("LATENCY_WARNING")) {
					appDetails.setLatencyWarnning(Integer.valueOf(row.get("LATENCY_WARNING").toString()));
				}
				
				if(null != row.get("ERROR_THRESHHOLD")) {
					appDetails.setErrorthreshold(Integer.valueOf(row.get("ERROR_THRESHHOLD").toString()));
				}
				
				if(null != row.get("ERROR_WARNING_THRESHHOLD")) {
					appDetails.setErrorwarnningthreshold(Integer.valueOf(row.get("ERROR_WARNING_THRESHHOLD").toString()));
				}
				
				if(null != row.get("PARENT")) {
					appDetails.setParent(row.get("PARENT").toString());
				}
				
				if(null != row.get("CHILD")) {
					appDetails.setChild(row.get("CHILD").toString());
				}
				
				if(null != row.get("url")) {
					appDetails.setUrl(row.get("url").toString());
				}
				if(row.get("D_APP_NM")!=null && row.get("D_APP_NM").equals("FileMonitoring")) {
					appDetails.setUrl("FTPS");
				}
				
				if(null != row.get("directory")) {
					appDetails.setDirectory(row.get("directory").toString());
				}
				
				if(null != row.get("schedule")) {
					appDetails.setSchedule(row.get("schedule").toString());
				}
				
				if(null != row.get("jobinterval")) {
					appDetails.setJobinterval((int)row.get("jobinterval"));
				}
				if(null != row.get("type")) {
					appDetails.setType(row.get("type").toString());
				}
				if(null != row.get("PAYMT_CNT")) {
					appDetails.setPaymentCount((int)row.get("PAYMT_CNT"));
				}
				if(null != row.get("pymt_amt")) {
					appDetails.setPaymentAmount((int)row.get("pymt_amt"));
				}
				if(null != row.get("responce_string")) {
					appDetails.setResponceString(row.get("responce_string").toString());
				}
				
				
				list.add(appDetails);
			}
			
		}
		
		return list;
	}
	
	/**
	 * @param date
	 * @return
	 */
	public Date convertStringToDate(String date) {
		java.util.Date d1 = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			//dateFormat.setTimeZone(TimeZone.getTimeZone(MonitorConstants.TIME_ZONE_EST));
			d1 = dateFormat.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return d1;
	}
	
	/**
	 * @param date
	 * @return
	 */
	public String convertFormat1(Date date){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		//dateFormat.setTimeZone(TimeZone.getTimeZone(MonitorConstants.TIME_ZONE_EST));
		String todayWithZeroTime = null;
		try {
			todayWithZeroTime = dateFormat.format(date);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return todayWithZeroTime;
		
	}
	
	
	/**
	 * @param date
	 * @return
	 */
	public Date convertFormat(Date date){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//dateFormat.setTimeZone(TimeZone.getTimeZone(MonitorConstants.TIME_ZONE_EST));
		Date todayWithZeroTime = null;
		try {
			todayWithZeroTime = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return todayWithZeroTime;
		
	}
}