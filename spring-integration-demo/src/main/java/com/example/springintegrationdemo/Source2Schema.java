package com.example.springintegrationdemo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Source2Schema {
	
	@JsonProperty("SourceName")
	private String sourceName;
	
	@JsonProperty("BCode")
	private String bCode;
	
	@JsonProperty("BankName")
	private String name;
	
	@JsonProperty("RoutingNumber")
	private String routingNumber;
	
	@JsonProperty("BankAddress")
	private String address;
	
	public String toJsonString() throws JsonProcessingException
	{
		ObjectMapper obj = new ObjectMapper();
		String jsonString = obj.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		return jsonString;
	}
}
