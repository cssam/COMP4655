package ca.bcit.comp4655.jms.p2p.receiver;

import javax.jms.Message;
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

public class JMSReceiver implements Constants 
{

	QueueSession session = null;	
	public JMSReceiver()
	{
        try 
        {	
        	Context ctx = new InitialContext( PropertiesFactory.getProperties( URL ) );
        	QueueConnectionFactory factory = ( QueueConnectionFactory ) ctx.lookup( "ConnectionFactory" );
			QueueConnection connection = factory.createQueueConnection();
			connection.start();
			
			Queue queue = ( Queue )ctx.lookup( REQUEST_QUEUE );
			session = connection.createQueueSession( false, Session.DUPS_OK_ACKNOWLEDGE );
			System.out.print( "Waiting for messages ..." );
			QueueReceiver receiver = session.createReceiver( queue );
			
			while ( true )
			{
				Message m = receiver.receive();
				if ( m != null && m instanceof TextMessage )
				{
					TextMessage message = ( TextMessage ) m;
					System.out.println( "Message Received From " + message.getStringProperty( "username" ) );
					
					TextMessage msg = ( TextMessage )message;
		            System.out.println( msg.getText() );
		            long conf = 98765;
		            StreamMessage smsg = session.createStreamMessage();
		            smsg.writeLong( conf );
		            QueueSender sender = session.createSender( ( Queue ) message.getJMSReplyTo() );
		            sender.send( smsg );
		            System.out.println ( this.getClass().getSimpleName() + " received a message and exiting");
		            connection.close();
		            System.exit(0);	
				}
			}
			
				
        } 
        catch ( Exception exception ) 
        {
            exception.printStackTrace();
        }
	}


	public static void main(String[] args) 
	{
		try 
		{
			new JMSReceiver();
	    }
		catch (Exception e) 
		{
	    	e.printStackTrace();
	    }
		
	}

}
