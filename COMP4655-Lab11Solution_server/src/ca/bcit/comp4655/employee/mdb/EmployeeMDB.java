package ca.bcit.comp4655.employee.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import ca.bcit.comp4655.employee.data.EmployeeDao;
import ca.bcit.comp4655.employee.data.EmployeeDaoImpl;
import ca.bcit.comp4655.employee.domain.Employee;
import ca.bcit.comp4655.employee.exception.EmployeeServerException;

@MessageDriven(mappedName="jms/employee",
		activationConfig=
		{
			@ActivationConfigProperty( propertyName="destinationType", propertyValue = "javax.jms.Queue" ),
			@ActivationConfigProperty( propertyName="destination", propertyValue="/queue/request/week11" ),
			@ActivationConfigProperty( propertyName="acknowledgeMode", propertyValue= "AUTO_ACKNOWLEDGE" )
		}
		)

public class EmployeeMDB implements MessageListener {
	
	
	@Resource
    private ConnectionFactory connectionFactory;

    private EmployeeDao employeeDao;

	public EmployeeMDB() {
		employeeDao = new EmployeeDaoImpl();

	}

	@Override
	public void onMessage(Message msg) {
		
		try {
			
			
			Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue)msg.getJMSDestination();
            MessageProducer producer = session.createProducer(queue);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            ObjectMessage message = session.createObjectMessage();
            TextMessage txtMessage = (TextMessage) msg;
			String employeeId = txtMessage.getText();
			Employee employee = employeeDao.getEmployeeById(employeeId);
			
            message.setObject(employee);
			producer.send( message );
			System.out.println ("Retrived in server: employee Name: "+employee.getFirstName() +" on Queue.");
			connection.close();
			
		} catch (JMSException | EmployeeServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
