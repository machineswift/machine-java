//package com.machine.activemq.producer;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import com.machine.activemq.bean.entity.Mail;
//
//@ContextConfiguration(locations = {"classpath:applicationContext.xml" })
//@RunWith(SpringJUnit4ClassRunner.class)
//public class MQProducerTest  {
//	@Autowired
//	private MQProducer mqProducer;
//
//	@Test
//	public void testSendMessage() {
//		Mail mail = new Mail();
//		mail.setTo("1254010874@qq.com");
//		mail.setSubject("异步发送邮件");
//		mail.setContent("Hi,This is a message!");
//		this.mqProducer.sendMessage(mail);
//		System.out.println("发送成功..");	
//	}
//}
