package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
public class MqTest {


	private static final String QUEUE_NAME = "my_queue";

	public static void main(String[] args) throws Exception {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.2");
		factory.setPort(5672);
		factory.setPassword("q1w2e3..");
		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);


		String message = "Hello, RabbitMQ!";
		channel.basicPublish("data.tsheets.sync", "tsheets-time-sync-key", null, message.getBytes("UTF-8"));
		System.out.println("Sent message: " + message);


		channel.close();
		connection.close();
	}
}
