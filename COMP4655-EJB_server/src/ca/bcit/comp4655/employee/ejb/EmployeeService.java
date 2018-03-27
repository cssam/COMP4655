package ca.bcit.comp4655.employee.ejb;

import java.util.List;

public interface EmployeeService {
	
	List<Employee> getEmployeeList();
	Employee getEmployeeById(String employeeId);
	ResponseCode addEmployee( Employee employee );
	ResponseCode removeEmployee(String employeeId);

}
