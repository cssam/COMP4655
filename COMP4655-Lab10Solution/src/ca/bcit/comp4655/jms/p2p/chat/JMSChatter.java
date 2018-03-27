package ca.bcit.comp4655.jms.p2p.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.naming.remote.client.InitialContextFactory;
import org.xnio.Options;
import org.jboss.remoting3.spi.*;


public class JMSChatter implements MessageListener {
	

	public JMSChatter() {
		
	}


	public static void main(String[] args) throws JMSException, NamingException, IOException {
		if(args.length!=1) System.out.println("enter username");
		else {
			String username = args[0];
			String queueName1 = "queue/lab10q1";
			String queueName2 = "queue/lab10q2";
			JMSChatter jmsChatter = new JMSChatter();
			Context initialContext = JMSChatter.getInitialContext();
			Queue queue01 = (Queue)initialContext.lookup(queueName1);
			Queue queue02 = (Queue)initialContext.lookup(queueName2);
			QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) initialContext.lookup("java:jboss/exported/jms/RemoteConnectionFactory");
			QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
			jmsChatter.subscribe(queueConnection, queue01, jmsChatter);
			jmsChatter.publish(queueConnection, queue02, username);
		}

	}
	
	public void subscribe(QueueConnection queueConnection, Queue queue, JMSChatter jmsChatter ) throws JMSException {
		
		QueueSession subscribeSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueReceiver queueReceiver = subscribeSession.createReceiver(queue);
		queueReceiver.setMessageListener(jmsChatter);
	}
	
	public void publish(QueueConnection queueConnection, Queue queue, String userName) throws JMSException, IOException {
		QueueSession publishSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueSender queueSender = publishSession.createSender(queue);
		queueConnection.start();
		BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String messageToSend = reader.readLine();
			if(messageToSend.equalsIgnoreCase("exit")) {
				queueConnection.close();
				System.exit(0);
			} else {
				TextMessage message = publishSession.createTextMessage();
				message.setText("["+userName+": "+messageToSend+"]");
				queueSender.send(message);
			}
		}
	}
	
	public static Context getInitialContext() throws JMSException, NamingException {
		Properties props = new Properties();
		props.setProperty( "java.naming.factory.initial" ,"org.jboss.naming.remote.client.InitialContextFactory" );
		props.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
		props.setProperty( "java.naming.provider.url", "local://localhost:4447" );
		
		Context context = new InitialContext(props);
		return context;
	}


	@Override
	public void onMessage(Message message) {
		try {
			System.out.println( ((TextMessage)message).getText());
		} catch (JMSException e) {
			
		}
		
	}

}
