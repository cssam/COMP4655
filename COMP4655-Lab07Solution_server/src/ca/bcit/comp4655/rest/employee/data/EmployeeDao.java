package ca.bcit.comp4655.rest.employee.data;


import java.util.List;

import ca.bcit.comp4655.rest.employee.domain.Employee;
import ca.bcit.comp4655.rest.employee.domain.ResponseCode;
import ca.bcit.comp4655.rest.employee.exception.EmployeeServerException;

public interface EmployeeDao
{
	List<Employee> getEmployeeList() throws EmployeeServerException;
	Employee getEmployeeById(String employeeId) throws EmployeeServerException;
	ResponseCode addEmployee( Employee employee ) throws EmployeeServerException;
	ResponseCode removeEmployee(String employeeId) throws EmployeeServerException;
	ResponseCode updateEmployee(int index, Employee newEmployee ) throws EmployeeServerException;
}
