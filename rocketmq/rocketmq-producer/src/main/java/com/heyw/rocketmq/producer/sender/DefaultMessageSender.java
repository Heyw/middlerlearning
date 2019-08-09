package com.heyw.rocketmq.producer.sender;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class DefaultMessageSender {

	private final DefaultMQProducer producer;
	
	public DefaultMessageSender(DefaultMQProducer producer) {
		
		this.producer=producer;
		
	}
	
	public SendResult syncSendMsg(String topic,String msg) {
		
		try {
			return this.producer.send(new Message(topic, msg.getBytes()));
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		DefaultMQProducer mqProducer = new DefaultMQProducer("producer-group");
		mqProducer.setNamesrvAddr("10.12.91.105:9876");
		try {
			mqProducer.start();
			
			Message msg = new Message("PushTopic","push","1","Just for test.".getBytes());
			SendResult result = mqProducer.send(msg);
			System.out.println("id:"+result.getMsgId()+ " result:"+result.getSendStatus());
			
			msg=new Message("PushTopic", "push", "2", "Just for test .".getBytes());
			result = mqProducer.send(msg);
			System.out.println("id:"+result.getMsgId()+ " result:"+result.getSendStatus());
			
			msg=new Message("PushTopic", "push", "3", "Just for test .".getBytes());
			result = mqProducer.send(msg);
			System.out.println("id:"+result.getMsgId()+ " result:"+result.getSendStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			mqProducer.shutdown();
		}
	}
}
