package ca.bcit.comp4655.webapp.employee.presentation.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.employee.types.ResponseCode;
import ca.bcit.comp4655.employee.xml.*;
import ca.bcit.comp4655.webapp.employee.presentation.util.PresentationUtil;

public class DeleteEmployeeCommand extends AbstractCommand
{
	Logger logger = Logger.getLogger( DeleteEmployeeCommand.class );
	
	@Override
	public void execute( HttpServletRequest request, EmployeeServicePort port)
	{
		ResponseCode responseCode = null;
		System.out.println("In DeleteEmployeeCommand");
		String empId = request.getParameter( "id" );
		try
		{
			responseCode = port.removeEmployee(empId);
			request.setAttribute( "delResponseCode", responseCode );
		} 
		catch ( EmployeeServerException e )
		{
			logger.error( "Deleting employee with ID " +empId + "failed" ,e );
			responseCode = new ResponseCode();
			responseCode.setCode(PresentationUtil.getString( "error.remove.employee.invalid.code" ));
			String[] args = { empId };
			responseCode.setDesc(PresentationUtil.getString( "error.remove.employee.invalid.desc", args) );
		} 


	}

}
