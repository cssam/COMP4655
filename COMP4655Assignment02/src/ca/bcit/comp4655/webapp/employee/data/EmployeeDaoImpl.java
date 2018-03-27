package ca.bcit.comp4655.webapp.employee.data;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.webapp.employee.domain.Employee;
import ca.bcit.comp4655.webapp.employee.domain.ResponseCode;
import ca.bcit.comp4655.webapp.employee.presentation.util.PresentationUtil;

public class EmployeeDaoImpl implements EmployeeDao
{
	private static List<Employee> employees;
	
	private static Logger logger = Logger.getLogger( EmployeeDaoImpl.class );

	public EmployeeDaoImpl()
	{
	}

	@Override
	public List<Employee> getEmployeeList() throws FileNotFoundException, ParseException
	{
		return getSingltonEmployeeList();
	}

	private synchronized static List<Employee> getSingltonEmployeeList() throws FileNotFoundException, ParseException
	{
		if (employees !=null )
		{
			return employees;
		}
		else
		{
			employees = new ArrayList<Employee>();
		
			try
			{
				Scanner lineScanner = getScanner( EmployeeDaoImpl.class.getClassLoader().getResourceAsStream( "/resources/Employees.txt" ) );
				//Scanner lineScanner = getScanner( EmployeeDaoImpl.class.getResourceAsStream("/resources/Employees.txt") );
				while ( lineScanner.hasNext() )
				{
					String line = lineScanner.next();
					Scanner employeeScanner = new Scanner( line );
					employeeScanner.useDelimiter( "\t" );
					Employee emp = new Employee();
					emp.setId( employeeScanner.next() );
					emp.setFirstName( employeeScanner.next() );
					emp.setLastName( employeeScanner.next() );
					emp.setDob( convertStringToDate( employeeScanner.next() ) );
					employees.add( emp );
				}
			}
	
			catch ( FileNotFoundException e )
			{
				logger.error( "Could not locate employees.txt file.", e );
				throw new FileNotFoundException( "employee.txt.notfound" );
			} 
			catch ( ParseException e )
			{
				logger.error( e );
				throw new ParseException( "date.notparsable" , e.getErrorOffset() );
			}
			return employees;
		}
	}

	private static Date convertStringToDate( String date )throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		return sdf.parse( date );
	}

	private static Scanner getScanner( InputStream file )
			throws FileNotFoundException
	{
		return new Scanner( file ).useDelimiter( "\\n" );
	}

	@Override
	public boolean addEmployee( Employee employee ) throws FileNotFoundException, ParseException
	{
		return getEmployeeList().add( employee );
	}

	@Override
	public ResponseCode removeEmployee( Employee employee )throws FileNotFoundException, ParseException
	{
		ResponseCode responseCode = new ResponseCode();
		
		if ( getEmployeeList().remove( employee ) )
		{
			responseCode.setCode( PresentationUtil
					.getString( "remove.employee.success.code" ) );
			
			String[] args ={ employee.getId() };
			responseCode.setDesc( PresentationUtil.getString( "remove.employee.success.desc", args ) );
			return responseCode;
		}
		responseCode.setCode( PresentationUtil.getString( "error.remove.employee.duplicate.code" ) );
		String[] args = { employee.getId() };
		responseCode.setDesc( PresentationUtil.getString( "error.remove.employee.duplicate.desc", args ) );
		return responseCode;
	}

}
