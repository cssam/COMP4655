package ca.bcit.comp4655.webapp.employee.service;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ca.bcit.comp4655.webapp.employee.domain.Employee;
import ca.bcit.comp4655.webapp.employee.domain.ResponseCode;

@WebService
public interface EmployeeServices
{
	//static final String EMP_ID_PATTERN="[A][0][0-9]{7}";
	@WebMethod
	List<Employee> getEmployeeList() throws FileNotFoundException, ParseException;
	@WebMethod
	Employee findEmployeeById( String id ) throws FileNotFoundException, ParseException;
	@WebMethod
	ResponseCode addEmployee( Employee employee ) throws FileNotFoundException, ParseException;
	@WebMethod
	ResponseCode removeEmployee( Employee employee ) throws FileNotFoundException, ParseException;
}
