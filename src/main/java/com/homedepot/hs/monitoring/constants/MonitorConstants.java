/**
 * 
 */
package com.homedepot.hs.monitoring.constants;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * @author syntel
 *
 */
public interface MonitorConstants {
	
	
	int REP_PERIOD = 120000;
	
	int START_TIME = 30000;
	
	int TASK_START_HOUR = 8;
	
	int TASK_END_HOUR = 22;
	
	int LATENCY_TRESHHOLD = 1000;
	
	String EMAIL_RECIPIENT = "guruprasad_padmanabhan@homedepot.com";

	String EMAIL_FROM = "guruprasad_padmanabhan@homedepot.com";
	
	int STATUS_SUCCESS = 200;
	
	public static final String TIMEZONE_STR = "America/New_York";
	public static final ZoneId TIME_ZONE_EST = ZoneId.of(TIMEZONE_STR);
	
	String DB_DT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	String DT_FORMAT = "MM/dd/yyyy HH:mm";
		
	
}
