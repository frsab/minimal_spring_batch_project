package com.spring.patch.minimal_spring_patch_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.apache.commons.lang3.time.StopWatch;

@SpringBootApplication
public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		

		StopWatch stopWatch = new StopWatch();
		logger.debug("STARTING STOPWATCH "+ stopWatch);
		stopWatch.start();
		ApplicationContext ctx=SpringApplication.run(Application.class, args);
		
		Environment envApp =ctx.getEnvironment();
		String[] defaultProfilesOfApp=envApp.getDefaultProfiles();
		int i=0;
		
		for(String profileApp:defaultProfilesOfApp){
			logger.info(++i+" - ApplicationContext"+profileApp);
		}
		stopWatch.stop();
		logger.debug("Stopwatch time: " + stopWatch);		
	}
}