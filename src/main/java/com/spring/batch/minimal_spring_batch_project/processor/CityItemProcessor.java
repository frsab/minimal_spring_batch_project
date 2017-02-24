package com.spring.batch.minimal_spring_batch_project.processor;

import com.google.common.collect.Lists;

import org.springframework.batch.item.ItemProcessor;

import com.spring.batch.minimal_spring_batch_project.bean.CityMongoDB;
import com.spring.batch.minimal_spring_batch_project.bean.CityRow;

public class CityItemProcessor implements ItemProcessor<CityRow, CityMongoDB> {

	@Override
	public CityMongoDB process(CityRow cityRow) throws Exception {
		final CityMongoDB city = new CityMongoDB();
	    city.setId(cityRow.getId());
	    final String alternateNames = cityRow.getAlternatenames();
	    if (alternateNames != null && !alternateNames.isEmpty()) {
	    	// com.google.common.collect.Lists;
	        city.setAlternateNames(Lists.newArrayList(alternateNames.split(",")));
	    }
	    // TODO ...   
	    return city;
	}

}
