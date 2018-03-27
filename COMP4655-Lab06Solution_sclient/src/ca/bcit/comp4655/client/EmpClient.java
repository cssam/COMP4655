package ca.bcit.comp4655.client;

import java.util.ArrayList;

import javax.xml.ws.WebServiceRef;

import ca.bcit.comp4655.impl.EmployeeServerException;
import ca.bcit.comp4655.impl.EmployeeServiceImplService;
import ca.bcit.comp4655.impl.EmployeeServicePort;
import ca.bcit.comp4655.employee.types.*;



public class EmpClient 
{
	@WebServiceRef( wsdlLocation="http://localhost:8181/employeeServices?wsdl=1")
	private static EmployeeServiceImplService service = new EmployeeServiceImplService();
	
	public static void main(String[] args) 
	{
		
		EmployeeServicePort port = service.getEmployeeServiceImplPort();
		
		try {
			ArrayList<Employee> list = (ArrayList<Employee>) port.getEmployeeList();
			
			for (Employee employee : list) {
				System.out.println(employee.getFirstName());
			}
			
			Employee find = (Employee) port.findEmployeeById("A01234599");
			System.out.println(find.getFirstName()+" "+find.getLastName());
			
		} catch (EmployeeServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
}
