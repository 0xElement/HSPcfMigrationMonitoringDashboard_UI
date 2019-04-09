/**
 * 
 */
package com.homedepot.hs.monitoring.service;

import com.homedepot.hs.monitoring.constants.MonitorConstants;
import com.homedepot.hs.monitoring.dao.MonitorEmailServiceDAO;
import com.homedepot.hs.monitoring.dto.ApplicationDetailsDTO;
import com.homedepot.hs.monitoring.dto.MonitoringResponseDTO;
import com.homedepot.hs.monitoring.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author syntel
 *
 */
@Service
public class MonitorEmailService {
	
	
	
	public static void sendAlerts(String appName,String url,String appStatus,double avgLatency ) {
		
		EmailUtils email = new EmailUtils();
		String alert = "{\r\n" + 
				"	\"text\": \"End Point: " + appName + "\r\nURL: " + url + "\r\nStatus Code: " + appStatus + "\r\nLatency: " + String.format("%.2f", avgLatency) + "\",\r\n" + 
				"	\"Message\": \"This alert may be due to High Latency or Errors. Please Check the Node \",\r\n" +
				"}";
		
		 email.sendMail(alert);	
		
		
	}
	
}
