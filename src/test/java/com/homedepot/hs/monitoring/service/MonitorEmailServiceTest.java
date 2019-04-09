/**
 * 
 */
package com.homedepot.hs.monitoring.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.homedepot.hs.monitoring.dao.MonitorEmailServiceDAO;
import com.homedepot.hs.monitoring.dto.ApplicationDetailsDTO;
import com.homedepot.hs.monitoring.util.EmailUtils;

/**
 * @author RXS8000
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MonitorEmailServiceTest {
	
	
	@Mock
	MonitorEmailServiceDAO monitorEmailServiceDAO;
	
	@Mock
	EmailUtils email;
	
	@InjectMocks
	MonitorEmailService monitorService;
	 
	@Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
    }

	
	@Test
	public void convertStringToDateTest()  {
		
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		
		ApplicationDetailsDTO dto = new ApplicationDetailsDTO();
		dto.setStatus(201);
		dto.setId(Long.decode("1"));
		dto.setApp_name("Login Now");
		dto.setUrl("LoginNowURL");
		dto.setLatency(3000);
		list.add(dto);
		
		dto = new ApplicationDetailsDTO();
		dto.setStatus(201);
		dto.setId(Long.decode("2"));
		dto.setApp_name("Login Now");
		dto.setUrl("LoginNowURL");
		dto.setLatency(3000);
		list.add(dto);
		
		dto = new ApplicationDetailsDTO();
		dto.setStatus(201);
		dto.setId(Long.decode("3"));
		dto.setApp_name("Login Now");
		dto.setUrl("LoginNowURL");
		dto.setLatency(3000);
		list.add(dto);
		
		when(monitorEmailServiceDAO.getLatestThreeRecords(Mockito.anyString())).thenReturn(list);
		//monitorService.verifyLatestRecords("Login Now");
	}
}
