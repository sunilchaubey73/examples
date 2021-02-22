package com.example.springintegrationdemo;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;

@Configuration

public class ProcessFlowConfig {
	
	@Bean
	public IntegrationFlow processFlow() {	
		return IntegrationFlows.from(fileReader(),spec -> spec.poller(Pollers.fixedDelay(500)))
				.transform(getConverter(),"convert")
				.transform(getGenericEnrichmentClass(),"enrichMessage")
				.handle(fileWriter())
				.get();
	}
	
	@Bean
	public FileReadingMessageSource fileReader() {
		System.out.print("Inside fileReader...");
		FileReadingMessageSource input_source = new FileReadingMessageSource();
		input_source.setDirectory(new File("source"));
		return input_source;
	}
	
	@Value("${source.name}")
	private String sourceName;
	@Bean
	public GenericConverter getConverter ()
	{
		System.out.println("\n...getConverter::sourceName=|"+sourceName+"|...");
		SourceTransformationFactory sourceTransformationFactory = new SourceTransformationFactory();
		GenericConverter genericConverter = sourceTransformationFactory.getSourceCoverter(sourceName);
		return genericConverter;
	}
	
	@Bean
	public GenericEnrichmentClass getGenericEnrichmentClass()
	{
		GenericEnrichmentClass genericEnrichmentClass = GenericEnrichmentClass.getEnrichmentClassForSource(sourceName);
		return genericEnrichmentClass;
	}
	@Bean
	public FileWritingMessageHandler fileWriter() {
		System.out.print("Inside fileWriter...");
		FileWritingMessageHandler genericHandler = new FileWritingMessageHandler(
				new File("destination"));
		genericHandler.setFileNameGenerator(defFileNameGenerator());
		genericHandler.setExpectReply(false);
		return genericHandler;
	}
	
	@Bean
	DefaultFileNameGenerator defFileNameGenerator()
	{
		DefaultFileNameGenerator defFileNameGenerator = new DefaultFileNameGenerator();
		defFileNameGenerator.setExpression("'output' + '.json'");
		return defFileNameGenerator;
	}
}
