 package ca.bcit.comp4655.webapp.employee.presentation.controller;

import javax.servlet.http.HttpServletRequest;

import ca.bcit.comp4655.employee.xml.EmployeeServicePort;



public interface Command 
{


	public void execute( HttpServletRequest request, EmployeeServicePort port) throws Exception;
}
