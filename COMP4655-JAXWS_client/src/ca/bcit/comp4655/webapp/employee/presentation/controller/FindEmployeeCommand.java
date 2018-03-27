package ca.bcit.comp4655.webapp.employee.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import ca.bcit.comp4655.employee.types.Employee;
import ca.bcit.comp4655.employee.types.ResponseCode;
import ca.bcit.comp4655.employee.xml.*;
import ca.bcit.comp4655.webapp.employee.presentation.util.PresentationUtil;


public class FindEmployeeCommand extends AbstractCommand 
{
	Logger logger = Logger.getLogger( FindEmployeeCommand.class );
	

	public void execute(HttpServletRequest request, EmployeeServicePort port)
	{
		ResponseCode responseCode = new ResponseCode();
		String empId = request.getParameter( "id" );
		System.out.println("In FindEmployeeCommand. empId:"+empId);

		try
		{

			Employee employee = port.findEmployeeById( empId ); 

			if ( employee!=null )
			{
				request.setAttribute( "foundEmp", employee )  ;
				responseCode.setCode( PresentationUtil.getString( "find.employee.success.code" ) );
				responseCode.setDesc( PresentationUtil.getString( "find.employee.success.desc" ) );
				request.setAttribute( "findResponseCode", responseCode );
				return;
			}
			
			responseCode.setCode( PresentationUtil.getString( "error.find.employee.duplicate.code" ) );
			String[] args = { empId };
			responseCode.setDesc( PresentationUtil.getString( "error.find.employee.duplicate.desc", args ) );
			request.setAttribute( "findResponseCode", responseCode );
		} catch (EmployeeServerException e) {
			logger.error( e );
			responseCode.setCode( PresentationUtil.getString( "error.find.employee.invalid.code" ) );
			String[] args = { empId };
			responseCode.setDesc( PresentationUtil.getString( "error.find.employee.invalid.desc", args ) );
			request.setAttribute( "findResponseCode", responseCode );
		}

	}

}
