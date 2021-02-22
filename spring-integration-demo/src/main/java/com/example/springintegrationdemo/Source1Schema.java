package com.example.springintegrationdemo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source1Schema {
	
	@JsonProperty("SourceName")
	private String sourceName;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Address")
	private String address;
}
