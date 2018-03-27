package ca.bcit.comp4655.webapp.employee.presentation.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


import ca.bcit.comp4655.employee.ejb.EmployeeService;
import ca.bcit.comp4655.employee.ejb.ResponseCode;

public class DeleteEmployeeCommand extends AbstractCommand
{
	Logger logger = Logger.getLogger( DeleteEmployeeCommand.class );
	
	@Override
	public void execute( HttpServletRequest request, EmployeeService service)
	{
		ResponseCode responseCode = null;
		System.out.println("In DeleteEmployeeCommand");
		String empId = request.getParameter( "id" );
		responseCode = service.removeEmployee(empId);
		request.setAttribute( "delResponseCode", responseCode );

	}

}
