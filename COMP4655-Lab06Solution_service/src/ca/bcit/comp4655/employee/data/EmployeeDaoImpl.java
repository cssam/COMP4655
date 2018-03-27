package ca.bcit.comp4655.employee.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import ca.bcit.comp4655.employee.exception.EmployeeServerException;
import ca.bcit.comp4655.employee.types.Employee;
import ca.bcit.comp4655.employee.types.ResponseCode;
import ca.bcit.comp4655.employee.util.ServerUtil;


public class EmployeeDaoImpl implements EmployeeDao
{
	private static List<Employee> employees;
	
	private static Logger logger = Logger.getLogger( EmployeeDaoImpl.class );

	public EmployeeDaoImpl()
	{
		if(employees==null) {
			try {
				getSingltonEmployeeList();
			} catch (FileNotFoundException | ParseException
					| DatatypeConfigurationException e) {
			}
		}
	}

	@Override
	public List<Employee> getEmployeeList() throws EmployeeServerException
	{
		
		try {
			return getSingltonEmployeeList();
		} catch (FileNotFoundException e) {
			new EmployeeServerException();
		} catch (ParseException e) {
			new EmployeeServerException();
		} catch (DatatypeConfigurationException e) {
			new EmployeeServerException();
		}
		return null;
		
	}
	
	@Override
	public Employee getEmployeeById(String employeeId) throws EmployeeServerException {

		if (employeeId!=null) {
			if(employees==null) {
				try {
					getSingltonEmployeeList();
				} catch (FileNotFoundException | ParseException
						| DatatypeConfigurationException e) {
					throw new EmployeeServerException();
				}
			}
			for (Employee employee : employees) {
				logger.info(employee.getId());
				if(employee.getId().trim().equals(employeeId.trim())) {
					return employee;
				}
			}
		} else {
			throw new EmployeeServerException("employeeId ID is Null");
		}
		
		
		return null;
	}

	private synchronized static List<Employee> getSingltonEmployeeList() throws FileNotFoundException, ParseException, DatatypeConfigurationException
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
	
				File employeeFile = new File("/Users/chandima/Google Drive/JavaProjectServ/Lab06Solution_service/build/classes/Employees.txt");
				Scanner lineScanner = getScanner(new FileInputStream(employeeFile));
				//Scanner lineScanner = getScanner( EmployeeDaoImpl.class.getClassLoader().getResourceAsStream( "/resources/Employees.txt" ) );
				//Scanner lineScanner = getScanner( EmployeeDaoImpl.class.getResourceAsStream("/data/Employees.txt") );

				while ( lineScanner.hasNext() )
				{
					String line = lineScanner.next();
					
					Scanner employeeScanner = new Scanner( line );
					employeeScanner.useDelimiter( "\t"  );
					Employee emp = new Employee();
					String id = employeeScanner.next(); 
					logger.info(id);
					emp.setId( id);
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
			catch ( DatatypeConfigurationException e )
			{
				logger.error( e );
				throw new DatatypeConfigurationException( "date.notconfigurable" );
			}
			return employees;
		}
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
			logger.error( e );
			throw new ParseException( "date.notparsable" , e.getErrorOffset() );
		}
		catch ( DatatypeConfigurationException e )
		{
			logger.error( e );
			throw new DatatypeConfigurationException( "date.notconfigurable" );
		}
		
        return result;
		
	}

	private static Scanner getScanner( InputStream file )
			throws FileNotFoundException
	{
		return new Scanner( file ).useDelimiter( "\\n" );
	}

//	@Override
//	public boolean addEmployee( Employee employee ) throws FileNotFoundException, ParseException
//	{
//		return getEmployeeList().add( employee );
//	}
//
//	@Override
//	public ResponseCode removeEmployee(Employee employee)
//			throws FileNotFoundException, ParseException {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public ResponseCode removeEmployee( Employee employee )throws FileNotFoundException, ParseException
//	{
//		ResponseCode responseCode = new ResponseCode();
//		
//		if ( getEmployeeList().remove( employee ) )
//		{
//			responseCode.setCode( PresentationUtil
//					.getString( "remove.employee.success.code" ) );
//			
//			String[] args ={ employee.getId() };
//			responseCode.setDesc( PresentationUtil.getString( "remove.employee.success.desc", args ) );
//			return responseCode;
//		}
//		responseCode.setCode( PresentationUtil.getString( "error.remove.employee.duplicate.code" ) );
//		String[] args = { employee.getId() };
//		responseCode.setDesc( PresentationUtil.getString( "error.remove.employee.duplicate.desc", args ) );
//		return responseCode;
//	}

}
