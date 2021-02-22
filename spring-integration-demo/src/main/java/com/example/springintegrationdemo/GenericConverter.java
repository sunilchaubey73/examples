package com.example.springintegrationdemo;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public interface GenericConverter{

	public Object convert(Message<String> filePath);
}
