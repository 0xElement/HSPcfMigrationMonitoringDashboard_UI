/**
 * 
 */
package com.homedepot.hs.monitoring.agent;

import com.homedepot.hs.monitoring.constants.MonitorConstants;
import com.homedepot.hs.monitoring.service.MonitorEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.TimerTask;



/**
 * @author syntel
 *
 */
@Component("emailSenderAgent")
public class EmailSenderAgent extends TimerTask{
	
	@Resource
	MonitorEmailService monitorEmailService;
	
	
	@Autowired
    private Environment env;
	
	LocalDateTime currentTime = null;
	int startTime = 0;
	int endTime = 0;
	
	
	@Override
	public void run() {
		
		currentTime = LocalDateTime.now(MonitorConstants.TIME_ZONE_EST);
		
		startTime = Integer.valueOf(env.getProperty("monitor.app.available.starttime"));
		endTime = Integer.valueOf(env.getProperty("monitor.app.available.endtime"));
		
		if((startTime == 0 || currentTime.getHour() >= startTime)  && (endTime == 0 || currentTime.getHour() < endTime)) {
			/*
			monitorEmailService.verifyLatestRecords(MonitorConstants.LOGIN_NOW_APP);
			
			monitorEmailService.verifyLatestRecords(MonitorConstants.LAUNCH_HDCONNECT_APP);
			
			monitorEmailService.verifyLatestRecords(MonitorConstants.PASSPORT_APP);
			
			monitorEmailService.verifyLatestRecords(MonitorConstants.PARTNER_PROFILE_APP);
			*/
		}
	}
}