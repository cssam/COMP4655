 package ca.bcit.comp4655.webapp.employee.presentation.controller;

import javax.servlet.http.HttpServletRequest;

import ca.bcit.comp4655.employee.ejb.EmployeeService;



public interface Command 
{


	public void execute( HttpServletRequest request, EmployeeService service) throws Exception;
}
