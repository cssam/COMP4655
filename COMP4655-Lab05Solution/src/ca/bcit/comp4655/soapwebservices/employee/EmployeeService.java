package ca.bcit.comp4655.soapwebservices.employee;

import javax.jws.WebService;

import ca.bcit.comp4655.soapwebservices.db.Database;
import ca.bcit.comp4655.soapwebservices.domain.Employee;

@WebService(endpointInterface="ca.bcit.comp4655.soapwebservices.employee.iEmployeeService")
public class EmployeeService implements iEmployeeService {

	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}

	public Employee getEmployee( int employeeId) {
		return Database.getEmployeeById(employeeId);
	}

}
