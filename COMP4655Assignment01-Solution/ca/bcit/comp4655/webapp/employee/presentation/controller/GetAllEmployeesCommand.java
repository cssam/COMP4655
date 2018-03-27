package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.webapp.employee.domain.ResponseCode;

public class GetAllEmployeesCommand extends AbstractCommand
{
	Logger logger = Logger.getLogger( GetAllEmployeesCommand.class );

	@Override
	public void execute( HttpServletRequest request )
	{
		ResponseCode responseCode = null;
		try
		{
			request.getSession().setAttribute( "employees", employeeServices.getEmployeeList() );
		} 
		catch ( FileNotFoundException e )
		{
			responseCode = new ResponseCode();
			responseCode.setCode( "error." + e.getMessage() + ".code" );
			responseCode.setDesc( "error." + e.getMessage() + ".desc" );
			logger.error( "Could not load the employee.txt file" + e );
		} catch ( ParseException e )
		{
			responseCode = new ResponseCode();
			responseCode.setCode( "error." + e.getMessage() + ".code" );
			responseCode.setDesc( "error." + e.getMessage() + ".desc" );
			logger.error( "Could not parse a date in the employee.txt file. Error in offset: " + e.getErrorOffset() , e);
		}
		
	}
}
