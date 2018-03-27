package ca.bcit.comp4655.webapp.employee.presentation.util;

import java.util.Calendar;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import ca.bcit.comp4655.webapp.employee.domain.Employee;

public class TimeDecorator extends TableDecorator 
{
	public String getDob()
	{
		Date dob = ( (Employee ) ( this.getCurrentRowObject()) ).getDob();
		if ( dob==null || "".equals( dob ) )
		{
			return "";
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime( dob );
		StringBuilder sb = new StringBuilder();
		sb.append( cal.get( Calendar.YEAR ) + " / " );
		sb.append( cal.get ( Calendar.MONTH) + " / ");
		sb.append( cal.get ( Calendar.DAY_OF_MONTH)  );
		return sb.toString() ;
	}
}
