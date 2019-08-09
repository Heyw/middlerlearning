package com.heyw.rocketmq.producer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heyw.rocketmq.producer.sender.DefaultMessageSender;

@Service
public class MessageProduceHandler {

	@Autowired
	private DefaultMessageSender sender;
	
	public void produce(String topic,String message) {
		
		this.sender.syncSendMsg(topic, message);
		
	}
}
