package com.spring.patch.minimal_spring_patch_project;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.apache.commons.lang3.time.StopWatch;

public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		StopWatch stopWatch = new StopWatch();
		System.out.println("STARTING STOPWATCH");
		stopWatch.start();
		ApplicationContext ctx= SpringApplication.run(Application.class, args);
		
		
		stopWatch.stop();
		System.out.println("Stopwatch time: " + stopWatch);
	}
}