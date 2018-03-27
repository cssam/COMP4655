package ca.bcit.comp4655.jms.pubsub.client;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;

import ca.bcit.comp4655.jms.admin.Constants;
import ca.bcit.comp4655.jms.admin.PropertiesFactory;

public class DurableSuscriber implements MessageListener, Constants
{
	public DurableSuscriber()
	{
		try
		{
			Context ctx = new InitialContext( PropertiesFactory.getProperties( URL ) );
			TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup( "ConnectionFactory" );
			TopicConnection connection = factory.createTopicConnection();
			connection.setClientID( "durable" );
			connection.start();
			

			Topic topic = (Topic) ctx.lookup( TOPIC );
			TopicSession session = connection.createTopicSession( false,Session.AUTO_ACKNOWLEDGE );
			TopicSubscriber subscriber = session.createDurableSubscriber( topic, "mySub" );
			
			subscriber.setMessageListener( this );

			
			// wait for messages
			System.out.print( "waiting for messages\n" );
			for ( int i = 0; i < 10; i++ ) 
			{
				Thread.sleep(1000);
				System.out.print( "." );
			}
			System.out.println();
			connection.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onMessage( Message message ) 
	{
		try 
		{
        	MapMessage msg = (MapMessage) message;
        	System.out.println("Received Price Update: " + 
        			msg.getString("symbol") + "," +
        			msg.getDouble("price"));
	    } 
		catch (Exception e) 
		{
	    	e.printStackTrace();
	    }
		
	}
	
	public static void main(String[] args) 
	{
		new DurableSuscriber();
	}
}
