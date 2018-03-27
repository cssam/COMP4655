package ca.bcit.comp4655.webapp.employee.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import ca.bcit.comp4655.employee.ejb.EmployeeService;



public abstract class AbstractCommand implements Command
{

	public AbstractCommand()
	{

	}
	
	public abstract void execute( HttpServletRequest request, EmployeeService service);
	
}
