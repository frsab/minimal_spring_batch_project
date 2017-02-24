package com.spring.patch.minimal_spring_patch_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;

import com.mongodb.MongoClient;
import com.spring.patch.minimal_spring_patch_project.bean.*;
import com.spring.patch.minimal_spring_patch_project.processor.CityItemProcessor;


@Configuration
@EnableBatchProcessing
//@EnableAutoConfiguration
public class BatchConfiguration {
	private static Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public ItemReader<CityRow> reader() {
		logger.debug("public ItemReader<CityRow> reader()");
		FlatFileItemReader<CityRow> reader = new FlatFileItemReader<CityRow>();
//	    final ClassPathResource resource = new ClassPathResource("allCountriesSample.txt");
//	    if(resource.exists()){
//		    System.out.println("allCountriesSample.txt exist");
//	    }
//	    else{
//		    System.out.println("allCountriesSample.txt NOTFOUND");
//	    }
//	    
//	    reader.setResource(resource);    
		reader.setResource( new ClassPathResource("allCountriesSample.txt"));
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
		logger.debug("	public ItemProcessor<CityRow, CityMongoDB> processor() {");
	    return new CityItemProcessor(); 
	}
	
  @Bean
  public Step step1() {
	  logger.debug("bean step1 construct");
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
	  logger.debug("bean job(Step step1) construct");
    return jobBuilderFactory.get("job1")
        .incrementer(new RunIdIncrementer())
        .start(step1)
        .build();

  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 



  @Bean
  public ItemWriter<CityMongoDB> writer(MongoOperations template) {
      final MongoItemWriter<CityMongoDB> mongoItemWriter = new MongoItemWriter<CityMongoDB>();
      mongoItemWriter.setTemplate(template);
      mongoItemWriter.setCollection("city");
      return mongoItemWriter;
  }
  // end::readerwriterprocessor[]

  // tag::jobstep[]
  @Bean
  public Job importCitiesJob(JobBuilderFactory jobs, @Qualifier("step1")Step s1, @Qualifier("step2")Step s2) {
      return jobs.get("importCitiesJob")
              .incrementer(new RunIdIncrementer())
              .flow(s1).next(s2)
              .end()
              .build();
  }

  @Bean
  public MongoDbFactory mongoDbFactory() throws Exception {
      return new SimpleMongoDbFactory(new MongoClient(), "test2");
  }

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
      return new MongoTemplate(mongoDbFactory());
  }

  @Bean
  public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<CityRow> reader,
                    ItemWriter<CityMongoDB> writer, ItemProcessor<CityRow, CityMongoDB> processor) {

      ItemReadListener<CityRow> itemReadListener = new ItemReadListener<CityRow>() {

          @Override
          public void beforeRead() {

          }

          @Override
          public void afterRead(CityRow o) {

          }

          @Override
          public void onReadError(Exception e) {
//              logger.warn("ysoutError reading line : {}", e.getMessage());
        	  System.out.println("ERROR123");
          }
      };

      return stepBuilderFactory.get("step1")
              .<CityRow, CityMongoDB> chunk(10)
              .reader(reader)
              .processor(processor)
              .writer(writer)
              .listener(itemReadListener)
              .faultTolerant().skip(Exception.class).skipLimit(200000)
              .build();
  }

  @Bean
  public Step step2(StepBuilderFactory stepBuilderFactory) {
      return stepBuilderFactory.get("step2").tasklet(new HelloWorldTasklet())
             .build();
  }
}