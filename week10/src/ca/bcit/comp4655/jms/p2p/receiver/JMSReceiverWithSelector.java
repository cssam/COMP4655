package ca.bcit.comp4655.jms.p2p.receiver;

import javax.jms.Message;
import javax.jms.MessageListener;
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

public class JMSReceiverWithSelector  implements Constants, MessageListener
{
	QueueSession session = null;
	public JMSReceiverWithSelector() throws Exception
	{
	
	    Context ctx = new InitialContext( PropertiesFactory.getProperties( URL ) );
	    QueueConnectionFactory factory = ( QueueConnectionFactory ) ctx.lookup( "ConnectionFactory" );
		QueueConnection connection = factory.createQueueConnection();
		connection.start();

		Queue queue = ( Queue )ctx.lookup( REQUEST_QUEUE );
		session = connection.createQueueSession( false, Session.AUTO_ACKNOWLEDGE );
		
		
		QueueReceiver receiver = session.createReceiver( queue );
		receiver.setMessageListener( this );
		// wait for messages
		for ( int i = 0; i < 10; i++ ) 
		{
			Thread.sleep(1000);
			System.out.print( "." );
		}
		System.out.println();
		connection.close();
		System.out.println( "Done Listening" );
		
	}

	@Override
	public void onMessage( Message message ) 
	{
		try
		{
			System.out.println( "Message Received From " + message.getStringProperty( "username" ) );
            TextMessage msg = ( TextMessage )message;
            System.out.println( msg.getText() );
            long conf = 123456;
            StreamMessage smsg = session.createStreamMessage();
            smsg.setJMSCorrelationID( message.getJMSMessageID() );
            System.out.println( "Reciever JMS ID: " + message.getJMSMessageID()); 
            //smsg.setJMSCorrelationID( "12344" );
            smsg.writeLong( conf );
            QueueSender sender = session.createSender( ( Queue ) message.getJMSReplyTo() );
            sender.send( smsg );
            
            System.out.println( "Waiting for messages..." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main( String[] args ) 
	{
		try 
		{
			new JMSReceiverWithSelector();
		} 
		catch (Exception e)
		{
			
			e.printStackTrace();
		}
	}
}
