/**
 * 
 */
package com.homedepot.hs.monitoring.dao;

import com.homedepot.hs.monitoring.constants.MonitorSqlConstatns;
import com.homedepot.hs.monitoring.dto.ApplicationDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author syntel
 *
 */
@Repository
public class MonitorEmailServiceDAO {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	 public List<Map<String, Object>> getResultSet(List<Object> inputList){
		 	return jdbcTemplate.queryForList(" SELECT  A.ID, A.ALERT_FLAG,B.D_APP_NM, APP_NM, LATENCY ,URL,STATUS, LATENCY_ERROR, LATENCY_WARNING,ALERT_LIST FROM HOMESERVICE_STATUS A,  HOMESERVICES_THRESHOLD B  WHERE A.app_NAME= B.APP_NM AND  A.TIME >= ? AND A.TIME <= ? AND D_APP_NM=? AND A.URL!='db' AND  A.LATENCY!=1  ORDER BY APP_NM", inputList.toArray());
	 }
	
	public List<ApplicationDetailsDTO> getLatestThreeRecords(String appName) {
		
		List<Object> inputList = new ArrayList<>();
		inputList.add(appName);
		
		List<Map<String, Object>> queryForList = 
				jdbcTemplate.queryForList(MonitorSqlConstatns.SQL_SELECT_TOP_3_APP_DATA, inputList.toArray());
		
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		ApplicationDetailsDTO appDetails = null;
		if(null != queryForList && !queryForList.isEmpty()) {
	    	for (Map<String, Object> row : queryForList) {
	    		 appDetails = new ApplicationDetailsDTO();
	    		if(null != row.get("app_name")) {
	    			appDetails.setApp_name(row.get("app_name").toString());
	    		}
	    		if(null != row.get("status")) {
	    			appDetails.setStatus(Integer.valueOf(row.get("status").toString()));
	    		}
	    		
	    		if(null != row.get("latency")) {
	    			appDetails.setLatency(Integer.valueOf(row.get("latency").toString()));
	    		}
	    		
	    		if(null != row.get("id")) {
	    			appDetails.setId(Long.valueOf(row.get("id").toString()));
	    		}
	    		
	    		if(null != row.get("email_sent_st")) {
	    			appDetails.setEmail_sent_st(row.get("email_sent_st").toString());
	    		}
	    		
	    		if(null != row.get("url")) {
	    			appDetails.setUrl(row.get("url").toString());
	    		}
	    		
	    		list.add(appDetails);
	    	}
		}
		
		return list;
	}
	
	/**
	 * @param applicationDetails
	 * @return
	 * @throws Exception
	 */
	public void updateApplicationDetails(String ids) {
		String sql = MonitorSqlConstatns.SQL_UPDATE_ROWS_BY_ID+ ids +")";
		jdbcTemplate.update(sql);
	}

}
