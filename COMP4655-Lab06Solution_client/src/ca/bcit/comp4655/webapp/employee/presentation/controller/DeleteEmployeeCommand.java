package ca.bcit.comp4655.webapp.employee.presentation.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.employee.types.Employee;
import ca.bcit.comp4655.employee.types.ResponseCode;
import ca.bcit.comp4655.impl.EmployeeServerException;
import ca.bcit.comp4655.impl.EmployeeServiceImplService;
import ca.bcit.comp4655.impl.EmployeeServicePort;

public class DeleteEmployeeCommand extends AbstractCommand
{
	Logger logger = Logger.getLogger( DeleteEmployeeCommand.class );
	
	@Override
	public void execute( HttpServletRequest request, EmployeeServicePort port)
	{
//		Employee employee = new Employee();
//		String empId = request.getParameter( "id" );
//		employee.setId( empId );
//		
//		try
//		{
//			ResponseCode responseCode = employeeServicePort.removeEmployee( employee );
//			request.setAttribute( "delResponseCode", responseCode );
//		} 
//		catch ( EmployeeServerException e )
//		{
//			logger.error( "Deleting employee with ID " +empId + "failed" ,e );
//		} 


	}

}
