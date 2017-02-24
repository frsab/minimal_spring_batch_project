package com.spring.batch.minimal_spring_patch_project.bean;

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAsciiname() {
		return asciiname;
	}
	public void setAsciiname(String asciiname) {
		this.asciiname = asciiname;
	}
	public List<String> getAlternateNames() {
		return alternateNames;
	}
	public void setAlternateNames(List<String> alternateNames) {
		this.alternateNames = alternateNames;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public List<String> getAlternateCountryCodes() {
		return alternateCountryCodes;
	}
	public void setAlternateCountryCodes(List<String> alternateCountryCodes) {
		this.alternateCountryCodes = alternateCountryCodes;
	}
	public String getAdmin1Code() {
		return admin1Code;
	}
	public void setAdmin1Code(String admin1Code) {
		this.admin1Code = admin1Code;
	}
	public String getAdmin2Code() {
		return admin2Code;
	}
	public void setAdmin2Code(String admin2Code) {
		this.admin2Code = admin2Code;
	}
	public String getAdmin3Code() {
		return admin3Code;
	}
	public void setAdmin3Code(String admin3Code) {
		this.admin3Code = admin3Code;
	}
	public String getAdmin4Code() {
		return admin4Code;
	}
	public void setAdmin4Code(String admin4Code) {
		this.admin4Code = admin4Code;
	}
	public BigInteger getPopulation() {
		return population;
	}
	public void setPopulation(BigInteger population) {
		this.population = population;
	}
	public Integer getElevation() {
		return elevation;
	}
	public void setElevation(Integer elevation) {
		this.elevation = elevation;
	}
	public Integer getDem() {
		return dem;
	}
	public void setDem(Integer dem) {
		this.dem = dem;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}


}
