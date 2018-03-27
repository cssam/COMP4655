package ca.bcit.comp4655.webapp.employee.presentation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.employee.types.Employee;
import ca.bcit.comp4655.employee.types.ResponseCode;
import ca.bcit.comp4655.employee.xml.EmployeeServerException;

import ca.bcit.comp4655.employee.xml.EmployeeServicePort;
import ca.bcit.comp4655.webapp.employee.presentation.util.PresentationUtil;


public class AddEmployeesCommand extends AbstractCommand {

	Logger logger = Logger.getLogger( AddEmployeesCommand.class );
	@Override
	public void execute(HttpServletRequest request, EmployeeServicePort port)
	{
		ResponseCode responseCode = null;
		System.out.println("In DeleteEmployeeCommand");
		
		try {
			Employee emp = new Employee();
			emp.setId( request.getParameter("id") );
			emp.setFirstName( request.getParameter( "fname" ) );
			emp.setLastName( request.getParameter( "lname" ) );
			emp.setDob(convertStringToDate(request.getParameter( "dob" )) );
			responseCode = port.addEmployee(emp);
		} catch (EmployeeServerException e) {
			logger.error( "Add employee with ID " +request.getParameter("id") + "failed" ,e );
			responseCode = new ResponseCode();
			responseCode.setCode(PresentationUtil.getString( "error.add.employee.invalid.code" ));
			String[] args = { request.getParameter("id") };
			responseCode.setDesc(PresentationUtil.getString( "error.add.employee.invalid.desc", args) );
		} catch (ParseException e) {
			logger.error( "Add employee with ID " +request.getParameter("id") + "failed" ,e );
			responseCode = new ResponseCode();
			responseCode.setCode(PresentationUtil.getString( "error.add.employee.invalid.code" ));
			String[] args = { request.getParameter("id") };
			responseCode.setDesc(PresentationUtil.getString( "error.add.employee.invalid.desc", args) );
		} catch (DatatypeConfigurationException e) {
			logger.error( "Add employee with ID " +request.getParameter("id") + "failed" ,e );
			responseCode = new ResponseCode();
			responseCode.setCode(PresentationUtil.getString( "error.add.employee.invalid.code" ));
			String[] args = { request.getParameter("id") };
			responseCode.setDesc(PresentationUtil.getString( "error.add.employee.invalid.desc", args) );
		}
		request.setAttribute( "addResponseCode", responseCode );
		
	}

	private static XMLGregorianCalendar convertStringToDate( String s ) throws ParseException, DatatypeConfigurationException
	{
		XMLGregorianCalendar result = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
			Date date = simpleDateFormat.parse(s);        
			GregorianCalendar gregorianCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
	        gregorianCalendar.setTime(date);
			result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		} 
		catch ( ParseException e ) {
			//logger.error( e );
			throw new ParseException( "date.notparsable" , e.getErrorOffset() );
		}
		catch ( DatatypeConfigurationException e )
		{
			//logger.error( e );
			throw new DatatypeConfigurationException( "date.notconfigurable" );
		}
		
        return result;
		
	}
	
}
