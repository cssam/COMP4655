package ca.bcit.comp4655.employee.data;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import ca.bcit.comp4655.employee.exception.EmployeeServerException;
import ca.bcit.comp4655.employee.types.Employee;
//import ca.bcit.comp4655.employee.types.ResponseCode;

public interface EmployeeDao
{
	List<Employee> getEmployeeList() throws EmployeeServerException;
	Employee getEmployeeById(String employeeId) throws EmployeeServerException;
//	boolean addEmployee( Employee employee ) throws FileNotFoundException, ParseException;
//	ResponseCode removeEmployee( Employee employee )throws FileNotFoundException, ParseException;
}
