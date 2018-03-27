package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


import ca.bcit.comp4655.employee.types.Employee;
import ca.bcit.comp4655.employee.types.ResponseCode;
import ca.bcit.comp4655.impl.EmployeeServerException;
import ca.bcit.comp4655.impl.EmployeeServiceImplService;
import ca.bcit.comp4655.impl.EmployeeServicePort;




public class GetAllEmployeesCommand extends AbstractCommand
{
	Logger logger = Logger.getLogger( GetAllEmployeesCommand.class );
	
	
	public void execute( HttpServletRequest request, EmployeeServicePort port )
	{
		ResponseCode responseCode = null;
		System.out.println("In GetAllEmployeesCommand");
		try
		{
			ArrayList<Employee> empList = (ArrayList<Employee>) port.getEmployeeList();
			request.getSession().setAttribute( "employees", empList);
			for (Employee employee : empList) {
				System.out.println(employee.getFirstName());
			}

			
		} 
		catch (EmployeeServerException e )
		{
			responseCode = new ResponseCode();
			responseCode.setCode( "error." + e.getMessage() + ".code" );
			responseCode.setDesc( "error." + e.getMessage() + ".desc" );
			logger.error( "Could not load the employee.txt file" + e );
		} 
		
	}
}
