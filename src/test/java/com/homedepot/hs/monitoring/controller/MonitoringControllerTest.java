package com.homedepot.hs.monitoring.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.homedepot.hs.monitoring.dao.MonitoringServiceDAOImpl;
import com.homedepot.hs.monitoring.dao.MonitoringValletDAOImpl;
import com.homedepot.hs.monitoring.dto.AppDataDTO;
import com.homedepot.hs.monitoring.dto.ApplicationResponseDTO;
import com.homedepot.hs.monitoring.dto.MonitoringResponseDTO;
import com.homedepot.hs.monitoring.dto.ResponseTo;
import com.homedepot.hs.monitoring.dto.ValetInputDTO;
import com.homedepot.hs.monitoring.service.MonitoringServiceImpl;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class MonitoringControllerTest 
{
	
	@InjectMocks
	MonitoringController controller;
	
	@Mock
	MonitoringServiceImpl monitoringServiceImpl;
	
	@Mock
	MonitoringServiceDAOImpl monitoringServiceDAOImpl;
	
	@Mock
	MonitoringValletDAOImpl monitoringValletDAOImpl;
	
	@Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
    }
	
//	@Test
//	public void getLastHourDataForTreeTest()  {
//		MonitoringResponseDTO monitoringResponseDTO = new MonitoringResponseDTO();
//	    when(monitoringServiceImpl.getLastHourDataForTree()).thenReturn(monitoringResponseDTO);
//	    ResponseTo<MonitoringResponseDTO> lastHourDataForTree = controller.getLastHourDataForTree("06/06/2017 13:25");
//	    assertNotNull(lastHourDataForTree);
//	}
	
	
	@Test
	public void loadApplicationsDataTest() throws ParseException  {
		Map<String, AppDataDTO> monitoringResponseDTO = new HashMap<String, AppDataDTO>();
	    when(monitoringServiceDAOImpl.getApplicationData("Login Now", "06/06/2017 13:25")).thenReturn(monitoringResponseDTO);
	    ResponseTo<ApplicationResponseDTO> lastHourDataForTree = controller.loadApplicationsData("Login Now", "06/06/2017 13:25");
	    assertNotNull(lastHourDataForTree);
	}
	
	@Test
	public void getMonitoringValletInforamtionTest() throws Exception  {
		ValetInputDTO valletInformation = new ValetInputDTO();
	    when(monitoringValletDAOImpl.getValletInformation()).thenReturn(valletInformation);
	    ResponseTo<ValetInputDTO> lastHourDataForTree = controller.getMonitoringValletInforamtion();
	    assertNotNull(lastHourDataForTree);
	}
}



