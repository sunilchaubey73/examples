package com.example.springintegrationdemo;

import org.springframework.stereotype.Component;

@Component
public class SourceTransformationFactory {
	
	public GenericConverter getSourceCoverter (String sourceName)
	{
		System.out.println("\n...getSourceCoverter::inputSourceName=|"+sourceName+"|...");

		switch (sourceName)
		{
			case "source1":
			{
				System.out.println("Returning Source1Converter object...\n");
				return new Source1Converter();
			}
			case "source2":
			{
				System.out.println("Returning Source2Converter object...\n");
				return new Source2Converter();
			}
			default:
			{
				return null;
			}
		}
	}
	
}
