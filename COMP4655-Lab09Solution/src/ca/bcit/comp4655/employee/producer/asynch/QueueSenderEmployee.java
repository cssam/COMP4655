package ca.bcit.comp4655.employee.producer.asynch;

import java.util.Properties;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ca.bcit.comp4655.employee.domain.Employee;
import org.jboss.naming.remote.client.InitialContextFactory;
import org.xnio.Options;
import org.jboss.remoting3.spi.*;


/**
 * Message Producer class
 * @author Arash Ghavami
 *
 */
public class QueueSenderEmployee 
{	
	QueueConnection conn = null;
	QueueSession session = null;
	Queue queue = null;
	
	public QueueSenderEmployee ( String url, String queueName ) throws NamingException, JMSException 
	{
		// Step 1. Create an initial context to perform the JNDI lookup.
		Properties props = new Properties();
		//props.setProperty( Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory" );
		props.setProperty( Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory" );
		props.setProperty( Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces" );
		props.setProperty( Context.PROVIDER_URL, url );
		Context context = new InitialContext( props );
		
		// Step 2. Lookup the connection factory
		QueueConnectionFactory factory = 
			(QueueConnectionFactory)context.lookup( "ConnectionFactory" );
		
		// Step 3. Lookup the JMS queue
		queue = (Queue)context.lookup(queueName);
		
		// Step 4. Create the JMS objects to connect to the server and manage a session
		conn =  (QueueConnection) factory.createConnection();
		session = conn.createQueueSession( false, QueueSession.AUTO_ACKNOWLEDGE );
		
		conn.start();	
	}

	
	public void disconnect() throws JMSException 
	{
		if (conn != null) 
		{
			conn.stop();
		}

		if (session != null) 
		{
			session.close();
		}

		if (conn != null) 
		{
			conn.close();
		}
	}
	
	private void send(Employee employee) throws JMSException, NamingException 
	{	
		// Step 5. Create a JMS Message Producer to send a message on the queue
		ObjectMessage msgProducer = session.createObjectMessage();
		msgProducer.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT );
		
		// Step 6. Create a Text Message and send it using the producer

		
		if (employee!=null )
		{
			msgProducer.setObject(employee);
		}
		else
		{
			//Sends an empty control message to indicate the end of the message stream:
			employee = new Employee();
			msgProducer.setObject(employee);
		}
	}
	
	public static void main(String[] args) 
	{
		System.out.println("Sending a employee");
		try 
		{
			QueueSenderEmployee sender = new QueueSenderEmployee ( "jnp://localhost:4447", "queue/employee" );
			
			Employee employee = new Employee();
			employee.setFirstName("Arash");
			employee.setLastName("Ghavami");
			
			sender.send(employee);
			sender.disconnect();
			
			System.out.println( "JMS EMployee Sender Complete - sent" );
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
}
