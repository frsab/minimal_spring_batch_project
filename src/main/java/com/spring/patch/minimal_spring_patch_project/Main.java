package com.spring.patch.minimal_spring_patch_project;

import org.springframework.boot.SpringApplication;

public class Main {

	public static void main(String[] args) {
	    System.exit(SpringApplication.exit(SpringApplication.run(
		        BatchConfiguration.class, args)));

	}
}