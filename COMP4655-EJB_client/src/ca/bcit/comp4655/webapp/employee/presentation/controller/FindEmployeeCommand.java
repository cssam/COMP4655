package ca.bcit.comp4655.webapp.employee.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import ca.bcit.comp4655.employee.ejb.Employee;
import ca.bcit.comp4655.employee.ejb.EmployeeService;
import ca.bcit.comp4655.employee.ejb.ResponseCode;
import ca.bcit.comp4655.webapp.employee.presentation.util.PresentationUtil;


public class FindEmployeeCommand extends AbstractCommand 
{
	Logger logger = Logger.getLogger( FindEmployeeCommand.class );
	

	public void execute(HttpServletRequest request, EmployeeService service)
	{
		ResponseCode responseCode = new ResponseCode();
		String empId = request.getParameter( "id" );
		System.out.println("In FindEmployeeCommand. empId:"+empId);

		Employee employee = service.getEmployeeById(empId); 

		if ( employee!=null )
		{
			request.setAttribute( "foundEmp", employee )  ;
			responseCode.setCode( PresentationUtil.getString( "find.employee.success.code" ) );
			responseCode.setDesc( PresentationUtil.getString( "find.employee.success.desc" ) );
			request.setAttribute( "findResponseCode", responseCode );
			
		} else {
			responseCode.setCode( PresentationUtil.getString( "error.find.employee.duplicate.code" ) );
			String[] args = { empId };
			responseCode.setDesc( PresentationUtil.getString( "error.find.employee.duplicate.desc", args ) );
			request.setAttribute( "findResponseCode", responseCode );

		}
		
		

	}

}
