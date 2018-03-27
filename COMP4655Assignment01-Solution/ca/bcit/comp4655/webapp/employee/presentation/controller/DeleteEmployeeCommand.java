package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.webapp.employee.domain.Employee;
import ca.bcit.comp4655.webapp.employee.domain.ResponseCode;

public class DeleteEmployeeCommand extends AbstractCommand
{
	Logger logger = Logger.getLogger( DeleteEmployeeCommand.class );
	
	@Override
	public void execute( HttpServletRequest request )
	{
		Employee employee = new Employee();
		String empId = request.getParameter( "id" );
		employee.setId( empId );
		
		try
		{
			ResponseCode responseCode = employeeServices.removeEmployee( employee );
			request.setAttribute( "delResponseCode", responseCode );
		} 
		catch ( FileNotFoundException e )
		{
			logger.error( "Deleting employee with ID " +empId + "failed" ,e );
		} 
		catch ( ParseException e )
		{
			logger.error( "Deleting employee with ID " +empId + "failed" ,e );
		}

	}

}
