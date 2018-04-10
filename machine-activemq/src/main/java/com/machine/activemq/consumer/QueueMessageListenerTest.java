package com.machine.activemq.consumer;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class QueueMessageListenerTest implements SessionAwareMessageListener<Message> {

	public synchronized void onMessage(Message message, Session session) {
		try {
			TextMessage msg = (TextMessage) message;
			final String ms = msg.getText();
			System.out.println("收到消息：" + ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
