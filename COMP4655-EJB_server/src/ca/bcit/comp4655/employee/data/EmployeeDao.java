package ca.bcit.comp4655.employee.data;


import java.util.List;

import ca.bcit.comp4655.employee.ejb.Employee;
import ca.bcit.comp4655.employee.ejb.ResponseCode;
import ca.bcit.comp4655.employee.exception.EmployeeServerException;


public interface EmployeeDao
{
	List<Employee> getEmployeeList() throws EmployeeServerException;
	Employee getEmployeeById(String employeeId) throws EmployeeServerException;
	ResponseCode addEmployee( Employee employee ) throws EmployeeServerException;
	ResponseCode removeEmployee(String employeeId) throws EmployeeServerException;
}
