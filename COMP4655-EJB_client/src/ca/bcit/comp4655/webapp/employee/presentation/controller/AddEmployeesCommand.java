package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.employee.ejb.Employee;
import ca.bcit.comp4655.employee.ejb.ResponseCode;
import ca.bcit.comp4655.employee.ejb.EmployeeService;
import ca.bcit.comp4655.webapp.employee.presentation.util.PresentationUtil;


public class AddEmployeesCommand extends AbstractCommand {

	Logger logger = Logger.getLogger( AddEmployeesCommand.class );
	@Override
	public void execute(HttpServletRequest request, EmployeeService service)
	{
		ResponseCode responseCode = null;
		System.out.println("In DeleteEmployeeCommand");
		
		try {
			Employee emp = new Employee();
			emp.setId( request.getParameter("id") );
			emp.setFirstName( request.getParameter( "fname" ) );
			emp.setLastName( request.getParameter( "lname" ) );
			emp.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter( "dob" )) );
			responseCode = service.addEmployee(emp);
		}  catch (ParseException e) {
			logger.error( "Add employee with ID " +request.getParameter("id") + "failed" ,e );
			responseCode = new ResponseCode();
			responseCode.setCode(PresentationUtil.getString( "error.add.employee.invalid.code" ));
			String[] args = { request.getParameter("id") };
			responseCode.setDesc(PresentationUtil.getString( "error.add.employee.invalid.desc", args) );
		} 
		request.setAttribute( "addResponseCode", responseCode );
		
	}

	
}
