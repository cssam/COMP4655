package ca.bcit.comp4655.webapp.employee.presentation.util;

import javax.xml.datatype.XMLGregorianCalendar;

import org.displaytag.decorator.TableDecorator;
import ca.bcit.comp4655.employee.types.Employee;;

public class TimeDecorator extends TableDecorator 
{
	public String getDob()
	{
		XMLGregorianCalendar dob = ( (Employee ) ( this.getCurrentRowObject()) ).getDob();
		if ( dob==null || "".equals( dob ) )
		{
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dob.getYear()+ " / " );
		sb.append(dob.getMonth() + " / ");
		sb.append(dob.getDay());
		return sb.toString() ;
	}
}
