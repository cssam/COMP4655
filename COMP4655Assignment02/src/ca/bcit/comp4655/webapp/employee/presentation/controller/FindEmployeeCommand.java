package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.webapp.employee.domain.Employee;
import ca.bcit.comp4655.webapp.employee.domain.ResponseCode;
import ca.bcit.comp4655.webapp.employee.presentation.util.PresentationUtil;

public class FindEmployeeCommand extends AbstractCommand 
{
	Logger logger = Logger.getLogger( FindEmployeeCommand.class );
	
	@Override
	public void execute(HttpServletRequest request)
	{
		ResponseCode responseCode = new ResponseCode();
		String empId = request.getParameter( "id" );
		try
		{
			Employee employee =employeeServices.findEmployeeById( empId ); 
			
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
		}
		catch ( FileNotFoundException fne )
		{
			//TODO update the responseCode
			logger.error( fne );
		}
		catch ( ParseException pe )
		{
			//TODO update the responseCode
			logger.error( pe );
		}
	}

}
