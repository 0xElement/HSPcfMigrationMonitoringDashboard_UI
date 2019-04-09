/**
 * 
 */
package com.homedepot.hs.monitoring.dao;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.homedepot.hs.monitoring.dto.ApplicationDetailsDTO;
import com.homedepot.hs.monitoring.dto.DonutChartDTO;

/**
 * @author RXS8000
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MonitoringServiceDAOImplTest {
	
	@InjectMocks
	MonitoringServiceDAOImpl monitoringServiceDAOImpl;
	
	
	@Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void convertStringToDateTest()  {
		Date convertStringToDate = monitoringServiceDAOImpl.convertStringToDate("2017-06-06 13:25:05.7");
	    assertNotNull(convertStringToDate);
	}
	
	@Test
	public void convertFormatTest()  {
		Date convertStringToDate = monitoringServiceDAOImpl.convertFormat(new Date());
	    assertNotNull(convertStringToDate);
	}
	
	@Test
	public void getListTest()  {
		List<Map<String, Object>>  list = new ArrayList<>();
		Map<String, Object> mapObj = new HashMap<>();
		
		mapObj.put("app_name", "Login Now");
		mapObj.put("url", "LoginNowURL");
		mapObj.put("time", "2017-06-06 13:25:05.7");
		mapObj.put("status", "200");
		mapObj.put("latency", "3000");
		list.add(mapObj);
		
		mapObj = new HashMap<>();
		mapObj.put("app_name", "THDPassport");
		mapObj.put("url", "LoginNowURL");
		mapObj.put("time", "2017-06-06 13:25:05.7");
		mapObj.put("status", "200");
		mapObj.put("latency", "3000");
		list.add(mapObj);
				
		List<ApplicationDetailsDTO> list2 = monitoringServiceDAOImpl.getList(list, Boolean.FALSE);
	    assertNotNull(list2);
	}
	
	@Test
	public void getDonutListTest() {
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		dto.setStatus(200);
		list.add(dto);
		
		dto = new ApplicationDetailsDTO();
		dto.setStatus(201);
		list.add(dto);
		
		List<DonutChartDTO> donutList = monitoringServiceDAOImpl.getDonutList(list);
		
		assertNotNull(donutList);
		
		
	}
	
	@Test
	public void getLatencyCountPerWeekTest() {
		
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		dto.setStatus(200);
		dto.setApp_name("Login Now");
		dto.setDateTime(monitoringServiceDAOImpl.convertStringToDate("2017-06-28 13:25:05.7"));
		dto.setLatency(4000);
		list.add(dto);
		
		dto = new ApplicationDetailsDTO();
		dto.setStatus(201);
		dto.setApp_name("Login Now");
		dto.setDateTime(monitoringServiceDAOImpl.convertStringToDate("2017-06-27 13:25:05.9"));
		dto.setLatency(3000);
		list.add(dto);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse= null;
		try {
			parse = format.parse("2017-06-28 14:25:05");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<Date, ApplicationDetailsDTO> latencyCountPerWeek = monitoringServiceDAOImpl.getLatencyCountPerWeek(list, parse,7);
		
		assertNotNull(latencyCountPerWeek);
		
		
	}
	
	
	
	
}
