package com.spring.patch.minimal_spring_patch_project.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Document
public class CityMongoDB {

    @Id
    private int id;
    private String name;
    private String asciiname;
    private List<String> alternateNames;
    private Double latitude;
    private Double longitude;
    private Feature feature;
    private String countryCode;
    private List<String> alternateCountryCodes;
    private String admin1Code;
    private String admin2Code;
    private String admin3Code;
    private String admin4Code;
    private BigInteger population;
    private Integer elevation;
    private Integer dem;
    private String timezone;
    private Date modificationDate;


}
