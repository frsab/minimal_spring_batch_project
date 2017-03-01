package com.spring.batch.minimal_spring_batch_project.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	 @Before("execution(* com.spring.batch.minimal_spring_batch_project.service.SameService.showString(java.lang.String))")// && args(sampleName)")
	    public void beforeShowString(String sampleName) {
		 logger.info("A request was issued for a sample name: "+sampleName);
	    }
	

}
