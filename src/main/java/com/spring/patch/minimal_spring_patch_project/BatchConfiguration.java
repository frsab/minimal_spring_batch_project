package com.spring.patch.minimal_spring_patch_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;

import com.spring.patch.minimal_spring_patch_project.bean.*;
import com.spring.patch.minimal_spring_patch_project.processor.CityItemProcessor;


@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public ItemReader<CityRow> reader() {
	    FlatFileItemReader<CityRow> reader = new FlatFileItemReader<CityRow>();
	    final ClassPathResource resource = new ClassPathResource("allCountriesSample.txt");
	    reader.setResource(resource);    
	    reader.setLineMapper(new DefaultLineMapper<CityRow>() {{
	      setLineTokenizer(new DelimitedLineTokenizer() {{
	         setNames(new String[] { "id","name","asciiname","alternatenames","latitude","longitude","featureClass","featureCode","countryCode","cc2","admin1Code",
	                 "admin2Code","admin3Code","admin4Code","population","elevation","dem","timezone","modificationDate"});
	         setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);
	      }});
	      setFieldSetMapper(new BeanWrapperFieldSetMapper<CityRow>() {{
	         setTargetType(CityRow.class);
	      }});
	    }}); return reader;
	}
	
	@Bean 
	public ItemProcessor<CityRow, CityMongoDB> processor() {
	    return new CityItemProcessor(); 
	}
	
  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
        .tasklet(new Tasklet() {
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
            return null;
          }
        })
        .build();
  }

  @Bean
  public Job job(Step step1) throws Exception {
    return jobBuilderFactory.get("job1")
        .incrementer(new RunIdIncrementer())
        .start(step1)
        .build();
  }
}