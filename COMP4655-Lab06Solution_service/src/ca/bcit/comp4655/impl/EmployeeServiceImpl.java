package ca.bcit.comp4655.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ca.bcit.comp4655.employee.data.EmployeeDao;
import ca.bcit.comp4655.employee.data.EmployeeDaoImpl;
import ca.bcit.comp4655.employee.types.Employee;
import ca.bcit.comp4655.employee.types.GetEmployeeList;
import ca.bcit.comp4655.employee.xml.EmployeeServerException;
import ca.bcit.comp4655.employee.xml.EmployeeServicePort;


@WebService( endpointInterface="ca.bcit.comp4655.employee.xml.EmployeeServicePort" )
public class EmployeeServiceImpl implements EmployeeServicePort {

	private static EmployeeDao dao = new EmployeeDaoImpl();
	
	public EmployeeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getEmployeeList", targetNamespace = "http://www.bcit.ca/comp4655/employee/types/", className = "ca.bcit.comp4655.employee.types.GetEmployeeList")
	@ResponseWrapper(localName = "getEmployeeListResponse", targetNamespace = "http://www.bcit.ca/comp4655/employee/types/", className = "ca.bcit.comp4655.employee.types.GetEmployeeListResponse")
	public List<Employee> getEmployeeList() throws EmployeeServerException {
		List<Employee> list = null;
		try {
			list = dao.getEmployeeList();
		} catch (ca.bcit.comp4655.employee.exception.EmployeeServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "findEmployeeById", targetNamespace = "http://www.bcit.ca/comp4655/employee/types/", className = "ca.bcit.comp4655.employee.types.FindEmployeeById")
	@ResponseWrapper(localName = "findEmployeeByIdResponse", targetNamespace = "http://www.bcit.ca/comp4655/employee/types/", className = "ca.bcit.comp4655.employee.types.FindEmployeeByIdResponse")
	public Employee findEmployeeById(
			@WebParam(name = "employeeId", targetNamespace = "") String employeeId)
			throws EmployeeServerException {
		Employee employee = null;
		try {
			employee = dao.getEmployeeById(employeeId);
		} catch (ca.bcit.comp4655.employee.exception.EmployeeServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}

}
