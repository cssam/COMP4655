package ca.bcit.comp4655.webapp.employee.presentation.controller;

import javax.servlet.http.HttpServletRequest;

import ca.bcit.comp4655.webapp.employee.service.EmployeeServices;
import ca.bcit.comp4655.webapp.employee.service.EmployeeServicesImpl;



public abstract class AbstractCommand implements Command
{

	protected EmployeeServices employeeServices;
	
	public AbstractCommand()
	{
		employeeServices = new EmployeeServicesImpl();
	}
	
	public abstract void execute( HttpServletRequest request );
	
}
