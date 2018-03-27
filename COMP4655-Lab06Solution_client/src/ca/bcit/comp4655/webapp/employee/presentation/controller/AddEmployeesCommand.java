package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.employee.types.Employee;
import ca.bcit.comp4655.impl.EmployeeServiceImplService;
import ca.bcit.comp4655.impl.EmployeeServicePort;


public class AddEmployeesCommand extends AbstractCommand {

	Logger logger = Logger.getLogger( AddEmployeesCommand.class );
	@Override
	public void execute(HttpServletRequest request, EmployeeServicePort port)
	{
		Employee emp = new Employee();
		emp.setId( request.getParameter("id") );
		emp.setFirstName( request.getParameter( "fname" ) );
		emp.setLastName( request.getParameter( "lname" ) );
		
//		try
//		{
			//request.setAttribute( "addResponseCode", employeeServices.addEmployee( emp ) );
//		} 
//		catch ( FileNotFoundException e )
//		{
//			//TODO update the responseCode
//			logger.error( e );
//		} catch ( ParseException e )
//		{	
//			//TODO update the responseCode
//			logger.error( e );
//		}
		
	}

	
	
}
