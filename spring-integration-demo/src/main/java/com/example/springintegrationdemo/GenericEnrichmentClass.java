package com.example.springintegrationdemo;

import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class GenericEnrichmentClass {
	
	private String sourceName;
	
	public static GenericEnrichmentClass getEnrichmentClassForSource (String sourceName)
	{
		GenericEnrichmentClass genericEnrichmentClass = new GenericEnrichmentClass();
		genericEnrichmentClass.sourceName = sourceName;
		return genericEnrichmentClass;
	}
	
	@Transformer
	public Object enrichMessage(Message<Object> message) throws JsonProcessingException
	{
		System.out.println("GenericEnrichmentClass::enrichMessage - "+this.sourceName+"--"+message.getPayload());
		Source2Schema source2Scema = (Source2Schema)message.getPayload();
		return source2Scema.toJsonString();
	}

}
