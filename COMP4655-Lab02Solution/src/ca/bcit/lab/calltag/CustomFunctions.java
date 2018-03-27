package ca.bcit.lab.calltag;

import java.util.Calendar;

import ca.bcit.lab.beans.User;

public class CustomFunctions {
	
	private static String regex = "[0-9]+";
	
	public static String getAge(User user) {
		
		if(user.getDob()==null || user.getDob().trim().equals("") ) {
			return "Enter you name and Birth year above";
		}
		
		if(user.getDob()!=null && ! user.getDob().trim().equals("") && user.getDob().matches(regex)) {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int byear = Integer.parseInt(user.getDob());
			if(year < byear) {
				return "You are yet to born";
			}
			int diff = year - byear;
			return user.getName()+", You are "+diff+" years old.";
		}
		return "Invalid inputs !!!";
		
	}

}
