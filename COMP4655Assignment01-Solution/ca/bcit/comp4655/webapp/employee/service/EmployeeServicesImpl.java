package ca.bcit.comp4655.webapp.employee.service;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

import ca.bcit.comp4655.webapp.employee.data.EmployeeDao;
import ca.bcit.comp4655.webapp.employee.data.EmployeeDaoImpl;
import ca.bcit.comp4655.webapp.employee.domain.Employee;
import ca.bcit.comp4655.webapp.employee.domain.ResponseCode;
import ca.bcit.comp4655.webapp.employee.presentation.util.PresentationUtil;

public class EmployeeServicesImpl implements EmployeeServices
{
	private EmployeeDao empDao;
	
	public  EmployeeServicesImpl()
	{
		empDao = new EmployeeDaoImpl();
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}

	
	@Override
	public List<Employee> getEmployeeList() throws FileNotFoundException, ParseException
	{
		return empDao.getEmployeeList();
	}

	@Override
	public Employee findEmployeeById( String id ) throws FileNotFoundException, ParseException
	{
		Employee employee = new Employee();
		employee.setId( id );
		if ( getEmployeeList().contains( employee ) )
		{
			employee = getEmployeeList().get( getEmployeeList().indexOf( employee ) );
			return employee;
		}
			
		return null;
	}

	@Override
	public ResponseCode addEmployee( Employee employee ) throws FileNotFoundException, ParseException
	{
		
		ResponseCode responseCode = validateEmployeeID( employee );
		if ( PresentationUtil.getString( "add.employee.success.code" ).equals( responseCode.getCode() ) )
		{
			empDao.addEmployee ( employee );
		}
		
		return responseCode;
	}

	@Override
	public ResponseCode removeEmployee( Employee employee ) throws FileNotFoundException, ParseException
	{
		return empDao.removeEmployee( employee );
	}

	private ResponseCode validateEmployeeID( Employee employee )
	{
		ResponseCode responseCode = new ResponseCode();
		
		//Check to avoid duplicate entries.
		try
		{
			if ( getEmployeeList().contains( employee ) )
			{
				responseCode.setCode( PresentationUtil.getString( "error.add.employee.duplicate.code" ) );
				responseCode.setDesc( PresentationUtil.getString( "error.add.employee.duplicate.desc" ) );
				return responseCode;
			}
		} catch ( FileNotFoundException e )
		{
			responseCode.setCode( "error." + e.getMessage() + ".code" );
			responseCode.setDesc( "error." + e.getMessage() + ".desc" );
		} catch ( ParseException e )
		{
			responseCode.setCode( "error." + e.getMessage() + ".code" );
			responseCode.setDesc( "error." + e.getMessage() + ".desc" );
		}

		if ( !Pattern.matches( EMP_ID_PATTERN, employee.getId() ) )
		{
			responseCode.setCode( PresentationUtil.getString( "error.add.employee.invalid.code" ) );
			responseCode.setDesc( PresentationUtil.getString( "error.add.employee.invalid.desc" ) );
			return responseCode;
		}				
		
		responseCode.setCode( PresentationUtil.getString( "add.employee.success.code" ) );
		String[] args = { employee.getFirstName(), employee.getLastName() };
		responseCode.setDesc( PresentationUtil.getString( "add.employee.success.desc", args ) );
		return responseCode;
	}
	
	

}
