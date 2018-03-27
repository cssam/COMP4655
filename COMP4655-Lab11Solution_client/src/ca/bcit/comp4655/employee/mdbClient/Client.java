package ca.bcit.comp4655.employee.mdbClient;


import java.util.Hashtable;

import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import ca.bcit.comp4655.employee.domain.Employee;



public class Client implements Constants
{
	public static void main(String[] args)
	{
		
		try
		{
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			
			Context ctx = new InitialContext(env);
			QueueConnectionFactory factory = ( QueueConnectionFactory ) ctx.lookup("/ConnectionFactory");
			QueueConnection connection = factory.createQueueConnection();
			connection.start();
			
			QueueSession session = connection.createQueueSession(false,
					Session.DUPS_OK_ACKNOWLEDGE);
			ObjectMessage message = session.createObjectMessage();
			
			message.setObject("A01234589");
			
			Queue queue = (Queue) ctx.lookup(REQUEST_QUEUE);
			MessageProducer producer = session.createProducer( queue );
			producer.send( message );
			System.out.println ( "Sent employee id" );
			
			Employee employee = (Employee )message.getObject();
			System.out.println ("Received in client: employee Name: "+employee.getFirstName() +" on Queue.");
			connection.close();
		}
		catch ( Exception exception ) 
		{
			exception.printStackTrace();
		}
		
		
	}
}
