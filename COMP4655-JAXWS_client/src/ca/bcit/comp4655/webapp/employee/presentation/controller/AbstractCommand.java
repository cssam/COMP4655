package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.net.ConnectException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceRef;

import ca.bcit.comp4655.employee.xml.EmployeeServiceImplService;
import ca.bcit.comp4655.employee.xml.EmployeeServicePort;



public abstract class AbstractCommand implements Command
{
//	@WebServiceRef( wsdlLocation="http://localhost:8181/employeeServices.wsdl")
//	public static EmployeeServiceImplService service = new EmployeeServiceImplService();
//	public static EmployeeServicePort port;

	public AbstractCommand()
	{
//		port = service.getEmployeeServiceImplPort();
	}
	
	public abstract void execute( HttpServletRequest request, EmployeeServicePort port);
	
}
