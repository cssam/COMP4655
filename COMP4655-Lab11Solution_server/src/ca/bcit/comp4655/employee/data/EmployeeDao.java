package ca.bcit.comp4655.employee.data;


import ca.bcit.comp4655.employee.exception.EmployeeServerException;
import ca.bcit.comp4655.employee.domain.Employee;


public interface EmployeeDao
{
	Employee getEmployeeById(String employeeId) throws EmployeeServerException;
}
