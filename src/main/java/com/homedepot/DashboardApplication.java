package com.homedepot;

import com.homedepot.hs.monitoring.agent.EmailSenderAgent;
import com.homedepot.hs.monitoring.constants.MonitorConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Timer;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.homedepot")
@EnableAutoConfiguration
public class DashboardApplication 
{
    public static void main( String[] args )
    {
    	
    	ApplicationContext context = SpringApplication.run(DashboardApplication.class, args);
    	EmailSenderAgent st = (EmailSenderAgent) context.getBean("emailSenderAgent");
		Timer time = new Timer(); 
		time.schedule(st, MonitorConstants.START_TIME, MonitorConstants.REP_PERIOD);
    }
}
