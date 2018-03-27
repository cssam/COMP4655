package ca.bcit.comp4655.jms.pubsub.server;

import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;

import ca.bcit.comp4655.jms.admin.Constants;
import ca.bcit.comp4655.jms.admin.PropertiesFactory;

public class Publisher implements Constants
{
	public static void main(String[] args) 
	{
	    try 
	    {
	    	Context ctx = new InitialContext( PropertiesFactory.getProperties ( URL ) );
	    	TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup( "ConnectionFactory" );
	    	
	    	//Establish TopicConnection from Factory
	    	TopicConnection connection = factory.createTopicConnection();
	    	connection.start();
	    	
	    	//Get the Topic using JNDI
	    	Topic topic = ( Topic )ctx.lookup( TOPIC );
	    	
	    	//Create TopicPublisher from TopicSession
	    	TopicSession session = 
				connection.createTopicSession( false, Session.AUTO_ACKNOWLEDGE );
	    	TopicPublisher publisher = session.createPublisher( topic );
	    	
	    	//Create Message from TopicSession
	    	MapMessage message = session.createMapMessage();
			
	    	//Create the Message Body
	    	message.setString( "symbol", "AAPL" );
			message.setDouble( "price", 150.59 );
			System.out.println( "Sending Map Message" );
	
			//Publish Message to Topic using TopicPublisher
			publisher.publish( message );
					    	
		    System.exit( 0 );
		        
	    } 
	    catch (Exception exception) 
	    {
	        exception.printStackTrace();
	    }
	}
}
