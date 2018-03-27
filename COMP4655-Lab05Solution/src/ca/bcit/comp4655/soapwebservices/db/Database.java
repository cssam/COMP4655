package ca.bcit.comp4655.soapwebservices.db;

import java.util.ArrayList;
import java.util.List;

import ca.bcit.comp4655.soapwebservices.domain.Employee;


public class Database
{
	
	public static List<Employee> employees = new ArrayList<Employee>();
	static
	{
		Employee emp1 = new Employee();
		emp1.setFirstName( "John" );
		emp1.setLastName( "Doe" );
		emp1.setId( 1 );
		
		Employee emp2 = new Employee();
		emp2.setFirstName("John" );
		emp2.setLastName("Doe");
		emp2.setId( 2 );
		
	}
	
	
	/* TODO Modify this method so that it searches 
	 * the collection for employee by the given id or return null if no match found.
	 */
	public static Employee getEmployeeById ( int id ) 
	{
		return Database.employees.get(id);
	}
}