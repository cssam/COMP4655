package ca.bcit.comp4655.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import ca.bcit.comp4655.rest.employee.data.EmployeeDao;
import ca.bcit.comp4655.rest.employee.data.EmployeeDaoImpl;
import ca.bcit.comp4655.rest.employee.domain.Employee;
import ca.bcit.comp4655.rest.employee.domain.ResponseCode;
import ca.bcit.comp4655.rest.employee.exception.EmployeeServerException;
import ca.bcit.comp4655.rest.util.ServerUtil;


@Path("/employeeService")
public class EmployeeRestServer {
	
	@Context 
	UriInfo uriInfo;
	
	private static EmployeeDao dao = new EmployeeDaoImpl();

	public EmployeeRestServer() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	//@Path( "/findEmployee/{employeeId:A[0-9]*}" )
	@Path( "/findEmployee/{employeeId}" )
	@Produces( MediaType.APPLICATION_XML )
	public Employee findEmployeeById(@PathParam("employeeId" ) String employeeId) {
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		String employeeIdMessage = params.getFirst("employeeId");
		try {
			Employee employee = dao.getEmployeeById(employeeIdMessage);
			return employee;
			
		} catch (EmployeeServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@POST
	@Path( "/employeeUpdate/{message}" )
	@Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
	@Produces ( MediaType.TEXT_XML )
	public ResponseCode updateMessage ( @FormParam( "message" ) Employee oldMessage )
	{
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		String newMessage = params.getFirst("message");

		ResponseCode responseCode;
		
		try {
			List<Employee> list = dao.getEmployeeList();
			int index = -1;			
			if (list.contains( oldMessage ) ) {
				index = list.indexOf(oldMessage);
			}
			Employee newEmployee = new Employee();
			responseCode = dao.updateEmployee(index, newEmployee);
			System.out.println("addEmployee response "+responseCode.getDesc());
		} catch (EmployeeServerException e) {
			e.printStackTrace();
			responseCode = new ResponseCode();
			responseCode.setCode( ServerUtil.getString( "error.update.employee.failed" ) );
			String[] args ={newMessage};
			responseCode.setDesc( ServerUtil.getString( "error.update.employee.failed", args ) );
			return  responseCode;
		} 

		return responseCode;
	}
	
	@PUT
	@Path( "/addEmployee/{newMessage}" )
	@Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
	@Produces ( MediaType.TEXT_XML )
	public ResponseCode createEmployee(Employee employee) {
		MultivaluedMap<String, String> params = uriInfo.getPathParameters();
		String firstname = params.getFirst("firstname");
		String lastname = params.getFirst("lastname");
		Employee newemployee = new Employee();
		newemployee.setFirstName(firstname);
		newemployee.setLastName(lastname);
		
		ResponseCode responseCode;
		
		try {
			responseCode = dao.addEmployee(newemployee);
			System.out.println("addEmployee response "+responseCode.getDesc());
		} catch (EmployeeServerException e) {
			e.printStackTrace();
			responseCode = new ResponseCode();
			responseCode.setCode( ServerUtil.getString( "error.add.employee.failed" ) );
			String[] args ={firstname};
			responseCode.setDesc( ServerUtil.getString( "error.add.employee.failed", args ) );
		}
		
		return responseCode;
	}
	

}
