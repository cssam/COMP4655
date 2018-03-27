package ca.bcit.comp4655.employee.ejb;

import java.util.List;

import javax.ejb.Stateless;

import ca.bcit.comp4655.employee.data.EmployeeDao;
import ca.bcit.comp4655.employee.data.EmployeeDaoImpl;
import ca.bcit.comp4655.employee.exception.EmployeeServerException;

@Stateless( mappedName="EmployeeServiceBean" )

public class EmployeeServiceBean implements EmployeeServiceLocal, EmployeeServiceRemote {
	
	EmployeeDao dao = new EmployeeDaoImpl();

	public EmployeeServiceBean() {

	}

	@Override
	public List<Employee> getEmployeeList() {
		try {
			return dao.getEmployeeList();
		} catch (EmployeeServerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		try {
			return dao.getEmployeeById(employeeId);
		} catch (EmployeeServerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseCode addEmployee(Employee employee) {
		try {
			return dao.addEmployee(employee);
		} catch (EmployeeServerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseCode removeEmployee(String employeeId) {
		try {
			return dao.removeEmployee(employeeId);
		} catch (EmployeeServerException e) {
			e.printStackTrace();
		}
		return null;
	}

}
