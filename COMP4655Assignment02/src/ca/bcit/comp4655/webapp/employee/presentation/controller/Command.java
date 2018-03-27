 package ca.bcit.comp4655.webapp.employee.presentation.controller;

import javax.servlet.http.HttpServletRequest;


public interface Command 
{

	public void execute( HttpServletRequest request ) throws Exception;
}
