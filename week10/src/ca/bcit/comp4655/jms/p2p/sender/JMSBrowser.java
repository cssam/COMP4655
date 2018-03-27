package ca.bcit.comp4655.jms.p2p.sender;

import java.util.Enumeration;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import ca.bcit.comp4655.jms.admin.Constants;
import ca.bcit.comp4655.jms.admin.PropertiesFactory;

public class JMSBrowser implements Constants
{
	public static void main(String[] args) 
	{
		try
		{
			Context ctx = new InitialContext( PropertiesFactory.getProperties( URL ) );
			QueueConnectionFactory factory = ( QueueConnectionFactory ) ctx.lookup( "ConnectionFactory" );
			QueueConnection connection = factory.createQueueConnection();
			connection.start();
			
			Queue queue = (Queue)ctx.lookup( REQUEST_QUEUE );
			QueueSession queueSession = connection.createQueueSession ( false, Session.AUTO_ACKNOWLEDGE );
			
			// create a queue browser
			QueueBrowser queueBrowser = queueSession.createBrowser(queue);
			// browse the messages
			Enumeration<Message> e = queueBrowser.getEnumeration();
			
			boolean messageExist = false;
			while ( e.hasMoreElements() )
			{
				TextMessage msg = ( TextMessage ) e.nextElement();
				messageExist = true;
				System.out.println ( msg.getText() );
			}
			if ( !messageExist )
			{
				System.out.println ( "No message to browse!" );
			}
			connection.close();
		}
		catch ( Exception e )
		{
			
		}
	}
}
