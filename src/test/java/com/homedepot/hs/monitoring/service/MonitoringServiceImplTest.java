/**
 * 
 */
package com.homedepot.hs.monitoring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.core.env.Environment;

import com.homedepot.hs.monitoring.dao.MonitoringServiceDAOImpl;
import com.homedepot.hs.monitoring.dto.ApplicationDetailsDTO;
import com.homedepot.hs.monitoring.dto.MonitoringResponseDTO;

/**
 * @author RXS8000
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MonitoringServiceImplTest {
	
	@Mock
	MonitoringServiceDAOImpl monitoringServiceDAOImpl;
	
	@Mock
	 private Environment env;
	
	@InjectMocks
	MonitoringServiceImpl monitorService;
	 
	@Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
    }
	
	
	/**
	 * Get data for an hour
	 * 
	 */
	@Test
	public void getLastHourDataForTreeTest()  {
		List<ApplicationDetailsDTO> list = new ArrayList<>();
		
		ApplicationDetailsDTO applicationDTO = new ApplicationDetailsDTO();
		applicationDTO.setApp_name("Login Now");
		applicationDTO.setStatus(200);
		applicationDTO.setLatency(3000);
		
		list.add(applicationDTO);
		
		applicationDTO = new ApplicationDetailsDTO();
		applicationDTO.setApp_name("Launch HDConnect");
		applicationDTO.setStatus(200);
		applicationDTO.setLatency(3000);
		
		list.add(applicationDTO);
		
		applicationDTO = new ApplicationDetailsDTO();
		applicationDTO.setApp_name("THDPassport");
		applicationDTO.setStatus(200);
		applicationDTO.setLatency(3000);
		
		list.add(applicationDTO);
		
		applicationDTO = new ApplicationDetailsDTO();
		applicationDTO.setApp_name("Partner Profile");
		applicationDTO.setStatus(201);
		applicationDTO.setLatency(4000);
		
		list.add(applicationDTO);
		
		
		applicationDTO = new ApplicationDetailsDTO();
		applicationDTO.setApp_name("Partner Profile");
		applicationDTO.setStatus(200);
		applicationDTO.setLatency(4000);
		
		list.add(applicationDTO);
		
		
//	    when(monitoringServiceDAOImpl.loadHourDataForTree(Mockito.anyString(), Mockito.anyString())).thenReturn(list);
	    when(env.getProperty("monitor.app.available.endtime")).thenReturn("0");
	    
	    when(env.getProperty("monitor.app.loginnow.errorthreshhold")).thenReturn("10");
	    when(env.getProperty("monitor.app.loginnow.warningthreshhold")).thenReturn("5");
	    when(env.getProperty("monitor.app.loginnow.latency.errorthreshhold")).thenReturn("3000");
	    when(env.getProperty("monitor.app.loginnow.latency.warningthreshhold")).thenReturn("2000");
	    
	    when(env.getProperty("monitor.app.hdconnect.errorthreshhold")).thenReturn("10");
	    when(env.getProperty("monitor.app.hdconnect.warningthreshhold")).thenReturn("5");
	    when(env.getProperty("monitor.app.hdconnect.latency.errorthreshhold")).thenReturn("3000");
	    when(env.getProperty("monitor.app.hdconnect.latency.warningthreshhold")).thenReturn("2000");
	    
	    when(env.getProperty("monitor.app.thdpassport.errorthreshhold")).thenReturn("10");
	    when(env.getProperty("monitor.app.thdpassport.warningthreshhold")).thenReturn("5");
	    when(env.getProperty("monitor.app.thdpassport.latency.errorthreshhold")).thenReturn("3000");
	    when(env.getProperty("monitor.app.thdpassport.latency.warningthreshhold")).thenReturn("2000");
	    
	    when(env.getProperty("monitor.app.partnerprofile.errorthreshhold")).thenReturn("10");
	    when(env.getProperty("monitor.app.partnerprofile.warningthreshhold")).thenReturn("5");
	    when(env.getProperty("monitor.app.partnerprofile.latency.errorthreshhold")).thenReturn("3000");
	    when(env.getProperty("monitor.app.partnerprofile.latency.warningthreshhold")).thenReturn("2000");
	    
	    
	    
//	    MonitoringResponseDTO lastHourDataForTree = monitorService.getLastHourDataForTree();
//	    assertNotNull(lastHourDataForTree);
//	    assertEquals("error", lastHourDataForTree.getStatus());
	}

}
