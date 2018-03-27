package ca.bcit.comp4655.webapp.employee.service;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import ca.bcit.comp4655.webapp.employee.domain.Employee;
import ca.bcit.comp4655.webapp.employee.domain.ResponseCode;

public interface EmployeeServices
{
	static final String EMP_ID_PATTERN="[A][0][0-9]{7}";
	List<Employee> getEmployeeList() throws FileNotFoundException, ParseException;
	Employee findEmployeeById( String id ) throws FileNotFoundException, ParseException;
	ResponseCode addEmployee( Employee employee ) throws FileNotFoundException, ParseException;
	ResponseCode removeEmployee( Employee employee ) throws FileNotFoundException, ParseException;
}
