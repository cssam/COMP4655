package ca.bcit.comp4655.rest.util;

import java.util.ArrayList;
import java.util.List;

import ca.bcit.comp4655.rest.employee.domain.Employee;

public class EmployeeResource {

	static List<Employee> employees;
	private static Employee employee;
	
	public EmployeeResource() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		
		employees = new ArrayList<Employee>();
		employee = new Employee();
		employee.setId("A01234589");
		employee.setFirstName("Norm");
		employee.setLastName("Winson");
		employees.add(employee);

		employee = new Employee();
		employee.setId("A01234599");
		employee.setFirstName("Homer");
		employee.setLastName("Simpson");
		employees.add(employee);
		
		employee = new Employee();
		employee.setId("A01234609");
		employee.setFirstName("Bart");
		employee.setLastName("Simpson");
		employees.add(employee);

	}
	
	
	public static List<Employee> getEmployees()
	{
		return employees;
	}

}
