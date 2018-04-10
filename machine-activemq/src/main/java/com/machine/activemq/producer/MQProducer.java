package com.machine.activemq.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.machine.activemq.bean.entity.Mail;


@Service("mqProducer")
public class MQProducer {
	
	@Autowired
	private JmsTemplate jmsTemplateProducerTest;
	
	public void sendMessage(final Mail mail) {
		jmsTemplateProducerTest.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(JSONObject.toJSONString(mail));
			}
		});
	}
}
