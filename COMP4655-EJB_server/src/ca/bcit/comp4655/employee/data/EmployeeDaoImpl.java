package ca.bcit.comp4655.employee.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;


import org.apache.log4j.Logger;

import ca.bcit.comp4655.employee.exception.EmployeeServerException;
import ca.bcit.comp4655.employee.ejb.Employee;
import ca.bcit.comp4655.employee.ejb.ResponseCode;
import ca.bcit.comp4655.employee.util.ServerUtil;


public class EmployeeDaoImpl implements EmployeeDao
{
	private static List<Employee> employees;
	
	private static Logger logger = Logger.getLogger( EmployeeDaoImpl.class );

	private static Scanner employeeScanner;

	public EmployeeDaoImpl() 
	{
		if(employees==null) {
			try {
				getSingltonEmployeeList();
			} catch (EmployeeServerException e) {
				logger.error( e );
			}
		}
	}

	@Override
	public List<Employee> getEmployeeList() throws EmployeeServerException
	{
		return getSingltonEmployeeList();
	}
	
	@Override
	public Employee getEmployeeById(String employeeId) throws EmployeeServerException {

		if (employeeId!=null) {
			getSingltonEmployeeList();
			for (Employee employee : employees) {
				logger.info(employee.getId());
				if(employee.getId().trim().equals(employeeId.trim())) {
					return employee;
				}
			}
			//throw new EmployeeServerException("No maching employeeId ID in the server.");
		} else {
			// throw new EmployeeServerException("employeeId ID is Null");
		}
		return null;

	}
	
	@Override
	public ResponseCode addEmployee( Employee employee ) throws EmployeeServerException {
		
		ResponseCode responseCode = new ResponseCode();
		if(employee.getId() != null) {
			String newEmployeeId = employee.getId();
			for (Employee anyEmployee : employees) {
				logger.info(anyEmployee.getId());
				if(anyEmployee.getId().equals(newEmployeeId)) {
					responseCode.setCode( ServerUtil.getString( "error.add.employee.duplicate.code" ) );
					String[] args = { employee.getId() };
					responseCode.setDesc( ServerUtil.getString( "error.add.employee.duplicate.desc", args ) );
					return  responseCode;
				}
			}
		 if (getEmployeeList().add( employee ))
			{
				responseCode.setCode( ServerUtil.getString( "add.employee.success.code" ) );
				String[] args ={ employee.getId() };
				responseCode.setDesc( ServerUtil.getString( "add.employee.success.desc", args ) );
				return  responseCode;
			}
		} else {
			responseCode.setCode( ServerUtil.getString( "error.add.employee.invalid.code" ) );
			String[] args = { employee.getId() };
			responseCode.setDesc( ServerUtil.getString( "error.add.employee.invalid.desc", args ) );
		}
		
		return  responseCode;
	}



	@Override
	public ResponseCode removeEmployee(String employeeId) throws EmployeeServerException
	{
		ResponseCode responseCode = new ResponseCode();
		
		try {
			
			if(employeeId != null) {
				for (Employee anyEmployee : employees) {
					logger.info(anyEmployee.getId());
					if(anyEmployee.getId().equals(employeeId)) {
						if ( getEmployeeList().remove(anyEmployee) ) {
							responseCode.setCode( ServerUtil.getString( "remove.employee.success.code" ) );
							String[] args ={employeeId};
							responseCode.setDesc( ServerUtil.getString( "remove.employee.success.desc", args ) );
							return  responseCode;
						}
					}
				}
				responseCode.setCode( ServerUtil.getString( "error.remove.employee.noentry" ) );
				String[] args ={employeeId};
				responseCode.setDesc( ServerUtil.getString( "error.remove.employee.noentry", args ) );
				return  responseCode;

			}
			
			
		} catch (EmployeeServerException e) {
			logger.error("Counld not remove record from the list. ", e );
			responseCode.setCode( ServerUtil.getString( "error.remove.employee.failed.code" ) );
			String[] args = {employeeId};
			responseCode.setDesc( ServerUtil.getString( "error.remove.employee.failed.desc", args ) );
		}
		return responseCode;
		
	}


	private synchronized static List<Employee> getSingltonEmployeeList() throws EmployeeServerException
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
//				Scanner lineScanner = getScanner( EmployeeDaoImpl.class.getClassLoader().getResourceAsStream( "/resources/Employees.txt" ) );
//				Scanner lineScanner = getScanner( EmployeeDaoImpl.class.getResourceAsStream("/data/Employees.txt") );

				while ( lineScanner.hasNext() )
				{
					String line = lineScanner.next();
					
					employeeScanner = new Scanner( line );
					employeeScanner.useDelimiter( "\t"  );
					Employee emp = new Employee();
					String id = employeeScanner.next(); 
					logger.info(id);
					emp.setId( id);
					emp.setFirstName( employeeScanner.next() );
					emp.setLastName( employeeScanner.next() );
					emp.setDob(  new SimpleDateFormat("yyyy-MM-dd").parse(employeeScanner.next() ) );
					employees.add( emp );
				}
			}
			
			catch (FileNotFoundException e) {
				logger.error( "Could not locate employees.txt file.", e );
				throw new EmployeeServerException();
			} catch (ParseException e) {
				logger.error("Date not parsable. ", e );
				throw new EmployeeServerException();
			} 

			return employees;
		}
	}


	private static Scanner getScanner( InputStream file )
			throws FileNotFoundException
	{
		return new Scanner( file ).useDelimiter( "\\n" );
	}


}
