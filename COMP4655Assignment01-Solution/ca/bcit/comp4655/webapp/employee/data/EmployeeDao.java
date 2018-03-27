package ca.bcit.comp4655.webapp.employee.data;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import ca.bcit.comp4655.webapp.employee.domain.Employee;
import ca.bcit.comp4655.webapp.employee.domain.ResponseCode;

public interface EmployeeDao
{
	List<Employee> getEmployeeList() throws FileNotFoundException, ParseException;
	boolean addEmployee( Employee employee ) throws FileNotFoundException, ParseException;
	ResponseCode removeEmployee( Employee employee )throws FileNotFoundException, ParseException;
}
