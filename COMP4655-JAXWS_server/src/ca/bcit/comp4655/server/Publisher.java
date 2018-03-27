package ca.bcit.comp4655.server;

import javax.xml.ws.Endpoint;

import ca.bcit.comp4655.employee.xml.EmployeeServiceImpl;


/**
 * 
 * @author Arash Ghavami
 * 
 */
public class Publisher {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Endpoint endpoint = Endpoint.create( new EmployeeServiceImpl() );
		  endpoint.publish( "http://localhost:8181/employeeServices" );
	}

}
