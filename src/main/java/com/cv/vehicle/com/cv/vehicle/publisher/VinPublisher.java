package com.cv.vehicle.com.cv.vehicle.publisher;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

import com.cv.vehicle.core.VehicleConfiguration;
import com.cv.vehicle.pojo.VehicleAgreement;
import org.apache.activemq.ActiveMQConnectionFactory;

public class VinPublisher {

    ConnectionFactory connectionFactory;
    static Connection connection;

    public VinPublisher(VehicleConfiguration config){
        connectionFactory =
                new ActiveMQConnectionFactory(config.getUsername(),config.getPassword(),System.getenv(config.getUrl()));
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("1");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void send(VehicleAgreement vin){


        try {


        Session session =
                connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("vin");
        MessageProducer messageProducer = session.createProducer(topic);

            ObjectMessage obj = session.createObjectMessage(vin);
            messageProducer.send(obj);


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
