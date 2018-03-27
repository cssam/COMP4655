package ca.bcit.comp4655.jms.p2p.sender;


import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import ca.bcit.comp4655.jms.admin.Constants;
import ca.bcit.comp4655.jms.admin.PropertiesFactory;

public class JMSSenderWithSelector implements Constants
{

	
	public static void main( String[] args )
	{
		try
		{
			Context ctx = new InitialContext( PropertiesFactory.getProperties( URL ) );
			QueueConnectionFactory factory = ( QueueConnectionFactory ) ctx.lookup( "ConnectionFactory" );
			QueueConnection connection = factory.createQueueConnection();
			connection.start();
			
			Queue request = (Queue)ctx.lookup( REQUEST_QUEUE );
			Queue reply = (Queue)ctx.lookup( RESPONSE_QUEUE );
			QueueSession session = connection.createQueueSession ( false, Session.DUPS_OK_ACKNOWLEDGE );
			QueueSender sender = session.createSender(request);
			StringBuffer xml = new StringBuffer( "\n" );
			xml.append( "<?xml version=\"1.0\"?>" + "\n" );
			xml.append( "<trade>"                 + "\n" );
			xml.append( "  <acctId>123</acctId>"  + "\n" );
			xml.append( "  <side>BUY</side>"      + "\n" );
			xml.append( "  <symbol>AAPL</symbol>" + "\n" );
			xml.append( "  <shares>100</shares>"  + "\n" );
			xml.append( "</trade>"                + "\n" );
			
			TextMessage message = session.createTextMessage();
			message.setText( xml.toString() );
			message.setJMSReplyTo( reply );
			message.setStringProperty( "username", "Moe Howard" );
			sender.send( message );
			
			String filter = "JMSCorrelationID = '" + message.getJMSMessageID() + "'";
			System.out.println("Sender JMS ID:" + message.getJMSMessageID() );
			//String filter = "JMSCorrelationID = '12344'";
			QueueReceiver receiver = session.createReceiver( reply, filter );
			StreamMessage smsg = ( StreamMessage ) receiver.receive();
			System.out.println( "conf: " + smsg.readLong() );
			
			connection.close();
	    	System.exit(0);
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

	}

}
