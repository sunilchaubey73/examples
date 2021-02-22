package com.example.springintegrationdemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component
public class Source1Converter implements GenericConverter{
	
	@Override
	public String convert(Message <String> filePath) {
		System.out.print("Inside Source-1Converter::convert..."+filePath.getPayload());
		String raw_message = "";
		Source1Schema source1SchemaObject = null;
		
		try {
			raw_message = new String(Files.readAllBytes(Paths.get(filePath.getPayload())));
			
			XmlMapper xmlMapper = new XmlMapper();
			try {
				source1SchemaObject = xmlMapper.readValue(raw_message, Source1Schema.class);
			}
			catch (Exception e)
			{
				System.out.println("\nERROR: "+e.getMessage()+"!!!");
			}
			
			ObjectMapper obj = new ObjectMapper();
			String jsonString = obj.writerWithDefaultPrettyPrinter().writeValueAsString(source1SchemaObject);
			
			return jsonString;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return raw_message;
	}
}
