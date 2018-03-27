package ca.bcit.as.decorator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;
import ca.bcit.as.beans.Employee;


public class DateDecorator extends TableDecorator {

DateFormat saveformater = new SimpleDateFormat("yyyy-MM-dd");
DateFormat displayformater = new SimpleDateFormat("MM/dd/yyyy");
	
	public String getDob() {
		String dateString ="";
		try {
			Employee employee = (Employee) getCurrentRowObject();
			String dob = employee.getDob();
			Date savedDate;
			savedDate = saveformater.parse(dob);
			dateString = displayformater.format(savedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateString;
	}

}
