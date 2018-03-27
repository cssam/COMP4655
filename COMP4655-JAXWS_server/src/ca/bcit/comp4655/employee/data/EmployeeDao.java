package ca.bcit.comp4655.employee.data;


import java.util.List;

import ca.bcit.comp4655.employee.exception.EmployeeServerException;
import ca.bcit.comp4655.employee.types.Employee;
import ca.bcit.comp4655.employee.types.ResponseCode;

public interface EmployeeDao
{
	List<Employee> getEmployeeList() throws EmployeeServerException;
	Employee getEmployeeById(String employeeId) throws EmployeeServerException;
	ResponseCode addEmployee( Employee employee ) throws EmployeeServerException;
	ResponseCode removeEmployee(String employeeId) throws EmployeeServerException;
}
