package com.spring.patch.minimal_spring_patch_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.apache.commons.lang3.time.StopWatch;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		StopWatch stopWatch = new StopWatch();
		logger.debug("STARTING STOPWATCH");
		stopWatch.start();
		ApplicationContext ctx;
		ctx = SpringApplication.run(Main.class, args);
		/*
		 * try {
			logger.debug("try time: " + stopWatch);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			Throwable cause=e;
			while (cause!=null){
				 cause = cause.getCause();
				 logger.debug(cause.toString());
			} 
			//System.out.println(ctx.containsBean(arg0));
		}
	    
		//System.exit(SpringApplication.exit(SpringApplication.run(BatchConfiguration.class, args)));
		 */
		logger.debug("STOPPING STOPWATCH");
		stopWatch.stop();
		logger.debug("Stopwatch time: " + stopWatch);
	}
}