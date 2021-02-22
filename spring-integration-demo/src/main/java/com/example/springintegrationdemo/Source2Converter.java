package com.example.springintegrationdemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.messaging.Message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Source2Converter implements GenericConverter {

	@Override
	public Source2Schema convert(Message <String> filePath) {
		System.out.print("Inside Source-2Transformer::convert..."+filePath.getPayload());
		String raw_message = "";
		Source2Schema source2SchemaObject = null;
		
		try {
			raw_message = new String(Files.readAllBytes(Paths.get(filePath.getPayload())));
			
			XmlMapper xmlMapper = new XmlMapper();
			try {
				source2SchemaObject = xmlMapper.readValue(raw_message, Source2Schema.class);
			}
			catch (Exception e)
			{
				System.out.println("\nERROR: "+e.getMessage()+"!!!");
			}
			
			ObjectMapper obj = new ObjectMapper();
			String jsonString = obj.writerWithDefaultPrettyPrinter().writeValueAsString(source2SchemaObject);
			
			System.out.println("--"+jsonString+"--");
			//return jsonString;
			return source2SchemaObject;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		//return raw_message;
		return source2SchemaObject;
	}

}
